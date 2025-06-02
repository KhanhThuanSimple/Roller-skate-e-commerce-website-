<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý mã giảm giá</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        .coupon-card {
            transition: all 0.3s ease;
        }
        .coupon-card:hover {
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }
        .fixed-badge {
            background-color: #6c757d;
        }
        .percent-badge {
            background-color: #0d6efd;
        }
        .active-badge {
            background-color: #198754;
        }
        .inactive-badge {
            background-color: #dc3545;
        }
        .expired {
            color: #6c757d;
            text-decoration: line-through;
        }
        .action-btns {
            min-width: 120px;
        }
    </style>
</head>
<body class="bg-light">
<div class="container py-4">
    <div class="row mb-4">
        <div class="col">
            <h2 class="fw-bold text-primary"><i class="bi bi-tag-fill"></i> Quản lý mã giảm giá</h2>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Mã giảm giá</li>
                </ol>
            </nav>
        </div>
    </div>

    <!-- Add Coupon Card -->
    <div class="card mb-4 coupon-card">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0"><i class="bi bi-plus-circle"></i> Thêm mã giảm giá mới</h5>
        </div>
        <div class="card-body">
            <form action="coupons" method="post" class="row g-3">
                <input type="hidden" name="action" value="add" />
                <div class="col-md-2">
                    <label for="coupon_code" class="form-label">Mã giảm giá</label>
                    <input type="text" class="form-control" id="coupon_code" name="coupon_code" placeholder="VD: SALE20" required>
                </div>
                <div class="col-md-2">
                    <label for="discount_type" class="form-label">Loại giảm giá</label>
                    <select class="form-select" id="discount_type" name="discount_type">
                        <option value="FIXED">Giảm tiền trực tiếp</option>
                        <option value="PERCENT">Giảm theo phần trăm</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="discount_value" class="form-label">Giá trị</label>
                    <input type="number" step="0.01" class="form-control" id="discount_value" name="discount_value" placeholder="VD: 20" required>
                </div>
                <div class="col-md-2">
                    <label for="min_order_amount" class="form-label">Đơn tối thiểu</label>
                    <input type="number" step="0.01" class="form-control" id="min_order_amount" name="min_order_amount" placeholder="VD: 100000" required>
                </div>
                <div class="col-md-2">
                    <label for="expire_date" class="form-label">Ngày hết hạn</label>
                    <input type="date" class="form-control" id="expire_date" name="expire_date" required>
                </div>
                <div class="col-md-2">
                    <label for="status" class="form-label">Trạng thái</label>
                    <select class="form-select" id="status" name="status">
                        <option value="ACTIVE">Kích hoạt</option>
                        <option value="INACTIVE">Tạm ẩn</option>
                    </select>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> Thêm mã</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Coupons List -->
    <div class="card coupon-card">
        <div class="card-header bg-white">
            <h5 class="mb-0"><i class="bi bi-list-ul"></i> Danh sách mã giảm giá</h5>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead class="table-light">
                    <tr>
                        <th>Mã</th>
                        <th>Loại</th>
                        <th>Giá trị</th>
                        <th>Tối thiểu</th>
                        <th>Hết hạn</th>
                        <th>Trạng thái</th>
                        <th class="text-end">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="coupon" items="${coupons}">
                        <tr>
                            <form action="coupons" method="post">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="id" value="${coupon.id}"/>
                                <td>
                                    <input type="text" class="form-control form-control-sm" name="coupon_code" value="${coupon.couponCode}" required>
                                </td>
                                <td>
                                    <select class="form-select form-select-sm" name="discount_type">
                                        <option value="FIXED" <c:if test="${coupon.discountType == 'FIXED'}">selected</c:if>>Cố định</option>
                                        <option value="PERCENT" <c:if test="${coupon.discountType == 'PERCENT'}">selected</c:if>>%</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="number" step="0.01" class="form-control form-control-sm" name="discount_value" value="${coupon.discountValue}" required>
                                </td>
                                <td>
                                    <input type="number" step="0.01" class="form-control form-control-sm" name="min_order_amount" value="${coupon.minOrderAmount}" required>
                                </td>
                                <td>
                                    <input type="date" class="form-control form-control-sm" name="expire_date" value="${coupon.expireDate}" required>
                                </td>
                                <td>
                                    <select class="form-select form-select-sm" name="status">
                                        <option value="ACTIVE" <c:if test="${coupon.status == 'ACTIVE'}">selected</c:if>>Hoạt động</option>
                                        <option value="INACTIVE" <c:if test="${coupon.status == 'INACTIVE'}">selected</c:if>>Ẩn</option>
                                    </select>
                                </td>
                                <td class="text-end action-btns">
                                    <button type="submit" class="btn btn-sm btn-outline-primary"><i class="bi bi-check-circle"></i> Lưu</button>
                                    <a href="#" class="btn btn-sm btn-outline-danger" onclick="document.getElementById('deleteForm${coupon.id}').submit();">
                                        <i class="bi bi-trash"></i> Xóa
                                    </a>
                                </td>
                            </form>
                            <form id="deleteForm${coupon.id}" action="coupons" method="post" style="display: none;">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="${coupon.id}"/>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="card-footer bg-white">
            <div class="row">
                <div class="col-md-6">
                    <span class="text-muted">Hiển thị <strong>${coupons.size()}</strong> mã giảm giá</span>
                </div>
                <div class="col-md-6">
                    <nav aria-label="Page navigation" class="float-end">
                        <ul class="pagination pagination-sm mb-0">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trước</a>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Add confirmation for delete action
    document.addEventListener('DOMContentLoaded', function() {
        const deleteForms = document.querySelectorAll('form[action="coupons"][method="post"] input[name="action"][value="delete"]').forEach(form => {
            form.closest('form').addEventListener('submit', function(e) {
                if (!confirm('Bạn có chắc chắn muốn xóa mã giảm giá này?')) {
                    e.preventDefault();
                }
            });
        });

        // Highlight expired coupons
        const today = new Date().toISOString().split('T')[0];
        document.querySelectorAll('input[name="expire_date"]').forEach(input => {
            if (input.value < today) {
                input.closest('tr').classList.add('table-danger');
            }
        });
    });
</script>
</body>
</html>