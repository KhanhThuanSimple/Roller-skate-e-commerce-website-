package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderDetail;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderDetailList", value = "/admin/order-detail")
public class AdminOrderDetailList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();

        String id = request.getParameter("pid");
        HttpSession session = request.getSession();
        User user = (User)  session.getAttribute("auth");

        System.out.println(id);

        // Lấy danh sách đơn hàng từ DAO
        List<OrderDetail> list = dao.getOrderDetails(0,Integer.parseInt(id));

        // Kiểm tra nếu không có đơn hàng nào
        if (list == null || list.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Đặt danh sách đơn hàng vào request để chuyển sang JSP

        request.setAttribute("list", list);
        // Chuyển tiếp đến trang JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("/admin/orderDetail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();

        String id = request.getParameter("pid");
        HttpSession session = request.getSession();
        User user = (User)  session.getAttribute("auth");

        System.out.println(id);

        // Lấy danh sách đơn hàng từ DAO
        List<OrderDetail> list = dao.getOrderDetails1(Integer.parseInt(id));

        // Kiểm tra nếu không có đơn hàng nào
        if (list == null || list.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Đặt danh sách đơn hàng vào request để chuyển sang JSP

        request.setAttribute("list", list);
        // Chuyển tiếp đến trang JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("/admin/orderDetail.jsp").forward(request, response);
    }
}

