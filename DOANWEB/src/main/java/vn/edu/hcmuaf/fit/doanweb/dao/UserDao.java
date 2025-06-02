package vn.edu.hcmuaf.fit.doanweb.dao;

import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.fit.doanweb.utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class UserDao {

    public int limit = 10;

    // ĐĂNG NHẬP
    public User login1(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            hashedPassword,
                            rs.getString("name"),
                            rs.getInt("type")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User findUserByUserName(String username) throws SQLException {
        String sql = "select * from user where username = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("username"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getList(int page, int type) throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user WHERE type = ? ORDER BY id LIMIT ?, ?";
            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, type);
            pstmt.setInt(2, (page - 1) * this.limit);
            pstmt.setInt(3, this.limit);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }
            pstmt.close();
            st.close();
            rs.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPage(int type) throws SQLException {
        Statement st = DBConnect.getStatement();
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) AS total_pages FROM user WHERE type = ?";
        try {
            PreparedStatement pstmt = st.getConnection().prepareStatement(sql);
            pstmt.setInt(1, this.limit);
            pstmt.setInt(2, type);
            rs = pstmt.executeQuery();
            int totalPages = 0;
            if (rs.next()) {
                totalPages = rs.getInt("total_pages");
            }
            pstmt.close();
            st.close();
            rs.close();
            return totalPages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ĐĂNG KÝ
    public boolean insertUser(String name, String email, String pass, String address, String phone, int type) throws SQLException {
        String sql = "insert into user(username,password,name,phone_number,address, type) values(?,?,?,?,?,?)";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);

            // Băm mật khẩu bằng BCrypt
            String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());

            pre.setString(1, email);
            pre.setString(2, hashedPassword);
            pre.setString(3, name);
            pre.setString(4, phone);
            pre.setString(5, address);
            pre.setInt(6, type);

            int rs = pre.executeUpdate();
            return rs == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(String name, String email, String address, String phone, int type, int id) throws SQLException {
        String sql = "UPDATE user SET username = ?, name = ?, phone_number = ?, address = ?, type = ? WHERE id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, name);
            pre.setString(3, phone);
            pre.setString(4, address);
            pre.setInt(5, type);
            pre.setInt(6, id);
            int rs = pre.executeUpdate();
            return rs == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(int uid) throws SQLException {
        String sql = "delete from user where id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, uid);
            int rs = pre.executeUpdate();
            return rs == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User findByResetToken(String token) throws SQLException {
        String sql = "SELECT * FROM user WHERE reset_token = ? AND token_expiry > NOW()";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setType(rs.getInt("type"));
                    user.setPhone(rs.getString("phone_number"));
                    user.setAddress(rs.getString("address"));
                    user.setEmail(rs.getString("username"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public User getUserByID(int id) throws SQLException {
        String sql = "select * from user where id=?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updatePassword(int id, String newPassword, String oldPassword) throws SQLException {
        String getSql = "SELECT password FROM user WHERE id = ?";
        String updateSql = "UPDATE user SET password = ? WHERE id = ?";

        try (Connection conn = DBConnect.getConn()) {
            // Lấy mật khẩu đã hash từ DB
            try (PreparedStatement getStmt = conn.prepareStatement(getSql)) {
                getStmt.setInt(1, id);
                try (ResultSet rs = getStmt.executeQuery()) {
                    if (rs.next()) {
                        String currentHashed = rs.getString("password");
                        if (!BCrypt.checkpw(oldPassword, currentHashed)) {
                            return false; // Mật khẩu cũ không đúng
                        }
                    } else {
                        return false; // User không tồn tại
                    }
                }
            }

            // Băm mật khẩu mới rồi cập nhật
            String hashedNew = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, hashedNew);
                updateStmt.setInt(2, id);
                return updateStmt.executeUpdate() == 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật mật khẩu", e);
        }
    }
    public boolean updatePasswordById(int id, String newPassword) throws SQLException {
        String updateSql = "UPDATE user SET password = ? WHERE id = ?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            String hashedNew = PasswordUtil.hashPassword(newPassword);

            updateStmt.setString(1, hashedNew);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() == 1;
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setPhone(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("username"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void savePasswordResetToken(int userId, String token, Timestamp expiryTime) throws SQLException {
        String sql = "UPDATE user SET reset_token = ?, token_expiry = ? WHERE id = ?";
        try {
            Statement st = DBConnect.getStatement();
            PreparedStatement pre = st.getConnection().prepareStatement(sql);
            pre.setString(1, token);
            pre.setTimestamp(2, expiryTime);
            pre.setInt(3, userId);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE username=?";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("name"),
                            rs.getInt("type"),
                            rs.getString("phone_number"),
                            rs.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User saveGoogleUserIfNotExists(String email, String name) {
        User user = getUserByEmail(email);
        if (user != null) return user;

        String sql = "INSERT INTO user(username,password,name,phone_number,address,type) VALUES(?,'',?, '', '', 0)";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, email);
            ps.setString(2, name);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return new User(keys.getInt(1), email, name, 0, "", "");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScreenPermissions getPerUserScreen(int user_id, String code) throws SQLException {
        String sql = "SELECT sp.* FROM user u JOIN screen_permissions sp ON u.idPer = sp.idRights JOIN screen s ON sp.idScreen = s.id WHERE u.id = ? AND s.code = ?";
        Statement st = DBConnect.getStatement();
        PreparedStatement pre = st.getConnection().prepareStatement(sql);
        pre.setInt(1, user_id);
        pre.setString(2, code);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            ScreenPermissions screenPermissions = new ScreenPermissions();
            screenPermissions.setId(rs.getInt("id"));
            screenPermissions.setIdRights(rs.getInt("idRights"));
            screenPermissions.setIdScreen(rs.getInt("idScreen"));
            screenPermissions.setRead(rs.getInt("read"));
            screenPermissions.setAdd(rs.getInt("add"));
            screenPermissions.setDelete(rs.getInt("delete"));
            screenPermissions.setEdit(rs.getInt("edit"));
            return screenPermissions;
        }
        return null;
    }

    public User findUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setType(rs.getInt("type"));
                    // Gán các trường còn lại nếu có
                    return user;
                }
            }
        }
        return null; // không tìm thấy user
    }

    private Connection getConnection() throws SQLException {
        return DBConnect.getConn();
    }


}
