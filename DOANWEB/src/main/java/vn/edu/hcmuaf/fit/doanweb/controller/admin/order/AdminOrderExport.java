package vn.edu.hcmuaf.fit.doanweb.controller.admin.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;
import vn.edu.hcmuaf.fit.doanweb.service.ImportService;
import vn.edu.hcmuaf.fit.doanweb.service.StockService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminOrderExport", value = "/admin/order/export")
public class AdminOrderExport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StockService stockService = new StockService(); //
        int id= Integer.parseInt(request.getParameter("id"));

        try {
            if(id>0) {
                stockService.exportStockByOrder(id);
                request.setAttribute("message", "Xóa thành công!");
            }else{
                request.setAttribute("message", "Xóa không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/import");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }



    }


}

