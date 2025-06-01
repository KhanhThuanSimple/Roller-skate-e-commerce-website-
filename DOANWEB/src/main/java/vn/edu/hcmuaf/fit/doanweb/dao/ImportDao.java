package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ExportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImportDao {
    public int limit=10;
    public ArrayList<ImportOrders> getListImport(int page) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<ImportOrders> imports = new ArrayList<>();
        try {
            String sql = "SELECT * FROM import_orders AS import_orders\n" +
                    " LEFT JOIN product AS product ON product.id = import_orders.product_id\n" +
                    " ORDER BY import_orders.id LIMIT ?,?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);

            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit

System.out.println(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ImportOrders importOrders= new ImportOrders();
                importOrders.setId(rs.getInt("id"));
                importOrders.setProduct_id(rs.getInt("product_id"));
                importOrders.setProduct_name(rs.getString("name"));
                importOrders.setImage(rs.getString("img"));
                importOrders.setPurchase_price(rs.getDouble("purchase_price"));
                importOrders.setQuantity(rs.getInt("quantity"));

                imports.add(importOrders);
            }
            pstmt.close();
            st.close();
            rs.close();
            return imports;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int getPageImport() throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM import_orders"; // Query tính số trang

        try {
            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, this.limit);
          //  pstmt.setInt(2, type);  // Đặt giá trị của type vào câu lệnh SQL

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
    public User findUserByUserName(String username) throws SQLException {
        String sql="select * from user where username=?  ";

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
    public boolean insertImport(int product_id,double purchase_price,int quantity) throws SQLException {
        String sql = "insert into import_orders(product_id,purchase_price,quantity) values(?, ?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, product_id);
     ;
            pre.setDouble(2,purchase_price);
            pre.setInt(3, quantity);



            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateImport(int product_id,double purchase_price,int quantity,int id) throws SQLException {
        String sql = "UPDATE import_orders SET product_id = ?,  purchase_price = ?, quantity = ? WHERE id = ?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, product_id);
          ;
            pre.setDouble(2, purchase_price);

            pre.setInt(3, quantity);
            pre.setInt(4, id);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteImport(int uid) throws SQLException {
        String sql = "delete from import_orders where id = ?";
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

    public ImportOrders findById(int uid) throws SQLException {
        String sql = "SELECT * FROM import_orders WHERE id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, uid);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                ImportOrders importOrders = new ImportOrders();
                importOrders.setId(rs.getInt("id"));
                importOrders.setProduct_id(rs.getInt("product_id"));
                importOrders.setQuantity(rs.getInt("quantity"));
                return importOrders;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
