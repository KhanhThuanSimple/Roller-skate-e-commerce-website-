package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.OrderDetail;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChiTietDonHang", value = "/chitiet")
public class ChiTietDonHang extends BaseServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung

        String id = request.getParameter("pid");
        HttpSession session = request.getSession();
        User user = (User)  session.getAttribute("auth");

        if (user == null) {
            // Nếu không có userId trong session, chuyển hướng người dùng đến trang đăng nhập
            response.sendRedirect("login");
            return;
        }

        // Lấy danh sách đơn hàng từ DAO
        List<OrderDetail> list = dao.getOrderDetails(user.getId(),Integer.parseInt(id));

        // Kiểm tra nếu không có đơn hàng nào
        if (list == null || list.isEmpty()) {
            request.setAttribute("message", "Không có đơn hàng nào.");
        }

        // Đặt danh sách đơn hàng vào request để chuyển sang JSP

        request.setAttribute("list", list);
        // Chuyển tiếp đến trang JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("chitietdonhang.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


