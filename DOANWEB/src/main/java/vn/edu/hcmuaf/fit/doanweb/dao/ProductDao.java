package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.coupon.CouponDAO;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.coupon.Coupon;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderDetail;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
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
                            rs.getInt("cateID"),
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

//    public List<Product> getList(int id ) {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT * FROM product where (cateID = 0 OR cateID = ?)";
//
//        try (Connection cons = DBConnect.getConn()) {
//            if (cons == null) {
//                throw new SQLException("Kết nối không thành công.");
//            }
//
//            try (PreparedStatement statement = cons.prepareStatement(query);
//                 statement.setInt(1, id);
//                 ResultSet rs = statement.executeQuery()) {
//                while (rs.next()) {
//                    products.add(new Product(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("img"),
//                            rs.getDouble("price"),
//                            rs.getString("title"),
//                            rs.getString("description"),
//                            rs.getString("offer")
//                    ));
//                }
//            }
//
//            if (products.isEmpty()) {
//                System.out.println("Không có sản phẩm nào được tìm thấy.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
//        }
//        return products;
//    }

    public List<Product> getProductByOrder(String order) {
        List<Product> products = new ArrayList<>();

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
                                rs.getInt("cateID"),
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
                                rs.getInt("cateID"),
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
                System.out.println(statement);

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getInt("cateID"),
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
                statement.setString(1, "%" + txtSearch + "%"); // Đặt giá trị tham số
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getInt("cateID"),
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
                            rs.getInt("cateID"),
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

    public boolean insertProduct(String name, String img, double price, String title, String description, int cateID, String offer) throws SQLException {
        String sql = "insert into product(name,img,price,title, description,cateID,offer) values(?,?,?, ?,?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, img);
            pre.setDouble(3, price);
            pre.setString(4, title);
            pre.setString(5, description);
            pre.setInt(6, cateID);
            pre.setString(7, offer);
            System.out.println(pre);


            int rs = pre.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateProduct(String name, String img, double price, String title, String description, int cateID, String offer, int id) throws SQLException {
        String sql = "UPDATE product SET name = ?, img = ?, price = ?, title = ?, description = ? ,cateID = ? ,offer = ? WHERE id = ?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, img);
            pre.setDouble(3, price);
            pre.setString(4, title);


            pre.setString(5, description);
            pre.setInt(6, cateID);
            pre.setString(7, offer);
            pre.setInt(8, id);

            int rs = pre.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteProduct(int uid) throws SQLException {
        String sql = "delete from product where id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, uid);

            int rs = pre.executeUpdate();

            return rs == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalProduct() {
        String query = "SELECT COUNT(*) FROM product";
        Connection cons = DBConnect.getConn();
        try {
            PreparedStatement prepa = cons.prepareStatement(query);
            ResultSet re = prepa.executeQuery();
            while (re.next()) {
                return re.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
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
                                rs.getInt("cateId"),
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
                            rs.getInt("cateId"),
                            rs.getString("offer")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }

        return product; // Trả về sản phẩm hoặc null nếu không tìm thấy
    }

    public List<Order> getOrdersWithProducts(int userId) {
        List<Order> orders = new ArrayList<>();
        String queryOrders = "SELECT * FROM orders WHERE  user_id = ?";


        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                // Xử lý khi không thể kết nối
            }
            try (PreparedStatement statement = cons.prepareStatement(queryOrders)) {
                statement.setInt(1, userId); // Sửa ở đây để truyền đúng giá trị

                try (ResultSet rs = statement.executeQuery()) {
                    System.out.println(statement);
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
                        // Ví dụ: order.addProduct(product, orderItem);
                        orders.add(order); // Thêm đơn hàng vào danh sách
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    public List<Order> getOrdersWithProducts1(int userId) {
        List<Order> orders = new ArrayList<>();
        String queryOrders;

        if (userId == 0) {
            // Admin: lấy tất cả đơn hàng
            queryOrders = "SELECT * FROM orders";
        } else {
            // Người dùng: lấy đơn hàng theo user_id
            queryOrders = "SELECT * FROM orders WHERE user_id = ?";
        }

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                return orders; // hoặc throw lỗi nếu cần
            }

            try (PreparedStatement statement = userId == 0 ? cons.prepareStatement(queryOrders) : cons.prepareStatement(queryOrders)) {
                if (userId != 0) {
                    statement.setInt(1, userId);
                }

                try (ResultSet rs = statement.executeQuery()) {
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


    public List<OrderDetail> getOrderDetails(int userId, int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        String query = "SELECT p.img , p.title, oi.quantity, oi.price, " +
                "o.id AS order_id, o.name AS customer_name, o.phone, o.total_amount, " +
                "o.status, o.payment_method, o.address, o.province, o.district, o.ward, " +
                "o.shipping_fee, o.created_at " +
                "FROM product p " +
                "JOIN order_items oi ON p.id = oi.product_id " +
                "JOIN orders o ON oi.order_id = o.id " +
                "WHERE o.user_id = ? AND o.id = ?";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Product
                    Product product = new Product();
                    product.setImg(rs.getString("img"));
                    product.setTitle(rs.getString("title"));

                    // OrderItem
                    OrderItems item = new OrderItems();
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));

                    // Order
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setName(rs.getString("customer_name"));
                    order.setPhone(rs.getString("phone"));
                    order.setTotalAmount(rs.getDouble("total_amount"));
                    order.setStatus(rs.getString("status"));
                    order.setPaymentMethod(rs.getString("payment_method"));
                    order.setAddress(rs.getString("address"));
                    order.setProvince(rs.getString("province"));
                    order.setDistrict(rs.getString("district"));
                    order.setWard(rs.getString("ward"));
                    order.setShippingFee(rs.getDouble("shipping_fee"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));

                    // Gộp vào detail
                    OrderDetail detail = new OrderDetail(order, product, item);
                    orderDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }


    public static double getDiscountValue(String couponCode) {
        double discount = 0.0;
        String query = "SELECT discount_value FROM Coupon WHERE code = ? AND is_active = TRUE";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, couponCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                discount = rs.getDouble("discount_value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }


    public User getUserById(int id) {

        String query = "SELECT * FROM `user` WHERE id = ?";
        User user = null;
        try (Connection conn = DBConnect.getConn();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            // Gán giá trị cho tham số id
            preparedStatement.setInt(1, id);

            // Thực hiện truy vấn
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Tạo đối tượng User từ kết quả
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setName(resultSet.getString("name"));
                    user.setPhone(resultSet.getString("phone_number"));
                    user.setAddress(resultSet.getString("address"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return user;
    }

    public List<Product> getProductsByCategoryAndSort(String cateID, String order) {
        List<Product> products = new ArrayList<>();
        String sortOrder = "asc".equalsIgnoreCase(order) ? "asc" : "desc";
        String query = "SELECT * FROM product WHERE cateID = ? ORDER BY price " + sortOrder;

        try (Connection cons = DBConnect.getConn()) {
            if (cons == null) {
                throw new SQLException("Kết nối không thành công.");
            }

            try (PreparedStatement statement = cons.prepareStatement(query)) {
                // Thiết lập tham số cho câu truy vấn
                statement.setString(1, cateID);

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        // Thêm sản phẩm vào danh sách
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getDouble("price"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getInt("cateID"),
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

    public List<Product> getProductsPurchasedNotReviewed(int userId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.* FROM product p " +
                "JOIN order_details od ON p.id = od.product_id " +
                "JOIN orders o ON od.order_id = o.id " +
                "LEFT JOIN reviews r ON p.id = r.product_id AND r.user_id = ? " +
                "WHERE o.user_id = ? AND r.id IS NULL " +
                "GROUP BY p.id";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImg(rs.getString("img"));
                    // Set other properties as needed
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public boolean addProductReview(int userId, int productId, int rating, String comment) {
        String sql = "INSERT INTO reviews (user_id, product_id, rating, comment, created_at) " +
                "VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, rating);
            ps.setString(4, comment);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Product> getProductsPurchased(int id) {
        return getProductsPurchasedNotReviewed(id);
    }
//    public List<Product> getProductsReviewed(int userId) {
//        List<Product> reviewedProducts = new ArrayList<>();
//        String sql = "SELECT p.img, r.comment, r.rating, p.title " +
//                "FROM reviews r " +
//                "JOIN product p ON p.id = r.product_id " +
//                "WHERE r.user_id = ? AND r.comment IS NOT NULL AND TRIM(r.comment) != ''";
//
//        try (Connection conn = DBConnect.getConn();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Product p = new Product();
//                p.setImg(rs.getString("img"));
//                p.setTitle(rs.getString("title"));
//                p.setRating(rs.getInt("rating"));
//                p.setComment(rs.getString("comment"));
//
//                reviewedProducts.add(p);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Hoặc log lỗi
//        }
//
//        return reviewedProducts;
//    }


    public List<Product> getProductsNotReviewed(int id) {
        return getProductsPurchasedNotReviewed(id);
    }



    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        List<OrderDetail> coupons = dao.getOrderDetails(1090,96);
        System.out.println("Coupons: " + coupons);
        for (OrderDetail c : coupons) {
            System.out.println(c);
        }
    }
}