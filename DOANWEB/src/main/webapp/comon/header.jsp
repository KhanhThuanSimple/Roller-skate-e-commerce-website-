<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22-Dec-24
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header id="header">
  <nav class="container">
    <a href="./index.html" id="logo"><img src="./image/logo1.png" alt="logo"></a>
    <form id="search-form" action="timkiem.html" method="get">
      <input type="text" name="query" placeholder="Tìm kiếm...">
      <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
    </form>
    <ul id="main-menu">
      <li><a href="index.html">TRANG CHỦ</a></li>
      <li>
        <a href="sanpham.html"> SẢN PHẨM</a>
        <ul class="sub-menu">
          <li><a href="sanphamnguoilon.html">Giày Patin Người Lớn</a></li>
          <li><a href="sanphamtreem.html">Giày Patin Trẻ Em</a></li>
          <li><a href="sanphamphukien.html">Phụ Kiện Giày Patin</a></li>
        </ul>
      </li>
      <li><a href="gioithieu.html">GIỚI THIỆU</a></li>
      <li><a href="lienhe.html">LIÊN HỆ</a></li>
      <li class="tooltip">
        <a href="giohang.html" title="Giỏ hàng"><i class="fa-solid fa-cart-shopping"></i></a>
        <span class="tooltiptext">Giỏ hàng</span>
      </li>
      <li class="tooltip">
        <a href="canhan.html" title="Cá nhân"><i class="fa-solid fa-user"></i></a>
        <span class="tooltiptext">Cá nhân</span>
      </li>
    </ul>
  </nav>
</header>
<body>

</body>
</html>
