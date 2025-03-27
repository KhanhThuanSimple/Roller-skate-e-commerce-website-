/*
package vn.edu.hcmuaf.fit.doanweb.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.CartDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertProduct", value = "/insertProduct")
public class InsertProduct extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc dữ liệu JSON từ request
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // Chuyển đổi JSON thành đối tượng Cart
        Gson gson = new Gson();
        Cart cartItem = gson.fromJson(sb.toString(), Cart.class);
        System.out.println(cartItem);
        CartDao cartDao = new CartDao();
        try {
            boolean update = cartDao.updateCartItemAmount(cartItem.getProduct_id(),cartItem.getAmount(), cartItem.getUsername());
            if (update) {
                response.setStatus(HttpServletResponse.SC_OK);
            }else {
            boolean success = cartDao.insertCartItem(cartItem);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
