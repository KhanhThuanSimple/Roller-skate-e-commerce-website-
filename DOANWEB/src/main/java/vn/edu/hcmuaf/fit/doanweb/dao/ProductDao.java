package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("img")
                    ));
                }
            }

            if (products.isEmpty()) {
                System.out.println("Không có sản phẩm nào được tìm thấy.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }
        return products;
    }

    public Product getById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (Connection cons = DBConnect.getConn();
             PreparedStatement statement = cons.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getString("img")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}