package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Rights;

import vn.edu.hcmuaf.fit.doanweb.service.CategoryService;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;
import vn.edu.hcmuaf.fit.doanweb.service.ScreenService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminRightsListController", value = "/admin/rights")
public class AdminRightsListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RightsService rightsService = new RightsService();
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

