package vn.edu.hcmuaf.fit.doanweb.dao.order;


import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Order;

import java.sql.*;
import java.util.ArrayList;

  public class OderDao {

      public void insertOrder(Order order) throws SQLException {
          Statement statement = DBConnect.getStatement();
          String sql = "INSERT INTO orders (user_id, name, phone, address, paymentMethod, totalAmount,status) VALUES ( ?,?, ?, ?, ?, ?,'Đang xử lí')";

          PreparedStatement stmt = statement.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          stmt.setInt(1, order.getUser_id());
          stmt.setString(2, order.getName());
          stmt.setString(3, order.getPhone());
          stmt.setString(4, order.getAddress());
          stmt.setDouble(6, order.getTotalAmount());
          stmt.setString(5, order.getPaymentMethod());

          stmt.executeUpdate();  // Thực thi câu lệnh INSERT

          // Lấy ID vừa tạo ra
          try (ResultSet rs = stmt.getGeneratedKeys()) {
              if (rs.next()) {
                  int orderId = rs.getInt(1);  // Lấy ID của đơn hàng vừa được tạo
                  order.setId(orderId);  // Gán lại ID cho đối tượng Order
              }
          }
      }

      public ArrayList<Order> getListOrder() {
          try {
              Statement statement = DBConnect.getStatement();
              String sql = "SELECT * FROM orders";
              PreparedStatement ps = statement.getConnection().prepareStatement(sql);
              ResultSet rs = ps.executeQuery();
              ArrayList<Order> orders = new ArrayList<>();
              while (rs.next()) {
                  Order order = new Order();
                  order.setUser_id(rs.getInt("user_id"));
                  order.setName(rs.getString("name"));
                  order.setPhone(rs.getString("phone"));
                  order.setAddress(rs.getString("adress"));
                  order.setTotalAmount(rs.getDouble("totalAmount"));
                  order.setPaymentMethod(rs.getString("payment_method"));
                  order.setStatus(rs.getString("status"));
                  orders.add(order);
              }
              return orders;
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return null;
      }

  }
