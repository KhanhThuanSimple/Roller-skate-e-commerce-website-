package vn.edu.hcmuaf.fit.doanweb.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.sql.SQLException;

@WebServlet(name = "AdminUserDeleteController" ,value = "loadUser")
public class AdminUserLoadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String a =request.getParameter("uid");
    int id = Integer.parseInt(a);
        UserDao userDao = new UserDao();
        User u= null;
        try {
            u = userDao.getUserByID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("user",u);
        request.getRequestDispatcher("/admin/user.jsp").forward(request,response);

//        try {
//            User u=userDao.getUserByID(Integer.parseInt(id));
//            request.setAttribute("sss",u);
//            request.getRequestDispatcher("user.jsp").forward(request,response);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
