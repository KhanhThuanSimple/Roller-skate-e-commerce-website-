package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Rights;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.RightsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminUserListController" ,value = "/admin/user")
public class AdminUserListController extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService();
        RightsService rightsService = new RightsService();

        int page = 1;
        String page_prams= request.getParameter("page");
        try {
            int totalPage = authService.getPage(1);

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage) page=totalPage;

            List<User>  users = authService.getList(page, 1);

            ArrayList<Rights> rights = rightsService.getListRights(1);
            request.setAttribute("rights", rights);
            request.setAttribute("users", users);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
