<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đặt hàng thành công | Your Store Name</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="./css/style.css">
  <style>
    :root {
      --primary-color: #2c3e50;
      --secondary-color: #3498db;
      --success-color: #28a745;
      --light-gray: #f8f9fa;
    }

    body {
      background-color: #f5f7fa;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .success-card {
      border: none;
      border-radius: 10px;
      overflow: hidden;
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
      max-width: 600px;
      margin: 2rem auto;
    }

    .success-header {
      background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
      color: white;
      padding: 1.5rem;
      text-align: center;
    }

    .success-body {
      padding: 2rem;
      background: white;
    }

    .success-icon {
      font-size: 4rem;
      color: var(--success-color);
      margin-bottom: 1.5rem;
    }

    .order-details {
      background: var(--light-gray);
      border-radius: 8px;
      padding: 1.5rem;
      margin: 1.5rem 0;
    }

    .order-number {
      font-weight: 700;
      color: var(--primary-color);
    }

    .order-total {
      font-size: 1.2rem;
      font-weight: 600;
    }

    .btn-primary {
      background-color: var(--secondary-color);
      border-color: var(--secondary-color);
      padding: 0.5rem 1.5rem;
      min-width: 180px;
    }

    .btn-outline-secondary {
      min-width: 180px;
    }

    .whats-next {
      margin-top: 2rem;
      padding-top: 1.5rem;
      border-top: 1px solid #eee;
    }

    .steps {
      display: flex;
      justify-content: space-between;
      margin-top: 1rem;
    }

    .step {
      text-align: center;
      flex: 1;
      padding: 0 0.5rem;
    }

    .step-icon {
      background: var(--light-gray);
      width: 50px;
      height: 50px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin: 0 auto 0.5rem;
      color: var(--secondary-color);
      font-size: 1.25rem;
    }

    @media (max-width: 576px) {
      .steps {
        flex-direction: column;
      }

      .step {
        margin-bottom: 1rem;
      }
    }
  </style>
</head>
<body>
<jsp:include page="comon/header.jsp" />

<div class="container py-5">
  <div class="success-card">
    <div class="success-header">
      <h2><i class="fas fa-check-circle me-2"></i>Đặt hàng thành công</h2>
    </div>
    <div class="success-body text-center">
      <div class="success-icon">
        <i class="fas fa-check-circle"></i>
      </div>
      <h3 class="mb-3">Cảm ơn bạn đã đặt hàng!</h3>
      <p class="mb-4">Đơn hàng của bạn đã được tiếp nhận và đang được xử lý.</p>

      <div class="order-details text-start">
        <div class="d-flex justify-content-between mb-2">
          <span>Mã đơn hàng:</span>
          <strong class="order-number">#${param.orderId}</strong>
        </div>
        <div class="d-flex justify-content-between mb-2">
          <span>Ngày đặt hàng:</span>
          <strong><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()) %></strong>
        </div>
        <div class="d-flex justify-content-between">
          <span>Tổng thanh toán:</span>
          <strong class="order-total text-success"><f:formatNumber value="${param.total}" type="currency" currencySymbol="₫"/></strong>
        </div>
      </div>

      <p class="mb-4">Chúng tôi sẽ gửi thông báo qua email khi đơn hàng được giao.</p>

      <div class="d-flex flex-wrap justify-content-center gap-3">
        <a href="lichsu" class="btn btn-primary">
          <i class="fas fa-history me-2"></i>Xem lịch sử đơn hàng
        </a>
        <a href="home" class="btn btn-outline-secondary">
          <i class="fas fa-home me-2"></i>Về trang chủ
        </a>
      </div>

      <div class="whats-next">
        <h5 class="mb-3">Tiếp theo sẽ là:</h5>
        <div class="steps">
          <div class="step">
            <div class="step-icon">
              <i class="fas fa-check"></i>
            </div>
            <div>Xác nhận đơn hàng</div>
          </div>
          <div class="step">
            <div class="step-icon">
              <i class="fas fa-box"></i>
            </div>
            <div>Đóng gói & vận chuyển</div>
          </div>
          <div class="step">
            <div class="step-icon">
              <i class="fas fa-truck"></i>
            </div>
            <div>Giao hàng</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="comon/footer.jsp" />

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  // Track conversion or other post-purchase actions
  document.addEventListener('DOMContentLoaded', function() {
    console.log('Order completed: #${param.orderId}');
    // You can add analytics tracking here
  });
</script>
</body>
</html>