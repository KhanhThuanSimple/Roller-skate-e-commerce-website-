<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Đơn Hàng</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="admin.css">

    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">

    <style>
        .order-summary {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .total-amount {
            font-weight: bold;
            color: #28a745;
        }
    </style>
</head>
<body>
<div class="container">
    <%@ include file="sidebar.jsp" %>
    <main class="main-content">
        <div class="container mt-5" style="flex-direction: column !important;">
            <!-- Header -->
            <div class="text-center mb-4">
                <h1 class="text-primary">Chi Tiết Đơn Hàng</h1>
                <p class="text-muted">Thông tin chi tiết về đơn hàng của bạn</p>
            </div>

            <div class="order-summary">
                <h4>Mã Đơn Hàng: <span class="text-primary">${list[0].order.id}</span></h4>
                <p>Tên Khách Hàng: <strong>${list[0].order.name}</strong></p>
                <p>Trạng Thái: <span class="badge bg-success">Đã Giao Hàng</span></p>
            </div>

            <!-- Product Table -->
            <div class="mt-4">
                <h4 class="mb-3">Danh Sách Sản Phẩm</h4>
                <table class="table table-bordered">
                    <thead class="table-light">
                    <tr>
                        <th>Tên Sản Phẩm</th>
                        <th>Số Lượng</th>
                        <th>Đơn Giá (VNĐ)</th>
                        <th>Phương Thức Thanh Toán</th>
                        <th>Thành Tiền (VNĐ)</th>
                    </tr>
                    </thead>
                    <c:set var="totalAmount" value="0" /> <!-- Khởi tạo totalAmount -->

                    <c:forEach var="p" items="${list}">
                        <tbody>
                        <tr>
                            <td>${p.product.title}</td>
                            <td>${p.orderItem.quantity}</td>
                            <td>${p.orderItem.price}</td>
                            <td> ${p.order.paymentMethod}</td>
                            <td>${p.orderItem.price*p.orderItem.quantity}</td>
                            <c:set var="totalAmount" value="${totalAmount+p.orderItem.price * p.orderItem.quantity}" />

                        </tr>

                        </tbody>

                    </c:forEach>
                    <tfoot class="table-light">
                    <tr>
                        <td colspan="4" class="text-end">Tổng Cộng</td>
                        <td class="total-amount">${totalAmount}</td>
                    </tr>
                    </tfoot>
                </table>
            </div>

            <!-- Back to Order History -->

        </div>
        <!-- Bootstrap JS -->
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
