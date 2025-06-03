package vn.edu.hcmuaf.fit.doanweb.dao.order;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.FailedOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FailedOrderDAO {
    public void insertFailedOrder(FailedOrder order) throws SQLException {
        String sql = "INSERT INTO failed_orders(user_id, name, phone, province, district, ward, address_detail, note, discount_code, shipping_fee, total_amount, payment_method, fail_reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getName());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getProvince());
            ps.setString(5, order.getDistrict());
            ps.setString(6, order.getWard());
            ps.setString(7, order.getAddressDetail());
            ps.setString(8, order.getNote());
            ps.setString(9, order.getDiscountCode());
            ps.setDouble(10, order.getShippingFee());
            ps.setDouble(11, order.getTotalAmount());
            ps.setString(12, order.getPaymentMethod());
            ps.setString(13, order.getFailReason());

            ps.executeUpdate();
        }
    }
}
