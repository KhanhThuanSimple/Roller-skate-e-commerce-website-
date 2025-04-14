package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.login.GoogleAccount;  // Đảm bảo import đúng GoogleAccount từ dao.login
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect("home.jsp"); // Nếu không có code từ Google
            return;
        }

        try {
            // Lấy access token từ code
            String accessToken = GoogleLogin.getToken(code);
            // Lấy thông tin người dùng từ Google
            GoogleAccount acc = GoogleLogin.getUserInfo(accessToken);
            System.out.println("Google Info: " + acc);

            // Lưu thông tin người dùng vào cơ sở dữ liệu
            UserDao dao = new UserDao();
            User user = dao.getOrInsertGoogleUser(acc);

            // Lưu vào session
            HttpSession session = request.getSession();
            session.setAttribute("auth", user);

            // Chuyển hướng đến trang home.jsp
            response.sendRedirect("home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
