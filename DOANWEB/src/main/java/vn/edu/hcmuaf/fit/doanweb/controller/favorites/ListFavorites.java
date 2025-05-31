package vn.edu.hcmuaf.fit.doanweb.controller.favorites;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.controller.product.BaseServlet;
import vn.edu.hcmuaf.fit.doanweb.dao.favorite.FavoriteDAO;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListFavorites", value = "/listFavorites")
public class ListFavorites extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavoriteDAO favoriteDAO = new FavoriteDAO();
        loadCommonData(request);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<Product> favorites = favoriteDAO.getUserFavorites(user.getId());
        request.setAttribute("favorites", favorites);
        request.getRequestDispatcher("Favorite.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavoriteDAO favoriteDAO = new FavoriteDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String clientIP = request.getRemoteAddr();
        String username = user.getUsername(); // Giả định User có getUsername()

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            favoriteDAO.removeFavorite(user.getId(), productId);
            Log.info(username, "REMOVE_FAVORITE", "Removed product ID " + productId + " from favorites", clientIP);
        } catch (NumberFormatException e) {
            Log.error(username, "REMOVE_FAVORITE", "Invalid productId parameter: " + request.getParameter("productId"), clientIP, e);
        }

        // Chuyển hướng về trang danh sách yêu thích để tải lại dữ liệu
        response.sendRedirect("listFavorites");
    }
}