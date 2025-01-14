package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderHistory", value = "/history")
public class OrderHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }

        List<Order> orders = fetchUserOrders(userId);

        // Đặt danh sách đơn hàng làm thuộc tính của request
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/thanhtoan.jsp").forward(request, response);
    }

    /**
     * Lấy danh sách đơn hàng của người dùng từ cơ sở dữ liệu.
     *
     * @param userId ID của người dùng.
     * @return Danh sách các đơn hàng.
     */
    private List<Order> fetchUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection connection = DBConnect.getConn();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setName(resultSet.getString("name"));
                    order.setEmail(resultSet.getString("email"));
                    order.setAddress(resultSet.getString("address"));
                    order.setPhone(resultSet.getString("phone"));
                    order.setTotalAmount(resultSet.getDouble("totalAmount"));
                    order.setShippingFee(resultSet.getDouble("shippingFee"));
                    order.setDiscountAmount(resultSet.getDouble("discountAmount"));
                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi
        }

        return orders;
    }
}
