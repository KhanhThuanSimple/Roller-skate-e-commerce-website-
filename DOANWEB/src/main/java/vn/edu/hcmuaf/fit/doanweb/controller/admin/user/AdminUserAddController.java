package vn.edu.hcmuaf.fit.doanweb.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminUserAddController" ,value = "/admin/user/add")
public class AdminUserAddController extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String uname=request.getParameter("name");
            String uemail=request.getParameter("email");
            String upass= request.getParameter("pass");


            if (uname == null || uemail == null || upass == null) {
                throw new IllegalArgumentException("Các tham số không được để trống!");
            }

            UserDao userDao= new UserDao();
            userDao.insertUser(uname,uemail,upass, 1);
            // Sau khi thêm tài khoản thành công
            request.setAttribute("message", "Tài khoản đã được thêm thành công!");
            request.getRequestDispatcher("/admin/user").forward(request, response);
        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            request.getRequestDispatcher("/admin/user").forward(request, response);
        }



    }

}
