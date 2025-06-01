package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.reviewAll.ReviewAll;
import vn.edu.hcmuaf.fit.doanweb.dao.reviews.Review;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public List<ReviewAll> getProductsReviewed(int userId) {
        List<ReviewAll> reviews = new ArrayList<>();
        String sql = "SELECT r.id, r.user_id, r.product_id, r.rating, r.comment, r.created_at, p.img, p.title " +
                "FROM reviews r JOIN product p ON p.id = r.product_id " +
                "WHERE r.user_id = ? AND r.comment IS NOT NULL or TRIM(r.comment) != ''";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setUserId(rs.getInt("user_id"));
                review.setProductId(rs.getInt("product_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                review.setCreatedAt(rs.getString("created_at"));

                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setImg(rs.getString("img"));
                product.setTitle(rs.getString("title"));
//                review.setProduct(product);
                ReviewAll reviewAll = new ReviewAll(review, product);
                reviews.add(reviewAll);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
    public List<Product> getProductsNotReviewed(int userId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT DISTINCT p.id, p.title, p.img " +
                "FROM orders o " +
                "JOIN order_items oi ON o.id = oi.order_id " +
                "JOIN product p ON p.id = oi.product_id " +
                "LEFT JOIN reviews r ON p.id = r.product_id AND r.user_id = o.user_id " +
                "WHERE o.user_id = ? AND r.id IS NULL";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setImg(rs.getString("img"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public List<Review> getReviewsByProductId(String productId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT r.id, r.user_id, r.product_id, r.rating, r.comment, r.created_at, u.name " +
                "FROM reviews r " +
                "JOIN user u ON r.user_id = u.id " +
                "WHERE r.product_id = ? " +
                "ORDER BY r.created_at DESC";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("id"));
                review.setUserId(rs.getInt("user_id"));
                review.setProductId(rs.getInt("product_id"));
                review.setRating(rs.getInt("rating"));
                review.setComment(rs.getString("comment"));
                Timestamp ts = rs.getTimestamp("created_at");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                review.setCreatedAt(sdf.format(ts)); // se
//                review.setCreatedAt(rs.getString("created_at"));
                review.setUserName(rs.getString("name")); // tên người dùng

                reviews.add(review);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy đánh giá: " + e.getMessage());
        }

        return reviews;
    }



    public static void main(String[] args) {
        ReviewDao dao = new ReviewDao();
        List<Review> reviews = dao.getReviewsByProductId("4");
        for (Review r : reviews) {
            System.out.println(r+"khong co san pham ");
        }
    }

}
