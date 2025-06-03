package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.controller.cart.Add;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.log.Log;
import vn.edu.hcmuaf.fit.doanweb.utils.PasswordUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true); // không tạo mới

        User user = (session != null) ? (User) session.getAttribute("auth") : null;

        if (user != null) {
            // Người dùng đã đăng nhập, chuyển đến trang chính
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        // Chưa đăng nhập, hiển thị trang login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    //
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String clientIP = request.getRemoteAddr();
//        String username = request.getParameter("uname");
//        String password = request.getParameter("pass");
//        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
//
//        if (username == null || username.isBlank() || password == null || password.isBlank()) {
//            Log.warn(username, "LOGIN_ATTEMPT", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.", clientIP);
//            request.setAttribute("error", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
//
//        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse, clientIP);
//        if (!verify) {
//            Log.warn(username, "LOGIN_ATTEMPT", "Xác thực reCAPTCHA không thành công.", clientIP);
//            request.setAttribute("error", "Xác thực reCAPTCHA không thành công.");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
//
//        try {
//            User user = authService.findByUsername(username);
//            if (user == null || !PasswordUtil.checkPassword(password, user.getPassword())) {
//                Log.warn(username, "LOGIN_ATTEMPT", "Tên đăng nhập hoặc mật khẩu không đúng.", clientIP);
//                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//                return;
//            }
//
//            // Nếu mật khẩu chưa băm, thì băm và cập nhật lại DB
//            if (user.getPassword() != null &&
//                    !(user.getPassword().startsWith("$2a$") ||
//                            user.getPassword().startsWith("$2b$") ||
//                            user.getPassword().startsWith("$2y$"))) {
//                String hashed = PasswordUtil.hashPassword(password);
//                authService.updatePassword(user.getId(), hashed);
//                user.setPassword(hashed);
//            }

    //            // Tạo OTP và gửi email
//            String otp = generateOtp();
//
//            HttpSession session = request.getSession(true);
//            session.setAttribute("otp", otp);
//            session.setAttribute("otpCreationTime", System.currentTimeMillis());
//            session.setAttribute("pendingUser", user);
//
//            CompletableFuture.runAsync(() -> {
//                try {
//                    boolean success = EmailUtil.sendEmail(user.getEmail(),
//                            "Mã OTP đăng nhập của bạn",
//                            "Mã OTP của bạn là: " + otp + ". Vui lòng không chia sẻ mã này với ai.");
//                    if (!success) {
//                        Log.warn(username, "SEND_OTP_EMAIL", "Gửi email OTP thất bại tới " + user.getEmail(), clientIP);
//                    } else {
//                        Log.info(username, "SEND_OTP_EMAIL", "Gửi email OTP thành công tới " + user.getEmail(), clientIP);
//                    }
//                } catch (Exception e) {
//                    Log.error(username, "SEND_OTP_EMAIL", "Lỗi gửi email OTP: " + e.getMessage(), clientIP, e);
//                }
//            });
//
//            Log.info(username, "LOGIN_SUCCESS", "Đăng nhập thành công, chờ xác thực OTP", clientIP);
//
//            request.setAttribute("otpModal", true);
//            request.setAttribute("emailForOtp", user.getEmail());
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//
//        } catch (SQLException e) {
//            Log.error(username, "LOGIN_ATTEMPT", "Lỗi khi truy vấn user: " + e.getMessage(), clientIP, e);
//            throw new ServletException("Lỗi hệ thống, vui lòng thử lại sau.");
//        }
//    }
//
//    private String generateOtp() {
//        int otp = (int)(Math.random() * 900000) + 100000; // 6 chữ số
//        return String.valueOf(otp);
//    }
//
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        if (uname == null || uname.isBlank() || pass == null || pass.isBlank()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse, clientIP);
        if (!verify) {
            request.setAttribute("error", "Xác thực reCAPTCHA không thành công.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService authService = new AuthService();
        UserDao userDao = new UserDao();

        try {
            User user = authService.findByUsername(uname);

            if (user != null) {
                // Nếu mật khẩu chưa được hash theo BCrypt, thì hash và cập nhật lại DB
                if (user.getPassword() != null &&
                        !(user.getPassword().startsWith("$2a$") ||
                                user.getPassword().startsWith("$2b$") ||
                                user.getPassword().startsWith("$2y$"))) {
                    String hashed = PasswordUtil.hashPassword(pass);
                    authService.updatePassword(user.getId(), hashed);
                    user.setPassword(hashed);
                }

                // Kiểm tra mật khẩu sau khi hash
                if (PasswordUtil.checkPassword(pass, user.getPassword())) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("auth", user);
                    session.setAttribute("user", user.getId());

                    if (user.getType() == 1) {
                        List<String> allowedScreens = userDao.getListPerUser(user.getId());
                        session.setAttribute("allowedScreens", allowedScreens);
                        response.sendRedirect(request.getContextPath() + "/admin/user");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                    return;
                }
            }

            // Sai username hoặc password
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Lỗi hệ thống, vui lòng thử lại sau.", e);
        }
    }
}
