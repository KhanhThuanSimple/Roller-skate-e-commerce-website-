package vn.edu.hcmuaf.fit.doanweb.controller.admin.rights;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminRightsDeleteController", value = "/admin/rights/delete")
public class AdminRightsDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RightsService rightsService = new RightsService();
        int uid= Integer.parseInt(request.getParameter("uid"));

        try {
            boolean rs = rightsService.deleteRights(uid);
            if(rs) {
                request.setAttribute("message", "Xóa thành công!");
            }else{
                request.setAttribute("message", "Xóa không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/rights");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }



    }

}

