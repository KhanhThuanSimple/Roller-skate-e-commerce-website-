package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleAccount;
import vn.edu.hcmuaf.fit.doanweb.controller.login.GoogleLogin;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import vn.edu.hcmuaf.fit.doanweb.dao.UserDao ;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        GoogleLogin gg = new GoogleLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);
        System.out.println(acc);

        // Lấy thông tin từ GoogleAccount
        String email = acc.getEmail();
        String name = acc.getName();
        String googleId = acc.getId();
        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        // Nếu chưa có người dùng, thêm mới vào DB
        if (user == null) {
            // Tạo user mới với thông tin từ Google
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setGoogleId(googleId); // lấy từ token Google
            user.setUsername(email); // Dùng email làm username
            user.setPassword(""); // Không có mật khẩu
            user.setAddress("");
            user.setPhone("");
            user.setType(0); // User thường

            userDao.insertUser(user); // Thêm user vào DB

            // Lấy lại user từ DB (để lấy ID và thông tin đầy đủ)
            user = userDao.getUserByEmail(email);
        }

        // Tạo session đăng nhập
        // Tạo session đăng nhập
        HttpSession session = request.getSession();
        session.setAttribute("auth", user); // ✅ Lưu user vào session

// Chuyển hướng về trang chính
        response.sendRedirect("canhan.jsp");

    }
}

