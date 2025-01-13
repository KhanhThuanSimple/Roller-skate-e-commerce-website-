package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ExportOrders;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminExportListController", value = "/admin/export")
public class AdminExportListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService();
        int page = 1;
        String page_prams= request.getParameter("page");
        try {
            int totalPage = authService.getPageExport();

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage) page=totalPage;

            List<ExportOrders> exports = authService.getListExport(page);

            request.setAttribute("exports", exports);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/admin/xuathang.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}

