package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ExportOrdersDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ImportDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.*;

import vn.edu.hcmuaf.fit.doanweb.utils.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthService {

    public User findByUsername(String username) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUserName(username);
        return user;
    }

    public ArrayList<User> getList(int page, int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getList(page, type);
    }

    public int getPage(int type) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getPage(type);
    }

    public int getPageExport() throws SQLException {
        ExportOrdersDao exportOrdersDao = new ExportOrdersDao();
        return exportOrdersDao.getPageExport();
    }

    public int getPageImport() throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.getPageImport();
    }



    public boolean update(String name, String email, String address, String phone, int type, int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.updateUser(name, email, address, phone, type, id);
    }

    public boolean delete(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.deleteUser(id);
    }

    public User findById(int id) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUserByID(id);
    }

    public ArrayList<ExportOrders> getListExport(int page) throws SQLException {
        ExportOrdersDao exportDao = new ExportOrdersDao();
        return exportDao.getListExport(page);
    }

    public ArrayList<ImportOrders> getListImport(int page) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.getListImport(page);
    }

    public boolean insertImport(int product_id, double purchase_price, int quantity) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.insertImport(product_id, purchase_price, quantity);
    }


    public boolean updateImport(int product_id, double purchase_price, int quantity, int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.updateImport(product_id, purchase_price, quantity, id);
    }
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

    public boolean deleteImport(int id) throws SQLException {
        ImportDao importDao = new ImportDao();
        return importDao.deleteImport(id);
    }

    public boolean insertProduct(String name, String img, double price, String title, String description, int cateID, String offer) throws SQLException {

        ProductDao productDao = new ProductDao();
        return productDao.insertProduct(name, img, price, title, description, cateID, offer);
    }

    public boolean updateProduct(String name, String img, double price, String title, String description, int cateID, String offer, int id) throws SQLException {
        ProductDao productDao = new ProductDao();
        return productDao.updateProduct(name, img, price, title, description, cateID, offer, id);
    }

    public boolean deleteProduct(int id) throws SQLException {
        ProductDao productDao = new ProductDao();
        return productDao.deleteProduct(id);
    }

    public User findByEmail(String email) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.findUserByEmail(email);
    }

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




    // Hàm cập nhật mật khẩu mới (đã hash sẵn), không kiểm tra mật khẩu cũ
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

    public boolean insert(String name, String email, String hashedPass, String address, String phone, int type) throws SQLException {
        return insertUser(name, email, hashedPass, address, phone, type, "");
    }

}
