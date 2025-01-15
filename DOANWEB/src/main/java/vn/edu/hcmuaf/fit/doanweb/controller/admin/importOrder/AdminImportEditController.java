package vn.edu.hcmuaf.fit.doanweb.controller.admin.importOrder;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminImportEditController", value = "/admin/import/update")
public class AdminImportEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();

        try{
            int product_id=Integer.parseInt(request.getParameter("product_id"));

            double purchase_price= Double.parseDouble(request.getParameter("purchase_price"));
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            int id=Integer.parseInt(request.getParameter("id"));

            boolean rs =authService.updateImport(product_id,purchase_price,quantity,id);
            System.out.println("KQs");

            System.out.println(rs);
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/import");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/import");
        }
    }

    }



