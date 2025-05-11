package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String to, String subject, String content) {
        try {
            final String fromEmail = "phumaihoang45@gmail.com";
            final String password = "zmiy yvko tpqn jleb";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("✅ Gửi email thành công đến: " + to);
        } catch (MessagingException e) {
            System.out.println("❌ Gửi email thất bại: " + e.getMessage());
            e.printStackTrace(); // Quan trọng để xem lỗi gì
        }
    }
}