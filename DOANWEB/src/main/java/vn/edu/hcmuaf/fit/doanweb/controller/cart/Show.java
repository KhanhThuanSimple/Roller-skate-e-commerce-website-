package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartP;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

@WebServlet(name = "ShowCart", value = "/ShowCart")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log.info("User requested to view the shopping cart."); // Ghi log khi người dùng yêu cầu xem giỏ hàng
        request.getRequestDispatcher("/giohang1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        CartP cartP = (CartP) session.getAttribute("cart");

        if (cartP != null) {
            // Xử lý checkout
            String checkout = request.getParameter("checkout");
            if (checkout != null) {
                Log.info("Checkout process initiated."); // Ghi log khi bắt đầu quá trình checkout
                // Bạn có thể thực thi logic checkout tại đây
            }

            // Xử lý xóa sản phẩm
            String removeParam = request.getParameter("remove");
            if (removeParam != null) {
                try {
                    int idRemove = Integer.parseInt(removeParam);
                    cartP.removeProduct(idRemove);
                    session.setAttribute("cart", cartP);
                    Log.info("Product with ID: " + idRemove + " removed from cart."); // Ghi log khi xóa sản phẩm
                    response.sendRedirect("ShowCart");
                } catch (NumberFormatException e) {
                    Log.error("Invalid product ID format: " + removeParam); // Ghi log lỗi nếu định dạng ID không hợp lệ
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
                    Log.info("Product with ID: " + idUpdate + " updated to quantity: " + qtUpdate); // Ghi log khi cập nhật sản phẩm
                    response.sendRedirect("ShowCart");
                } catch (NumberFormatException e) {
                    Log.error("Invalid input for product ID or quantity. ID: " + id + ", Quantity: " + qt); // Ghi log lỗi nếu có vấn đề khi cập nhật
                }
            }
        } else {
            Log.warn("Attempted to update or remove a product from a non-existent cart."); // Ghi log cảnh báo nếu không có giỏ hàng
        }
    }
}