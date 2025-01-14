package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  ProductDao {
    static Map<Integer, Product> data = new HashMap<>();

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
                            rs.getString("description"),
                            rs.getString("offer")
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
    public List<Product> getProductByOrder(String order) {
        List<Product> products = new ArrayList<>();

        // Kiểm tra giá trị order


        // Dùng cột mà bạn muốn sắp xếp, ví dụ là "price"
        String query = "SELECT * FROM product ORDER BY price " + order;

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getString("offer")
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
                                rs.getString("description"),
                                rs.getString("offer")
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
                                rs.getString("description"),
                                rs.getString("offer")
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
                                rs.getString("description"),
                                rs.getString("offer")
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
                            rs.getString("description"),
                            rs.getString("offer")
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
    public int getTotalProduct() {
    String query = "SELECT COUNT(*) FROM product";
        Connection cons = DBConnect.getConn();
        try {
            PreparedStatement prepa = cons.prepareStatement(query);
            ResultSet re = prepa.executeQuery();
            while (re.next()){
                return re.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0 ;
    }
    public List<Product> pagingProduct(int index) {
        List<Product> lists = new ArrayList<>();
        String query = "SELECT * FROM product " +
                "ORDER BY id " +
                "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY;";

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                statement.setInt(1, (index - 1) * 20); // Đặt tham số ở đây

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        lists.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getString("offer")
                        ));
                    }
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

    public Product getById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        Product product = null;

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                statement.setInt(1, id); // Thiết lập ID vào câu truy vấn
                ResultSet rs = statement.executeQuery();

                // Nếu tìm thấy sản phẩm
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("img"),
                            rs.getDouble("price"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("offer")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }

        return product; // Trả về sản phẩm hoặc null nếu không tìm thấy
    }


    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
//        List<Product> products = dao.pagingProduct(2);
//for (Product product : products) {
//     System.out.println(product);
//}
        System.out.println(dao.getAllProductId("1"));
    }
}



