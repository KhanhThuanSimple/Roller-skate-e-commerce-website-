package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LichsudonhangSeverlet1", value = "/donhangdahuy")
public class DonHangDaHuySeverlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        if (user == null) {
            // Nếu không có userId trong session, chuyển hướng người dùng đến trang đăng nhập
            response.sendRedirect("login");
            return;
        }


        // Lấy danh sách đơn hàng từ DAO
        List<Order> list = dao.getOrdersWithProductDaHuys(user.getId());

        // Kiểm tra nếu không có đơn hàng nào
        if (list == null || list.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Đặt danh sách đơn hàng vào request để chuyển sang JSP
        request.setAttribute("list", list);

        // Chuyển tiếp đến trang JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("donhangdahuy.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


