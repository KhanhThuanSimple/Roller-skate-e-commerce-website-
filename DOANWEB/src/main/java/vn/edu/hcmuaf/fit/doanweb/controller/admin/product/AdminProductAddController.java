package vn.edu.hcmuaf.fit.doanweb.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminProductAddController" ,value = "/admin/product/insert")
public class AdminProductAddController extends  HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();

        try{
            String name=request.getParameter("name");
            String img=request.getParameter("img");
            double price=Double.parseDouble(request.getParameter("price")) ;
            String title=request.getParameter("title");
            String description=request.getParameter("description");
            int cateID = Integer.parseInt(request.getParameter("cateID"));
            String offer=request.getParameter("offer");

            boolean rs =authService.insertProduct(name,img,price,title,description,cateID,offer);
            if(rs) {
                request.setAttribute("message", "Thêm thành công!");
            }else{
                request.setAttribute("message", "Thêm không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/user");
        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/product");

        }

    }
}