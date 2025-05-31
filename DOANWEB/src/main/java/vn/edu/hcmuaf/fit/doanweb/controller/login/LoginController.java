package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            Log.warn(username, "LOGIN_ATTEMPT", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", clientIP);
            request.setAttribute("error", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse, clientIP);
        if (!verify) {
            Log.warn(username, "LOGIN_ATTEMPT", "Xác thực reCAPTCHA không thành công.", clientIP);
            request.setAttribute("error", "Xác thực reCAPTCHA không thành công.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService authService = new AuthService();
        try {
            User user = authService.findByUsername(username);
            if (user == null || !authService.checkPassword(password, user.getPassword())) {
                Log.warn(username, "LOGIN_ATTEMPT", "Tên đăng nhập hoặc mật khẩu không đúng.", clientIP);
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            String otp = generateOtp();

            HttpSession session = request.getSession(true);
            session.setAttribute("otp", otp);
            session.setAttribute("otpCreationTime", System.currentTimeMillis());
            session.setAttribute("pendingUser", user);

            CompletableFuture.runAsync(() -> {
                try {
                    boolean success = EmailUtil.sendEmail(user.getEmail(),
                            "Mã OTP đăng nhập của bạn",
                            "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với ai.");
                    if (!success) {
                        Log.warn(username, "SEND_OTP_EMAIL", "Gửi email OTP thất bại tới " + user.getEmail(), clientIP);
                    } else {
                        Log.info(username, "SEND_OTP_EMAIL", "Gửi email OTP thành công tới " + user.getEmail(), clientIP);
                    }
                } catch (Exception e) {
                    Log.error(username, "SEND_OTP_EMAIL", "Lỗi gửi email OTP: " + e.getMessage(), clientIP, e);
                }
            });

            Log.info(username, "LOGIN_SUCCESS", "Đăng nhập thành công, chờ xác thực OTP", clientIP);

            // Gửi email thành công → quay lại login.jsp và bật modal OTP
            request.setAttribute("otpModal", true);
            request.setAttribute("emailForOtp", user.getEmail());
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (SQLException e) {
            Log.error(username, "LOGIN_ATTEMPT", "Lỗi khi truy vấn user: " + e.getMessage(), clientIP, e);
            throw new ServletException("Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }
    private String generateOtp() {
        int otp = (int)(Math.random() * 900000) + 100000; // 6 chữ số
        return String.valueOf(otp);
    }

}