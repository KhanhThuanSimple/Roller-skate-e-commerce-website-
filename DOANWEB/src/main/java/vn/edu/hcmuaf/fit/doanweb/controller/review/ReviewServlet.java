package vn.edu.hcmuaf.fit.doanweb.controller.review;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.service.review.ReviewService;

import java.io.IOException;

@WebServlet(name = "ReviewServlet", urlPatterns = "/review")
public class ReviewServlet extends HttpServlet {
    private final ReviewService reviewService = new ReviewService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String reviewText = request.getParameter("review");

        int rating = reviewService.evaluate(reviewText);

        request.setAttribute("reviewText", reviewText);
        request.setAttribute("rating", rating);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}

