package vn.edu.hcmuaf.fit.doanweb.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListTotal", value = "/list")
public class ListTotal extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        loadCommonData(request); // Gọi phương thức chung
    String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage="1";
        }
    int index = Integer.parseInt(indexPage);
        int count = dao.getTotalProduct();
        int endPage = count / 20;
        if(count % 20 != 0){
            endPage++;
        }
        List<Product> list  = dao.pagingProduct(index);
        request.setAttribute("products", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.getRequestDispatcher("product.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}


