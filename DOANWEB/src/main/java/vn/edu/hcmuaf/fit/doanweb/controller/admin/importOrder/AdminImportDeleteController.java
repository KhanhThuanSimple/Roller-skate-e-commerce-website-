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

@WebServlet(name = "AdminImportDeleteController", value = "/admin/import/delete")
public class AdminImportDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImportService importService = new ImportService();
        int uid= Integer.parseInt(request.getParameter("uid"));

        try {
            ImportOrders importOrders = importService.findById(uid);

            boolean rs = importService.deleteImport(uid);
            System.out.println("'rssss'" + rs);
            if(rs) {
                System.out.println("'importOrders'" + importOrders);
                if(importOrders!=null){
                    StockService stockService = new StockService();
                    Stock stock = stockService.findProduct(importOrders.getProduct_id());

                    if(stock!=null){
                        rs =stockService.updateStock(stock.getId(),-1*importOrders.getQuantity());
                    }
                }

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


