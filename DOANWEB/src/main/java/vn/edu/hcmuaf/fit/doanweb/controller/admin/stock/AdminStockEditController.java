package vn.edu.hcmuaf.fit.doanweb.controller.admin.stock;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;
import vn.edu.hcmuaf.fit.doanweb.service.StockService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminStockEditController", value = "/admin/stock/edit")
public class AdminStockEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StockService stockService = new StockService();

        try{

            int product_id= Integer.parseInt(request.getParameter("product_id"));
            String product_name=request.getParameter("product_name");
            int quantity_stock=Integer.parseInt(request.getParameter("quantity_stock"));
            int id=Integer.parseInt(request.getParameter("id"));


            boolean rs =stockService.updateStock(product_id,product_name,quantity_stock,id);

            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/stock");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/stock");
        }
    }
    }


