package vn.edu.hcmuaf.fit.doanweb.dao.db;

import java.sql.*;

public class DBConnect {

    static Connection conn;
    static String url = "jdbc:mysql://"
            + DBProperties.host() + ":"
            + DBProperties.port() + "/"
            + DBProperties.dbname() + "?"
            + DBProperties.option();

    public static Statement getStatement() {
        try {
            if (conn == null || conn.isClosed()) {
                makeConnect();
            }
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
                return conn.createStatement();
            } else {
                System.out.println("Kết nối đến cơ sở dữ liệu không thành công!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            return null;
        }
    }

    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                makeConnect();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy kết nối: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi tìm driver: " + e.getMessage());
        }
        return conn;
    }

    private static void makeConnect() throws SQLException, ClassNotFoundException {
        System.out.println("Đang kết nối đến cơ sở dữ liệu..." + url);
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, DBProperties.username(), DBProperties.password());
    }

    public static void main(String[] args) {
        try {
            Statement statement = getStatement();
            if (statement != null) {
                ResultSet rs = statement.executeQuery("SELECT * FROM product");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) +
                            ", " + rs.getString(2) +
                            ", " + rs.getString(3) +
                            ", " + rs.getDouble(4) +
                            ", " + rs.getString(5) +
                            ", " + rs.getString(6)
                    );
                }
                rs.close();  // Đóng ResultSet
                statement.close();  // Đóng Statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}