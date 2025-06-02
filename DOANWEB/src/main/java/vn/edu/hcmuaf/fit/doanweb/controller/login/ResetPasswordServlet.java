package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientIP = request.getRemoteAddr();

        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (token == null || token.trim().isEmpty()) {
            Log.warn("UNKNOWN", "RESET_PASSWORD_ATTEMPT", "Token không hợp lệ hoặc trống.", clientIP);
            request.setAttribute("error", "Token không hợp lệ.");
            request.getRequestDispatcher("/forgot-password-new.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Log.warn("UNKNOWN", "RESET_PASSWORD_ATTEMPT", "Mật khẩu nhập lại không khớp.", clientIP);
            request.setAttribute("error", "Mật khẩu nhập lại không khớp.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/forgot-password-new.jsp").forward(request, response);
            return;
        }

        try {
            User user = userDao.findByResetToken(token);
            if (user == null) {
                Log.warn("UNKNOWN", "RESET_PASSWORD_ATTEMPT", "Token không hợp lệ hoặc hết hạn: " + token, clientIP);
                request.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
                request.getRequestDispatcher("/forgot-password-new.jsp").forward(request, response);
                return;
            }

            String username = user.getUsername();

            boolean updated = userDao.updatePasswordById(user.getId(), newPassword);
            if (!updated) {
                Log.warn(username, "RESET_PASSWORD_FAIL", "Không thể cập nhật mật khẩu.", clientIP);
                request.setAttribute("error", "Không thể cập nhật mật khẩu. Vui lòng thử lại.");
                request.setAttribute("token", token);
                request.getRequestDispatcher("/forgot-password-new.jsp").forward(request, response);
                return;
            }

            // Xóa token reset sau khi đổi mật khẩu thành công
            userDao.savePasswordResetToken(user.getId(), null, null);

            Log.info(username, "RESET_PASSWORD_SUCCESS", "Đổi mật khẩu thành công.", clientIP);

        } catch (SQLException e) {
            Log.error("UNKNOWN", "RESET_PASSWORD_ERROR", "Lỗi hệ thống khi đổi mật khẩu: " + e.getMessage(), clientIP, e);
            throw new RuntimeException(e);
        }

        response.sendRedirect("login.jsp?resetSuccess=true");
    }
}
