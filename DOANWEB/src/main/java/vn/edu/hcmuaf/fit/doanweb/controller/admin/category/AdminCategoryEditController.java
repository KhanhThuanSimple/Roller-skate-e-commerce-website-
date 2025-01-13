package vn.edu.hcmuaf.fit.doanweb.controller.admin.category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminCategoryEditController", value = "/admin/category/update")
public class AdminCategoryEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();

        try{
            String name=request.getParameter("name");
            int id=Integer.parseInt(request.getParameter("id"));

            boolean rs =authService.updateCategory(name,id);
            System.out.println("KQs");

            System.out.println(rs);
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/category");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
    }
    }


