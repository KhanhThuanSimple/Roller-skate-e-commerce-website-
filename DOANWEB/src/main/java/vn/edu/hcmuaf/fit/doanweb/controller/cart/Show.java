package vn.edu.hcmuaf.fit.doanweb.controller.cart;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doanweb.dao.model.CartP;

import java.io.IOException;

@WebServlet(name = "ShowCart", value = "/ShowCart")
public class Show extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/giohang1.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        CartP cartP = (CartP) session.getAttribute("cart");
        if (cartP != null) {
            String removeParam = request.getParameter("remove");
            if (removeParam != null) {
                int idRemove = Integer.parseInt(removeParam);
                if (cartP != null) {
                    cartP.removeProduct(idRemove);
                    session.setAttribute("cart", cartP);
                    response.sendRedirect("ShowCart");
                }
            }

            String id = request.getParameter("id");
            String qt = request.getParameter("qt");
            if (id != null && qt != null) {
                int idUpdate = Integer.parseInt(id);
                int qtUpdate = Integer.parseInt(qt);
                if (cartP != null) {
                    cartP.update(idUpdate, qtUpdate);
                    session.setAttribute("cart", cartP);
                    response.sendRedirect("ShowCart");
                }
            }



        }
    }
}


