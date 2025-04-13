/*
package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.CartDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/removeCartItem")
public class RemoveCartItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        User user = (User) request.getSession().getAttribute("auth");
        String username = user.getUsername();

        // Gọi phương thức để xóa sản phẩm khỏi giỏ hàng
        CartDao cartDAO = new CartDao();
        boolean isDeleted = false;
        try {
            isDeleted = cartDAO.removeCartItem(productId,username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Trả về phản hồi cho client
        response.setContentType("application/json");
        response.getWriter().write("{\"success\": " + isDeleted + "}");
    }

}
*/
