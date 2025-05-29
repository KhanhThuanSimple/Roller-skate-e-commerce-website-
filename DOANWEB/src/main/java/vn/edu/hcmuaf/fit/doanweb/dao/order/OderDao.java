package vn.edu.hcmuaf.fit.doanweb.dao.order;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.sql.*;
import java.util.ArrayList;

public class OderDao {

    public int insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, province, district, ward, address, name, phone, note, " +
                "total_amount, payment_method, status, discount_code, shipping_fee, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getUser_id());
            pstmt.setString(2, order.getProvince());
            pstmt.setString(3, order.getDistrict());
            pstmt.setString(4, order.getWard());
            pstmt.setString(5, order.getAddress());
            pstmt.setString(6, order.getName());
            pstmt.setString(7, order.getPhone());
            pstmt.setString(8, order.getNote());
            pstmt.setDouble(9, order.getTotalAmount());
            pstmt.setString(10, order.getPaymentMethod());
            pstmt.setString(11, order.getStatus());
            pstmt.setString(12, order.getDiscountCode());
            pstmt.setDouble(13, order.getShippingFee());

            Log.info(String.valueOf(order.getUser_id()), "CREATE_ORDER",
                    String.format("Đang lưu đơn hàng: user_id=%d, tên=%s, tổng tiền=%.2f, mã giảm=%s",
                            order.getUser_id(), order.getName(), order.getTotalAmount(), order.getDiscountCode()),
                    "system");

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                Log.error(String.valueOf(order.getUser_id()), "CREATE_ORDER", "Tạo đơn hàng thất bại: không có dòng nào được thêm", "system");
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int orderId = rs.getInt(1);
                    order.setId(orderId);
                    Log.info(String.valueOf(order.getUser_id()), "CREATE_ORDER",
                            String.format("Đơn hàng được tạo thành công! Mã đơn: %d", orderId),
                            "system");
                    return orderId;
                } else {
                    Log.error(String.valueOf(order.getUser_id()), "CREATE_ORDER", "Tạo đơn hàng thất bại: không nhận được ID", "system");
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            Log.error(String.valueOf(order.getUser_id()), "CREATE_ORDER", "Lỗi khi tạo đơn hàng: " + e.getMessage(), "system", e);
            throw e;
        }
    }

    public ArrayList<Order> getListOrder() {
        String sql = "SELECT * FROM orders";
        ArrayList<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setProvince(rs.getString("province"));
                order.setDistrict(rs.getString("district"));
                order.setWard(rs.getString("ward"));
                order.setAddress(rs.getString("address"));
                order.setName(rs.getString("name"));
                order.setPhone(rs.getString("phone"));
                order.setNote(rs.getString("note"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setStatus(rs.getString("status"));
                order.setDiscountCode(rs.getString("discount_code"));
                order.setShippingFee(rs.getDouble("shipping_fee"));

                orders.add(order);
            }

            Log.info("system", "GET_ORDERS", "Lấy danh sách đơn hàng thành công. Tổng: " + orders.size(), "system");

        } catch (SQLException e) {
            Log.error("system", "GET_ORDERS", "Lỗi khi lấy danh sách đơn hàng: " + e.getMessage(), "system", e);
        }

        return orders;
    }

    private double calculateDiscount(String discountCode) {
        if ("discount10".equalsIgnoreCase(discountCode)) {
            return 0.1;
        }
        if ("discount20".equalsIgnoreCase(discountCode)) {
            return 0.2;
        }
        if ("discount50".equalsIgnoreCase(discountCode)) {
            return 0.5;
        }
        return 0.0;
    }
}