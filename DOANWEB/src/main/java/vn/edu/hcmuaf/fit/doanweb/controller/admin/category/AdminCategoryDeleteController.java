package vn.edu.hcmuaf.fit.doanweb.controller.admin.category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminCategoryDeleteController", value = "/admin/category/delete")
public class AdminCategoryDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        int uid= Integer.parseInt(request.getParameter("uid"));

        try {
            boolean rs = authService.delete(uid);
            if(rs) {
                request.setAttribute("message", "Xóa thành công!");
            }else{
                request.setAttribute("message", "Xóa không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/category");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }




    }
}

