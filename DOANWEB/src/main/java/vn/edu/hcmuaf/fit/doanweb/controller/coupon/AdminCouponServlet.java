package vn.edu.hcmuaf.fit.doanweb.controller.coupon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.dao.coupon.CouponDAO;
import vn.edu.hcmuaf.fit.doanweb.dao.model.coupon.Coupon;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/admin/coupons")
public class AdminCouponServlet extends HttpServlet {
    private CouponDAO couponDAO = new CouponDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Coupon> coupons = couponDAO.getAllCoupons();
        request.setAttribute("coupons", coupons);
        request.getRequestDispatcher("/admin/coupons.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String code = request.getParameter("coupon_code");
            String type = request.getParameter("discount_type");
            BigDecimal value = new BigDecimal(request.getParameter("discount_value"));
            BigDecimal minOrder = new BigDecimal(request.getParameter("min_order_amount"));
            String expire = request.getParameter("expire_date");
            String status = request.getParameter("status");

            Coupon coupon = new Coupon(id, code, type, value, minOrder, Date.valueOf(expire), status);
            couponDAO.updateCoupon(coupon);
        } else if ("add".equals(action)) {
            String code = request.getParameter("coupon_code");
            String type = request.getParameter("discount_type");
            BigDecimal value = new BigDecimal(request.getParameter("discount_value"));
            BigDecimal minOrder = new BigDecimal(request.getParameter("min_order_amount"));
            String expire = request.getParameter("expire_date");
            String status = request.getParameter("status");

            Coupon coupon = new Coupon(0, code, type, value, minOrder, Date.valueOf(expire), status);
            couponDAO.addCoupon(coupon);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            couponDAO.deleteCoupon(id);
        }

        response.sendRedirect("coupons");
    }

}
