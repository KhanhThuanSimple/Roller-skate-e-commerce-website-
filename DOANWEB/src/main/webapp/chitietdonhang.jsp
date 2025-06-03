<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Đơn Hàng #${not empty list[0].order.id ? list[0].order.id : ''}</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #3498db;
            --success-color: #28a745;
            --info-color: #17a2b8;
            --warning-color: #ffc107;
            --danger-color: #dc3545;
            --light-gray: #f8f9fa;
        }

        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .order-header {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 1.5rem;
            border-radius: 8px;
            margin-bottom: 2rem;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .order-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            margin-bottom: 1.5rem;
            overflow: hidden;
        }

        .order-card-header {
            background-color: var(--light-gray);
            padding: 1rem 1.5rem;
            border-bottom: 1px solid #eee;
            font-weight: 600;
        }

        .order-card-body {
            padding: 1.5rem;
        }

        .order-info-grid {
            display: grid;
            grid-template-columns: 160px 1fr;
            gap: 0.75rem;
            margin-bottom: 0.75rem;
        }

        .order-info-label {
            font-weight: 600;
            margin-left: 90px;

        }

        .status-badge {
            padding: 0.35rem 0.75rem;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
        }

        .status-delivered {
            background-color: #d4edda;
            color: var(--success-color);
            margin-right: 30px;
        }

        .product-table {
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        .product-table thead th {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 1rem;
        }

        .product-table tbody td {
            vertical-align: middle;
            padding: 1rem;
            border-bottom: 1px solid #eee;
        }

        .product-table tfoot td {
            font-weight: 600;
            background-color: var(--light-gray);
            padding: 1rem;
        }

        .total-amount {
            color: var(--success-color);
            font-size: 1.1rem;
        }

        .product-img {
            width: 60px;
            height: 60px;
            object-fit: cover;
            border-radius: 4px;
            margin-right: 1rem;
        }

        .btn-primary {
            background-color: var(--secondary-color);
            border-color: var(--secondary-color);
            padding: 0.5rem 1.5rem;
        }

        .back-link {
            color: var(--secondary-color);
            text-decoration: none;
            font-weight: 500;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .empty-state {
            text-align: center;
            padding: 2rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp"/>

<div class="container py-4">
    <!-- Breadcrumb -->
    <nav aria-label="breadcrumb" class="mb-4">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
            <li class="breadcrumb-item"><a href="lichsu">Lịch sử đơn hàng</a></li>
            <li class="breadcrumb-item active">Chi tiết đơn hàng</li>
        </ol>
    </nav>

    <c:choose>
        <c:when test="${empty list}">
            <div class="empty-state">
                <i class="fas fa-box-open fa-3x mb-3"></i>
                <h4>Không tìm thấy đơn hàng</h4>
                <p>Không có thông tin chi tiết cho đơn hàng này</p>
                <a href="lichsu" class="btn btn-primary mt-3">
                    <i class="fas fa-arrow-left me-2"></i>Quay lại lịch sử
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <!-- Order Header -->
                <div class="order-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h2 class="h4 mb-2">Chi Tiết Đơn Hàng</h2>
                            <p class="mb-0">Mã đơn hàng: <strong>#${list[0].order.id}</strong></p>
                        </div>
                        <div class="order-info-label">Trạng thái thanh toán:</div>

                        <span class="status-badge status-delivered">
                                <c:choose>
                                    <c:when test="${list[0].order.status eq 'Đã thanh toán ngân hàng' or list[0].order.status eq 'Thanh toán khi nhận hàng'}">
                                        Đã thanh toán
                                    </c:when>
                                    <c:otherwise>
                                        Đã thanh toán
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        <div class="order-info-label">Trạng thái đơn hàng:</div>
                        <div class="status-badge status-delivered">
                            <c:choose>
                                <c:when test="${list[0].order.status eq 'Đã thanh toán ngân hàng' or list[0].order.status eq 'Thanh toán khi nhận hàng'}">
                                    Đã giao hàng
                                </c:when>
                                <c:otherwise>
                                    Đã giao hàng
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </div>
                </div>

            <div class="row">
                <!-- Order Information -->
                <div class="col-lg-4 mb-4">
                    <div class="order-card">
                        <div class="order-card-header">
                            <i class="fas fa-info-circle me-2"></i>Thông tin đơn hàng
                        </div>
                        <div class="order-card-body">
                            <div class="order-info-grid">
                                <div class="order-info-label">Ngày đặt hàng:</div>
                                <div class="order-info-value">
                                    <f:formatDate value="${list[0].order.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                </div>

                                <div class="order-info-label">Khách hàng:</div>
                                <div class="order-info-value">${list[0].order.name}</div>

                                <div class="order-info-label">Điện thoại:</div>
                                <div class="order-info-value">${list[0].order.phone}</div>

                                <div class="order-info-label">Địa chỉ:</div>
                                <div class="order-info-value">
                                        ${list[0].order.address}, ${list[0].order.ward},
                                        ${list[0].order.district}, ${list[0].order.province}
                                </div>

                                <div class="order-info-label">PT thanh toán:</div>
                                <div class="order-info-value">${list[0].order.paymentMethod}</div>

                                <div class="order-info-label">Phí vận chuyển:</div>
                                <div class="order-info-value">
                                    <f:formatNumber value="${list[0].order.shippingFee}" type="currency" currencySymbol="₫"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Order Items -->
                <div class="col-lg-8">
                    <div class="order-card">
                        <div class="order-card-header">
                            <i class="fas fa-box-open me-2"></i>Sản phẩm đã đặt
                        </div>
                        <div class="order-card-body p-0">
                            <div class="table-responsive">
                                <table class="table product-table">
                                    <thead>
                                    <tr>
                                        <th>Sản phẩm</th>
                                        <th>Đơn giá</th>
                                        <th>Số lượng</th>
                                        <th>Thành tiền</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="totalAmount" value="0" />
                                    <c:forEach var="p" items="${list}">
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <img src="${p.product.img}" alt="${p.product.title}" class="product-img">
                                                    <div>${p.product.title}</div>
                                                </div>
                                            </td>
                                            <td><f:formatNumber value="${p.orderItem.price}" type="currency" currencySymbol="₫"/></td>
                                            <td>${p.orderItem.quantity}</td>
                                            <td><f:formatNumber value="${p.orderItem.price * p.orderItem.quantity}" type="currency" currencySymbol="₫"/></td>
                                            <c:set var="totalAmount" value="${totalAmount + p.orderItem.price * p.orderItem.quantity}" />
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="3" class="text-end">Tạm tính:</td>
                                        <td><f:formatNumber value="${totalAmount}" type="currency" currencySymbol="₫"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" class="text-end">Phí vận chuyển:</td>
                                        <td><f:formatNumber value="${list[0].order.shippingFee}" type="currency" currencySymbol="₫"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" class="text-end">Tổng cộng:</td>
                                        <td class="total-amount">
                                            <f:formatNumber value="${totalAmount + list[0].order.shippingFee}" type="currency" currencySymbol="₫"/>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- Order Actions -->
                    <div class="d-flex justify-content-between align-items-center mt-4">
                        <a href="lichsu" class="back-link">
                            <i class="fas fa-arrow-left me-2"></i>Quay lại lịch sử đơn hàng
                        </a>
                        <div>
                            <button class="btn btn-primary me-2">
                                <i class="fas fa-print me-2"></i>In hóa đơn
                            </button>
                            <button class="btn btn-outline-secondary">
                                <i class="fas fa-headset me-2"></i>Hỗ trợ
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="comon/footer.jsp"/>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Add any necessary JavaScript here
    document.addEventListener('DOMContentLoaded', function() {
        // Print button functionality
        document.querySelector('.btn-primary .fa-print').closest('button').addEventListener('click', function() {
            window.print();
        });
    });
</script>
</body>
</html>