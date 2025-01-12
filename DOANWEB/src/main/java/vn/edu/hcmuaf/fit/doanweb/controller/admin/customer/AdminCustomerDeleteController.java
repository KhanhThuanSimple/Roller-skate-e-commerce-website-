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

@WebServlet(name = "AdminCustomerDeleteController" ,value = "/admin/customer/delete")
public class AdminCustomerDeleteController extends HttpServlet {
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
            request.getRequestDispatcher("/admin/customer").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin/customer");

    }

}
