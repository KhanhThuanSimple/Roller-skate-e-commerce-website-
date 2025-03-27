package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String search = request.getParameter("txt");
        loadCommonData(request); // Gọi phương thức chung

        ProductDao productDao = new ProductDao();
        request.setCharacterEncoding("UTF-8");

        List<Product> all = productDao.getProductByTitle(search);
        request.setAttribute("products", all);
        request.setAttribute("txtS", search);
        request.getRequestDispatcher("product.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


