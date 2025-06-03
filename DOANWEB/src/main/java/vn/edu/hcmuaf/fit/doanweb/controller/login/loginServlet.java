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
        HttpSession session = request.getSession(false); // Không tạo mới

        // Nếu user đã đăng nhập -> redirect luôn
        if (session != null && session.getAttribute("user") != null) {
            User loggedUser = (User) session.getAttribute("user");
            Log.info(loggedUser.getEmail(), "LOGIN_GOOGLE", "Đã có trong session, bỏ qua xác thực lại", request.getRemoteAddr());
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String code = request.getParameter("code");
        String clientIP = request.getRemoteAddr();

        try {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            GoogleAccount acc = gg.getUserInfo(accessToken);
            if (acc != null) {
                Log.info(acc.getEmail(), "LOGIN_GOOGLE", "Đăng nhập Google thành công", clientIP);

                // Tìm user trong DB
                AuthService authService = new AuthService();
                User user = authService.findByEmail(acc.getEmail());

                if (user == null) {
                    // Chưa có user, tạo mới
                    user = new User();
                    user.setEmail(acc.getEmail());
                    user.setName(acc.getName());
                    user.setGoogleId(acc.getId());
                    user.setUsername(acc.getEmail());
                    user.setPassword("");
                    user.setAddress("");
                    user.setPhone("");
                    user.setType(0);
                    authService.createUser(user);
                }

                // Lưu user vào session mới
                session = request.getSession(true);
                session.setAttribute("auth", user);
                Log.info(user.getEmail(), "LOGIN_GOOGLE", "User được lưu vào session, sessionID=" + session.getId(), clientIP);

                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                Log.warn("Guest", "LOGIN_GOOGLE", "Đăng nhập Google thất bại: tài khoản null", clientIP);
                request.setAttribute("error", "Đăng nhập Google thất bại, vui lòng thử lại.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Log.error("Guest", "LOGIN_GOOGLE", "Lỗi khi đăng nhập Google", clientIP, e);
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}