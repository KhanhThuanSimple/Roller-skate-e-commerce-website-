package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login1Controller", value = "/login1")
public class Login1Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        UserDao userDao = new UserDao();
        User u;
        try {
            u = userDao.login1(uname,pass);
            if(u==null){
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

