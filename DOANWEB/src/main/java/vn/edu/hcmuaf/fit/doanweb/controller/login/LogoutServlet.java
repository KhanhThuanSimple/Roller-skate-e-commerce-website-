package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý tương tự doGet hoặc bỏ đi
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        String username = "Guest";

        if (session != null) {
            Object auth = session.getAttribute("auth");
            if (auth != null) {
                // Giả sử auth là đối tượng User có phương thức getUsername()
                if (auth instanceof vn.edu.hcmuaf.fit.doanweb.dao.model.User) {
                    username = ((vn.edu.hcmuaf.fit.doanweb.dao.model.User) auth).getUsername();
                } else {
                    username = auth.toString();
                }
            }
            Log.info(username, "LOGOUT", "Người dùng đã đăng xuất", clientIP);
            session.invalidate();
        } else {
            Log.info("Guest", "LOGOUT", "Không có session để đăng xuất", clientIP);
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
