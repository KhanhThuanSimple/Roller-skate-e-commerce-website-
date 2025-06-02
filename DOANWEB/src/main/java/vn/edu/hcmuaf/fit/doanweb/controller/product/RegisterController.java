package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        String clientIP = request.getRemoteAddr();

        try {
            String name = request.getParameter("name");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            if (!pass.equals(repass)) {
                Log.warn(uname, "REGISTER", "Mật khẩu không khớp", clientIP);
                request.setAttribute("errorMessage", "Mật khẩu không khớp!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            User user = authService.findByUsername(uname);
            if (user != null) {
                Log.warn(uname, "REGISTER", "Tài khoản đã tồn tại", clientIP);
                request.setAttribute("errorMessage", "Tài khoản đã tồn tại!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Hash mật khẩu trước khi lưu
            String hashedPass = vn.edu.hcmuaf.fit.doanweb.utils.PasswordUtil.hashPassword(pass);

            boolean rs = authService.insert(name, uname, hashedPass, address, phone, 0);
            if (rs) {
                Log.info(uname, "REGISTER", "Đăng ký thành công", clientIP);
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                Log.warn(uname, "REGISTER", "Lỗi khi thêm người dùng vào database", clientIP);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            Log.error("Guest", "REGISTER", "Lỗi khi đăng ký", clientIP, e);
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống, vui lòng thử lại!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}