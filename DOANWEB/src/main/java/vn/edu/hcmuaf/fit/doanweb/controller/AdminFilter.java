package vn.edu.hcmuaf.fit.doanweb.controller;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.doanweb.dao.model.User;

import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = "//admin/dashbroad")
public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void destroy() {

    }
    public void doFilter(ServletRequest re, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) re;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session= request.getSession(true);
        User user= (User) session.getAttribute("auth");
        if(user==null || user.getType()< 1){
            response.sendRedirect("../login.jsp");
            return;
        }


        chain.doFilter(request, response);
    }
}
