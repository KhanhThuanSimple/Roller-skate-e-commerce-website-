<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đặt hàng thành công</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="./css/sanpham.css">
  <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<jsp:include page="comon/header.jsp" />
<div class="container mt-5">
  <div class="card shadow">
    <div class="card-body text-center">
      <h2 class="card-title text-success">Đặt hàng thành công!</h2>
      <p class="card-text">Đơn hàng của bạn đã được đặt thành công.</p>
      <p class="card-text"><strong></strong> <span class="text-primary">${param.total}</span></p>
      <div class="mt-4">
        <a href="lichsu" class="btn btn-primary me-2">Xem lịch sử đơn hàng</a>
        <a href="home" class="btn btn-secondary">Quay về trang chủ</a>
      </div>
    </div>
  </div>
</div>
<jsp:include page="comon/footer.jsp" />

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
