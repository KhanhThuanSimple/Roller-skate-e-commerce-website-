package vn.edu.hcmuaf.fit.doanweb.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminUserAddController" ,value = "/admin/customer/add")
public class AdminCustomerAddController extends  HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String name=request.getParameter("name");
            String username=request.getParameter("username");
            String pass= request.getParameter("pass");
            String phone=request.getParameter("phone");
            String address=request.getParameter("address");
            String type= request.getParameter("type");




            UserDao userDao= new UserDao();
            userDao.insertUserCustomer(name,username,pass,address,phone,0);

            // Sau khi thêm tài khoản thành công
            request.setAttribute("message", "Tài khoản đã được thêm thành công!");
            request.getRequestDispatcher("/admin/user").forward(request, response);
        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            request.getRequestDispatcher("/admin/customer").forward(request, response);
        }



    }

}