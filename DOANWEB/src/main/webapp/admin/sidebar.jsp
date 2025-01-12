<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="sidebar">
    <h2>Admin Dashboard</h2>
    <ul>
        <li><a href="admin/user/add">Tài khoản Admin</a></li>
        <li><a href="order.jsp">Đơn hàng</a></li>
        <li><a   href="${pageContext.request.contextPath}/admin/customer">Khách hàng</a></li>
        <li><a href="product.jsp">Sản phẩm</a></li>
        <li><a href="product.jsp">Nhập hàng</a></li>
        <li><a href="xuathang.jsp">Xuất hàng</a></li>

    </ul>
</aside>