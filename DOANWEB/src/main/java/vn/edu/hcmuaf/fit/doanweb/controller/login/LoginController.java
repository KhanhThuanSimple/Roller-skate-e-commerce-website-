package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        // Xác thực reCAPTCHA
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse, request.getRemoteAddr());
        if (!verify) {
            request.setAttribute("error", "Xác thực reCAPTCHA không thành công. Vui lòng thử lại.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService authService = new AuthService();
        try {
            User user = authService.findByUsername(uname);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", user);
                session.setAttribute("user", user.getId());

                if (user.getType() == 1) {
                    response.sendRedirect(request.getContextPath() + "/admin/user");
                } else {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else {
                request.setAttribute("error", "Đăng nhập không thành công");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
