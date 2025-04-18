package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.ScreenPermissions;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;
import vn.edu.hcmuaf.fit.doanweb.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminProductListController" ,value = "/admin/product")
public class AdminProductListController extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        try {
            List<Product>  products = productService.getAll();
          System.out.println(1234);
            request.setAttribute("products", products);
            request.getRequestDispatcher("/admin/product.jsp").forward(request, response);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ScreenPermissions permission = null;
        try {
            permission = userDao.getPerUserScreen(user.id, "sanpham");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(permission==null || permission.read!=1) {
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
        }
        request.getSession().setAttribute("permission", permission);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
