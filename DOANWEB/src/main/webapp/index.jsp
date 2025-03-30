<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/sanpham.css">
</head>

<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />


    <div class="container1">
        <div class="gallery-display-area">
            <div class="gallery-wrap">
                <div class="image">
                    <img src="./image/home1.jpg" alt="1  ">

                </div>
                <div class="image">
                    <img src="./image/home2.jpg" alt="1">

                </div>
                <div class="image">
                    <img src="./image/home3.jpg" alt="1"> <!-- Hình ảnh GIF mới -->

                </div>
                <div class="image">
                    <img src="./image/home4.jpg" alt="1">

                </div>
                <div class="image">
                    <img src="./image/home5.jpg" alt="1">

                </div>

            </div>
        </div>

        <div id="main-container-index">

            <div id="main-contain-product-index">


                <div class="healine-index">
                    <h3>SẢN PHẨM NỔI BẬT</h3>
                </div>
                <ul class="product">
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat1.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Cougar</a>
                                <a href="" class="product-name">Giày Patin Người Lớn Cougar MZS 307C</a>
                                <div class="product-price">Giá: 1.090.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat2.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat"> HOT</a>
                                <a href="" class="product-name">Giày Patin Người Lớn HOT 2</a>
                                <div class="product-price">Giá: 1.190.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat3.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat"> TNT</a>
                                <a href="" class="product-name">Giày Patin Người Lớn TNT QS 2</a>
                                <div class="product-price">Giá: 1.350.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat4.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Calary</a>
                                <a href="" class="product-name">Giày Patin Người Lớn Calary C9</a>
                                <div class="product-price">Giá: 1.490.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat5.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Flying Eagle</a>
                                <a href="" class="product-name">Giày patin người lớn Flying Eagle B3S</a>
                                <div class="product-price">Giá: 2.500.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat6.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Flying Eagle</a>
                                <a href="" class="product-name">Giày Patin Người Lớn Flying Eagle F1S+ Màu Đen</a>
                                <div class="product-price">Giá: 2.199.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="../image/noibat7.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Flying Eagle</a>
                                <a href="" class="product-name">Giày Patin Người Lớn Flying Eagle B5S</a>
                                <div class="product-price">Giá: 1.790.000 đ</div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="product-item">
                            <div class="product-top">
                                <a href="" class="product-thumb">
                                    <img src="image/noibat8.jpg" alt="" />
                                </a>
                                <div class="button-container">
                                    <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                    <a href="chitietsanpham.html" class="view-details">Xem Chi Tiết</a>
                                </div>
                            </div>
                            <div class="product-info">
                                <a href="" class="product-cat">Flying Eagle</a>
                                <a href="" class="product-name">Giày Trượt Patin Người Lớn Flying Eagle B5S</a>
                                <div class="product-price"> Giá: 2.590.000 đ</div>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>
        </div>



        <jsp:include page="comon/footer.jsp" >

</body>

</html>