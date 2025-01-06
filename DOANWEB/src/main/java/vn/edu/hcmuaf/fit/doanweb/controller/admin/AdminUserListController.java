package vn.edu.hcmuaf.fit.doanweb.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminUserListController" ,value = "/admin/user/list")
public class AdminUserListController extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet13");
        AuthService authService = new AuthService();
        try {
            List<User>  users = authService.getList(0, 1);
            System.out.println(users.size());
            request.setAttribute("users", users);
            request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
