package vn.edu.hcmuaf.fit.doanweb.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminUserDeleteController" ,value = "/admin/user/delete")
public class AdminUserDeleteController  extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            int uid= Integer.parseInt(request.getParameter("uid"));

        UserDao userDao=new UserDao();
        try {
            userDao.deleteUser(uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin/user");





    }
}
