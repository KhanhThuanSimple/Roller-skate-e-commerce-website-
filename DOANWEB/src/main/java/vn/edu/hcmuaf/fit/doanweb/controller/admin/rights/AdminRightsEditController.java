package vn.edu.hcmuaf.fit.doanweb.controller.admin.rights;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminRightsEditController", value = "/admin/rights/edit")
public class AdminRightsEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RightsService rightsService = new RightsService();

        try{
            int id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("name");


            boolean rs =rightsService.updateRights(name,id);
            System.out.println("KQs");

            System.out.println(rs);
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/rights");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/rights");
        }
    }
}

