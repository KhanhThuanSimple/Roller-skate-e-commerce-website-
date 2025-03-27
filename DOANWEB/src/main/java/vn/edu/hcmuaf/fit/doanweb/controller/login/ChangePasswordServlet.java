package vn.edu.hcmuaf.fit.doanweb.controller.login;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangePassword", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        User user = (User) request.getSession().getAttribute("auth");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        UserDao userDAO = new UserDao();
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPassword);
        System.out.println(user);


        String message;
        // Kiểm tra mật khẩu cũ và xác nhận mật khẩu mới
        if (user != null && user.getPassword().equals(oldPassword) && newPassword.equals(confirmPassword)) {
            System.out.println("doPost1");

            try {
            boolean rs = userDAO.updatePassword(user.getId(), newPassword, oldPassword); // Cập nhật mật khẩu mới
            if(rs) {
                message = "Đổi mật khẩu thành công.";
            }else {
                message = "Mật khẩu cũ không đúng hoặc không khớp mật khẩu mới.";
            }

            request.setAttribute("message", message);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            message = "Mật khẩu cũ không đúng hoặc không khớp mật khẩu mới.";
        }

        // Thêm thông báo vào request
        request.setAttribute("message", message);

        // Chuyển tiếp về trang hiện tại
        request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/doimatkhau.jsp").forward(request, response);

    }
}


