<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<header id="header">
    <nav class="container">
        <a href="home" id="logo"><img src="./image/logo1.png" alt="logo"></a>
        <form id="search-form" action="search" method="get">
<%--            // get day du duong dan van de bao mat --%>
            <input value="${txtS}" type="text" name="txt" placeholder="Tìm kiếm...">
            <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </form>
        <ul id="main-menu">
            <li><a href="home">TRANG CHỦ</a></li>
            <li>
                <a href="product"> SẢN PHẨM</a>
                <ul class="sub-menu">
                    <c:forEach items="${listc}" var="o">
                        <li> <a href="product?category=${o.id}">${o.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <li><a href="gioithieu.jsp">GIỚI THIỆU</a></li>
            <li><a href="lienhe.jsp">LIÊN HỆ</a></li>
            <li class="tooltip">
                <a href="ShowCart" title="Giỏ hàng"><i class="fa-solid fa-cart-shopping"></i></a>
                <span class="tooltiptext">Giỏ hàng</span>
            </li>
            <li class="tooltip">
                <a href="canhan" title="Cá nhân"><i class="fa-solid fa-user"></i></a>
                <span class="tooltiptext">Cá nhân</span>
            </li>
        </ul>
    </nav>
</header>
<body>

</body>
</html>
