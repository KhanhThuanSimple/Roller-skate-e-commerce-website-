package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartP;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;

@WebServlet(name = "Add", value = "/add-cart")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        // Lấy thông tin sản phẩm
        String productId = request.getParameter("pid");
        Product product = dao.getAllProductId(productId);

        if (product == null) {
            // Nếu sản phẩm không tồn tại, chuyển hướng và dừng xử lý
            response.sendRedirect(request.getHeader("referer") + "?addCart=false");
            return;
        }

        // Lấy hoặc khởi tạo giỏ hàng trong session
        HttpSession session = request.getSession();
        CartP cart = (CartP) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartP();
        }

        // Thêm sản phẩm vào giỏ hàng
        cart.addProduct(product);
        session.setAttribute("cart", cart);

        // Lấy URL của trang trước đó
        String referer = request.getHeader("referer");
        if (referer != null) {
            // Chuyển hướng về trang trước đó
            response.sendRedirect(referer + "?addCart=ok");
        } else {
            // Nếu không lấy được URL trước đó, quay về trang mặc định
            response.sendRedirect("product?addCart=ok");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}