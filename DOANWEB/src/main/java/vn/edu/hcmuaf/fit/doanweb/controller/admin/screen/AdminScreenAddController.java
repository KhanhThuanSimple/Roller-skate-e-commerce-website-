package vn.edu.hcmuaf.fit.doanweb.controller.admin.screen;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.ScreenService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminScreenAddController", value = "/admin/screen/add")
public class AdminScreenAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScreenService screenService = new ScreenService();

        try{
            String code=request.getParameter("code");
            String name=request.getParameter("name");
         ;
            boolean rs =screenService.insertScreen(code,name);
            if(rs) {
                request.setAttribute("message", "Thêm thành công!");
            }else{
                request.setAttribute("message", "Thêm không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/screen");
        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/screen");

        }




    }

}

