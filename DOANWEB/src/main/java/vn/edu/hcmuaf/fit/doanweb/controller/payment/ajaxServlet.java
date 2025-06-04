
package vn.edu.hcmuaf.fit.doanweb.controller.payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.controller.product.BaseServlet;
import vn.edu.hcmuaf.fit.doanweb.dao.model.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartProduct;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderItems;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OderDao;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OrderItemDAO;
import vn.edu.hcmuaf.fit.doanweb.log.Log;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
@WebServlet(name = "ajaxServlet", value = "/bank-code")

public class ajaxServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String bankCode = request.getParameter("bankCode");
        String bankCode = request.getParameter("action");


        if (request.getParameter("totalBill") == null) {
            response.sendRedirect("cart");
            return;
        }

        double amountDouble = Double.parseDouble(request.getParameter("totalBill"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        CartP cart = (CartP) session.getAttribute("cart");
        if (user != null) {
            int userId = user.getId(); // Lấy userId từ đối tượng User
            // Sử dụng userId ở đây
        } else {
            response.sendRedirect("login");
            return;
        }
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String addressDetail = request.getParameter("shippingAddress");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        String paymentMethod = request.getParameter("paymentMethod");
        String discountCode = request.getParameter("discountCode");
        String shippingFeeStr = request.getParameter("shippingFee");

        // Chuyển đổi phí vận chuyển
        double shippingFee = 0;
        try {
            shippingFee = Double.parseDouble(shippingFeeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Tính tổng tiền
        double totalAmount = cart.getTotal() + shippingFee;
        if ("confirm".equalsIgnoreCase(bankCode)) {

            try {
                OderDao orderDao = new OderDao();
                Order order = new Order();

                // Thiết lập thông tin đơn hàng
                order.setUser_id(user.getId());
                order.setProvince(province);
                order.setDistrict(district);
                order.setWard(ward);
                order.setAddress(addressDetail);
                order.setName(name);
                order.setPhone(phone);
                order.setNote(note);
                order.setTotalAmount(totalAmount);
                order.setPaymentMethod(paymentMethod);
                order.setStatus("Bank".equalsIgnoreCase(paymentMethod) ? "Đã thanh toán ngân hàng." : "Thanh toán khi nhận hàng.");
                order.setDiscountCode(discountCode);
                order.setShippingFee(shippingFee);

                int  orderId = 0;
                try {
                    orderId =orderDao.insertOrder (order);
                    order.setId(orderId);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Thêm chi tiết đơn hàng
                OrderItemDAO orderItemDAO = new OrderItemDAO();

                for (CartProduct cartProduct : cart.getList()) {

                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrder_id(order.getId());
                    orderItem.setProduct_id(cartProduct.getId());
                    orderItem.setQuantity(cartProduct.getQuantity());
                    orderItem.setPrice(cartProduct.getPrice());
                    orderItemDAO.insertOrderItems(orderItem);
                }
                session.removeAttribute("cart");
                if (!"Bank".equalsIgnoreCase(paymentMethod)) {
                    // Nếu COD → không đi qua VNPay
                    response.sendRedirect("success.jsp?orderId=" + order.getId());
                    return;
                }



                long amount = (long) (amountDouble * 100); // Chuyển đổi sang đơn vị đồng
                String vnp_TxnRef = orderId+"";
                String vnp_IpAddr = Config.getIpAddress(request);

                String vnp_Version = "2.1.0";
                String vnp_Command = "pay";
                String orderType = "other";



                String vnp_TmnCode = Config.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");

                if (bankCode != null && !bankCode.isEmpty()) {
                    vnp_Params.put("vnp_BankCode", bankCode);
                }
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);

                String locate = request.getParameter("language");
                if (locate != null && !locate.isEmpty()) {
                    vnp_Params.put("vnp_Locale", locate);
                } else {
                    vnp_Params.put("vnp_Locale", "vn");
                }
                vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
                response.sendRedirect(paymentUrl);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Có lỗi xảy ra khi đặt hàng. Vui lòng thử lại.");
                request.getRequestDispatcher("thanhtoan1.jsp").forward(request, response);
            }
        } else if ("cancle".equalsIgnoreCase(bankCode)) {
            try {
//            OderDao falseOrderDao = new OderDao(); // DAO lưu đơn hủy (bạn tạo tương tự OderDao)
//            Order falseOrder = new Order();
//
//            // Thiết lập thông tin đơn hàng hủy tương tự Order nhưng đánh dấu trạng thái hủy
//            falseOrder.setUser_id(user.getId());
//            falseOrder.setProvince(province);
//            falseOrder.setDistrict(district);
//            falseOrder.setWard(ward);
//            falseOrder.setAddress(addressDetail);
//            falseOrder.setName(name);
//            falseOrder.setPhone(phone);
//            falseOrder.setNote(note);
//            falseOrder.setTotalAmount(cart.getTotal() + shippingFee);
//            falseOrder.setPaymentMethod(paymentMethod);
//            falseOrder.setStatus("Đơn hàng đã bị hủy");
//            falseOrder.setDiscountCode(discountCode);
//            falseOrder.setShippingFee(shippingFee);
////
////            int falseOrderId = falseOrderDao.insertFalseOrder(falseOrder);
////            falseOrder.setId(falseOrderId);
////
//
//            int  falseOrderId = 0;
//            try {
//                falseOrderId =falseOrderDao.insertFalseOrder (order);
//                order.setId(orderId);
//
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
                OderDao orderDao = new OderDao();
                Order order = new Order();

                // Thiết lập thông tin đơn hàng
                order.setUser_id(user.getId());
                order.setProvince(province);
                order.setDistrict(district);
                order.setWard(ward);
                order.setAddress(addressDetail);
                order.setName(name);
                order.setPhone(phone);
                order.setNote(note);
                order.setTotalAmount(totalAmount);
                order.setPaymentMethod(paymentMethod);
                order.setStatus("Đơn hàng đã bị hủy");
                order.setDiscountCode(discountCode);
                order.setShippingFee(shippingFee);

                int orderId = 0;
                try {
                    orderId = orderDao.insertFalseOrder(order);
                    order.setId(orderId);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                OrderItemDAO orderItemDAO = new OrderItemDAO();

                for (CartProduct cartProduct : cart.getList()) {

                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrder_id(order.getId());
                    orderItem.setProduct_id(cartProduct.getId());
                    orderItem.setQuantity(cartProduct.getQuantity());
                    orderItem.setPrice(cartProduct.getPrice());
                    orderItemDAO.insertOrderItemsDahuy(orderItem);
                }

                // Redirect hoặc hiển thị thông báo hủy thành công
                response.sendRedirect("ShowCart");

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Có lỗi xảy ra khi hủy đơn hàng. Vui lòng thử lại.");
                request.getRequestDispatcher("thanhtoan1.jsp").forward(request, response);
            }

        }}

}