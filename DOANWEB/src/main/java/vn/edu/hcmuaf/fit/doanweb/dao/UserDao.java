package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleAccount;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    public int limit = 10;

    public User login1(String username, String password) throws SQLException {
        String sql="select * from user where username=? and password=?";
        try {
            Statement st = DBConnect.getStatement();
            ResultSet rs = null;
           PreparedStatement pre= st.getConnection().prepareStatement(sql);
           pre.setString(1, username);
           pre.setString(2, password);
           rs = pre.executeQuery();
           while (rs.next()) {
               return new User(
                       rs.getInt("id"),
                       rs.getString("username"),
                       rs.getString("password"),
                       rs.getString("name"),
                       rs.getInt("type"));
           }

        }catch (SQLException e) {

        }

        return  null;

    }

    public User findUserByUserName(String username) throws SQLException {
        String sql="select * from user where username=? ";

        try {
            Statement st = DBConnect.getStatement();
            ResultSet rs = null;
            PreparedStatement pre= st.getConnection().prepareStatement(sql);
            pre.setString(1, username);
            rs = pre.executeQuery();
            if (rs.next()) {
                System.out.println(rs);
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));      
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                System.out.println("type");

                System.out.println(rs.getString("type"));
                System.out.println(rs.getInt(5));

                System.out.println(user.getType());
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<User> getList(int page, int type) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user WHERE type = ? ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, type); // Gán giá trị cho type
            pstmt.setInt(2, page-1);      // Gán giá trị cho offset
            pstmt.setInt(3, this.limit);       // Gán giá trị cho limit


            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }
            pstmt.close();
            st.close();
            rs.close();
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getPage(int type) throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM user WHERE type = ?"; // Query tính số trang

        try {
            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, this.limit);
            pstmt.setInt(2, type);  // Đặt giá trị của type vào câu lệnh SQL

            System.out.println(pstmt);

            rs = pstmt.executeQuery();

            int totalPages = 0;
            if (rs.next()) {
                totalPages = rs.getInt("total_pages"); // Lấy tổng số trang
            }

            pstmt.close();
            st.close();
            rs.close();

            return totalPages;  // Trả về tổng số trang

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertUser(String name, String email, String pass,String address,String phone, int type) throws SQLException {
        String sql = "insert into user(username,password,name,phone_number,address, type) values(?,?,?, ?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, pass);
            pre.setString(3, name);
            pre.setString(4, phone);
            pre.setString(5, address);

            pre.setInt(6, type);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(String name, String email,String address,String phone, int type, int id) throws SQLException {
        String sql = "UPDATE user SET username = ?, name = ?, phone_number = ?, address = ?, type = ? WHERE id = ?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, name);
            pre.setString(3, phone);
            pre.setString(4, address);

            pre.setInt(5, type);
            pre.setInt(6, id);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int uid) throws SQLException {
        String sql = "delete from user where id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, uid);

           int rs = pre.executeUpdate();

           return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByID(int id) throws SQLException {
        String sql = "select * from user where id=?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                return new User(rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"));
            }

            // System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updatePassword(int id, String newPassword, String oldPassword) throws SQLException {
        String sql = "UPDATE user SET password = ? WHERE id = ? AND password = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.setString(3, oldPassword);

            System.out.println(pstmt);
            int rs = pstmt.executeUpdate(); // Thực hiện cập nhật
            return rs==1;
        }
    }
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("type")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void savePasswordResetToken(int userId, String token, Timestamp expiryTime) throws SQLException {
        String sql = "UPDATE user SET reset_token = ?, token_expiry = ? WHERE id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, token);
            pre.setTimestamp(2, expiryTime);
            pre.setInt(3, userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*dăng nhập bằng gg*/

}