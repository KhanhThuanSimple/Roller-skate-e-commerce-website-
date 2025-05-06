package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderDetail;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DanhGiaSanPham", value = "/danhgia")
public class DanhGiaSanPham extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung nếu có

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy tham số tab từ request để xác định tab nào đang được chọn
        String tab = request.getParameter("tab");
        if (tab == null || tab.isEmpty()) {
            tab = "all"; // Mặc định là tab "Tất cả sản phẩm"
        }

        List<Product> productsToReview = null;
        List<Product> reviewedProducts = null;
        List<Product> notReviewedProducts = null;

        switch (tab) {
            case "all":
                // Lấy tất cả sản phẩm đã mua
                productsToReview = dao.getProductsPurchased(user.getId());
//                productsToReview = dao.getAllProductnew();
                break;
            case "reviewed":
                // Lấy sản phẩm đã đánh giá
                reviewedProducts = dao.getProductsReviewed(user.getId());
                break;
            case "not-reviewed":
                // Lấy sản phẩm chưa đánh giá
                notReviewedProducts = dao.getProductsNotReviewed(user.getId());
                break;
            default:
                productsToReview = dao.getProductsPurchased(user.getId());
                break;
        }

        // Đặt các thuộc tính vào request
        request.setAttribute("tab", tab);
        request.setAttribute("productsToReview", productsToReview);
        request.setAttribute("reviewedProducts", reviewedProducts);
        request.setAttribute("notReviewedProducts", notReviewedProducts);

        request.getRequestDispatcher("danhgia.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String rating = request.getParameter("rating");
        String comment = request.getParameter("comment");
        String currentTab = request.getParameter("currentTab"); // Lấy tab hiện tại

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user != null) {
            ProductDao dao = new ProductDao();
            boolean success = dao.addProductReview(user.getId(), Integer.parseInt(productId),
                    Integer.parseInt(rating), comment);

            if (success) {
                session.setAttribute("reviewSuccess", "Cảm ơn bạn đã đánh giá sản phẩm!");
            } else {
                session.setAttribute("reviewError", "Sản phẩm đã được đánh giá.");
            }
        }

        // Chuyển hướng trở lại trang đánh giá với tab hiện tại
        response.sendRedirect("danhgia?tab=" + (currentTab != null ? currentTab : "all"));
    }
}