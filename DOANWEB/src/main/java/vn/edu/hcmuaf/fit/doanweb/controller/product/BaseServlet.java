package vn.edu.hcmuaf.fit.doanweb.controller.product;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Category;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.util.List;

public class BaseServlet extends HttpServlet {

    protected void loadCommonData(HttpServletRequest request) {
        ProductDao productDao = new ProductDao();
        List<Category> listc = productDao.getAllCategory();
        request.setAttribute("listc", listc);
    }

    protected void handlePaging(HttpServletRequest request, ProductDao dao) {
        String indexPage = request.getParameter("index");
        int index = 1; // Mặc định trang 1
        if (indexPage != null) {
            try {
                index = Integer.parseInt(indexPage);
                if (index < 1) {
                    index = 1; // Không cho index âm hoặc 0
                }
            } catch (NumberFormatException e) {
                index = 1; // Nếu lỗi parse, đặt mặc định 1
            }
        }

        int count = dao.getTotalProduct();
        int endPage = count / 20;
        if (count % 20 != 0) {
            endPage++;
        }

        List<Product> list = dao.pagingProduct(index);
        request.setAttribute("products", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
    }
}
