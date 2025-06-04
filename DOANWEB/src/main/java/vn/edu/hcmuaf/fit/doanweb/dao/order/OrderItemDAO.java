package vn.edu.hcmuaf.fit.doanweb.dao.order;


import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderItems;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderItemDAO {

    public void insertOrderItems(OrderItems orderItems) throws SQLException {

        Statement statement = DBConnect.getStatement();
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";


        PreparedStatement stmt = statement.getConnection().prepareStatement(sql);
        stmt.setInt(1, orderItems.getOrder_id());
        stmt.setInt(2, orderItems.getProduct_id());
        stmt.setInt(3, orderItems.getQuantity());
        stmt.setDouble(4, orderItems.getPrice());
        stmt.executeUpdate();


    }
    public void insertOrderItemsDahuy(OrderItems orderItems) throws SQLException {

        Statement statement = DBConnect.getStatement();
        String sql = "INSERT INTO order_itemsdahuy (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";


        PreparedStatement stmt = statement.getConnection().prepareStatement(sql);
        stmt.setInt(1, orderItems.getOrder_id());
        stmt.setInt(2, orderItems.getProduct_id());
        stmt.setInt(3, orderItems.getQuantity());
        stmt.setDouble(4, orderItems.getPrice());
        stmt.executeUpdate();


    }
}