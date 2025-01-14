<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/checkout.css">
    <link rel="stylesheet" href="./css/donhang.css">
    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
<div id="wrapper">
    <!-- Header -->
    <jsp:include page="comon/header.jsp" />

    <div class="container my-5">
        <h2 class="mb-4">Thanh Toán</h2>

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

        <!-- Thông tin vận chuyển -->
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-success text-white">
                <h5 class="m-0">Thông tin vận chuyển</h5>
            </div>
            <div class="card-body">
                <form action="placeOrder" method="post">
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
                </form>
            </div>
        </div>

        <!-- Mã giảm giá và phương thức thanh toán -->
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-warning">
                <h5 class="m-0">Mã giảm giá & Phương thức thanh toán</h5>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="couponCode" class="form-label">Mã giảm giá</label>
                    <input type="text" class="form-control" id="couponCode" name="couponCode">
                </div>
                <div class="mb-3">
                    <label class="form-label">Phương thức thanh toán</label>
                    <div>
                        <input type="radio" id="cod" name="paymentMethod" value="COD" checked>
                        <label for="cod">Thanh toán khi nhận hàng (COD)</label>
                    </div>
                    <div>
                        <input type="radio" id="bank" name="paymentMethod" value="Bank">
                        <label for="bank">Chuyển khoản ngân hàng</label>
                    </div>
                </div>
            </div>
        </div>

        <!-- Tổng tiền và nút đặt hàng -->
        <div class="p-4 bg-light rounded shadow-sm">
            <div class="d-flex justify-content-between align-items-center">
                <p class="m-0">Tổng tiền: <span class="fw-bold text-primary">${sessionScope.cart.getTotal()}đ</span></p>
                <button type="submit" class="btn btn-primary" form="checkoutForm">Đặt hàng</button>
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
