<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả thanh toán | ${initParam.appName}</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="./css/sanpham.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/checkout.css">
    <style>
        :root {
            --primary-color: #3498db;
            --success-color: #2ecc71;
            --danger-color: #e74c3c;
            --dark-color: #2c3e50;
            --light-color: #f8f9fa;
            --border-radius: 12px;
            --box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
        }

        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', 'Roboto', sans-serif;
            line-height: 1.6;
            color: #333;
        }

        .payment-container {
            max-width: 650px;
            margin: 2rem auto;
        }

        .payment-card {
            border: none;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            overflow: hidden;
            transition: transform 0.3s ease;
        }

        .payment-card:hover {
            transform: translateY(-5px);
        }

        .payment-header {
            padding: 2rem;
            text-align: center;
            color: white;
            position: relative;
            overflow: hidden;
        }

        .payment-header::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: radial-gradient(circle, rgba(255,255,255,0.2) 0%, rgba(255,255,255,0) 70%);
            transform: rotate(30deg);
        }

        .success-bg {
            background: linear-gradient(135deg, var(--success-color), #27ae60);
        }

        .failure-bg {
            background: linear-gradient(135deg, var(--danger-color), #c0392b);
        }

        .payment-icon {
            font-size: 4.5rem;
            margin-bottom: 1.5rem;
            position: relative;
            display: inline-block;
        }

        .payment-icon::after {
            content: '';
            position: absolute;
            top: -10px;
            left: -10px;
            right: -10px;
            bottom: -10px;
            border: 2px solid rgba(255,255,255,0.3);
            border-radius: 50%;
        }

        .payment-body {
            padding: 2.5rem;
            background-color: white;
        }

        .detail-item {
            display: flex;
            align-items: center;
            padding: 1.2rem 0;
            border-bottom: 1px solid rgba(0,0,0,0.05);
        }

        .detail-item:last-child {
            border-bottom: none;
        }

        .detail-icon {
            width: 50px;
            height: 50px;
            background-color: var(--light-color);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 1.5rem;
            color: var(--primary-color);
            font-size: 1.25rem;
        }

        .detail-content {
            flex: 1;
        }

        .detail-label {
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            color: #7f8c8d;
            margin-bottom: 0.25rem;
            font-weight: 600;
        }

        .detail-value {
            font-size: 1.1rem;
            font-weight: 500;
            color: var(--dark-color);
            word-break: break-word;
        }

        .btn-action {
            border-radius: 50px;
            padding: 0.75rem 2rem;
            font-weight: 500;
            letter-spacing: 0.5px;
            transition: all 0.3s ease;
            margin: 0.5rem;
            min-width: 200px;
        }

        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }

        .btn-outline-secondary {
            border-color: #ddd;
        }

        .payment-actions {
            margin-top: 2rem;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .bank-logo {
            height: 30px;
            margin-right: 10px;
            vertical-align: middle;
        }

        @media (max-width: 576px) {
            .payment-body {
                padding: 1.5rem;
            }

            .detail-item {
                flex-direction: column;
                align-items: flex-start;
            }

            .detail-icon {
                margin-bottom: 1rem;
                margin-right: 0;
            }

            .payment-actions {
                flex-direction: column;
                align-items: center;
            }

            .btn-action {
                width: 100%;
                margin: 0.5rem 0;
            }
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp"/>

<div class="container py-5">
    <div class="payment-container">
        <%
            Map<String, String> params = new HashMap<>();

            // Get parameters from request
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                params.put(paramName, paramValue);
            }

            // Check payment result
            String responseCode = params.get("vnp_ResponseCode");
            boolean isSuccess = "00".equals(responseCode);
            String message = isSuccess ? "Thanh toán thành công" : "Thanh toán không thành công";
            String amount = params.get("vnp_Amount");
            long amountValue = amount != null ? Long.parseLong(amount) / 100 : 0;

            // Format transaction date
            String payDate = params.get("vnp_PayDate");
            String formattedDate = "Không có dữ liệu";
            if (payDate != null) {
                formattedDate = payDate.replaceAll("(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})",
                        "$3/$2/$1 $4:$5:$6");
            }

            // Bank code mapping
            Map<String, String> bankCodes = new HashMap<>();
            bankCodes.put("VNBANK", "Ngân hàng nội địa");
            bankCodes.put("INTCARD", "Thẻ quốc tế");
            // Add more bank codes as needed

            String bankCode = params.get("vnp_BankCode");
            String bankName = bankCodes.getOrDefault(bankCode, bankCode != null ? bankCode : "Không xác định");
        %>

        <div class="payment-card">
            <div class="payment-header <%= isSuccess ? "success-bg" : "failure-bg" %>">
                <div class="payment-icon">
                    <% if (isSuccess) { %>
                    <i class="fas fa-check-circle"></i>
                    <% } else { %>
                    <i class="fas fa-exclamation-circle"></i>
                    <% } %>
                </div>
                <h2 class="mb-3"><%= message %></h2>
                <p class="mb-0 opacity-75">
                    <% if (isSuccess) { %>
                    Cảm ơn bạn đã thanh toán. Đơn hàng của bạn đang được xử lý.
                    <% } else { %>
                    Thanh toán không thành công. Vui lòng thử lại hoặc chọn phương thức thanh toán khác.
                    <% } %>
                </p>
            </div>

            <div class="payment-body">
                <div class="detail-item">
                    <div class="detail-icon">
                        <i class="fas fa-receipt"></i>
                    </div>
                    <div class="detail-content">
                        <div class="detail-label">Mã giao dịch</div>
                        <div class="detail-value"><%= params.get("vnp_TxnRef") != null ? params.get("vnp_TxnRef") : "Không có dữ liệu" %></div>
                    </div>
                </div>

                <div class="detail-item">
                    <div class="detail-icon">
                        <i class="fas fa-money-bill-wave"></i>
                    </div>
                    <div class="detail-content">
                        <div class="detail-label">Số tiền</div>
                        <div class="detail-value">
                            <fmt:formatNumber value="<%= amountValue %>" type="currency" currencySymbol="₫"/>
                        </div>
                    </div>
                </div>

                <div class="detail-item">
                    <div class="detail-icon">
                        <i class="fas fa-building"></i>
                    </div>
                    <div class="detail-content">
                        <div class="detail-label">Ngân hàng</div>
                        <div class="detail-value"><%= bankName %></div>
                    </div>
                </div>

                <div class="detail-item">
                    <div class="detail-icon">
                        <i class="fas fa-clock"></i>
                    </div>
                    <div class="detail-content">
                        <div class="detail-label">Thời gian giao dịch</div>
                        <div class="detail-value"><%= formattedDate %></div>
                    </div>
                </div>

                <div class="detail-item">
                    <div class="detail-icon">
                        <i class="fas fa-info-circle"></i>
                    </div>
                    <div class="detail-content">
                        <div class="detail-label">Trạng thái</div>
                        <div class="detail-value"><%= params.get("vnp_Message") != null ? params.get("vnp_Message") : "Không có dữ liệu" %></div>
                    </div>
                </div>

                <div class="payment-actions">
                    <a href="home" class="btn btn-primary btn-action">
                        <i class="fas fa-home me-2"></i> Về trang chủ
                    </a>
                    <% if (isSuccess) { %>
                    <a href="lichsu" class="btn btn-outline-secondary btn-action">
                        <i class="fas fa-file-invoice me-2"></i> Xem đơn hàng đã đặt
                    </a>
                    <% } else { %>
                    <a href="checkout" class="btn btn-outline-secondary btn-action">
                        <i class="fas fa-credit-card me-2"></i> Thử lại thanh toán
                    </a>
                    <% } %>
                </div>

                <% if (isSuccess) { %>
                <div class="mt-4 text-center text-muted">
                    <small>
                        <i class="fas fa-envelope me-1"></i> Chúng tôi đã gửi email xác nhận đến bạn.
                        Vui lòng kiểm tra hộp thư đến hoặc thư mục spam.
                    </small>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<jsp:include page="comon/footer.jsp"/>

<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Track payment completion
    document.addEventListener('DOMContentLoaded', function() {
        console.log('Payment processed - Status: <%= isSuccess ? "Success" : "Failed" %>');

        // You can add analytics tracking here
        <% if (isSuccess) { %>
        // Track successful payment
        <% } else { %>
        // Track failed payment
        <% } %>
    });
</script>
</body>
</html>