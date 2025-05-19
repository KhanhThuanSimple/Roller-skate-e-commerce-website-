package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleAccount;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleLogin;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung

        List<Product> productNew = productDao.getAllProductnew();
        request.setAttribute("productNew", productNew);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String code = request.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);

        // Lấy thông tin từ GoogleAccount
        String email = acc.getEmail();
        String name = acc.getName();
        String picture = acc.getPicture();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        // Nếu chưa có người dùng, thêm mới vào DB
        if (user == null) {
            // Tạo user mới với thông tin từ Google
            user = new User(email, name, picture);

            // Thêm user vào DB (mặc định role = 1)
            userDao.insertUser(name, email, "", "", "", 0, 1);

            // Lấy lại user từ DB nếu cần ID
            user = userDao.getUserByEmail(email);
        }

        // Tạo session đăng nhập
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Chuyển hướng về trang chính
        response.sendRedirect("home.jsp");
    }
}
