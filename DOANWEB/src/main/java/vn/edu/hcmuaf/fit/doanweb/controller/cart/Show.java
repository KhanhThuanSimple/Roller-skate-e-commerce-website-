package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "ShowCart", value = "/ShowCart")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        String username = "Guest";

        // Lấy username từ session
        if (session != null) {
            Object auth = session.getAttribute("auth");
            username = (auth instanceof String) ? (String) auth : (auth != null ? auth.toString() : "Guest");
        }

        Log.info(username, "VIEW_CART", "User requested to view the shopping cart", clientIP);
        request.getRequestDispatcher("/giohang1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(true);
        String username = "Guest";

        // Lấy username từ session
        Object auth = session.getAttribute("auth");
        if (auth != null) {
            username = (auth instanceof String) ? (String) auth : auth.toString();
        }

        CartP cartP = (CartP) session.getAttribute("cart");

        if (cartP != null) {
            // Xử lý checkout
            String checkout = request.getParameter("checkout");
            if (checkout != null) {
                Log.info(username, "CHECKOUT", "Checkout process initiated", clientIP);
                // Bạn có thể thực thi logic checkout tại đây
            }

            // Xử lý xóa sản phẩm
            String removeParam = request.getParameter("remove");
            if (removeParam != null) {
                try {
                    int idRemove = Integer.parseInt(removeParam);
                    cartP.removeProduct(idRemove);
                    session.setAttribute("cart", cartP);
                    Log.info(username, "REMOVE_PRODUCT", "Product with ID: " + idRemove + " removed from cart", clientIP);
                    response.sendRedirect("ShowCart");
                } catch (NumberFormatException e) {
                    Log.error(username, "REMOVE_PRODUCT", "Invalid product ID format: " + removeParam, clientIP, e);
                }
            }

            // Xử lý cập nhật số lượng sản phẩm
            String id = request.getParameter("id");
            String qt = request.getParameter("qt");
            if (id != null && qt != null) {
                try {
                    int idUpdate = Integer.parseInt(id);
                    int qtUpdate = Integer.parseInt(qt);
                    cartP.update(idUpdate, qtUpdate);
                    session.setAttribute("cart", cartP);
                    Log.info(username, "UPDATE_QUANTITY", "Product with ID: " + idUpdate + " updated to quantity: " + qtUpdate, clientIP);
                    response.sendRedirect("ShowCart");
                } catch (NumberFormatException e) {
                    Log.error(username, "UPDATE_QUANTITY", "Invalid input for product ID or quantity. ID: " + id + ", Quantity: " + qt, clientIP, e);
                }
            }
        } else {
            Log.warn(username, "CART_ACTION", "Attempted to update or remove a product from a non-existent cart", clientIP);
        }
    }
}