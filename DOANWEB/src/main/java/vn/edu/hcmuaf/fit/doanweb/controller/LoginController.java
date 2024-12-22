package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginController" ,value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        AuthService authService = new AuthService();

        if(authService.login(uname,pass)){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            request.setAttribute("error", "Dang nhap khong thanh cong");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
