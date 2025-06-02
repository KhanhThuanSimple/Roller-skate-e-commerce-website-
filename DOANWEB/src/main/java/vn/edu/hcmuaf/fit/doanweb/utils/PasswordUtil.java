package vn.edu.hcmuaf.fit.doanweb.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Kiểm tra mật khẩu: hỗ trợ cả plaintext và bcrypt hash
    public static boolean checkPassword(String rawPassword, String storedPassword) {
        if (storedPassword == null || rawPassword == null) return false;

        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
            try {
                return BCrypt.checkpw(rawPassword, storedPassword);
            } catch (IllegalArgumentException e) {
                // Tránh lỗi "Invalid salt version" hoặc lỗi khác
                return false;
            }
        }
        // So sánh trực tiếp nếu chưa băm
        return rawPassword.equals(storedPassword);
    }

    // Băm mật khẩu bằng BCrypt
    public static String hashPassword(String password) {
        if (password == null) throw new IllegalArgumentException("Password cannot be null");
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 rounds là thông thường, có thể điều chỉnh
    }
}
