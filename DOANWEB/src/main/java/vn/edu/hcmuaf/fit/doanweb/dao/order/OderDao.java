package vn.edu.hcmuaf.fit.doanweb.dao.order;


import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;

import java.sql.*;
import java.util.ArrayList;

public class OderDao {

    public int insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, province, district, ward, address,name, phone, note, " +
                "total_amount, payment_method, status, discount_code, shipping_fee, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, NOW())";

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

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            // Lấy ID của đơn hàng vừa tạo
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int orderId = rs.getInt(1);  // Lấy ID của đơn hàng vừa được tạo
                    order.setId(orderId);  // Gán lại ID cho đối tượng Order
                    return orderId;
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
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
        // Truy vấn SQL để lấy danh sách đơn hàng
        String sql = "SELECT * FROM orders"; // Hoặc thay đổi theo cấu trúc của bảng 'orders'
        ArrayList<Order> orders = new ArrayList<>();

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Duyệt qua kết quả trả về từ cơ sở dữ liệu
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

                // Thêm đơn hàng vào danh sách
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra nếu có
        }

        return orders; // Trả về danh sách các đơn hàng
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
