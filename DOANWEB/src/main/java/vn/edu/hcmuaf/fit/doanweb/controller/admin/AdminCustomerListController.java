package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminCustomerListController" ,value = "/admin/customer")
public class AdminCustomerListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        int page = 1;
        String page_prams= request.getParameter("page");
        try {
            int totalPage = authService.getPage(0);

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage) page=totalPage;

            List<User>  customers = authService.getList(page, 0);
            ScreenPermissions permission = userDao.getPerUserScreen(user.getId(), "kh");

            if(permission==null || permission.read!=1) {
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
            }
            request.getSession().setAttribute("permission", permission);


            request.setAttribute("customers", customers);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);



            request.getRequestDispatcher("/admin/customer.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
