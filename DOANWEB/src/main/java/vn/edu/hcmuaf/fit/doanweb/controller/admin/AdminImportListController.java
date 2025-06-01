package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ImportOrders;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminImportListController", value = "/admin/import")
public class AdminImportListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        System.out.println("Nhannnnnn nè");
        int page = 1;
        String page_prams= request.getParameter("page");
        try {
            int totalPage = authService.getPageImport();

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage && totalPage>0) page=totalPage;

            List<ImportOrders> imports = authService.getListImport(page);
System.out.println(imports);
            request.setAttribute("imports", imports);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/admin/nhaphang.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

