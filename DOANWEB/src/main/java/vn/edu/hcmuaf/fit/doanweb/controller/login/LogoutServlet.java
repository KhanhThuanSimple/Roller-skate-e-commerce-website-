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
        response.sendRedirect(request.getContextPath() + "/logout");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        String username = "Guest";

        if (session != null) {
            Object auth = session.getAttribute("auth");
            username = (auth != null) ? auth.toString() : "Guest";
            Log.info(username, "LOGOUT", "Người dùng đã đăng xuất", clientIP);
            session.invalidate();
        } else {
            Log.info("Guest", "LOGOUT", "Không có session để đăng xuất", clientIP);
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }
}