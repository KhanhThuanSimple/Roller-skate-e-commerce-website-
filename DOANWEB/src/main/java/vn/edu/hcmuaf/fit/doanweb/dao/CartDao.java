//package vn.edu.hcmuaf.fit.doanweb.dao;
//
//import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.Cart;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartDao {
//
//    public ArrayList<Cart> getListCartByUsername(String username) throws SQLException {
//
//        Statement st=   DBConnect.getStatement();
//        ResultSet rs = null;
//        ArrayList<Cart> users = new ArrayList<>();
//        try {
//
//            String sql = "SELECT * FROM cart WHERE username = ?";
//
//            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
//            pstmt.setString(1, username); // Gán giá trị cho username
//
//
//            System.out.println();
//
//            rs =  pstmt.executeQuery();
//
//            while (rs.next()) {
//                users.add(new Cart(
//                        rs.getInt("id_cart"),
//                        rs.getInt("amount"),
//                        rs.getString("img_path"),
//                        rs.getDouble("price"),
//                        rs.getInt("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("username")
//                ));
//            }
//
//            return users;
//
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public boolean insertCartItem(Cart cartItem) throws SQLException {
//        String sql = "INSERT INTO cart (id_cart,product_id, product_name, amount, price, img_path, username) VALUES (?, ?, ?, ?, ?, ?,?)";
//        try (Connection conn = DBConnect.getConn();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, cartItem.getId());
//            pstmt.setInt(2, cartItem.getProduct_id());
//            pstmt.setString(3, cartItem.getProduct_name());
//            pstmt.setInt(4, cartItem.getAmount());
//            pstmt.setDouble(5, cartItem.getPrice());
//            pstmt.setString(6, cartItem.getImg_path());
//            pstmt.setString(7, cartItem.getUsername());
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
//        } catch (SQLException e) {
//            throw new RuntimeException("Error inserting item into cart", e);
//        }
//    }
//
//
//
//
//
//
//
//    public boolean updateCartItemAmount(int productId, int newAmount, String username) throws SQLException {
//        String checkSql = "SELECT amount FROM cart WHERE product_id = ? and username = ?";
//        String updateSql = "UPDATE cart SET amount = ? WHERE product_id = ? and username = ?";
//
//        try (Connection conn = DBConnect.getConn();
//             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
//             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
//
//            // Kiểm tra xem sản phẩm có trong giỏ hàng không
//            checkStmt.setInt(1, productId);
//            checkStmt.setString(2, username);
//            ResultSet rs = checkStmt.executeQuery();
//
//            if (rs.next()) {
//                // Nếu sản phẩm tồn tại, cập nhật số lượng
//                int currentAmount = rs.getInt("amount");
//                int updatedAmount = currentAmount + newAmount; // Cập nhật số lượng mới
//
//                updateStmt.setInt(1, updatedAmount);
//                updateStmt.setInt(2, productId);
//                updateStmt.setString(3, username);
//                int rowsAffected = updateStmt.executeUpdate();
//                return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
//            } else {
//                // Nếu sản phẩm không tồn tại trong giỏ hàng
//                return false;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error updating item amount in cart", e);
//        }
//    }
//
//
//
//    public boolean removeCartItem(int productId, String username) throws SQLException {
//        String sql = "DELETE FROM cart WHERE product_id = ? AND username = ?";
//        try (Connection conn = DBConnect.getConn();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, productId);
//            pstmt.setString(2, username);
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
//        }
//    }
//}
