package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartP;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartProduct;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "PlaceOrder", value = "/PlaceOrder")
public class PlaceOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("giohang1.jsp"); // Redirect to the cart page for GET requests
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartP cart = (CartP) session.getAttribute("cart");

        if (cart == null || cart.getList().isEmpty()) {
            response.sendRedirect("giohang1.jsp?error=empty"); // Redirect with error if the cart is empty
            return;
        }

        String discountCode = request.getParameter("discountCode");
        double discountAmount = validateDiscountCode(discountCode);

        double total = cart.calculateFinalTotal(0, discountAmount);

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Unable to establish a database connection.");
            }

            // Insert order
            String orderSql = "INSERT INTO orders (user_id, total_amount, discount_code, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement orderStatement = cons.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                int userId = (int) session.getAttribute("userId"); // Retrieve userId from session
                orderStatement.setInt(1, userId);
                orderStatement.setDouble(2, total);
                orderStatement.setString(3, discountCode);
                orderStatement.setString(4, "pending");

                orderStatement.executeUpdate();

                // Get the generated order ID
                ResultSet rs = orderStatement.getGeneratedKeys();
                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // Insert order items
                String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement itemStatement = cons.prepareStatement(itemSql)) {
                    for (CartProduct item : cart.getList()) {
                        itemStatement.setInt(1, orderId);
                        itemStatement.setInt(2, item.getId());
                        itemStatement.setInt(3, item.getQuantity());
                        itemStatement.setDouble(4, item.getPrice());
                        itemStatement.addBatch(); // Add to batch for efficiency
                    }
                    itemStatement.executeBatch(); // Execute batch
                }
            }

            // Clear the cart after successful order placement
            session.removeAttribute("cart");
            response.sendRedirect("success.jsp?total=" + total);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=" + e.getMessage());
        }
    }

    private double validateDiscountCode(String discountCode) {
        // Check the validity of the discount code
        if ("MA_GIAM_GIA".equals(discountCode)) {
            return 10000.0; // Fixed discount amount
        }
        return 0.0;
    }
}
