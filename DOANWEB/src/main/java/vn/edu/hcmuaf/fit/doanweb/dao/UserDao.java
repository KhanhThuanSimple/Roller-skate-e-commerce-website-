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
}