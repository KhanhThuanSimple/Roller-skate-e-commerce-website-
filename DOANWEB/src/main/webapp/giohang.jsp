<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


    <% User user = (User) session.getAttribute("auth"); %>
    <!-- Main Content Section -->
    <div class="content-giohang">


        <c:if test="${not empty cart}">
            <c:forEach var="p" items="${cart}">
                <div class="cart-item">
                    <div class="item-image">
                        <img src="${p.img_path}" alt="Giày Patin Flying Eagle X3">
                    </div>
                    <div class="item-details">
                        <h3>${p.product_name}
                        </h3>
                        <p>Đơn giá: <span class="price">${p.price}</span></p>
                        <p>Số lượng:
                            <button class="qty-btn">-</button>
                            <input type="number" value="${p.amount}" class="qty-input" data-product-id="${p.product_id}">
                            <button class="qty-btn">+</button>
                        </p>
                        <p>Thành tiền: <span id="total-price" class="total-price">${p.price}</span></p>
                    </div>
                    <button class="delete-item">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </div>
            </c:forEach>
        </c:if>

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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $(".delete-item").click(function() {
            const productId = $(this).closest(".cart-item").find(".qty-input").data("product-id");
            const username = "<%= session.getAttribute("username") %>"; // Lấy username từ input ẩn

            $.ajax({
                type: "POST",
                url: "/DOANWEB/removeCartItem",
                data: { product_id: productId, username: username }, // Gửi username
                success: function(response) {
                    if (response.success) {
                        // Xóa sản phẩm khỏi DOM
                        $(this).closest(".cart-item").remove();
                    } else {
                        alert("Không thể xóa sản phẩm.");
                    }
                }.bind(this),
                error: function() {
                    alert("Có lỗi xảy ra.");
                }
            });
        });
    });
</script>

</html>
