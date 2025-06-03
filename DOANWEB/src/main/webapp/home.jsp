<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/sanpham.css">

</head>
<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp"/>


    <div class="container1">
        <div class="gallery-display-area">
                <div class="image">
                    <img src="./image/home1.jpg" alt="1  ">

                </div>

            </div>
        </div>


        <div id="main-contain-product">


            <div class="healine-index">
                <h3>SẢN PHẨM MỚI NHẤT</h3>
            </div>

            <ul class="product">

                <c:if test="${not empty productNew}">
                    <c:forEach var="p" items="${productNew}">
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <c:choose>
                                        <c:when test="${p.favorited}">
                                            <!-- Sản phẩm đã có trong danh sách yêu thích, hiển thị nút xóa yêu thích -->
                                            <form action="${pageContext.request.contextPath}/favorite" method="post" style="display: inline;">
                                                <input type="hidden" name="productId" value="${p.id}">
                                                <input type="hidden" name="action" value="remove">
                                                <button type="submit" style="border: none; background: none; cursor: pointer;">
                                                    <!-- Trái tim đầy (đã yêu thích) -->
                                                    <i class="fa-solid fa-heart" style="color: #e74c3c !important; font-size: 20px; padding: 20px; margin-left: 145px"></i>
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Sản phẩm chưa có trong danh sách yêu thích, hiển thị nút thêm yêu thích -->
                                            <form action="${pageContext.request.contextPath}/favorite" method="post" style="display: inline;">
                                                <input type="hidden" name="productId" value="${p.id}">
                                                <input type="hidden" name="action" value="add">
                                                <button type="submit" style="border: none; background: none; cursor: pointer;">
                                                    <!-- Trái tim viền (chưa yêu thích) -->
                                                    <i class="fa-regular fa-heart" style="color: #e74c3c; font-size: 20px;padding: 20px;margin-left: 145px"></i>
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>






                                    <a href="" class="product-thumb">
                                        <img src="${p.img}" alt=""/>
                                    </a>
                                    <div class="button-container">
                                        <a href="add-cart?pid=${p.id}" class="buy-now">Thêm Vào Giỏ Hàng</a>
                                        <a href="detail?pid=${p.id}" class="view-details">Xem Chi Tiết Sản Phẩm</a>
                                    </div>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-cat">${p.name}</a>
                                    <a href="" class="product-name">${p.title}</a>
                                    <div class="product-price">
                                        <fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0" />đ
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>

        </div>
    </div>
<script src="js/script.js"></script>
</div>
<jsp:include page="comon/footer.jsp"/>

</div>
</body>

</html>