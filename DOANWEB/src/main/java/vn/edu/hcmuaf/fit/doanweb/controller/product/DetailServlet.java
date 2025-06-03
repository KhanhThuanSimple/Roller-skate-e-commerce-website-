package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.ReviewDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.reviews.Review;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailServlet", value = "/detail")
public class DetailServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCommonData(request); // Gọi phương thức chung

        String id = request.getParameter("pid");
        ProductDao dao = new ProductDao();
        ReviewDao reviewDao = new ReviewDao();
        Product product= dao.getAllProductId(id);
        List<Review> reviews = reviewDao.getReviewsByProductId(id); // lấy đánh giá

        request.setAttribute("detail", product);
        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher("product_detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


