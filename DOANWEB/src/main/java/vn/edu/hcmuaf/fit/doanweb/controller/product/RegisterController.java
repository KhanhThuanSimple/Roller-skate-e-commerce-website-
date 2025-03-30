package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();

        try {
            String name = request.getParameter("name");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            // Kiểm tra mật khẩu có khớp không
            if (!pass.equals(repass)) {
                request.setAttribute("errorMessage", "Mật khẩu không khớp!");
                request.getRequestDispatcher("register.jsp").forward(request, response);

            } else {
                User user = authService.findByUsername(uname);
                if (user == null) {
                    boolean rs = authService.insert(name, uname, pass,address,phone, 0);
                    if(rs){
                        response.sendRedirect(request.getContextPath() + "/login");

                    }else{
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }

                } else {
                    response.sendRedirect("register.jsp");
                }

            }




        }catch (Exception e) {}
    }
}