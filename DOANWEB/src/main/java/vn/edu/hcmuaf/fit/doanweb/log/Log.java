package vn.edu.hcmuaf.fit.doanweb.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    /**
     * Ghi log vào cơ sở dữ liệu.
     */
    private static void writeToDB(String username, String action, String ipAddress) {
        // Giới hạn độ dài để tránh lỗi
        username = username != null ? username.substring(0, Math.min(username.length(), 100)) : "Guest";
        action = action != null ? action.substring(0, Math.min(action.length(), 50)) : "UNKNOWN";
        ipAddress = ipAddress != null ? ipAddress.substring(0, Math.min(ipAddress.length(), 45)) : "UNKNOWN";

        String sql = "INSERT INTO logs (username, action, ip_address, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, action);
            ps.setString(3, ipAddress);
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to write log to database: " + e.getMessage(), e);
        }
    }

    /**
     * Ghi log mức INFO.
     */
    public static void info(String username, String action, String message, String ipAddress) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.info(logMessage);
        writeToDB(username, action, ipAddress);
    }

    /**
     * Ghi log mức WARN.
     */
    public static void warn(String username, String action, String message, String ipAddress) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.warn(logMessage);
        writeToDB(username, action, ipAddress);
    }

    /**
     * Ghi log mức ERROR.
     */
    public static void error(String username, String action, String message, String ipAddress) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.error(logMessage);
        writeToDB(username, action, ipAddress);
    }

    /**
     * Ghi log mức ERROR với Exception.
     */
    public static void error(String username, String action, String message, String ipAddress, Exception e) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.error(logMessage, e);
        writeToDB(username, action, ipAddress);
    }

    /**
     * Ghi log mức DEBUG.
     */
    public static void debug(String username, String action, String message, String ipAddress) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.debug(logMessage);
        writeToDB(username, action, ipAddress);
    }

    /**
     * Ghi log mức FATAL.
     */
    public static void fatal(String username, String action, String message, String ipAddress) {
        String logMessage = String.format("[%s] %s: %s", username != null ? username : "Guest", action, message);
        logger.fatal(logMessage);
        writeToDB(username, action, ipAddress);
    }
    public static void main(String[] args) {
        info("testuser", "TEST_ACTION", "Test log message", "127.0.0.1");
        System.out.println("Test completed. Check logs/app.log and database.");
    }
}