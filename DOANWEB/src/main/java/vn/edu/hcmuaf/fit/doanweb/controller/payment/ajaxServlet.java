
package vn.edu.hcmuaf.fit.doanweb.controller.payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.controller.product.BaseServlet;
import vn.edu.hcmuaf.fit.doanweb.dao.model.*;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OderDao_vnpay;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OrderItemDAO;
import vn.edu.hcmuaf.fit.doanweb.log.Log;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ajaxServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bankCode = req.getParameter("bankCode");
        Log.info("Bank Code: " + bankCode);

        if (req.getParameter("totalBill") == null) {
            resp.sendRedirect("cart");
            Log.warn("Total bill is null, redirecting to cart");
            return;
        }

        double amountDouble = Double.parseDouble(req.getParameter("totalBill"));
        Log.info("Total Bill Amount: " + amountDouble);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");
        CartP cart = (CartP) session.getAttribute("cart");
        if (user != null) {
            int userId = user.getId(); // Lấy userId từ đối tượng User
            // Sử dụng userId ở đây
            Log.info("User ID: " + user.getId());
        } else {
            Log.warn("User is not authenticated, redirecting to login");
            resp.sendRedirect("login");
            return;
        }
        OderDao_vnpay dao_vnpay = new OderDao_vnpay();
    Order order = new Order();
        order.setUser_id(user.getId());
        order.setName(req.getParameter("fullName"));
        order.setPhone(req.getParameter("phone"));
        order.setAddress(req.getParameter("address"));
        order.setDiscountCode(req.getParameter("discountCode"));
        order.setTotalAmount(amountDouble);


        int  orderId = 0;
        try {
            orderId = dao_vnpay.insertOrder(order);
            Log.info("Order ID inserted: " + orderId);
        } catch (SQLException e) {
            Log.error("SQL Exception while inserting order: " + e.getMessage());
            throw new RuntimeException(e);
        }


        if( orderId < 1 ){
            resp.sendRedirect("cart");
            Log.warn("Order ID is less than 1, redirecting to cart");
            return;
        }
        OrderItemDAO daoItem = new OrderItemDAO();
        if (cart != null && cart.getList().size() > 0) {


            for (CartProduct cartProduct : cart.getList()) {
                int productId = cartProduct.getId();
                int quantity = cartProduct.getQuantity();
                double price = cartProduct.getPrice();
// Tạo đối tượng OrderItems để lưu chi tiết sản phẩm
                OrderItems orderItem = new OrderItems();
                orderItem.setOrder_id(orderId);
                orderItem.setProduct_id(productId);
                orderItem.setQuantity(quantity);
                orderItem.setPrice(price);

                // Chèn chi tiết sản phẩm vào cơ sở dữ liệu
                try {
                    daoItem.insertOrderItems(orderItem);
                    Log.warn("Order ID is less than 1, redirecting to cart");
                } catch (SQLException e) {
                    Log.error("SQL Exception while inserting order item: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            }

        }

        long amount = (long) (amountDouble * 100); // Chuyển đổi sang đơn vị đồng
        String vnp_TxnRef = orderId+"";
        String vnp_IpAddr = Config.getIpAddress(req);
        Log.info("Preparing VNPay payment - Amount: " + amount +
                ", OrderID: " + vnp_TxnRef + ", IP: " + vnp_IpAddr);

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

        String locate = req.getParameter("language");
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
        Log.debug( "Using specific bank code: " + bankCode);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        Log.debug("Payment created at: " + vnp_CreateDate +
                ", expires at: " + vnp_ExpireDate);
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
        Log.info( "Redirecting to VNPay payment URL");
        resp.sendRedirect(paymentUrl);
    }
    }