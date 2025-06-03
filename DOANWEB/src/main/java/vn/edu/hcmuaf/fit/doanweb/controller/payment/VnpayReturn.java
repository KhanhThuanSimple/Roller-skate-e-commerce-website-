package vn.edu.hcmuaf.fit.doanweb.controller.payment;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OderDao;
import vn.edu.hcmuaf.fit.doanweb.dao.order.OderDao_vnpay;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet(name = "VnpayReturn", value = "/vnpay_Return")

    public class VnpayReturn extends HttpServlet {
        OderDao orderDao = new OderDao();

        /**
         * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                // Lấy tất cả tham số, loại bỏ 2 tham số chữ ký
                Map<String, String> fields = new HashMap<>();
                for (String paramName : request.getParameterMap().keySet()) {
                    if (!paramName.equals("vnp_SecureHash") && !paramName.equals("vnp_SecureHashType")) {
                        fields.put(paramName, request.getParameter(paramName));
                    }
                }

                // Lấy chữ ký do VNPay gửi
                String vnp_SecureHash = request.getParameter("vnp_SecureHash");

                // Tính chữ ký lại từ tham số nhận được
                String signValue = Config.hashAllFields(fields);

                if (signValue.equalsIgnoreCase(vnp_SecureHash)) {
                    String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
                    String orderId = request.getParameter("vnp_TxnRef");

                    Order order = new Order();
                    order.setId(Integer.parseInt(orderId));

                    if ("00".equals(vnp_ResponseCode)) {
                        order.setStatus("Đã thanh toán");
                    } else {
                        order.setStatus("Chờ xử lí");
                    }
                    try {
                        boolean updated = orderDao.updateOrderStatus(order);
                    } catch (Exception e) {
                    }
                    // Truyền dữ liệu cần thiết sang JSP
                    request.setAttribute("paymentStatus", order.getStatus());
                    request.setAttribute("params", new HashMap<>(fields)); // truyền toàn bộ params

                    request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid signature");
                }

            }
        }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

        /**
         * Handles the HTTP <code>GET</code> method.
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

    }
