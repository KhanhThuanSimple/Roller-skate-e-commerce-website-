package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (uname == null || uname.isBlank() || pass == null || pass.isBlank()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Xác thực reCAPTCHA
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse, request.getRemoteAddr());
        if (!verify) {
            request.setAttribute("error", "Xác thực reCAPTCHA không thành công. Vui lòng thử lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService authService = new AuthService();
        try {
            User user = authService.findByUsername(uname);
            if (user == null || !authService.checkPassword(pass, user.getPassword())) {
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            String otp = generateOtp();

            HttpSession session = request.getSession(true);
            session.setAttribute("otp", otp);
            session.setAttribute("otpCreationTime", System.currentTimeMillis());
            session.setAttribute("pendingUser", user);

            // Gửi mail bất đồng bộ để không block luồng chính
            CompletableFuture.runAsync(() -> {
                try {
                    boolean success = EmailUtil.sendEmail(user.getEmail(),
                            "Mã OTP đăng nhập của bạn",
                            "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với ai.");
                    if (!success) {
                        logger.warning("Gửi email OTP thất bại tới " + user.getEmail());
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Lỗi gửi email OTP: ", e);
                }
            });

            // Redirect sang trang nhập OTP (không forward để tránh reload gửi lại form)
            response.sendRedirect(request.getContextPath() + "/verify-otp");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi truy vấn user: ", e);
            throw new ServletException("Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }

    private String generateOtp() {
        int otpInt = (int) (Math.random() * 900000) + 100000; // 100000 - 999999
        return String.valueOf(otpInt);
    }
}
