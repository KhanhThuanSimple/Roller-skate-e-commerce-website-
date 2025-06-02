package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleAccount;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleLogin;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.log.Log;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String clientIP = request.getRemoteAddr();

        try {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            GoogleAccount acc = gg.getUserInfo(accessToken);

            if (acc != null) {
                Log.info(acc.getEmail(), "LOGIN_GOOGLE", "Đăng nhập Google thành công", clientIP);

                // Bước 1: Tìm user trong DB dựa theo email của GoogleAccount
                AuthService authService = new AuthService();
                User user = authService.findByEmail(acc.getEmail());

                if (user == null) {
                    // Nếu chưa có user trong DB, có thể tạo mới (hoặc redirect đăng ký)
                    user = new User();
                    user.setEmail(acc.getEmail());
                    user.setName(acc.getName());
                    user.setUsername(acc.getEmail()); // hoặc tùy theo cách bạn lưu username
                    user.setType(Integer.parseInt("google")); // kiểu user đăng nhập google
                    // Có thể set password null hoặc mặc định

                    authService.createUser(user); // lưu user mới vào DB
                }

                // Bước 2: Lưu user vào session để duy trì đăng nhập
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

                // Bước 3: Redirect đến trang chính hoặc trang dashboard
                response.sendRedirect(request.getContextPath() + "/home");

            } else {
                Log.warn("Guest", "LOGIN_GOOGLE", "Đăng nhập Google thất bại: tài khoản null", clientIP);
                request.setAttribute("error", "Đăng nhập Google thất bại, vui lòng thử lại.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            Log.error("Guest", "LOGIN_GOOGLE", "Lỗi khi đăng nhập Google", clientIP, e);
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}