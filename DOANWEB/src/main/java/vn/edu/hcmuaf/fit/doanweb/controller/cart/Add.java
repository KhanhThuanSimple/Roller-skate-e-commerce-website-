package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.cart.Cart;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.service.ProductService;

import java.io.IOException;

@WebServlet(name = "Add", value = "/add-cart")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ProductService ps = new ProductService();
        Product pId= ps.getDetail(request.getParameter("id"));
        if(pId != null){
            response.sendRedirect("/cart");
        }

        HttpSession session = request.getSession(true);
        Cart c = (Cart) session.getAttribute("cart");
        if(c == null) c = new Cart();
        c.add(pId);
        session.setAttribute("cart", c);
        response.sendRedirect("list-products?add-cart=ok");
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
