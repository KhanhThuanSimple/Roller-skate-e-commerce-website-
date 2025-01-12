package vn.edu.hcmuaf.fit.doanweb.controller.admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "AdminUserController" ,value = "/admin/dashbroad")

public class DashboardController  extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        User user= (User) session.getAttribute("auth");
                if(user==null || user.getType()< 1){
                    response.sendRedirect("../login.jsp");
                }
    PrintWriter OUT= response.getWriter();
    OUT.println("<h1>XIN CHAO ADMIN</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}