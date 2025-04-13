package vn.edu.hcmuaf.fit.doanweb.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderList", value = "/admin/order")
public class AdminOrderList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

//        if (user == null) {
//            // Nếu không có userId trong session, chuyển hướng người dùng đến trang đăng nhập
//            response.sendRedirect(request.getContextPath()+"/login");
//            return;
//        }
        // Lấy danh sách đơn hàng từ DAO
        List<Order> list = dao.getOrdersWithProducts(0);

        // Kiểm tra nếu không có đơn hàng nào
        if (list == null || list.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Đặt danh sách đơn hàng vào request để chuyển sang JSP
        request.setAttribute("orders", list);

        // Chuyển tiếp đến trang JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

