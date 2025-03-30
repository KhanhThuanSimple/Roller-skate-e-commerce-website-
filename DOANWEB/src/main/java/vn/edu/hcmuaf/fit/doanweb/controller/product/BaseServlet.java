package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.http.HttpServlet; // Để tạo lớp servlet
import jakarta.servlet.http.HttpServletRequest; // Để xử lý yêu cầu HTTP
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao; // Để sử dụng ProductDao
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category; // Để sử dụng mô hình Category
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.util.List; // Để sử dụng danh sách
public class BaseServlet extends HttpServlet {
    protected void loadCommonData(HttpServletRequest request) {
        ProductDao productDao = new ProductDao();
        List<Category> listc = productDao.getAllCategory();
        request.setAttribute("listc", listc);
    }
    protected void handlePaging(HttpServletRequest request, ProductDao dao) {
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1"; // Mặc định trang 1 nếu không có tham số
        }
        int index = Integer.parseInt(indexPage);
        int count = dao.getTotalProduct();
        int endPage = count / 20; // Mỗi trang hiển thị 20 sản phẩm
        if (count % 20 != 0) {
            endPage++;
        }
        List<Product> list = dao.pagingProduct(index); // Lấy danh sách sản phẩm theo trang
        request.setAttribute("products", list);
        request.setAttribute("endP", endPage); // Tổng số trang
        request.setAttribute("tag", index); // Trang hiện tại
    }

}