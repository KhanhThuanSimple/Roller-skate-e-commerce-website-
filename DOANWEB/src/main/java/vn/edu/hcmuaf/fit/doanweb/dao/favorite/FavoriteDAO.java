package vn.edu.hcmuaf.fit.doanweb.dao.favorite;

import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {
    public boolean addFavorite(int userId, int productId) {
        String sql = "INSERT INTO favorites (user_id, product_id) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE is_active = TRUE";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFavorite(int userId, int productId) {
        String sql = "DELETE FROM favorites WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean isFavorited(int userId, int productId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND product_id = ? AND is_active = TRUE";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Nếu có ít nhất một dòng với is_active = TRUE thì trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Nếu không tìm thấy sản phẩm trong danh sách yêu thích hoặc sản phẩm không có is_active = TRUE
    }


    public List<Product> getUserFavorites(int userId) {
        List<Product> favorites = new ArrayList<>();
        String sql = "SELECT p.id,p.title,p.price,p.img FROM product p " +
                "JOIN favorites f ON p.id = f.product_id " +
                "WHERE f.user_id = ? AND f.is_active = TRUE";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("title"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImg(rs.getString("img"));
                    // Thêm các thuộc tính khác nếu cần
                    favorites.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }

    public static void main(String[] args) {
        FavoriteDAO favoriteDAO = new FavoriteDAO();

        List<Product> products = favoriteDAO.getUserFavorites(1);
        for (Product product : products) {
            System.out.println(product);
        }
    }
};