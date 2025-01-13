<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/sanpham.css">
    <link rel="stylesheet" href="./css/chitietsanpham.css">
</head>

<body>
<div id="wrapper">

    <jsp:include page="comon/header.jsp"/>


    <div id="main-container">
        <!-- Main Content Section -->
        <div class="card-wrapper">
            <div class="card">
                <!-- card left -->
                <div class="product-imgs">
                    <div class="img-display">
                        <div class="img-showcase">
                            <img src="${detail.img}"/>

                        </div>
                    </div>

                </div>
                <!-- card right -->
                <div class="product-content">
                    <h2 class="product-title">${detail.title}</h2>     <div class="product-rating">
                        <i class="fas "></i>

                        <span>Thương hiệu:${detail.name} </span>
                    </div>

                    <div class="product-price">
                        <p class="new-price">Giá: <span>${detail.price}đ</span></p>
                    </div>

                    <div class="product-detail">
                        <h2>Thông tin sản phẩm:</h2>
                        <p>
                            <span id="descriptionText">${detail.description}</span>
                        </p>
                        <ul>
                            <h2>Ưu đãi:</h2>
                            <li><span id="offerText">${detail.offer}</span></li>
                        </ul>

                        <script>
                            document.addEventListener("DOMContentLoaded", function() {
                                // Định dạng thông tin sản phẩm
                                const descriptionElement = document.getElementById('descriptionText');
                                const descriptionText = descriptionElement.innerHTML;
                                descriptionElement.innerHTML = descriptionText.replace(/\./g, '.<br>');

                                // Định dạng ưu đãi
                                const offerElement = document.getElementById('offerText');
                                const offerText = offerElement.innerHTML;
                                offerElement.innerHTML = offerText.replace(/\./g, '.<br>');
                            });
                        </script>
                    </div>


                    <div class="purchase-info">
                        <input type="number" min="0" value="1"/>

                        <button type="button" class="btn" id="first">
                            Thêm vào giỏ hàng <i class="fas fa-shopping-cart"></i>
                        </button>

                        <button type="button" class="btn" id="last">Đặt hàng nhanh</button>
                    </div>


                </div>
            </div>

            <div class="modal" id="productModal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div class="modal-header">
                        <h2> Giày Patin Người Lớn Cougar MZS 307C 3 Màu Đen/ Trắng/ Đỏ</h2>
                        <img src="../image/chitiet3.jpg "
                             alt="Giày Patin Người Lớn Cougar MZS 307C 3 Màu Đen/ Trắng/ Đỏ"/>
                    </div>
                    <div class="modal-body">
                        <p>Giày Patin Người Lớn Eagle A5 với thiết kế mạnh mẽ, phù hợp cho người lớn, chất liệu bền
                            bỉ...</p>
                        <div class="modal-price">Giá: 1.200.000đ</div>
                        <div class="buttons-container">
                            <button class="btn-pay-now" id="thanhtoan">Thanh toán ngay</button>
                            <button class="btn-continue-shopping">Tiếp tục mua hàng</button>
                        </div>
                    </div>
                    <div class="modal-footer user-info">
                        <input type="text" placeholder="Họ và tên của bạn" required/>
                        <input type="tel" placeholder="Điện thoại của bạn" required/>
                        <input type="text" placeholder="Địa chỉ của bạn" required/>
                    </div>

                </div>
            </div>

            <script src="js/script.js"></script>
        </div>
        <script src="js/chitietsanpham.js"></script>
    </div>

    <jsp:include page="comon/footer.jsp"/>
</div>
</body>

</html>
