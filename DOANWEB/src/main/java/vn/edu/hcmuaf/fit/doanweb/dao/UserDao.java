package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User findUserByUserName(String userName) throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery("select * from user");
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("type")
//                     rs.getString("phone"),
//                     rs.getString("address")
                );
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
            pstmt.setInt(2, page);      // Gán giá trị cho offset
            pstmt.setInt(3, 50);       // Gán giá trị cho limit

            System.out.println();

            rs = pstmt.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("type")
//                        rs.getString("phone"),
//                        rs.getString("address")

                ));
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertUser(String name, String email, String pass, int type) throws SQLException {
        String sql="insert into user(username,password,name, type) values(?,?,?, ?)";
    try{
        Statement st= DBConnect.getStatement();
        PreparedStatement pre= st.getConnection().prepareStatement(sql);
        pre.setString(1, email);
        pre.setString(2, pass);
        pre.setString(3, name);
        pre.setInt(4,type);

        int rs=pre.executeUpdate();

        System.out.println(rs);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    public void deleteUser(int uid) throws SQLException {
        String sql="delete from user where id = ?";
        try{
            Statement st= DBConnect.getStatement();
            PreparedStatement pre= st.getConnection().prepareStatement(sql);
            pre.setInt(1, uid);

            pre.executeUpdate();

           // System.out.println(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<User> getListC(int page, int type) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<User> customers = new ArrayList<>();
        try {

            String sql = "SELECT * FROM user WHERE type = ? ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, type); // Gán giá trị cho type
            pstmt.setInt(2, page);      // Gán giá trị cho offset
            pstmt.setInt(3, 50);       // Gán giá trị cho limit

            System.out.println();

            rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                     //   rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("type"),
                       rs.getString("phone_number"),
                       rs.getString("address")

                ));
            }

            return customers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    public User register(User user,String repass) throws SQLException {
//        String sql="INSERT INTO user (name, username, password, repass, phone_number,address)\n" +
//                "VALUES (?,?,?,?,?,?)";
//        try {
//            Statement st = DBConnect.getStatement();
//            PreparedStatement pre= st.getConnection().prepareStatement(sql);
//            pre.setString(1, user.getName());
//            pre.setString(2, user.getUsername());
//            pre.setString(3, user.getPassword());
//            pre.setString(4,user.getPhone());
//            pre.setString(5,repass);
//            pre.setString(6,user.getAddress());
//
//
//        }catch (SQLException e) {   }
//
//
//        return user;
//    }
public User register(User user, String repass) throws SQLException {
    Statement st = DBConnect.getStatement();
        String sql = "INSERT INTO user (name, username, password, phone_number, address) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement pre =st.getConnection().prepareStatement(sql)) {
        pre.setString(1, user.getName());
        pre.setString(2, user.getUsername());
        pre.setString(3, user.getPassword());
        pre.setString(4, user.getPhone());
        pre.setString(5, user.getAddress());

        pre.executeUpdate(); // Thực thi câu lệnh
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi để dễ dàng khắc phục
        throw e; // Ném lại ngoại lệ để xử lý ở nơi khác nếu cần
    }
    return user; // Trả về đối tượng user đã đăng ký
}

}