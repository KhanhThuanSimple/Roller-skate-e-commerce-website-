package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ExportOrders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExportOrdersDao {
    private int limit=2;

    public ArrayList<ExportOrders> getListExport(int page) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<ExportOrders> exports = new ArrayList<>();
        try {
            String sql = "SELECT * FROM export_orders  ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
          //  pstmt.setInt(1, type); // Gán giá trị cho type
            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit


            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs);
                ExportOrders export= new ExportOrders();
                export.setId(rs.getInt("id"));
                export.setProductId(rs.getInt("product_id"));
                export.setProductName(rs.getString("product_name"));
                export.setImage(rs.getString("image"));
                export.setSalePrice(rs.getDouble("sale_price"));
                export.setQuantity(rs.getInt("quantity"));

                exports.add(export);
                System.out.println("Number of exports retrieved: " + exports.size());
            }
            pstmt.close();
            st.close();
            rs.close();
            return exports;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int getPageExport() throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM export_orders"; // Query tính số trang

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
}
