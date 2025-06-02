package vn.edu.hcmuaf.fit.doanweb.dao.coupon;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.coupon.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO {
    public Coupon findByCode(String code) {
        String sql = "SELECT * FROM coupons WHERE coupon_code = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Coupon c = new Coupon();
                c.setId(rs.getInt("id"));
                c.setCouponCode(rs.getString("coupon_code"));
                c.setDiscountType(rs.getString("discount_type"));
                c.setDiscountValue(rs.getBigDecimal("discount_value"));
                c.setMinOrderAmount(rs.getBigDecimal("min_order_amount"));
                c.setExpireDate(rs.getDate("expire_date"));
                c.setStatus(rs.getString("status"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Coupon> getAllCoupons() {
        List<Coupon> list = new ArrayList<>();
        String sql = "SELECT * FROM coupons";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Coupon c = new Coupon();
                c.setId(rs.getInt("id"));
                c.setCouponCode(rs.getString("coupon_code"));
                c.setDiscountType(rs.getString("discount_type"));
                c.setDiscountValue(rs.getBigDecimal("discount_value"));
                c.setMinOrderAmount(rs.getBigDecimal("min_order_amount"));
                c.setExpireDate(rs.getDate("expire_date"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Coupon> getActiveCoupons() {
        List<Coupon> list = new ArrayList<>();
        String query = "SELECT * FROM coupons WHERE status = 'ACTIVE' AND expire_date >= NOW()";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Coupon c = new Coupon();
                c.setId(rs.getInt("id"));
                c.setCouponCode(rs.getString("coupon_code"));
                c.setDiscountType(rs.getString("discount_type"));
                c.setDiscountValue(rs.getBigDecimal("discount_value"));
                c.setMinOrderAmount(rs.getBigDecimal("min_order_amount"));
                c.setExpireDate(rs.getDate("expire_date"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateCoupon(Coupon coupon) {
        String sql = "UPDATE coupons SET coupon_code=?, discount_type=?, discount_value=?, min_order_amount=?, expire_date=?, status=? WHERE id=?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coupon.getCouponCode());
            ps.setString(2, coupon.getDiscountType());
            ps.setBigDecimal(3, coupon.getDiscountValue());
            ps.setBigDecimal(4, coupon.getMinOrderAmount());
            java.util.Date expireUtilDate = coupon.getExpireDate();
            if (expireUtilDate != null) {
                ps.setDate(5, new java.sql.Date(expireUtilDate.getTime()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }            ps.setString(6, coupon.getStatus());
            ps.setInt(7, coupon.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addCoupon(Coupon coupon) {
        String sql = "INSERT INTO coupons (coupon_code, discount_type, discount_value, min_order_amount, expire_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, coupon.getCouponCode());
            ps.setString(2, coupon.getDiscountType());
            ps.setBigDecimal(3, coupon.getDiscountValue());
            ps.setBigDecimal(4, coupon.getMinOrderAmount());
            ps.setDate(5, new java.sql.Date(coupon.getExpireDate().getTime()));
            ps.setString(6, coupon.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCoupon(int id) {
        String sql = "DELETE FROM coupons WHERE id = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        CouponDAO dao = new CouponDAO();
        List<Coupon> coupons = dao.getActiveCoupons();
        System.out.println("Coupons loaded: " + coupons.size());

        for (Coupon coupon : coupons) {
            // Ví dụ: in thông tin mã giảm giá
            System.out.println("Code: " + coupon.getCouponCode());
            System.out.println("Type: " + coupon.getDiscountType());
            System.out.println("Value: " + coupon.getDiscountValue());
            System.out.println("Min Order: " + coupon.getMinOrderAmount());
            System.out.println("Status: " + coupon.getStatus());
            System.out.println("------------------------");
        }
    }
}
