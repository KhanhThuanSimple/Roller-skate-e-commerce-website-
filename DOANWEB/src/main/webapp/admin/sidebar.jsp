<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="allowedScreens" value="${sessionScope.allowedScreens}" />
<c:set var="currentPath" value="${pageContext.request.requestURI}" />

<aside class="sidebar">
    <h2 style="font-size: 24px !important;">Admin Dashboard</h2>
    <ul style="padding-left: 0 !important;">
        <c:if test="${allowedScreens.contains('tkam')}">
            <li><a  class="${currentPath.contains('/admin/user') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/user">Tài khoản Admin</a></li>
        </c:if>
        <c:if test="${allowedScreens.contains('dh')}">
           <li ><a class="${currentPath.contains('/admin/order') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/order">Đơn hàng</a></li>
        </c:if>
        <c:if test="${allowedScreens.contains('kh')}">
            <li><a  class="${currentPath.contains('/admin/customer') ? 'background-active' : ''}"  href="${pageContext.request.contextPath}/admin/customer">Khách hàng</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('sanpham')}">
            <li><a  class="${currentPath.contains('/admin/product') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/product">Sản phẩm</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('nh')}">
            <li><a  class="${currentPath.contains('/admin/import') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/import">Nhập hàng</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('lsp')}">
            <li><a  class="${currentPath.contains('/admin/category') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/category">Loại sản phẩm</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('kho')}">
            <li><a  class="${currentPath.contains('/admin/stock') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/stock">Kho hàng</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('mh')}">
            <li><a  class="${currentPath.contains('/admin/screen') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/screen">Màn hình</a></li>

        </c:if>
        <c:if test="${allowedScreens.contains('quyen')}">
            <li><a  class="${currentPath.contains('/admin/rights') ? 'background-active' : ''}" href="${pageContext.request.contextPath}/admin/rights">Quyền</a></li>

        </c:if>

        <c:if test="${allowedScreens.contains('kho')}">
            <li><a  href="${pageContext.request.contextPath}/admin/coupons">Mã giảm giá </a></li>

        </c:if>


<%--        <li><a href="${pageContext.request.contextPath}/admin/export">Xuất hàng</a></li>--%>

        <li><a style="background-color: #657a8f !important;"  href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>


    </ul>
</aside>