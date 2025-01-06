package vn.edu.hcmuaf.fit.doanweb.dao;

import org.apache.taglibs.standard.tag.el.core.ForEachTag;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  ProductDao {
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
                            rs.getString("img"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("description")
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
    public Product getAllProductId(String id) {
        String query = "SELECT * FROM product WHERE id = ?";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                statement.setString(1, id); // Đặt giá trị tham số
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        return new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description")
                        );
                    }
                }
            }


        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }
        return null;
    }
    public List<Product> getAllByCategory(String id) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE cateID = ?";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                statement.setString(1, id); // Đặt giá trị tham số
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description")
                        ));
                    }
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
    public List<Product> getProductByTitle(String txtSearch) {
        List<Product> products = new ArrayList<>();
        String query = " SELECT * FROM product  WHERE title LIKE ?";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                statement.setString(1, "%"+txtSearch+"%"); // Đặt giá trị tham số
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description")
                        ));
                    }
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

    public List<Product> getAllProductnew() {
        List<Product> productNew = new ArrayList<>();
        String query = "SELECT * FROM product ORDER BY id DESC LIMIT 8";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    productNew.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("img"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("description")
                    ));
                }
            }

            if (productNew.isEmpty()) {
                System.out.println("Không có sản phẩm nào được tìm thấy.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }
        return productNew;
    }

    public List<Category> getAllCategory() {
        List<Category> lists = new ArrayList<>();
        String query = "SELECT * FROM category";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    lists.add(new Category(rs.getInt(1), rs.getString(2)));
                }
            }

            if (lists.isEmpty()) {
                System.out.println("Không có sản phẩm nào được tìm thấy.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }
        return lists;
    }

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
//        List<Product> products = dao.getAllProductId("1");
//        for(Product Category : products) {
//            System.out.println(Category);
//        }
        Product products = dao.getAllProductId("1");
            System.out.println(products);
    }
}



