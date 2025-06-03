package vn.edu.hcmuaf.fit.doanweb.controller.admin.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminCustomerEditController" ,value = "/admin/customer/update")
public class AdminCustomerEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService();

        try{
//            String name=request.getParameter("name");
//            String username=request.getParameter("username");
//            String phone=request.getParameter("phone");
//            String address=request.getParameter("address");
//            int id=Integer.parseInt(request.getParameter("id"));
//
//            boolean rs = authService.update(name, username, address, phone, 0, id);
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int id = Integer.parseInt(request.getParameter("id"));
            int type = 0;  // ví dụ mặc định nếu bạn không lấy từ form
            int role = 0;  // cũng có thể lấy từ request nếu có

            boolean rs = authService.update(name, username, address, phone, type, id, role);

            System.out.println("KQs");

            System.out.println(rs);
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/customer");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/customer");
        }
    }
}
