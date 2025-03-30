package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet(name = "UserServerlet", value = "/canhan")
public class UserServerlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            // Nếu không có userId trong session, chuyển hướng người dùng đến trang đăng nhập
            response.sendRedirect("login");
            return;
        }

        User userFromDb =dao.getUserById(user.getId());
        if (userFromDb == null) {
            // Xử lý trường hợp không tìm thấy người dùng trong cơ sở dữ liệu
            response.sendRedirect("home.jsp");
            return;
        }
        request.setAttribute("user", userFromDb);

        request.getRequestDispatcher("canhan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


