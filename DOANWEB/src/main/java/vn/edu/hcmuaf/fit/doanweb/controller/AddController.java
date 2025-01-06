package vn.edu.hcmuaf.fit.doanweb.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

    @WebServlet(name = "AddController" ,value = "/users/adds")
    public class AddController  extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            String uname=request.getParameter("name");
//            String uemail=request.getParameter("email");
//            String upass= request.getParameter("pass");
//
//            UserDao userDao= new UserDao();
//            userDao.insertUser(uname,uemail,upass);
//            response.sendRedirect("/admin/user");






        }
}
