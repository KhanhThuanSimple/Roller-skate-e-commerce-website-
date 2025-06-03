<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="./css/sanpham.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/checkout.css">
</head>

<body>
<div id="wrapper">
    <!-- Header -->
    <jsp:include page="comon/header.jsp" />

    <div class="container my-5">
        <h2 class="mb-4">Thanh Toán</h2>
        <div class="d-flex justify-content-end mb-4">
            <a href="ShowCart" class="btn btn-secondary">Giỏ Hàng </a>
        </div>

        <!-- Danh sách sản phẩm -->
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-primary text-white">
                <h5 class="m-0">Danh sách sản phẩm</h5>
            </div>
            <div class="card-body">
                <c:forEach items="${sessionScope.cart.list}" var="cp">
                    <div class="row border-bottom py-2">
                        <div class="col-md-2">
                            <img src="${cp.img}" class="img-fluid" alt="">
                        </div>
                        <div class="col-md-6">
                            <h6>${cp.title}</h6>
                            <p>Đơn giá: <span class="fw-bold text-danger">${cp.price}đ</span></p>
                            <p>Số lượng: <span class="fw-bold">${cp.quantity}</span></p>
                        </div>
                        <div class="col-md-4 text-end">
                            <p>Thành tiền: <span class="fw-bold text-primary">${cp.price * cp.quantity}đ</span></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Thay thế phần nhập mã giảm giá cũ bằng đoạn code này -->
        <div class="summary-item">
            <div class="mb-3">
                <label class="form-label">Mã giảm giá</label>
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="" placeholder="Nhập mã hoặc chọn bên dưới">
                    <button class="btn btn-outline-primary" type="button" id="applyCoupon">Áp dụng</button>
                </div>

                <!-- Danh sách mã giảm giá dạng dropdown -->
                <div class="coupon-dropdown mb-3">
                    <button class="btn btn-sm btn-link p-0 text-decoration-none" type="button" data-bs-toggle="collapse" data-bs-target="#couponList">
                        <i class="bi bi-tag-fill me-1"></i> Chọn từ mã giảm giá khả dụng
                    </button>

                    <div class="collapse mt-2" id="couponList">
                        <div class="card card-body p-2">
                            <div class="coupon-list">
                                <c:forEach var="coupon" items="${availableCoupons}">
                                    <div class="coupon-item mb-2 p-2 border rounded"
                                         data-code="${coupon.couponCode}"
                                         data-type="${coupon.discountType}"
                                         data-value="${coupon.discountValue}"
                                         data-min="${coupon.minOrderAmount}">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                        <span class="badge ${coupon.discountType == 'FIXED' ? 'bg-primary' : 'bg-success'} me-2">
                                                ${coupon.discountType == 'FIXED' ? 'Giảm tiền' : 'Giảm %'}
                                        </span>
                                                <strong>${coupon.couponCode}</strong>
                                            </div>
                                            <button type="button" class="btn btn-sm btn-outline-primary apply-coupon-btn">Chọn</button>
                                        </div>
                                        <div class="coupon-details mt-1 small text-muted">
                                            Giảm
                                            <c:choose>
                                                <c:when test="${coupon.discountType == 'FIXED'}">
                                                    <f:formatNumber value="${coupon.discountValue}" type="currency" currencySymbol="đ"/>
                                                </c:when>
                                                <c:otherwise>
                                                    ${coupon.discountValue}%
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${coupon.minOrderAmount > 0}">
                                                | Đơn tối thiểu: <f:formatNumber value="${coupon.minOrderAmount}" type="currency" currencySymbol="đ"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>

                                <c:if test="${empty availableCoupons}">
                                    <div class="text-center py-2 text-muted">
                                        Hiện không có mã giảm giá khả dụng
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Thông tin vận chuyển -->
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-success text-white">
                <h5 class="m-0">Thông tin vận chuyển</h5>
            </div>
            <div class="card-body">

    <form action="payment" method="post">
        <div class="mb-3">
            <label for="fullName" class="form-label">Họ và tên</label>
            <input type="text" class="form-control" id="fullName" name="fullName" required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label>
            <input type="tel" class="form-control" id="phone" name="phone" required>
        </div>
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-warning">
                <h5 class="m-0">Mã giảm giá & Phương thức thanh toán</h5>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="couponCode" class="form-label">Mã giảm giá</label>
                    <input type="text" class="form-control" id="couponCode" name="discountCode">
                </div>
                <div class="mb-3">
                    <label class="form-label">Phương thức thanh toán</label>
                    <div>
                        <input type="radio" name="paymentMethod" value="COD" checked>
                        <label>Thanh toán khi nhận hàng (COD)</label>
                    </div>
                    <div>
                        <input type="radio" name="paymentMethod" value="Bank">
                        <label>Chuyển khoản ngân hàng</label>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="totalBill" value="${sessionScope.cart.getTotal()}">
        <button type="submit" class="btn btn-primary">Đặt hàng</button>
    </form>
            </div>
        </div>

        <!-- Mã giảm giá và phương thức thanh toán -->


        <!-- Tổng tiền và nút đặt hàng -->
        <div class="p-4 bg-light rounded shadow-sm">
            <div class="d-flex justify-content-between align-items-center">
                <p class="m-0">Tổng tiền: <span class="fw-bold text-primary">${sessionScope.cart.getTotal()}đ</span></p>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="comon/footer.jsp" />
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
