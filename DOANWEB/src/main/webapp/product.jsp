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
</head>

<body>
<div id="wrapper">

    <jsp:include page="comon/header.jsp"/>

    <div id="main-container">

        <div id="main-contain-product">

            <div class="healine">
                <h3>SẢN PHẨM MỚI</h3>
            </div>
            <div class="sort-buttons">
                <button>Sắp xếp</button>
                <div class="dropdown-content">
                    <a href="stanggian.html">
                        <button onclick="sortProducts('asc')">Tăng dần</button>
                    </a>
                    <a href="sgiamgian.html">
                        <button onclick="sortProducts('desc')">Giảm dần</button>
                    </a>
                </div>
            </div>
            <ul class="product">

                <c:if test="${not empty products}">
                    <c:forEach var="p" items="${products}">
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="" class="product-thumb">
                                        <img src="${p.img}" alt="" />
                                    </a>
                                    <div class="button-container">
                                        <a href="giohang.html" class="buy-now">Mua Ngay</a>
                                        <a href="detail?pid=${p.id}" class="view-details">Xem Chi Tiết</a>
                                    </div>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-cat">${p.name}</a>
                                    <a href="" class="product-name">${p.title}</a>
                                    <div class="product-price">${p.price}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>


        <script src="js/script.js"></script>
    </div>
    <jsp:include page="comon/footer.jsp"/>

</div>
</body>

</html>