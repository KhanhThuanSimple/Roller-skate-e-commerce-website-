package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ScreenPremissionsDao {
    public int limit=10;
    public ArrayList<ScreenPermissions> getList(int page) throws SQLException {

        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<ScreenPermissions> screenPermissionss = new ArrayList<>();
        try {
            String sql = "SELECT * FROM screen_permissions  ORDER BY id LIMIT ?, ?";

            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            // Gán giá trị cho type
            pstmt.setInt(1, page-1);      // Gán giá trị cho offset
            pstmt.setInt(2, this.limit);       // Gán giá trị cho limit


            rs = pstmt.executeQuery();

            while (rs.next()) {
                ScreenPermissions screenPermissions = new ScreenPermissions();
                screenPermissions.setId(rs.getInt("id"));
                screenPermissions.setIdRights(rs.getInt("idRights"));
                screenPermissions.setIdScreen(rs.getInt("idScreen"));
                screenPermissions.setRead(rs.getInt("read"));
                screenPermissions.setAdd(rs.getInt("add"));
                screenPermissions.setDelete(rs.getInt("delete"));
                screenPermissions.setEdit(rs.getInt("edit"));



                screenPermissionss.add(screenPermissions);
            }
            pstmt.close();
            st.close();
            rs.close();
            return screenPermissionss;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getPage() throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM screen_permissions "; // Query tính số trang

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
    public boolean insert(int idRights, int idScreen, int read, int add, int delete, int edit) throws SQLException {

        String sql = "insert into screen_permissions(idRights,idScreen,`read`, `add`, `delete`, `edit`) values(?,?,?,?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, idRights);
            pre.setInt(2, idScreen);
            pre.setInt(3, read);
            pre.setInt(4, add);
            pre.setInt(5, delete);
            pre.setInt(6, edit);



            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update( int read, int add, int delete, int edit , int id) throws SQLException {
        String sql = "UPDATE screen_permissions SET `read`=?, `add`=?, `delete`=?, `edit`=? WHERE id=?";

        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, read);
            pre.setInt(2, add);
            pre.setInt(3, delete);
            pre.setInt(4, edit);
            pre.setInt(5, id);

            int rs = pre.executeUpdate();

            return rs==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "delete from screen_permissions where id = ?";
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
