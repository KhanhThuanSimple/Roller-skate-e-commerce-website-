package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.ServletException;  // Để xử lý ngoại lệ servlet
import jakarta.servlet.http.HttpServlet; // Để tạo lớp servlet
import jakarta.servlet.http.HttpServletRequest; // Để xử lý yêu cầu HTTP
import jakarta.servlet.http.HttpServletResponse; // Để xử lý phản hồi HTTP
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao; // Để sử dụng ProductDao
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category; // Để sử dụng mô hình Category

import java.util.List; // Để sử dụng danh sách
public class BaseServlet extends HttpServlet {
    protected void loadCommonData(HttpServletRequest request) {
        ProductDao productDao = new ProductDao();
        List<Category> listc = productDao.getAllCategory();
        request.setAttribute("listc", listc);
    }

}