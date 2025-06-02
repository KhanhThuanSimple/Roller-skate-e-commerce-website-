package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangePassword", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        String clientIP = request.getRemoteAddr();

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserDao userDAO = new UserDao();
        User user = null;
        try {
            user = userDAO.getUserByID(userId);
        } catch (SQLException e) {
            Log.error("userId=" + userId, "CHANGE_PASSWORD", "Lỗi truy vấn user: " + e.getMessage(), clientIP, e);
            e.printStackTrace();
        }

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = user.getUsername(); // giả sử User có phương thức getUsername()
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (oldPassword == null || newPassword == null || confirmPassword == null) {
            Log.warn(username, "CHANGE_PASSWORD_ATTEMPT", "Thiếu thông tin đầu vào.", clientIP);
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Log.warn(username, "CHANGE_PASSWORD_ATTEMPT", "Mật khẩu mới và xác nhận không khớp.", clientIP);
            request.setAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
            return;
        }

        try {
            boolean isUpdated = userDAO.updatePassword(user.getId(), newPassword, oldPassword);
            if (isUpdated) {
                Log.info(username, "CHANGE_PASSWORD_SUCCESS", "Đổi mật khẩu thành công.", clientIP);
                request.setAttribute("message", "Đổi mật khẩu thành công!");
            } else {
                Log.warn(username, "CHANGE_PASSWORD_FAIL", "Mật khẩu cũ không đúng hoặc lỗi cập nhật.", clientIP);
                request.setAttribute("error", "Mật khẩu cũ không đúng hoặc lỗi cập nhật.");
            }
        } catch (SQLException e) {
            Log.error(username, "CHANGE_PASSWORD", "Lỗi hệ thống khi đổi mật khẩu: " + e.getMessage(), clientIP, e);
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau.");
        }

        request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
    }
}
