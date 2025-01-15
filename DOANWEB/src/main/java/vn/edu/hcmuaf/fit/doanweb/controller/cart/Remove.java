package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartP;

import java.io.IOException;

@WebServlet(name = "Remove", value = "/Remove")
public class Remove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pId = -1;
        try{
            pId = Integer.parseInt(request.getParameter("pId"));
        }catch(NumberFormatException e){
            response.sendRedirect("ShowCart");
        }
        HttpSession session = request.getSession(true);
        CartP cartP = (CartP) session.getAttribute("cart");
        if(cartP != null) cartP= new CartP();
        cartP.removeProduct(pId);
        session.setAttribute("cart", cartP);
        response.sendRedirect("ShowCart");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}


