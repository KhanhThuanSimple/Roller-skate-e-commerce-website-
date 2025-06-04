<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Đơn Hàng | Hệ thống Bán Hàng</title>
    <!-- Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="./css/style.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #ecf0f1;
            --accent-color: #3498db;
            --success-color: #27ae60;
            --warning-color: #f39c12;
            --danger-color: #e74c3c;
        }

        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', 'Roboto', sans-serif;
        }

        .order-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
            padding: 2rem;
            margin-bottom: 2rem;
        }

        .page-header {
            border-bottom: 1px solid #eee;
            padding-bottom: 1rem;
            margin-bottom: 2rem;
        }

        .page-title {
            color: var(--primary-color);
            font-weight: 600;
        }

        .order-table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
        }

        .order-table thead th {
            background-color: var(--primary-color);
            color: white;
            padding: 1rem;
            font-weight: 500;
            font-size: 0.9rem;
            position: sticky;
            top: 0;
        }

        .order-table tbody td {
            padding: 1rem;
            vertical-align: middle;
            border-bottom: 1px solid #eee;
        }

        .order-table tbody tr:last-child td {
            border-bottom: none;
        }

        .order-table tbody tr:hover {
            background-color: rgba(44, 62, 80, 0.03);
        }

        .status-badge {
            padding: 0.5rem 0.75rem;
            border-radius: 4px;
            font-size: 0.8rem;
            font-weight: 500;
            text-transform: uppercase;
        }

        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }

        .status-processing {
            background-color: #cce5ff;
            color: #004085;
        }

        .status-completed {
            background-color: #d4edda;
            color: #155724;
        }

        .status-cancelled {
            background-color: #f8d7da;
            color: #721c24;
        }

        .btn-details {
            padding: 0.5rem 1rem;
            font-size: 0.85rem;
            border-radius: 4px;
            transition: all 0.2s ease;
        }

        .btn-details:hover {
            transform: translateY(-1px);
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .empty-state {
            text-align: center;
            padding: 3rem;
            color: #6c757d;
        }

        .empty-state i {
            font-size: 3rem;
            margin-bottom: 1rem;
            color: #dee2e6;
        }

        .total-amount {
            font-weight: 600;
            color: #2c3e50;
        }

        /* Responsive table */
        @media (max-width: 768px) {
            .order-table thead {
                display: none;
            }

            .order-table tbody tr {
                display: block;
                margin-bottom: 1rem;
                border: 1px solid #eee;
                border-radius: 8px;
                padding: 0.5rem;
            }

            .order-table tbody td {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 0.75rem;
                border-bottom: 1px dashed #eee;
            }

            .order-table tbody td:before {
                content: attr(data-label);
                font-weight: 600;
                color: var(--primary-color);
                margin-right: 1rem;
            }

            .order-table tbody td:last-child {
                border-bottom: none;
            }
        }

        /* Print styles */
        @media print {
            .no-print {
                display: none !important;
            }

            .order-container {
                box-shadow: none;
                padding: 0;
            }

            .order-table {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp"/>

<div class="container py-4">
    <div class="row">
        <div class="col-12">
            <div class="order-container">
                <!-- Page Header -->
                <div class="page-header">
                    <div class="row align-items-center">
                        <div class="col-md-6">
                            <h1 class="page-title mb-0"><i class="fas fa-clipboard-list me-2"></i>Quản lý Đơn Hàng</h1>
                        </div>
                        <div class="col-md-6 text-md-end">
                            <button class="btn btn-outline-secondary no-print" onclick="window.print()">
                                <i class="fas fa-print me-2"></i>In danh sách
                            </button>
                            <a href="home" class="btn btn-outline-primary no-print">
                                <i class="fas fa-home me-2"></i>Về trang chủ
                            </a>
                        </div>
                    </div>
                    <p class="text-muted mt-2">Xem lại lịch sử các đơn hàng bạn đã đặt</p>
                </div>

                <!-- Order Table -->
                <div class="table-responsive">
                    <table class="order-table">
                        <thead>
                        <tr>
                            <th class="text-center">STT</th>
                            <th>Mã Đơn Hàng</th>
                            <th>Khách Hàng</th>
                            <th>Điện Thoại</th>
                            <th>Thanh Toán</th>
                            <th>Trạng Thái</th>
                            <th class="text-center no-print">Thao Tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty list}">
                                <c:forEach var="p" items="${list}" varStatus="loop">
                                    <tr>
                                        <td class="text-center" data-label="STT">${loop.index + 1}</td>
                                        <td data-label="Mã Đơn Hàng"><strong>#${p.id}</strong></td>
                                        <td data-label="Khách Hàng">${p.name}</td>
                                        <td data-label="Điện Thoại">${p.phone}</td>
                                        <td data-label="Thanh Toán">${p.paymentMethod}</td>
                                        <td data-label="Trạng Thái">
                                            <c:choose>
                                                <c:when test="${p.status eq 'Pending' || p.status eq 'Chờ xử lý'}">
                                                    <span class="status-badge status-pending">${p.status}</span>
                                                </c:when>
                                                <c:when test="${p.status eq 'Processing' || p.status eq 'Đang xử lý'}">
                                                    <span class="status-badge status-processing">${p.status}</span>
                                                </c:when>
                                                <c:when test="${p.status eq 'Completed' || p.status eq 'Hoàn thành'}">
                                                    <span class="status-badge status-completed">${p.status}</span>
                                                </c:when>
                                                <c:when test="${p.status eq 'Cancelled' || p.status eq 'Đã hủy'}">
                                                    <span class="status-badge status-cancelled">${p.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="status-badge">${p.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td class="text-center no-print" data-label="Thao Tác">
                                            <a href="chitietdahuy?pid=${p.id}" class="btn btn-primary btn-details">
                                                <i class="fas fa-eye me-1"></i> Chi tiết
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="8">
                                        <div class="empty-state">
                                            <i class="fas fa-box-open"></i>
                                            <h4>Không có đơn hàng nào</h4>
                                            <p>Bạn chưa đặt đơn hàng nào trong hệ thống.</p>
                                            <a href="home" class="btn btn-primary mt-3 no-print">
                                                <i class="fas fa-shopping-cart me-2"></i>Đặt hàng ngay
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination (optional) -->
                <div class="d-flex justify-content-center mt-4 no-print">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">Trước</a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Sau</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="comon/footer.jsp"/>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Format currency on client side if needed
        document.querySelectorAll('.total-amount').forEach(el => {
            let amount = el.textContent.trim();
            if (!amount.startsWith('₫')) {
                let num = parseFloat(amount.replace(/[^\d.-]/g, ''));
                if (!isNaN(num)) {
                    el.textContent = new Intl.NumberFormat('vi-VN', {
                        style: 'currency',
                        currency: 'VND'
                    }).format(num);
                }
            }
        });

        // Add animation to table rows
        const rows = document.querySelectorAll('.order-table tbody tr');
        rows.forEach((row, index) => {
            row.style.opacity = '0';
            row.style.transform = 'translateY(20px)';
            row.style.transition = `all 0.3s ease ${index * 0.05}s`;

            setTimeout(() => {
                row.style.opacity = '1';
                row.style.transform = 'translateY(0)';
            }, 50);
        });
    });
</script>
</body>
</html>