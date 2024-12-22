package vn.edu.hcmuaf.fit.doanweb.dao.db;

import java.sql.*;

public class DBConnect {
    static String url = "jdbc:mysql://"
            +DBProperties.host()+":"
            +DBProperties.port()+"/"
            +DBProperties.dbname()+"?"
            +DBProperties.option();

    static Connection conn ;
    public  static Statement getStatement() throws SQLException {

        try {
            if (conn == null || conn.isClosed()) {
                makeConnect();
            }
            return conn.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void makeConnect() throws SQLException, ClassNotFoundException {
       System.out.println("Connecting to database..." + url);

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url,DBProperties.username(),DBProperties.password());
    }
    public static void main(String[] args) throws SQLException {
//     Statement st=   DBConnect.getStatement();
//
//     try {
//         ResultSet rs =  st.executeQuery("select id from user");
//
//         while (rs.next()) {
//            System.out.println(rs.getInt(1));
//         }
//     }catch (SQLException e) {
//         throw new RuntimeException(e);
//     }

    }
}
