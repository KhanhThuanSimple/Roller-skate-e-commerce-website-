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
    <jsp:include page="comon/header.jsp" />

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
<jsp:include page="comon/footer.jsp" />

</div>
</body>

</html>
