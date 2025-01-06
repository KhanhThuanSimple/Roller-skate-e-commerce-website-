package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;

@WebServlet(name = "DetailServlet", value = "/detail")
public class DetailServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCommonData(request); // Gọi phương thức chung

        String id = request.getParameter("pid");
        ProductDao dao = new ProductDao();
        Product product= dao.getAllProductId(id);
        request.setAttribute("detail", product);
        request.getRequestDispatcher("product_detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


