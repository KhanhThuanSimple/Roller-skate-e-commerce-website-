package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenPremissionsDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ScreenPremissions", value = "/admin/screen-premissions")
public class AdminScreenPremissionsListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScreenPremissionsDao screenPremissionsDao = new ScreenPremissionsDao();
        int page = 1;
        String page_prams= request.getParameter("page");
        System.out.println("page_prams"+page_prams);
        try {
            int totalPage = screenPremissionsDao.getPage();

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage) page=totalPage;

            List<ScreenPermissions> screenPermissionss = screenPremissionsDao.getList(page);

            request.setAttribute("screenPremissionss", screenPermissionss);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);

            System.out.println(screenPermissionss);


            request.getRequestDispatcher("/admin/screenPremissions.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

