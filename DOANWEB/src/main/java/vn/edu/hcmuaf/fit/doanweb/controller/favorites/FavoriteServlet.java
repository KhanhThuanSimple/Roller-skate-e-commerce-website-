package vn.edu.hcmuaf.fit.doanweb.controller.favorites;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.favorite.FavoriteDAO;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebServlet(name = "FavoriteServlet", value = "/favorite")
public class FavoriteServlet extends HttpServlet {
    private FavoriteDAO favoriteDao;
    public void init() {
        favoriteDao = new FavoriteDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            session.setAttribute("message", "Vui lòng đăng nhập để sử dụng tính năng này");
            response.sendRedirect("login");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");

        // Kiểm tra trạng thái yêu thích hiện tại
        if (favoriteDao.isFavorited(user.getId(), productId)) {
            // Nếu đã yêu thích, thì xóa yêu thích
            favoriteDao.removeFavorite(user.getId(), productId);
            session.setAttribute("message", "Đã xóa khỏi danh sách yêu thích");
        } else {
            // Nếu chưa yêu thích, thì thêm yêu thích
            favoriteDao.addFavorite(user.getId(), productId);
            session.setAttribute("message", "Đã thêm vào danh sách yêu thích");
        }

        response.sendRedirect(request.getHeader("referer"));
    }
}


