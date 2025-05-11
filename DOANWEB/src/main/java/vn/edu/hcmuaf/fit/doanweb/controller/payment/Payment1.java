//package vn.edu.hcmuaf.fit.doanweb.controller.payment;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//import vn.edu.hcmuaf.fit.doanweb.controller.product.BaseServlet;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartP;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.cart.CartProduct;
//import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderItems;
//import vn.edu.hcmuaf.fit.doanweb.dao.order.OrderItemDAO;
//
//import java.io.IOException;
//
//@WebServlet(name = "Payment1", value = "/Payment1")
//public class Payment1 extends BaseServlet {
//
//        @Override
//        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            HttpSession session = request.getSession();
//            User user = (User) session.getAttribute("auth");
//
//            // Kiểm tra đăng nhập
//            if (user == null) {
//                response.sendRedirect("login");
//                return;
//            }
//
//            CartP cart = (CartP) session.getAttribute("cart");
//            if (cart == null || cart.getList().isEmpty()) {
//                response.sendRedirect("cart");
//                return;
//            }
//
//            // Lấy thông tin từ form
//            String province = request.getParameter("province");
//            String district = request.getParameter("district");
//            String ward = request.getParameter("ward");
//            String addressDetail = request.getParameter("shippingAddress");
//            String phone = request.getParameter("phone");
//            String note = request.getParameter("note");
//            String paymentMethod = request.getParameter("paymentMethod");
//            String discountCode = request.getParameter("discountCode");
//            String shippingFeeStr = request.getParameter("shippingFee");
//
//            // Chuyển đổi phí vận chuyển
//            double shippingFee = 0;
//            try {
//                shippingFee = Double.parseDouble(shippingFeeStr);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//
//            // Tính tổng tiền
//            double totalAmount = cart.getTotal() + shippingFee;
//
//            try {
//                OderDao1 orderDao = new OderDao1();
//                Order1 order = new Order1();
//
//                // Thiết lập thông tin đơn hàng
//                order.setUser_id(user.getId());
//                order.setProvince(province);
//                order.setDistrict(district);
//                order.setWard(ward);
//                order.setAddress(addressDetail);
//                order.setPhone(phone);
//                order.setNote(note);
//                order.setTotalAmount(totalAmount);
//                order.setPaymentMethod(paymentMethod);
//                order.setStatus("Bank".equalsIgnoreCase(paymentMethod) ? "Đã thanh toán" : "Đang xử lí");
//                order.setDiscountCode(discountCode);
//                order.setShippingFee(shippingFee);
//
//                // Thêm đơn hàng vào database
//                orderDao.insertOrder(order);
//
//                // Thêm chi tiết đơn hàng
//                OrderItemDAO orderItemDAO = new OrderItemDAO();
//                for (CartProduct cartProduct : cart.getList()) {
//                    OrderItems orderItem = new OrderItems();
//                    orderItem.setOrder_id(order.getId());
//                    orderItem.setProduct_id(cartProduct.getId());
//                    orderItem.setQuantity(cartProduct.getQuantity());
//                    orderItem.setPrice(cartProduct.getPrice());
//                    orderItemDAO.insertOrderItems(orderItem);
//                }
//
//                // Xóa giỏ hàng sau khi đặt hàng thành công
//                session.removeAttribute("cart");
//
//                // Chuyển đến trang thành công
//                response.sendRedirect("success.jsp?orderId=" + order.getId());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                request.setAttribute("error", "Có lỗi xảy ra khi đặt hàng. Vui lòng thử lại.");
//                request.getRequestDispatcher("thanhtoan1.jsp").forward(request, response);
//            }
//        }
//    }