<%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>GioHang Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/giohang.css">
</head>

<body>
<div id="wrapper">
    <!-- Header Section -->
    <header id="header">
        <nav class="container">
            <a href="index.html" id="logo"><img src="./image/logo1.png" alt="logo"></a>
            <form id="search-form" action="search.html" method="get">
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


    <!-- Main Content Section -->
    <div class="content-giohang">
        <div class="cart-item">
            <div class="item-image">
                <img src="./image/ENGLE X3.png" alt="Giày Patin Flying Eagle X3">
            </div>
            <div class="item-details">
                <h3>Giày Patin Flying Eagle X3</h3>
                <p>Đơn giá: <span class="price">2.690.000đ</span></p>
                <p>Số lượng:
                    <button class="qty-btn">-</button>
                    <input type="number" value="1" class="qty-input">
                    <button class="qty-btn">+</button>
                </p>
                <p>Thành tiền: <span class="total-price">2.690.000đ</span></p>
            </div>
            <button class="delete-item">
                <i class="fa-solid fa-trash"></i>
            </button>
        </div>

        <div class="cart-item">
            <div class="item-image">
                <img src="./image/S6S.png" alt="Giày Patin Flying Eagle X3">
            </div>
            <div class="item-details">
                <h3>Giày Patin Trẻ Em Flying Eagle S5S
                </h3>
                <p>Đơn giá: <span class="price">1.550.000đ</span></p>
                <p>Số lượng:
                    <button class="qty-btn">-</button>
                    <input type="number" value="1" class="qty-input">
                    <button class="qty-btn">+</button>
                </p>
                <p>Thành tiền: <span id="total-price" class="total-price">1.550.000đ</span></p>
            </div>
            <button class="delete-item">
                <i class="fa-solid fa-trash"></i>
            </button>
        </div>

        <div class="cart-footer">
            <p>Tổng tiền: <span class="grand-total">4.240.000 đ</span></p>
            <button id="XemThemSP" class="btn view-products">XEM THÊM SẢN PHẨM</button>
            <button id="ThanhToan" class="btn checkout">THANH TOÁN</button>
        </div>

    </div>


    <script src="js/giohang.js"></script>
</div>

<!-- Footer Section -->
<footer id="footer">
    <div class="footer-container">
        <div class="footer-section">
            <h4>Chính sách</h4>
            <p>Chính sách chăm sóc khách hàng.<br><a href="chinhsachtrahang.html">Chính sách đổi trả.</a><br><a
                    href="chinhsachthanhtoan.html">Chính sách thanh toán.</a></p>
        </div>
        <div class="footer-section">
            <h4>Liên hệ:</h4>
            <p>Email: NTN@company.com</p>
            <p>Điện thoại: 0383967879</p>
        </div>
        <div class="footer-section">
            <h4>Theo dõi chúng tôi</h4>
            <div class="social-link">
                <p><a href="https://www.facebook.com" target="_blank"><i class="fa-brands fa-facebook"></i></a></p>
                <p><a href="https://www.twitter.com" target="_blank"><i class="fa-brands fa-twitter"></i></a></p>
                <p><a href="https://www.instagram.com" target="_blank"><i class="fa-brands fa-instagram"></i></a>
                </p>
            </div>
        </div>
    </div>
</footer>
</div>
</body>

</html>
