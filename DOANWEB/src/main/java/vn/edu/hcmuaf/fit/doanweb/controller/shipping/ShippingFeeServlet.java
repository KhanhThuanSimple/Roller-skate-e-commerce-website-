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

@WebServlet("/calculateShippingFee")
public class ShippingFeeServlet extends HttpServlet {
    // Địa chỉ mặc định của shop
    private static final int SHOP_DISTRICT_ID = 1463; // Quận Thủ Đức
    private static final String SHOP_WARD_CODE = "21806"; // Phường Linh Đông

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Log.info("Received shipping fee calculation request");

            // Sử dụng địa chỉ mặc định của shop cho from_district_id và from_w ard_code
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
//            feeRequest.setService_id(53321);  // ID dịch vụ tiêu chuẩn của GHN
            feeRequest.setService_id(53320);  // ID dịch vụ tiêu chuẩn của GHN
            feeRequest.setService_type_id(2); // Loại dịch vụ (2: Thường)

            Log.info("Shipping parameters: FromDistrict=" + SHOP_DISTRICT_ID +
                    ", FromWard=" + SHOP_WARD_CODE +
                    ", ToDistrict=" + toDistrictId +
                    ", ToWard=" + toWardCode +
                    ", Weight=" + weight + "g");

            ShippingFeeResponse feeResponse = GHNApiClient.calculateShippingFee(feeRequest);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(feeResponse));

            Log.info("Shipping fee calculation completed successfully");

        } catch (Exception e) {
            Log.error("Error calculating shipping fee: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}