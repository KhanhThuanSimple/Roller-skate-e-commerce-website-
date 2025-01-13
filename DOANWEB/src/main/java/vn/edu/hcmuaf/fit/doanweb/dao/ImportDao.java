package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ExportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;

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
            String sql = "SELECT * FROM import_orders  ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);

            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit


            rs = pstmt.executeQuery();

            while (rs.next()) {
                ImportOrders importOrders= new ImportOrders();
                importOrders.setId(rs.getInt("id"));
                importOrders.setProduct_id(rs.getInt("product_id"));
                importOrders.setProduct_name(rs.getString("product_name"));
                importOrders.setImage(rs.getString("image"));
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
}
