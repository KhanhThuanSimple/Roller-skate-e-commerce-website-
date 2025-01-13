package vn.edu.hcmuaf.fit.doanweb.controller;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.CartDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Cart;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "ListCart", value = "/cart")
public class CartServlet extends  BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        loadCommonData(request); // Gọi phương thức chung
        User user = (User) request.getSession().getAttribute("auth");
        String username = user.getUsername();
        CartDao cartDao = new CartDao();
        List<Cart> cart = null;
        try {
            cart = cartDao.getListCartByUsername(username);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("cart",cart);

        request.getRequestDispatcher("giohang.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
