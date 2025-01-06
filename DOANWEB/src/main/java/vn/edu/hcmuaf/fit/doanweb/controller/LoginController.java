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

@WebServlet(name = "LoginController" ,value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        AuthService authService = new AuthService();

        try {
            if (authService.login(uname, pass)) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("error", "Dang nhap khong thanh cong");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                try {
                    if (authService.login(uname, pass)) {
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        request.setAttribute("error", "Dang nhap khong thanh cong");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}