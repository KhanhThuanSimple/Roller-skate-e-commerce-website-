package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.login.GoogleAccount;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String code = request.getParameter("code");
    GoogleLogin gg = new GoogleLogin();
    String accessToken= gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
    System.out.println(acc);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


