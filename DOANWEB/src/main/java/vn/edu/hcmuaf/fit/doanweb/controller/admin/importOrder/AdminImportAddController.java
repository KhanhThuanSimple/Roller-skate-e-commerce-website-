package vn.edu.hcmuaf.fit.doanweb.controller.admin.importOrder;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.StockService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminImportAddController", value = "/admin/import/insert")
public class AdminImportAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();

        try{

            int product_id = Integer.parseInt(request.getParameter("product_id"));
            String product_name = request.getParameter("product_name");
            String image = request.getParameter("image");
            double purchase_price =Double.parseDouble(request.getParameter("purchase_price")) ;
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            boolean rs =authService.insertImport(product_id,purchase_price,quantity);

            if(rs) {
                StockService stockService = new StockService();
                Stock stock = stockService.findProduct(product_id);

                if(stock==null){
                    rs =stockService.insertStock(product_id,quantity);
                }else{
                    rs =stockService.updateStock(stock.getId(),quantity);
                }
                request.setAttribute("message", "Thêm thành công!");
            }else{
                request.setAttribute("message", "Thêm không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/import");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/import");

        }



    }

}

