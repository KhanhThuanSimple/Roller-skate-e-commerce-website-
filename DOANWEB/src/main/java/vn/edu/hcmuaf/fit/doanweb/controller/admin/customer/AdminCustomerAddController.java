package vn.edu.hcmuaf.fit.doanweb.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminUserAddController" ,value = "/admin/customer/add")
public class AdminCustomerAddController extends  HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService();

        try{
            String name=request.getParameter("name");
            String username=request.getParameter("username");
            String pass= request.getParameter("pass");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");

            boolean rs =authService.insert(name,username,pass,address,phone,0,0);
            if(rs) {
                request.setAttribute("message", "Thêm thành công!");
            }else{
                request.setAttribute("message", "Thêm không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/customer");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/customer");

        }



    }

}