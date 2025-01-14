package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    static ProductDao productDao = new ProductDao();

    // Lấy tất cả sản phẩm
    public List<Product> getAll() throws SQLException {
        return productDao.getAll();
    }

    // Lấy chi tiết sản phẩm theo id
    public Product getDetail(String in) {
        try {
            int id = Integer.parseInt(in);
            Product product = productDao.getById(id);
            if (product != null) {
                return product;
            } else {
                // Trả về null nếu sản phẩm không tìm thấy trong CSDL
                return null;
            }
        } catch (NumberFormatException e) {
            // Xử lý trường hợp id không hợp lệ (không phải số nguyên)
            return null;
        }
    }
}
