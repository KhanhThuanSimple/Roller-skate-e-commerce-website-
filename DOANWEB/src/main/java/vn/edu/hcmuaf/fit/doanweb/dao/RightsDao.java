package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Rights;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RightsDao {
    public int limit=10;
    public ArrayList<Rights> getListRights(int page) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<Rights> rightss = new ArrayList<>();
        try {
            String sql = "SELECT * FROM rights  ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            // Gán giá trị cho type
            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit


            rs = pstmt.executeQuery();

            while (rs.next()) {
                Rights rights = new Rights();
                rights.setId(rs.getInt("id"));
                rights.setName(rs.getString("name"));


                rightss.add(rights);
            }
            pstmt.close();
            st.close();
            rs.close();
            return rightss;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getPageRights() throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM rights "; // Query tính số trang

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
    public boolean insertRights(int id, String name) throws SQLException {

        String sql = "insert into rights(id, name) values(?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, name);


            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateRights(String name, int id) throws SQLException {
        String sql = "UPDATE rights SET  name = ? WHERE id = ?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, name);
            pre.setInt(2, id);


            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteRights(int id) throws SQLException {
        String sql = "delete from rights where id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, id);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
