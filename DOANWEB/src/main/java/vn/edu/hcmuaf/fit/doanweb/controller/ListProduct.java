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
public class ListProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<Product> all = productDao.getAll();
        List<Category> listc = productDao.getAllCategory();
        List<Product> productNew = productDao.getAllProductnew();
        request.setAttribute("products", all);
        request.setAttribute("listc", listc);
        request.setAttribute("productNew", productNew);

        request.getRequestDispatcher("product.jsp").forward(request, response);
        request.getRequestDispatcher("header.jsp").forward(request, response);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
