package vn.edu.hcmuaf.fit.doanweb.controller.admin.importOrder;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.ImportService;
import vn.edu.hcmuaf.fit.doanweb.service.StockService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminImportEditController", value = "/admin/import/update")
public class AdminImportEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImportService importService = new ImportService();

        try{
            int product_id=Integer.parseInt(request.getParameter("product_id"));

            double purchase_price= Double.parseDouble(request.getParameter("purchase_price"));
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            int id=Integer.parseInt(request.getParameter("id"));

            ImportOrders importOrders = importService.findById(id);
            boolean rs =importService.updateImport(product_id,purchase_price,quantity,id);

            if(rs) {
                if(importOrders!=null){
                    StockService stockService = new StockService();
                    Stock stock = stockService.findProduct(importOrders.getProduct_id());

                    if(stock!=null){
                        rs =stockService.updateStock(stock.getId(),quantity-importOrders.getQuantity());
                    }
                }

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



