package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.RightsDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ScreenPremissionsDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ScreenPremissions", value = "/admin/screenPremissions")
public class AdminScreenPremissionsListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        int id = Integer.parseInt(request.getParameter("pid"));

        RightsDao rightsDao = new RightsDao();

        try {
            List<ScreenPermissions> screenPermissionss = rightsDao.getListPer(id);

            request.setAttribute("screenPremissionss", screenPermissionss);
            request.setAttribute("pid", id);


            System.out.println(screenPermissionss);

            request.getRequestDispatcher("/admin/screenPremissions.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        int id = Integer.parseInt(request.getParameter("pid"));

        RightsDao rightsDao = new RightsDao();

        try {
            List<ScreenPermissions> screenPermissionss = rightsDao.getListPer(id);

            request.setAttribute("screenPremissionss", screenPermissionss);
            request.setAttribute("pid", id);

            System.out.println(screenPermissionss);

            request.getRequestDispatcher("/admin/screenPremissions.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

