package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.controller.login.EmailUtil;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xóa session cũ nếu có
        request.getSession().invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        // Validate input
        if (uname == null || uname.isEmpty() || pass == null || pass.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService authService = new AuthService();
        try {
            User user = authService.findByUsername(uname);
            if (user != null) {
                // Kiểm tra mật khẩu
                if (!user.getPassword().equals(pass)) {
                    request.setAttribute("error", "Sai mật khẩu!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                // Kiểm tra email
                if (user.getEmail() == null || user.getEmail().isEmpty()) {
                    request.setAttribute("error", "Tài khoản chưa đăng ký email");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

                // Tạo mã OTP (6 chữ số)
                String otp = String.format("%06d", (int)(Math.random() * 1000000));

                try {
                    // Gửi email OTP
                    EmailUtil.sendEmail(user.getEmail(), "Mã OTP xác thực đăng nhập",
                            "Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 2 phút");

                    // Lưu thông tin vào session
                    HttpSession session = request.getSession();
                    session.setAttribute("otp", otp);
                    session.setAttribute("pendingUser", user);
                    session.setAttribute("otpCreationTime", System.currentTimeMillis()); // ✅ Dòng quan trọng

                    // Chuyển hướng sang trang nhập OTP
                    response.sendRedirect("verify-otp.jsp");

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Lỗi khi gửi mã OTP. Vui lòng thử lại sau.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("error", "Tài khoản không tồn tại");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống. Vui lòng thử lại sau.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
