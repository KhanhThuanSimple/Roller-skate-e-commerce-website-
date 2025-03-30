<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="sidebar">
    <h2 style="font-size: 24px !important;">Admin Dashboard</h2>
    <ul style="padding-left: 0 !important;">
        <li><a href="${pageContext.request.contextPath}/admin/user">Tài khoản Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/order">Đơn hàng</a></li>
        <li><a   href="${pageContext.request.contextPath}/admin/customer">Khách hàng</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/product">Sản phẩm</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/import">Nhập hàng</a></li>
<%--        <li><a href="${pageContext.request.contextPath}/admin/export">Xuất hàng</a></li>--%>
        <li><a href="${pageContext.request.contextPath}/admin/category">Loại sản phẩm</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/screen">Màn hình</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/rights">Quyền</a></li>

        <li><a style="background-color: #657a8f !important;"  href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>


    </ul>
</aside>