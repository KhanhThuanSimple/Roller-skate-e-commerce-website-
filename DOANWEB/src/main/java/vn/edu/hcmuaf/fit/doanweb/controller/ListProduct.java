package vn.edu.hcmuaf.fit.doanweb.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ListProduct", value = "/product")
public class ListProduct extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCommonData(request); // Gọi phương thức chung
        ProductDao productDao = new ProductDao();
        String order = request.getParameter("order");
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = productDao.getTotalProduct();
        int endPage = count / 20;
        if (count % 20 != 0) {
            endPage++;
        }
        List<Product> all1 = productDao.getProductByOrder(order);
        request.setAttribute("products", all1);
        List<Product> all = productDao.getAll();
        List<Product> list = productDao.pagingProduct(index);
        request.setAttribute("products", all);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("products", list);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
