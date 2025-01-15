package vn.edu.hcmuaf.fit.doanweb.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.doanweb.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminProductEditController" ,value = "/admin/product/update")
public class AdminProductEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AuthService authService = new AuthService();

        try{
            String name=request.getParameter("name");
            String img=request.getParameter("img");
            double price=Double.parseDouble(request.getParameter("price")) ;
            String title=request.getParameter("title");
            String description=request.getParameter("description");
            int cateID = Integer.parseInt(request.getParameter("cateID"));
            String offer=request.getParameter("offer");
            int id=Integer.parseInt(request.getParameter("id"));

            boolean rs =authService.updateProduct(name,img,price,title,description,cateID,offer,id);
            System.out.println("KQs");

            System.out.println("Nhanf");
            if(rs) {
                request.setAttribute("message", "Cập nhật thành công!");
            }else{
                request.setAttribute("message", "Cập nhật không thành công!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/product");

        }catch (SQLException e) {
            e.printStackTrace(); // Ghi lại stack trace để dễ theo dõi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm tài khoản!");
            response.sendRedirect(request.getContextPath() + "/admin/product");
        }
    }


}
