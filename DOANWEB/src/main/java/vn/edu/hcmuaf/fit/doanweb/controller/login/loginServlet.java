package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleAccount;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleLogin;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        String clientIP = request.getRemoteAddr();

        try {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            GoogleAccount acc = gg.getUserInfo(accessToken);

            if (acc != null) {
                Log.info(acc.getEmail(), "LOGIN_GOOGLE", "Đăng nhập Google thành công", clientIP);
                // Xử lý session nếu cần
            } else {
                Log.warn("Guest", "LOGIN_GOOGLE", "Đăng nhập Google thất bại: tài khoản null", clientIP);
            }

            System.out.println(acc);
        } catch (Exception e) {
            Log.error("Guest", "LOGIN_GOOGLE", "Lỗi khi đăng nhập Google", clientIP, e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}