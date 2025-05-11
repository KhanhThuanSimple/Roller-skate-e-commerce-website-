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

import java.io.IOException;


@WebServlet(name = "Payment", value = "/checkout")
public class Payment extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loadCommonData(request); // Gọi phương thức chung
        User user = (User) session.getAttribute("auth");
        CartP cart = (CartP) session.getAttribute("cart");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        if (cart == null) {
            cart = new CartP();
            session.setAttribute("cart", cart);
        }

        // Tính tổng giỏ hàng
        double totalAmount = cart.getTotal();

        // Cộng thêm phí giao hàng
        totalAmount += 20000;

        // Lưu vào session
        session.setAttribute("totalAmount", totalAmount);

        // Chuyển hướng đến trang thanh toán
        request.getRequestDispatcher("thanhtoan1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        String discountCode = request.getParameter("discountCode");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        CartP cart = (CartP) session.getAttribute("cart");

        if (user == null) {
           response.sendRedirect("login");
            return;
        }


        Double totalAmount = (Double) session.getAttribute("totalAmount");

        try {

            OderDao dao = new OderDao();
            Order order = new Order();

            order.setUser_id(user.getId());
//            order.setName(name);
            order.setPhone(phone);
            order.setAddress(address);
            order.setTotalAmount(totalAmount);
            order.setPaymentMethod(paymentMethod);
            String status;
            if ("Bank".equalsIgnoreCase(paymentMethod)) {
                status = "Đã thanh toán";
            } else {
                status = "Đang xử lí";
            }
            order.setStatus(status);
            order.setDiscountCode(discountCode);

//            dao.insertOrder(order);  // Phương thức này sẽ tự động cập nhật ID của đơn hàng


            int orderId = order.getId();
            request.setAttribute("orderId", order.getId());


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
                    daoItem.insertOrderItems(orderItem);
                }

            }


            // Xóa giỏ hàng sau khi đặt hàng
            session.removeAttribute("cart");




        } catch (Exception e) {
            e.printStackTrace();

        }
        response.sendRedirect("success.jsp");

    }
}
