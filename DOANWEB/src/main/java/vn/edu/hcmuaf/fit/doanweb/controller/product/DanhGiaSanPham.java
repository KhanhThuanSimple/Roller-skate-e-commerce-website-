package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ReviewDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderDetail;
import vn.edu.hcmuaf.fit.doanweb.dao.reviewAll.ReviewAll;
import vn.edu.hcmuaf.fit.doanweb.dao.reviews.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DanhGiaSanPham", value = "/danhgia")
public class DanhGiaSanPham extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewDao dao = new ReviewDao();
        ReviewAll reviewAll = new ReviewAll();
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

        List<ReviewAll> productsToReview = null;
        List<ReviewAll> reviewedProducts = null;
      List<Product> notReviewedProducts = null;

        switch (tab) {
            case "all":
                notReviewedProducts = dao.getProductsNotReviewed(user.getId());
                reviewedProducts = dao.getProductsReviewed(user.getId());

                productsToReview = new ArrayList<>();
                if (reviewedProducts != null) {
                    productsToReview.addAll(reviewedProducts);
                }
                if (notReviewedProducts != null) {
                    for (Product p : notReviewedProducts) {
                        ReviewAll ra = new ReviewAll();
                        ra.setProduct(p);
                        ra.setReview(null);  // chưa đánh giá
                        productsToReview.add(ra);
                    }
                }
                break;
            case "reviewed":
                // Lấy sản phẩm đã đánh giá
                reviewedProducts = dao.getProductsReviewed(user.getId());
                System.out.println("Số sản phẩm đã review: " + (reviewedProducts != null ? reviewedProducts.size() : 0));
                break;
            case "not-reviewed":
                notReviewedProducts = dao.getProductsNotReviewed(user.getId());
                break;
            default:
//                productsToReview = dao.getProductsPurchased(user.getId());
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
        response.sendRedirect("danhgia?" + (currentTab != null ? currentTab : "tab"));
    }
}