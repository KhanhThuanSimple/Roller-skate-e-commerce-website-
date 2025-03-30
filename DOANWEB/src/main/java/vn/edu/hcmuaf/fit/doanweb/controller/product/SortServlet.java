package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "SortServlet", value = "/sort")
public class SortServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("order"); // order có thể là "asc" hoặc "desc"
        loadCommonData(request); // Gọi phương thức chung

        ProductDao productDao = new ProductDao();
        List<Product> all = productDao.getProductByOrder(sort);
        request.setAttribute("products", all);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


