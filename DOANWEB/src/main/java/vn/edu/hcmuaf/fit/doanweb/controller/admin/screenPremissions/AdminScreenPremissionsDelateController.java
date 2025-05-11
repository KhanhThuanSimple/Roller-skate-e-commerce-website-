package vn.edu.hcmuaf.fit.doanweb.controller.admin.screenPremissions;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenPremissionsDao;
import vn.edu.hcmuaf.fit.doanweb.service.ScreenService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminScreenPremissionsDelateController", value = "/admin/screenPremissions/delete")
public class AdminScreenPremissionsDelateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ScreenPremissionsDao screenPremissionsDao = new ScreenPremissionsDao();
        int uid= Integer.parseInt(request.getParameter("uid"));

        try {
            boolean rs = screenPremissionsDao.delete(uid);
            if(rs) {
                request.setAttribute("message", "Xóa thành công!");
            }else{
                request.setAttribute("message", "Xóa không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/screenPremissions");


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } }
}

