<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>GioHang Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/giohang.css">
</head>

<body>
<div id="wrapper">
    <!-- Header -->
    <jsp:include page="comon/header.jsp" />

    <!-- Main Content -->
    <div class="container my-5">
        <h2 class="mb-4">Giỏ Hàng Của Bạn</h2>

        <c:forEach  items="${sessionScope.cart.list}" var="cp">
            <div class="card mb-3 shadow-sm">
                <div class="row g-0">
                    <div class="col-md-2">
                        <img src="${cp.img}" class="img-fluid rounded-start" alt=""/>
                    </div>
                    <div class="col-md-7">
                        <div class="card-body">
                            <h5 class="card-title">${cp.title}</h5>
                            <p class="card-text">Đơn giá: <span class="fw-bold text-danger">${cp.price} đ</span></p>
                            <div class="input-group" style="max-width: 150px;">
                                <!-- Increase and Decrease buttons -->
                                <button class="btn btn-outline-secondary btn-sm decrease-qty" data-product-id="${cp.id}">
                                    <i class="fa-solid fa-minus"></i>
                                </button>

                                <input type="number" class="form-control text-center qty-input" value="${cp.quantity}" data-product-id="${cp.id}" min="1">


                                <button class="btn btn-outline-secondary btn-sm increase-qty" data-product-id="${cp.id}">
                                    <i class="fa-solid fa-plus"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1 d-flex align-items-center justify-content-center">
                        <!-- New Delete Order button -->
                        <button class="btn btn-danger delete-order" data-product-id="${cp.id}">
                            Xóa
                        </button>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center">
                        <p class="m-0">Thành tiền: <span class="fw-bold text-primary">${cp.price * cp.quantity}đ</span></p>
                    </div>
                    <div class="col-md-1 d-flex align-items-center justify-content-center">
                        <!-- Delete button next to "Thành tiền" -->
                        <button class="btn btn-danger delete-item" data-product-id="${cp.id}">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </div>

                    <div class="col-md-1 d-flex align-items-center justify-content-center">
                        <!-- Delete button next to "Thành tiền" -->
                        <button class="btn btn-danger delete-item" data-product-id="${cp.id}">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>

        <!-- Footer Section -->
        <div class="p-4 mt-4 bg-light rounded shadow-sm">
            <div class="d-flex justify-content-between align-items-center">
                <p class="m-0">Tổng tiền: <span class="fw-bold text-primary">${sessionScope.cart.getTotal()}đ</span></p>
                <a id="XemThemSP" href="product" class="btn btn-outline-secondary me-2">Xem thêm sản phẩm</a>
                <a id="ThanhToan" href="checkout" class="btn btn-primary">Thanh toán</a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="comon/footer.jsp" />
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="./js/giohang.js"></script>
</body>
</html>
