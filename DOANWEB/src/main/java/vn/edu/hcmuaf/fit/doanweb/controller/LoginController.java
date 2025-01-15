package vn.edu.hcmuaf.fit.doanweb.controller;

import com.mysql.cj.xdevapi.JsonString;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginController" ,value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        AuthService authService = new AuthService();
        try {
            User user=authService.login(uname,pass);
            if (user!=null){
                HttpSession session = request.getSession(true);
                session.setAttribute("auth",user);
                session.setAttribute("user",user.getId());
                System.out.println("User details: " + user);
                System.out.println("User ID from session: " + session.getAttribute("user"));


                if(user.getType()==1){
                    response.sendRedirect(request.getContextPath() + "/admin/user");
                }else{
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }else {
                request.setAttribute("error","Dang nhap khong thaanh cong");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}