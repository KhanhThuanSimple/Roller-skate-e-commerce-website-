package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "Remove", value = "/remove")
public class Remove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng GET sang POST để bảo vệ CSRF
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        String username = "Guest";

        // Lấy username từ session (giả định auth chứa username)
        if (session != null) {
            Object auth = session.getAttribute("auth");
            username = (auth instanceof String) ? (String) auth : "Guest";
        }

        int pId = -1;
        try {
            pId = Integer.parseInt(request.getParameter("pId"));
            Log.info(username, "REMOVE_PRODUCT", "Attempting to remove product with ID: " + pId, clientIP);
        } catch (NumberFormatException e) {
            Log.error(username, "REMOVE_PRODUCT", "Invalid product ID format: " + request.getParameter("pId"), clientIP, e);
            response.sendRedirect(request.getContextPath() + "/showCart");
            return;
        }

        if (session == null) {
            Log.warn(username, "REMOVE_PRODUCT", "No session found when trying to remove product with ID: " + pId, clientIP);
            response.sendRedirect(request.getContextPath() + "/showCart");
            return;
        }

        CartP cartP = (CartP) session.getAttribute("cart");
        if (cartP != null) {
            try {
                boolean removed = cartP.removeProduct(pId); // Giả định removeProduct trả về boolean
                if (removed) {
                    Log.info(username, "REMOVE_PRODUCT", "Product with ID: " + pId + " has been removed from the cart", clientIP);
                } else {
                    Log.warn(username, "REMOVE_PRODUCT", "Product with ID: " + pId + " not found in the cart", clientIP);
                }
                session.setAttribute("cart", cartP);
            } catch (Exception e) {
                Log.error(username, "REMOVE_PRODUCT", "Failed to remove product with ID: " + pId, clientIP, e);
            }
        } else {
            Log.warn(username, "REMOVE_PRODUCT", "No cart found in session when trying to remove product with ID: " + pId, clientIP);
        }

        response.sendRedirect(request.getContextPath() + "/showCart");
    }
}