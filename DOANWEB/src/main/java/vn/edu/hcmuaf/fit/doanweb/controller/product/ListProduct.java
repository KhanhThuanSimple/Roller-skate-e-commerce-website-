package vn.edu.hcmuaf.fit.doanweb.controller.product;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/product")
public class ListProduct extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCommonData(request);
        ProductDao productDao = new ProductDao();
        String order = request.getParameter("order");
        String indexPage = request.getParameter("index");
        String sort = request.getParameter("sort");
        String cateID = request.getParameter("category");
        String search = request.getParameter("txt");

        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        List<Product> products;

        if (cateID != null && !cateID.isEmpty()) {
            products = productDao.getAllByCategory(cateID);
        } else {
            products = productDao.getAll();
        }

        if (sort != null && !sort.isEmpty()) {
            products = productDao.getProductByOrder(sort);
        }

        int count = products.size();
        int endPage = (int) Math.ceil((double) count / 20);

        List<Product> paginatedProducts = new ArrayList<>();
        if (count > 0) {
            int fromIndex = (index - 1) * 20;
            int toIndex = Math.min(fromIndex + 20, count);
            if (fromIndex < count) {
                paginatedProducts = products.subList(fromIndex, toIndex);
            }
        }

        request.setAttribute("products", paginatedProducts);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
}