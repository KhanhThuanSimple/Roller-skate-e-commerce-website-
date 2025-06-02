package vn.edu.hcmuaf.fit.doanweb.controller.shipping;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.shipping_model.ShippingFeeRequest;
import vn.edu.hcmuaf.fit.doanweb.dao.shipping_model.ShippingFeeResponse;
import vn.edu.hcmuaf.fit.doanweb.service.shipping.GHNApiClient;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;

/**
 * Servlet xử lý yêu cầu tính phí vận chuyển dựa trên API Giao Hàng Nhanh (GHN).
 * URL: /calculateShippingFee
 */
@WebServlet("/calculateShippingFee")
public class ShippingFeeServlet extends HttpServlet {
    // Địa chỉ mặc định của shop
    private static final int SHOP_DISTRICT_ID = 1463; // Quận Thủ Đức
    private static final String SHOP_WARD_CODE = "21806"; // Phường Linh Đông

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy IP và username cho log
        String clientIP = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        String username = "Guest";

        // Lấy username từ session (giả định auth chứa username)
        if (session != null) {
            Object auth = session.getAttribute("auth");
            username = (auth instanceof String) ? (String) auth : "Guest";
        }

        try {
            Log.info(username, "CALCULATE_SHIPPING", "Received shipping fee calculation request", clientIP);

            // Sử dụng địa chỉ mặc định của shop cho from_district_id và from_ward_code
            int toDistrictId = Integer.parseInt(request.getParameter("toDistrictId"));
            String toWardCode = request.getParameter("toWardCode");
            int weight = Integer.parseInt(request.getParameter("weight"));
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));

            ShippingFeeRequest feeRequest = new ShippingFeeRequest();
            feeRequest.setFrom_district_id(SHOP_DISTRICT_ID);
            feeRequest.setFrom_ward_code(SHOP_WARD_CODE);
            feeRequest.setTo_district_id(toDistrictId);
            feeRequest.setTo_ward_code(toWardCode);
            feeRequest.setWeight(weight);
            feeRequest.setLength(length);
            feeRequest.setWidth(width);
            feeRequest.setHeight(height);
            feeRequest.setInsurance_value(0);
            feeRequest.setService_id(53320); // ID dịch vụ tiêu chuẩn của GHN
            feeRequest.setService_type_id(2); // Loại dịch vụ (2: Thường)

            Log.info(username, "CALCULATE_SHIPPING",
                    String.format("Shipping parameters: FromDistrict=%d, FromWard=%s, ToDistrict=%d, ToWard=%s, Weight=%dg",
                            SHOP_DISTRICT_ID, SHOP_WARD_CODE, toDistrictId, toWardCode, weight),
                    clientIP);

            ShippingFeeResponse feeResponse = GHNApiClient.calculateShippingFee(feeRequest);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(feeResponse));

            Log.info(username, "CALCULATE_SHIPPING", "Shipping fee calculation completed successfully", clientIP);

        } catch (Exception e) {
            Log.error(username, "CALCULATE_SHIPPING", "Error calculating shipping fee: " + e.getMessage(), clientIP, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + escapeJson(e.getMessage()) + "\"}");
        }
    }

    /**
     * Thoát các ký tự đặc biệt trong JSON để tránh lỗi.
     */
    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}