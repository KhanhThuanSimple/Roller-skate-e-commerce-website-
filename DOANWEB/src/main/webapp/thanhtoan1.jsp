<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán | Your Store</title>

    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">

    <!-- CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/sanpham.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/thanhtoan1.css">
    <!-- JavaScript -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>

</head>

<body>
<div id="wrapper">
    <!-- Header -->
    <jsp:include page="comon/header.jsp" />

    <div class="py-5">
        <div class="checkout-container">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home"><i class="bi bi-house-door"></i> Trang chủ</a></li>
                    <li class="breadcrumb-item"><a href="ShowCart">Giỏ hàng</a></li>
                    <li class="breadcrumb-item active">Thanh toán</li>
                </ol>
            </nav>

            <div class="row">
                <!-- Left Column - Order Details -->
                <div class="col-lg-8">
                    <!-- Order Summary -->
                    <div class="checkout-card">
                        <div class="card-header">
                            <i class="bi bi-cart-check"></i> Đơn hàng của bạn
                        </div>
                        <div class="card-body">
                            <c:forEach items="${sessionScope.cart.list}" var="cp">
                                <div class="row product-item align-items-center">
                                    <div class="col-md-2 product-img-container">
                                        <img src="${cp.img}" class="product-img" alt="${cp.title}">
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="mb-2">${cp.title}</h6>
                                        <div class="d-flex">
                                            <p class="me-3 mb-0">Đơn giá: <span class="price-highlight"><f:formatNumber value="${cp.price}" type="currency" currencySymbol="đ"/></span></p>
                                            <p class="mb-0">Số lượng: <span class="fw-bold">${cp.quantity}</span></p>
                                        </div>
                                    </div>
                                    <div class="col-md-4 text-md-end">
                                        <p class="mb-0">Thành tiền: <span class="price-highlight"><f:formatNumber value="${cp.price * cp.quantity}" type="currency" currencySymbol="đ"/></span></p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Shipping Information -->
                    <div class="checkout-card">
                        <div class="card-header">
                            <i class="bi bi-truck"></i> Thông tin giao hàng
                        </div>
                        <div class="card-body">
                            <div class="address-selector">
                                <h5 class="mb-4">Địa chỉ nhận hàng</h5>

                                <div class="row">
                                    <div class="col-md-4 select-box">
                                        <label for="province" class="form-label">Tỉnh/Thành phố</label>
                                        <select id="province" class="form-select" onchange="loadDistricts()">
                                            <option value="">-- Chọn tỉnh/thành phố --</option>
                                        </select>
                                        <span id="provinceLoading" class="loading" style="display: none;">Đang tải...</span>
                                    </div>

                                    <div class="col-md-4 select-box">
                                        <label for="district" class="form-label">Quận/Huyện</label>
                                        <select id="district" class="form-select" onchange="loadWards()" disabled>
                                            <option value="">-- Chọn quận/huyện --</option>
                                        </select>
                                        <span id="districtLoading" class="loading" style="display: none;">Đang tải...</span>
                                    </div>

                                    <div class="col-md-4 select-box">
                                        <label for="ward" class="form-label">Phường/Xã</label>
                                        <select id="ward" class="form-select" onchange="updateAddressText()" disabled>
                                            <option value="">-- Chọn phường/xã --</option>
                                        </select>
                                        <span id="wardLoading" class="loading" style="display: none;">Đang tải...</span>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="addressDetail" class="form-label">Địa chỉ cụ thể</label>
                                    <input type="text" class="form-control" id="addressDetail" placeholder="Số nhà, tên đường, tòa nhà...">
                                </div>
                                <div class="mb-3">
                                    <label for="addressDetail" class="form-label">Họ và tên</label>
                                    <input type="text" class="form-control" id="name" placeholder="Họ và tên khách hàng.. ">
                                </div>

                                <div class="mb-3">
                                    <label for="phone" class="form-label">Số điện thoại</label>
                                    <input type="tel" class="form-control" id="phone" placeholder="Nhập số điện thoại nhận hàng">
                                </div>

                                <div class="mb-3">
                                    <label for="note" class="form-label">Ghi chú (tùy chọn)</label>
                                    <textarea class="form-control" id="note" rows="2" placeholder="Ghi chú về đơn hàng..."></textarea>
                                </div>

                                <div class="alert alert-info mt-3">
                                    <i class="bi bi-info-circle"></i> Phí vận chuyển sẽ được tính sau khi bạn chọn đầy đủ địa chỉ.
                                </div>

                                <div id="selectedAddress" class="alert alert-light mt-3" style="display: none;">
                                    <strong>Địa chỉ giao hàng:</strong>
                                    <span id="addressText"></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Payment Method -->
                    <div class="checkout-card">
                        <div class="card-header">
                            <i class="bi bi-credit-card"></i> Phương thức thanh toán
                        </div>
                        <div class="card-body">
                            <div class="payment-method">
                                <input type="radio" id="cod" name="paymentMethod" value="COD" checked>
                                <label for="cod">
                                    <i class="bi bi-cash-coin"></i> Thanh toán khi nhận hàng (COD)
                                </label>
                            </div>

                            <div class="payment-method">
                                <input type="radio" id="bank" name="paymentMethod" value="Bank">
                                <label for="bank">
                                    <i class="bi bi-bank"></i> Chuyển khoản ngân hàng
                                </label>
                            </div>


                        </div>
                    </div>
                </div>

                <!-- Right Column - Order Summary -->
                <div class="col-lg-4">
                    <div class="summary-card sticky-top" style="top: 20px;">
                        <h5 class="summary-title"><i class="bi bi-receipt"></i> Tóm tắt đơn hàng</h5>

                        <div class="summary-item">
                            <span>Tạm tính:</span>
                            <span><f:formatNumber value="${sessionScope.cart.getTotal()}" type="currency" currencySymbol="đ"/></span>
                        </div>

                        <div class="summary-item">
                            <span>Phí vận chuyển:</span>
<%--                            <span id="result">--</span>--%>
                                <div id="result" style=" margin-left: 125px;"></div>

                            <div class="section">

                                <form id="shippingForm">
                                    <input type="hidden" id="fromDistrictId" name="fromDistrictId" value="1463">
                                    <input type="hidden" id="fromWardCode" name="fromWardCode" value="21806">
                                    <input type="hidden" id="toDistrictId" name="toDistrictId">
                                    <input type="hidden" id="toWardCode" name="toWardCode">
                                    <input type="hidden" name="weight" value="1000"> <!-- 1kg -->
                                    <input type="hidden" name="length" value="20">   <!-- 20cm -->
                                    <input type="hidden" name="width" value="20">    <!-- 20cm -->
                                    <input type="hidden" name="height" value="10">   <!-- 10cm -->
                                </form>

<%--                                <div id="result" style="margin-top: 20px;"></div>--%>
                            </div>
                        </div>

                        <div class="summary-item">
                            <span>Giảm giá:</span>
                            <span id="discountAmount">0đ</span>
                        </div>

                        <div class="summary-item">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" id="couponCode" placeholder="Mã giảm giá">
                                <button class="btn btn-outline-secondary" type="button" id="applyCoupon">Áp dụng</button>
                            </div>
                        </div>

                        <div class="summary-total">
                            <span>Tổng cộng:</span>
                            <span id="totalAmount"><f:formatNumber value="${sessionScope.cart.getTotal()}" type="currency" currencySymbol="đ"/></span>
                        </div>

                        <form action="bank-code" method="post" id="checkoutForm">
                            <!-- Các trường ẩn để lưu thông tin -->
                            <input type="hidden" name="province" id="provinceInput">
                            <input type="hidden" name="district" id="districtInput">
                            <input type="hidden" name="ward" id="wardInput">
                            <input type="hidden" name="totalBill" value="${sessionScope.cart.getTotal()}">
                            <input type="hidden" name="paymentMethod" id="paymentMethodInput" value="COD">
                            <input type="hidden" name="discountCode" id="discountCodeInput">
                            <input type="hidden" name="shippingAddress" id="shippingAddressInput">
                            <input type="hidden" name="name" id="nameInput">
                            <input type="hidden" name="phone" id="phoneInput">
                            <input type="hidden" name="note" id="noteInput">
                            <input type="hidden" name="shippingFee" id="shippingFeeInput">

                            <button type="submit" class="btn btn-primary w-100 mt-3 py-3">
                                <i class="bi bi-check-circle"></i> ĐẶT HÀNG NGAY
                            </button>
                        </form>

                        <div class="mt-3 text-center">
                            <small class="text-muted">Bằng cách đặt hàng, bạn đồng ý với <a href="#">Điều khoản dịch vụ</a> của chúng tôi</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="comon/footer.jsp" />
</div>

<!-- Hidden form for shipping calculation -->


<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/shipping.js"></script>
<script>
    $(document).ready(function() {
        // Xử lý khi phương thức thanh toán thay đổi
        $('input[name="paymentMethod"]').change(function() {
            $('#paymentMethodInput').val($(this).val());
            if ($(this).val() === 'Bank') {
                $('#bankInfo').show();
            } else {
                $('#bankInfo').hide();
            }
        });

        // Xử lý khi submit form
        $('#checkoutForm').submit(function(e) {
            // Lấy các giá trị từ form
            const province = $('#province').val();
            const provinceText = $('#province option:selected').text();
            const district = $('#district').val();
            const districtText = $('#district option:selected').text();
            const ward = $('#ward').val();
            const wardText = $('#ward option:selected').text();
            const addressDetail = $('#addressDetail').val();
            const name = $('#name').val();
            const phone = $('#phone').val();
            const note = $('#note').val();
            const paymentMethod = $('input[name="paymentMethod"]:checked').val();
            const shippingFee = $('#result').text().replace(/[^\d]/g, ''); // Lấy phí vận chuyển

            // Kiểm tra dữ liệu bắt buộc
            if (!province || !district || !ward) {
                alert('Vui lòng chọn đầy đủ địa chỉ giao hàng');
                e.preventDefault();
                return;
            }

            if (!addressDetail) {
                alert('Vui lòng nhập địa chỉ cụ thể');
                e.preventDefault();
                return;
            }

            if (!name) {
                alert('Vui lòng nhập họ và tên');
                e.preventDefault();
                return;
            }
            if (!phone) {
                alert('Vui lòng nhập số điện thoại');
                e.preventDefault();
                return;
            }

            // Tạo địa chỉ đầy đủ
            const fullAddress = addressDetail + ', ' + wardText + ', ' + districtText + ', ' + provinceText;

            // Cập nhật giá trị vào các trường ẩn
            $('#provinceInput').val(provinceText);
            $('#districtInput').val(districtText);
            $('#wardInput').val(wardText);
            $('#shippingAddressInput').val(fullAddress);
            $('#nameInput').val(name);
            $('#phoneInput').val(phone);
            $('#noteInput').val(note);
            $('#paymentMethodInput').val(paymentMethod);
            $('#shippingFeeInput').val(shippingFee || '0');
        }); });
    function updateShippingFee(fee) {
        // Nếu không truyền fee, lấy từ div #result
        if (fee === undefined) {
            const resultText = $('#result').text();
            fee = resultText ? parseInt(resultText.replace(/[^\d]/g, '')) : 0;
        }

        // Hiển thị phí vận chuyển
        $('#result').text(fee.toLocaleString('vi-VN') + 'đ');

        // Lấy tổng ban đầu từ JSP
        const originalTotal = ${sessionScope.cart.getTotal()};

        // Lấy giảm giá nếu có
        const discountText = $('#discountAmount').text();
        const discount = discountText ? parseInt(discountText.replace(/[^\d]/g, '')) : 0;

        // Tính tổng mới
        const newTotal = originalTotal + fee - discount;

        // Cập nhật giao diện
        $('#totalAmount').text(newTotal.toLocaleString('vi-VN') + 'đ');

        // Cập nhật vào input ẩn để submit
        $('#shippingFeeInput').val(fee);
    }

</script>
<%--<script src="./js/shipping.js"></script>--%>

</body>
</html>