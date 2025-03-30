package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet(name = "Information", value = "/information")
public class InformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("auth");
        UserDao userDAO = new UserDao();

//        try {
//         //   User user = userDao.findUserByUserName(username);
//
//            request.setAttribute("user", user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        request.getRequestDispatcher("canhan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


