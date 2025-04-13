package vn.edu.hcmuaf.fit.doanweb.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    // Khởi tạo instance của Logger
    private static final Logger logger = LogManager.getLogger(Log.class);

    // Phương thức để ghi log mức INFO
    public static void info(String message) {
        logger.info(message);
    }

    // Phương thức để ghi log mức WARN
    public static void warn(String message) {
        logger.warn(message);
    }

    // Phương thức để ghi log mức ERROR
    public static void error(String message) {
        logger.error(message);
    }

    // Phương thức để ghi log mức DEBUG
    public static void debug(String message) {
        logger.debug(message);
    }

    // Phương thức để ghi log mức FATAL
    public static void fatal(String message) {
        logger.fatal(message);
    }
}