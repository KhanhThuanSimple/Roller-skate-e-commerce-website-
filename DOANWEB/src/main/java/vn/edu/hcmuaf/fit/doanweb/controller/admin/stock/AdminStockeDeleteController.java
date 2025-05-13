package vn.edu.hcmuaf.fit.doanweb.controller.admin.stock;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.StockService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AinStockeDeleteController", value = "/admin/stock/delete")
public class AdminStockeDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StockService stockService = new StockService();
        int uid= Integer.parseInt(request.getParameter("uid"));

        try {
            boolean rs = stockService.deleteProduct(uid);
            if(rs) {
                request.setAttribute("message", "Xóa thành công!");
            }else{
                request.setAttribute("message", "Xóa không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/stock");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }



    }
    }


