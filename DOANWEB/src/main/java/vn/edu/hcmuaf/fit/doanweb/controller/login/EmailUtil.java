package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailUtil {
    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());

    // Thông tin SMTP - nên chuyển ra file cấu hình sau này
    private static final String SMTP_USERNAME = "phumaihoang45@gmail.com";
    private static final String SMTP_PASSWORD = "zmiy yvko tpqn jleb";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final int TIMEOUT_MS = 10000; // 10 giây timeout

    public static boolean sendEmail(String to, String subject, String content) {
        // Kiểm tra email hợp lệ
        if (to == null || to.isBlank() || !to.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            logger.warning("Email không hợp lệ: " + to);
            return false;
        }

        try {
            // Cấu hình SMTP
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.connectiontimeout", TIMEOUT_MS);
            props.put("mail.smtp.timeout", TIMEOUT_MS);
            props.put("mail.smtp.writetimeout", TIMEOUT_MS);

            // Tạo session
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
                }
            });

            // Tạo message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);
            logger.info("Gửi email thành công đến: " + to);
            return true;

        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Lỗi khi gửi email đến " + to + ": " + e.getMessage(), e);
            return false;
        }
    }
}