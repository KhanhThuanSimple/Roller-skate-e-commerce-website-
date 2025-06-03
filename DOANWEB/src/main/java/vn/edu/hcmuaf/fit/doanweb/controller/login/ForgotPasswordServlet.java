
package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.controller.login.EmailUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        try {
            UserDao userDao = new UserDao();
            User user = userDao.findByEmail(email);

            if (user == null) {
                request.setAttribute("error", "Email không tồn tại trong hệ thống.");
                request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
                return;
            }

            // Tạo token và thời gian hết hạn
            String token = UUID.randomUUID().toString();
            Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + 15 * 60 * 1000); // 15 phút

            // Lưu token vào DB
            userDao.savePasswordResetToken(user.getId(), token, expiryTime);

            // Gửi email
            String resetLink = request.getRequestURL().toString().replace("forgot-password", "forgot-password-new.jsp") + "?token=" + token;
            String subject = "Yêu cầu đặt lại mật khẩu";
            String content = "Xin chào " + user.getUsername() + ",\n\n"
                    + "Bạn đã yêu cầu đặt lại mật khẩu. Nhấp vào liên kết sau để thực hiện:\n"
                    + resetLink + "\n\n"
                    + "Lưu ý: Liên kết sẽ hết hạn sau 15 phút.";

            EmailUtil.sendEmail(email, subject, content);

            request.setAttribute("message", "Email khôi phục đã được gửi. Vui lòng kiểm tra hộp thư của bạn.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi gửi email. Vui lòng thử lại sau.");
        }

        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }
}
