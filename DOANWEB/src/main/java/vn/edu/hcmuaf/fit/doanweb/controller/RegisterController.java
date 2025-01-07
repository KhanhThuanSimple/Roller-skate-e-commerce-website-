package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Có thể trả về trang đăng ký
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            // Kiểm tra mật khẩu có khớp không
            if (!pass.equals(repass)) {
                request.setAttribute("errorMessage", "Mật khẩu không khớp!");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            User user = new User(name, uname, pass, phone, address);
            UserDao userDao = new UserDao();

            // Gọi phương thức đăng ký
            User registeredUser = userDao.register(user, repass);

            // Nếu đăng ký thành công, chuyển hướng đến trang khác (ví dụ: trang đăng nhập)
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng ký!"); // Thông báo lỗi
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi!"); // Thông báo lỗi chung
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}