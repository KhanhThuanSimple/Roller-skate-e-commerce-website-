package vn.edu.hcmuaf.fit.doanweb.controller.cart;

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

@WebServlet(name = "Add", value = "/add-cart")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("auth");
        String username = (userObj != null) ? userObj.toString() : "Guest";

        String productId = request.getParameter("pid");
        Log.info(username, "ADD_TO_CART", "Yêu cầu thêm sản phẩm ID: " + productId, clientIP);

        ProductDao dao = new ProductDao();
        Product product = dao.getAllProductId(productId);

        if (product == null) {
            Log.warn(username, "ADD_TO_CART", "Sản phẩm ID: " + productId + " không tồn tại", clientIP);
            response.sendRedirect("?addCart=false");
            return;
        }

        CartP cart = (CartP) session.getAttribute("cart");
        if (cart == null) cart = new CartP();

        cart.addProduct(product);
        session.setAttribute("cart", cart);

        Log.info(username, "ADD_TO_CART", String.format("Đã thêm sản phẩm '%s' (ID: %s) vào giỏ hàng", product.getName(), product.getId()), clientIP);

        String referer = request.getHeader("Referer");
        if (referer == null || referer.isEmpty()) {
            referer = "product";
        }
        response.sendRedirect(referer + "?addCart=ok");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/product");
    }
}