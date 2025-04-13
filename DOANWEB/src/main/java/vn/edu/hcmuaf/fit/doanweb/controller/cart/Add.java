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
        ProductDao dao = new ProductDao();
        // Lấy thông tin sản phẩm
        String productId = request.getParameter("pid");
        Log.info("Thêm sản phẩm có ID: " + productId);
        System.out.println(productId);
        Product product = dao.getAllProductId(productId);
        System.out.println(product.getId());

        if (product == null) {
            // Nếu sản phẩm không tồn tại, chuyển hướng và dừng xử lý
            Log.warn("San phẩm"+productId+"không tồn tại.");
            response.sendRedirect("?addCart=false");
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
Log.info("Sản phẩm "+product.getName()+"thêm vào giỏ hàng thành công.");
        // Lấy URL của trang hiện tại từ Referer header
        String referer = request.getHeader("Referer");

        // Nếu không có referer (chẳng hạn khi người dùng trực tiếp truy cập trang), điều hướng đến trang sản phẩm
        if (referer == null || referer.isEmpty()) {
            referer = "product"; // Hoặc bất kỳ URL mặc định nào bạn muốn chuyển hướng về
        }

        // Chuyển hướng người dùng quay lại trang trước đó
        response.sendRedirect(referer + "?addCart=ok");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}



