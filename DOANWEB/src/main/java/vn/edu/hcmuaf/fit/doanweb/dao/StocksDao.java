package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StocksDao {
    public int limit=10;
    public ArrayList<Stock> getListStocks(int page) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<Stock> stocks = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.product_id, p.name, p.img, s.quantity_stock FROM  stock as s LEFT JOIN product as p ON s.product_id = p.id ORDER BY s.id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            // Gán giá trị cho type
            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit


            System.out.println(pstmt);


            rs = pstmt.executeQuery();

            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("id"));
                stock.setProduct_id(rs.getInt("product_id"));
                stock.setProduct_name(rs.getString("name"));
                stock.setImg(rs.getString("img"));
                stock.setQuantity_stock(rs.getInt("quantity_stock"));

                stocks.add(stock);
            }
            pstmt.close();
            st.close();
            rs.close();
            return stocks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getPageStock() throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM stock "; // Query tính số trang

        try {
            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, this.limit);
            // Đặt giá trị của type vào câu lệnh SQL

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
    public boolean insertStock(int product_id,String product_name,int quantity_stock ) throws SQLException {
        String sql = "insert into stock(product_id,product_name,quantity_stock) values(?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, product_id);
            pre.setString(2, product_name);
            pre.setInt(3, quantity_stock);


            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStock(int product_id,String product_name,int quantity_stock , int id) throws SQLException {
        String sql = "UPDATE stock SET product_id  = ?,product_name=?, quantity_stock=? WHERE id = ?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, product_id);
            pre.setString(2, product_name);
            pre.setInt(3, quantity_stock);
            pre.setInt(4, id);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStock(int uid) throws SQLException {
        String sql = "delete from stock where id = ?";
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
}
