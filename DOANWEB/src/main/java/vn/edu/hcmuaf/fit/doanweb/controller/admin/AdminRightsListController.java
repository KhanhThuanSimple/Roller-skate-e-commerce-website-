package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Rights;

import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminRightsListController", value = "/admin/rights")
public class AdminRightsListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RightsService rightsService = new RightsService();
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        int page = 1;
        String page_prams = request.getParameter("page");
        System.out.println("page_prams" + page_prams);
        try {
            int totalPage = rightsService.getPageRights();

            if (page_prams != null) {
                page = Integer.parseInt(page_prams);
            }
            if (page < 1) page = 1;
            if (page > totalPage) page = totalPage;

            ArrayList<Rights> rightss = rightsService.getListRights(page);
            ScreenPermissions permission = userDao.getPerUserScreen(user.id, "quyen");
            if(permission==null || permission.read!=1) {
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
            }
            request.getSession().setAttribute("permission", permission);


            request.setAttribute("rightss", rightss);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);

            System.out.println(rightss);


            request.getRequestDispatcher("/admin/rights.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

