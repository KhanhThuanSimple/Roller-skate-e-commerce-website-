package vn.edu.hcmuaf.fit.doanweb.controller.coupon;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import vn.edu.hcmuaf.fit.doanweb.dao.coupon.CouponDAO;
import vn.edu.hcmuaf.fit.doanweb.dao.model.coupon.Coupon;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@WebServlet("/apply-coupon")
public class ApplyCouponServlet extends HttpServlet {
    CouponDAO couponDAO = new CouponDAO();


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Coupon> availableCoupons = couponDAO.getActiveCoupons();
    request.setAttribute("availableCoupons", availableCoupons);
    request.getRequestDispatcher("/thanhtoan1.jsp").forward(request, response);
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        BigDecimal orderTotal = new BigDecimal(request.getParameter("total"));

        Coupon coupon = couponDAO.findByCode(code);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject result = new JSONObject();

        if (coupon == null || coupon.getStatus().equals("INACTIVE") || coupon.getExpireDate().before(new Date())) {
            result.put("success", false);
            result.put("message", "Mã không hợp lệ hoặc đã hết hạn.");
        } else if (orderTotal.compareTo(coupon.getMinOrderAmount()) < 0) {
            result.put("success", false);
            result.put("message", "Đơn hàng chưa đạt mức tối thiểu để áp dụng mã.");
        } else {
            BigDecimal discount = coupon.getDiscountType().equals("FIXED")
                    ? coupon.getDiscountValue()
                    : orderTotal.multiply(coupon.getDiscountValue()).divide(new BigDecimal(100));

            result.put("success", true);
            result.put("discount", discount);
            result.put("code", code);
        }

        out.print(result.toString());
    }
}



