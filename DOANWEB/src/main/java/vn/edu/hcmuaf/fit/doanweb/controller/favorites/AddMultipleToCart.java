package vn.edu.hcmuaf.fit.doanweb.controller.favorites;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "AddMultipleToCart", value = "/add-multiple-to-cart")
public class AddMultipleToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();

        // Lấy IP và username cho log
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession();
        String username = "Guest";

        // Lấy username từ session
        Object auth = session.getAttribute("auth");
        if (auth != null) {
            username = (auth instanceof String) ? (String) auth : auth.toString();
        }

        // Lấy danh sách các ID sản phẩm được chọn
        String[] productIds = request.getParameterValues("productIds");

        if (productIds == null || productIds.length == 0) {
            // Nếu không có sản phẩm nào được chọn
            Log.warn(username, "ADD_TO_CART", "No products selected to add to cart from favorites", clientIP);
            response.sendRedirect("listFavorites?error=no_selection");
            return;
        }

        // Lấy giỏ hàng từ session
        CartP cart = (CartP) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartP();
        }

        // Thêm từng sản phẩm vào giỏ hàng
        for (String productId : productIds) {
            Product product = dao.getAllProductId(productId);
            if (product != null) {
                cart.addProduct(product);
                Log.info(username, "ADD_TO_CART", "Added product ID " + productId + " to cart from favorites", clientIP);
            } else {
                Log.warn(username, "ADD_TO_CART", "Product ID " + productId + " not found", clientIP);
            }
        }

        // Cập nhật giỏ hàng trong session
        session.setAttribute("cart", cart);
        String referer = request.getHeader("Referer");

        // Nếu không có referer (chẳng hạn khi người dùng trực tiếp truy cập trang), điều hướng đến trang sản phẩm
        if (referer == null || referer.isEmpty()) {
            referer = "listFavorites";
        }

        // Chuyển hướng đến trang giỏ hàng
        response.sendRedirect("ShowCart?addMultiple=success");
    }
}