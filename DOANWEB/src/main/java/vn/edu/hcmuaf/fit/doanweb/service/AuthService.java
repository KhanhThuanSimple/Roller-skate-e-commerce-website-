package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.*;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.*;
import vn.edu.hcmuaf.fit.doanweb.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthService {

    // --- Phương thức tìm user theo username ---
    public User findByUsername(String username) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUserName(username);
        // 2 bản giống nhau, có 1 đoạn kiểm tra null, nên giữ đoạn null check
        if (user == null) {
            return null;
        }
        return user;
    }

    // --- Lấy danh sách user theo trang và type ---
    public ArrayList<User> getList(int page, int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getList(page, type);
    }

    // --- Lấy số trang theo type user ---
    public int getPage(int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getPage(type);
    }

    // --- Lấy số trang export orders ---
    public int getPageExport() throws SQLException {
        ExportOrdersDao exportOrdersDao = new ExportOrdersDao();
        return exportOrdersDao.getPageExport();
    }

    // --- Lấy số trang import orders ---
    public int getPageImport() throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.getPageImport();
    }

    // --- Thêm user (có 2 bản khác nhau, 1 bản có thêm role int) ---
    // Mình giữ lại bản có role vì nó có thêm biến này, nếu bạn không dùng role có thể bỏ tham số role
    public boolean insert(String name, String email, String pass, String address, String phone, int type, int role) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.insertUser(name, email, pass, address, phone, type, role);
    }

    // --- Cập nhật user (có thêm role) ---
    public boolean update(String name, String email, String address, String phone, int type, int id, int role) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.updateUser(name, email, address, phone, type, id, role);
    }

    // --- Xóa user ---
    public boolean delete(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.deleteUser(id);
    }

    // --- Tìm user theo id ---
    public User findById(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUserByID(id);
    }

    // --- Lấy danh sách ExportOrders ---
    public ArrayList<ExportOrders> getListExport(int page) throws SQLException {
        ExportOrdersDao exportDao = new ExportOrdersDao();
        return exportDao.getListExport(page);
    }

    // --- Lấy danh sách ImportOrders ---
    public ArrayList<ImportOrders> getListImport(int page) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.getListImport(page);
    }

    // --- Thêm đơn nhập hàng ---
    public boolean insertImport(int product_id, double purchase_price, int quantity) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.insertImport(product_id, purchase_price, quantity);
    }

    // --- Cập nhật đơn nhập hàng ---
    public boolean updateImport(int product_id, double purchase_price, int quantity, int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.updateImport(product_id, purchase_price, quantity, id);
    }

    // --- Xóa đơn nhập hàng ---
    public boolean deleteImport(int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.deleteImport(id);
    }

    // --- Thêm sản phẩm ---
    public boolean insertProduct(String name, String img, double price, String title, String description, int cateID, String offer) throws SQLException {
        ProductDao productDao = new ProductDao();
        return productDao.insertProduct(name, img, price, title, description, cateID, offer);
    }

    // --- Cập nhật sản phẩm ---
    public boolean updateProduct(String name, String img, double price, String title, String description, int cateID, String offer, int id) throws SQLException {
        ProductDao productDao = new ProductDao();
        return productDao.updateProduct(name, img, price, title, description, cateID, offer, id);
    }

    // --- Xóa sản phẩm ---
    public boolean deleteProduct(int id) throws SQLException {
        ProductDao productDao = new ProductDao();
        return productDao.deleteProduct(id);
    }

    // --- Tìm user theo email ---
    public User findByEmail(String email) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.findUserByEmail(email);
    }

    // --- Tạo user (dùng đối tượng User) ---
    public void createUser(User user) {
        UserDao userDao = new UserDao();
        try {
            boolean success = userDao.insertUser(user);
            if (!success) {
                System.out.println("Insert user failed");
                // Xử lý thêm nếu cần
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }

    // --- Cập nhật mật khẩu mới (đã hash sẵn), không kiểm tra mật khẩu cũ ---
    public boolean updatePassword(int id, String hashed) throws SQLException {
        String updateSql = "UPDATE user SET password = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, hashed);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật mật khẩu", e);
        }
    }

    // --- Thêm user với googleId (bản đầu tiên có, bản 2 không) ---
    public boolean insertUser(String name, String username, String hashedPass, String address, String phone, int type, String googleId) throws SQLException {
        String sql = "INSERT INTO user (name, username, password, address, phone_number, type, google_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, hashedPass);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, type);
            ps.setString(7, googleId);
            return ps.executeUpdate() == 1;
        }
    }

    // --- Hàm insert không có googleId, gọi lại hàm trên với googleId rỗng ---
    public boolean insert(String name, String email, String hashedPass, String address, String phone, int type) throws SQLException {
        return insertUser(name, email, hashedPass, address, phone, type, "");
    }

}
