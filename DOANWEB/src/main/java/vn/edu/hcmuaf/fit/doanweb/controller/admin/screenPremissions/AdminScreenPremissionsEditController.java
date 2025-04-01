package vn.edu.hcmuaf.fit.doanweb.controller.admin.screenPremissions;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenPremissionsDao;
import vn.edu.hcmuaf.fit.doanweb.service.ScreenService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminScreenPremissionsController", value = "/admin/screenPremissions/edit")
public class AdminScreenPremissionsEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ScreenPremissionsDao screenPremissionsDao = new ScreenPremissionsDao();


        try{
            int idRights=Integer.parseInt(request.getParameter("idRights"));
            int idScreen= Integer.parseInt(request.getParameter("idScreen"));
            int read= Integer.parseInt(request.getParameter("read"));
            int add= Integer.parseInt(request.getParameter("add"));
            int delete= Integer.parseInt(request.getParameter("delete"));
            int edit= Integer.parseInt(request.getParameter("edit"));
            int id=Integer.parseInt(request.getParameter("id"));

            boolean rs =screenPremissionsDao.update(idRights,idScreen,read,add,delete,edit,id);
            System.out.println("KQs");

            System.out.println(rs);
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/screenPremissions");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/screenPremissions");
        } }
}

