package vn.edu.hcmuaf.fit.doanweb.dao.order;


import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OderDao_vnpay {

    public int insertOrder(Order order) throws SQLException {
        Statement statement = DBConnect.getStatement();
        String sql = "INSERT INTO orders (user_id, name, phone, address, paymentMethod, totalAmount, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Tính toán số tiền sau giảm giá
        double discount = calculateDiscount(order.getDiscountCode());
        double finalAmount = order.getTotalAmount() - (order.getTotalAmount() * discount);
        String status = "Đang xử lí"; // Mặc định

        if ("Bank".equalsIgnoreCase(order.getPaymentMethod())) {
            status = "Đã thanh toán";
        }

        PreparedStatement stmt = statement.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, order.getUser_id());
        stmt.setString(2, order.getName());
        stmt.setString(3, order.getPhone());
        stmt.setString(4, order.getAddress());
        stmt.setString(5, order.getPaymentMethod());
        stmt.setDouble(6, finalAmount);
        stmt.setString(7, status);

        // Thực thi câu lệnh INSERT
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating order failed, no rows affected.");
        }

        // Lấy ID vừa tạo ra
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                int orderId = rs.getInt(1);  // Lấy ID của đơn hàng vừa được tạo
                order.setId(orderId);  // Gán lại ID cho đối tượng Order
                return orderId;  // Trả về ID của đơn hàng
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        }
    }

    private double calculateDiscount(String discountCode) {
        // Logic để kiểm tra mã giảm giá và tính toán
        if ("discount10".equalsIgnoreCase(discountCode)) {
            return 0.1; // Giảm 10
        }
        if ("discount20".equalsIgnoreCase(discountCode)) {
            return 0.2; // Giảm 10
        }
        if ("discount50".equalsIgnoreCase(discountCode)) {
            return 0.5; // Giảm 10
        }
        return 0.0; // Không có giảm giá
    }

    public ArrayList<Order> getListOrder() {
        try {
            Statement statement = DBConnect.getStatement();
            String sql = "SELECT * FROM orders";
            PreparedStatement ps = statement.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setUser_id(rs.getInt("user_id"));
                order.setName(rs.getString("name"));
                order.setPhone(rs.getString("phone"));
                order.setAddress(rs.getString("adress"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateOrderStatus(Order order) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [Status] = ?\n"
                + " WHERE Id = ?";
        try {
            Statement statement = DBConnect.getStatement();
            PreparedStatement st = statement.getConnection().prepareStatement(sql);
            st.setString(1, order.getStatus());
            st.setInt(2, order.getId());
            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

}
