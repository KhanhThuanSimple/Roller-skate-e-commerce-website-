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
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .order-history {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .status-badge {
            font-size: 0.9rem;
        }

        .btn-details {
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp"/>

<div class="container mt-5">
    <!-- Header -->
    <div class="text-center mb-4">
        <h1 class="text-primary">Lịch Sử Đơn Hàng</h1>
        <p class="text-muted">Xem lại các đơn hàng mà bạn đã đặt</p>
    </div>

    <!-- Order History Table -->
    <div class="order-history">
        <table class="table table-striped">
            <thead class="table-light">


            <tr>
                <th></th>
                <th>Mã Đơn Hàng</th>
                <th>Tên Khách Hàng</th>
                <th>Trạng Thái</th>
                <th>Tổng Tiền (VNĐ)</th>
                <th>Hành Động</th>
            </tr>
            </thead>
            <c:forEach var="p" items="${list}">
            <tbody>
            <tr>
                <td></td>
                <td>#${p.id}</td>
                <td>${p.name}</td>
                <td><span class="badge bg-success status-badge">${p.status}</span></td>
                <td>${p.totalAmount}</td>
                <td>
                    <a href="order-details.html?id=123456" class="btn btn-primary btn-sm btn-details">Xem Chi Tiết</a>
                </td>
            </tr>


            </tbody>
            </c:forEach>
        </table>

    </div>

    <!-- Back to Home -->
    <div class="text-center mt-4">
        <a href="home" class="btn btn-secondary">Quay Lại Trang Chủ</a>
    </div>
</div>
<jsp:include page="comon/footer.jsp"/>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
