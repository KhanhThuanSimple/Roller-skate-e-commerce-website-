package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
import vn.edu.hcmuaf.fit.doanweb.log.Log;
import java.io.IOException;

@WebServlet(name = "Remove", value = "/Remove")
public class Remove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pId = -1;
        try {
            pId = Integer.parseInt(request.getParameter("pId"));
            Log.info("Attempting to remove product with ID: " + pId); // Ghi log khi bắt đầu yêu cầu xóa sản phẩm
        } catch (NumberFormatException e) {
            Log.error("Invalid product ID format: " + request.getParameter("pId")); // Ghi log lỗi nếu không thể chuyển đổi ID
            response.sendRedirect("ShowCart");
            return; // Thêm return để dừng xử lý tiếp
        }

        HttpSession session = request.getSession(true);
        CartP cartP = (CartP) session.getAttribute("cart");

        if (cartP != null) {
            cartP.removeProduct(pId);
            Log.info("Product with ID: " + pId + " has been removed from the cart."); // Ghi log khi sản phẩm được xóa thành công
        } else {
            Log.warn("No cart found in session when trying to remove product with ID: " + pId); // Ghi log cảnh báo nếu giỏ hàng không tồn tại
        }

        session.setAttribute("cart", cartP);
        response.sendRedirect("ShowCart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


