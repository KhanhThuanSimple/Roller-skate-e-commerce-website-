<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả thanh toán</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .payment-card {
            max-width: 600px;
            margin: 50px auto;
            border-radius: 15px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .payment-header {
            padding: 20px;
            text-align: center;
            color: white;
        }
        .success {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
        }
        .failure {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        }
        .payment-icon {
            font-size: 60px;
            margin-bottom: 20px;
        }
        .payment-body {
            padding: 30px;
            background-color: white;
        }
        .detail-item {
            display: flex;
            margin-bottom: 15px;
            padding-bottom: 15px;
            border-bottom: 1px solid #eee;
        }
        .detail-icon {
            width: 40px;
            color: #6c757d;
            font-size: 20px;
        }
        .detail-content {
            flex: 1;
        }
        .btn-home {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
            border: none;
            color: white;
            padding: 10px 25px;
            border-radius: 50px;
            font-weight: 500;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp"/>
<div class="container">
    <%
        Map<String, String> params = new HashMap<>();

        // Lấy tham số từ request
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }

        // Kiểm tra kết quả thanh toán
        String responseCode = params.get("vnp_ResponseCode");
        boolean isSuccess = "00".equals(responseCode);
        String message = isSuccess ? "Giao dịch thành công" : "Giao dịch không thành công";
        String amount = params.get("vnp_Amount") != null ?
                String.format("%,d", Long.parseLong(params.get("vnp_Amount")) / 100) + " VND" : "Không có dữ liệu";
    %>

    <div class="payment-card">
        <div class="payment-header <%= isSuccess ? "success" : "failure" %>">
            <div class="payment-icon">
                <% if (isSuccess) { %>
                <i class="fas fa-check-circle"></i>
                <% } else { %>
                <i class="fas fa-times-circle"></i>
                <% } %>
            </div>
            <h2><%= message %></h2>
        </div>

        <div class="payment-body">
            <div class="detail-item">
                <div class="detail-icon">
                    <i class="fas fa-receipt"></i>
                </div>
                <div class="detail-content">
                    <h6 class="mb-1">Mã giao dịch</h6>
                    <p class="mb-0 text-muted"><%= params.get("vnp_TxnRef") != null ? params.get("vnp_TxnRef") : "Không có dữ liệu" %></p>
                </div>
            </div>

            <div class="detail-item">
                <div class="detail-icon">
                    <i class="fas fa-money-bill-wave"></i>
                </div>
                <div class="detail-content">
                    <h6 class="mb-1">Số tiền</h6>
                    <p class="mb-0 text-muted"><%= amount %></p>
                </div>
            </div>

            <div class="detail-item">
                <div class="detail-icon">
                    <i class="fas fa-clock"></i>
                </div>
                <div class="detail-content">
                    <h6 class="mb-1">Thời gian giao dịch</h6>
                    <p class="mb-0 text-muted"><%= params.get("vnp_PayDate") != null ?
                            params.get("vnp_PayDate").replaceAll("(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1-$2-$3 $4:$5:$6") :
                            "Không có dữ liệu" %></p>
                </div>
            </div>

            <div class="detail-item">
                <div class="detail-icon">
                    <i class="fas fa-info-circle"></i>
                </div>
                <div class="detail-content">
                    <h6 class="mb-1">Trạng thái</h6>
                    <p class="mb-0 text-muted"><%= params.get("vnp_Message") != null ? params.get("vnp_Message") : "Không có dữ liệu" %></p>
                </div>
            </div>

            <div class="text-center">
                <a href="home" class="btn btn-home">
                    <i class="fas fa-home me-2"></i> Trang chủ
                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="comon/footer.jsp"/>
<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>