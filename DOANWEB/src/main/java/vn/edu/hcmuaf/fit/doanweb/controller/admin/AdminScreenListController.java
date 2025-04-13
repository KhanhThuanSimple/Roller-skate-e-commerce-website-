package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Screen;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.ScreenService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminScreenListController", value = "/admin/screen")
public class AdminScreenListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScreenService screenService = new ScreenService();
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        int page = 1;
        String page_prams= request.getParameter("page");
        System.out.println("page_prams"+page_prams);
        try {
            int totalPage = screenService.getPageScreen();

            if(page_prams!=null){
                page = Integer.parseInt(page_prams);
            }
            if(page<1) page=1;
            if(page>totalPage) page=totalPage;

            List<Screen> screens = screenService.getListScreen(page);
            ScreenPermissions permission = userDao.getPerUserScreen(user.id, "mh");
            if(permission==null || permission.read!=1) {
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
            }
            request.getSession().setAttribute("permission", permission);

            request.setAttribute("screens", screens);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);

            System.out.println(screens);


            request.getRequestDispatcher("/admin/screen.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

