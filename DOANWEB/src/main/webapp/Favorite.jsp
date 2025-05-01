<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">

    <title>Sản phẩm yêu thích</title>
    <style>
        .product-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            padding: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            width: 250px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .product-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .product-name {
            font-weight: bold;
            margin-bottom: 5px;
            font-size: 16px;
        }
        .product-price {
            color: #e53935;
            margin-bottom: 10px;
            font-size: 18px;
            font-weight: 600;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 10px;
            margin-top: 10px;
        }
        .delete-btn {
            background-color: #ff4444;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        .delete-btn:hover {
            background-color: #cc0000;
            transform: translateY(-1px);
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 30px;
            background-color: #f5f5f5;
            border-bottom: 1px solid #ddd;
            margin-bottom: 20px;
        }
        .buy-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: all 0.3s ease;
        }
        .buy-btn:hover {
            background-color: #45a049;
            transform: translateY(-1px);
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }
        .action-buttons input[type="checkbox"] {
            transform: scale(1.2);
            cursor: pointer;
        }
        .product-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            padding: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            width: 250px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .product-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .product-name {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .product-price {
            color: #e53935;
            margin-bottom: 10px;
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .remove-btn {
            background-color: #ff4444;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }
        .remove-btn:hover {
            background-color: #cc0000;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #f5f5f5;
            border-bottom: 1px solid #ddd;
        }
        .buy-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 3px;
            cursor: pointer;
        }
        .buy-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />
    <div class="header">
        <h1>Sản phẩm yêu thích</h1>
    </div>

    <!-- Form riêng cho việc mua hàng -->
<%--    <form id="checkoutForm" action="CheckoutServlet" method="post">--%>
        <div class="product-container">
            <c:forEach var="product" items="${favorites}">
                <div class="product-card">
                    <img src="${product.img}" alt="${product.name}" class="product-img">
                    <div class="product-name">${product.name}</div>
                    <div class="product-price">
                        <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />đ
                    </div>
                    <div class="action-buttons">
                        <input type="checkbox" name="productIds" value="${product.id}">
                        <form method="post" action="listFavorites" style="margin: 0;">
                            <input type="hidden" name="productId" value="${product.id}">
                            <button type="submit" class="delete-btn">
                                <i class="fas fa-trash-alt"></i> Xóa
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div style="text-align: center; margin: 20px;">
            <button type="submit" class="buy-btn">Mua hàng đã chọn</button>
        </div>
<%--    </form>--%>


    <jsp:include page="comon/footer.jsp" />
</div>
</body>
</html>