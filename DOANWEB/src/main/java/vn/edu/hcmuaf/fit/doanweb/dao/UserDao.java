package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "SELECT u.*,r.name as perName FROM user u LEFT JOIN rights r on r.id = u.idPer WHERE type = ? ORDER BY id LIMIT ?, ?";

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
                user.setIdPer(rs.getInt("idPer"));
                user.setNamePer(rs.getString("perName"));
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

    public boolean insertUser(String name, String email, String pass,String address,String phone, int type, int role) throws SQLException {
        String sql = "insert into user(username,password,name,phone_number,address, type, idPer) values(?,?,?, ?,?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, pass);
            pre.setString(3, name);
            pre.setString(4, phone);
            pre.setString(5, address);

            pre.setInt(6, type);
            pre.setInt(7, role);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(String name, String email,String address,String phone, int type, int id, int role) throws SQLException {
        String sql = "UPDATE user SET username = ?, name = ?, phone_number = ?, address = ?, type = ?, idPer = ? WHERE id = ?";

        try {
            System.out.println("id: " + id);
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, name);
            pre.setString(3, phone);
            pre.setString(4, address);
            pre.setInt(5, type);
            pre.setInt(6, role);
            pre.setInt(7, id);

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

    public List<String> getListPerUser(int user_id) throws SQLException {
        List<String> screenCodes = new ArrayList<>();
        String sql = "SELECT s.code FROM user u \n" +
                "                 JOIN screen_permissions sp ON u.idPer = sp.idRights \n" +
                "                 JOIN screen s ON sp.idScreen = s.id \n" +
                "                 WHERE u.id = ? AND sp.read = 1";
        Statement st = DBConnect.getStatement();
        PreparedStatement pre = st.getConnection().prepareStatement(sql);
        pre.setInt(1, user_id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            screenCodes.add(rs.getString("code"));
        }
        return screenCodes;
    }

    public ScreenPermissions getPerUserScreen(int user_id, String code) throws SQLException {

        String sql = "SELECT sp.*\n" +
                "FROM user u\n" +
                "JOIN screen_permissions sp ON u.idPer = sp.idRights\n" +
                "JOIN screen s ON sp.idScreen = s.id\n" +
                "WHERE u.id = ? AND s.code = ?;";
        Statement st = DBConnect.getStatement();
        PreparedStatement pre = st.getConnection().prepareStatement(sql);
        System.out.println(pre.toString());
        pre.setInt(1, user_id);
        pre.setString(2, code);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            ScreenPermissions screenPermissions = new ScreenPermissions();
            screenPermissions.setId(rs.getInt("id"));
            screenPermissions.setIdRights(rs.getInt("idRights"));
            screenPermissions.setIdScreen(rs.getInt("idScreen"));
            screenPermissions.setRead(rs.getInt("read"));
            screenPermissions.setAdd(rs.getInt("add"));
            screenPermissions.setDelete(rs.getInt("delete"));
            screenPermissions.setEdit(rs.getInt("edit"));

            return screenPermissions;

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

}