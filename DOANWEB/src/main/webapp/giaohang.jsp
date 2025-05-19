<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 14-Apr-25
  Time: 6:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tính phí giao hàng</title>
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      document.getElementById("shippingForm").addEventListener("submit", async function(event) {
        event.preventDefault();

        const pickupPoint = document.getElementById("pickupPoint").value;
        const deliveryAddress = document.getElementById("deliveryAddress").value;

        // Gửi yêu cầu đến servlet để tính phí ship
        const response = await fetch('calculateShipping', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ pickupPoint, deliveryAddress })
        });

        // Kiểm tra xem phản hồi có hợp lệ không
        if (response.ok) {
          const data = await response.json();
          document.getElementById("shippingCost").innerText = `Phí ship: ${data.cost}`;
        } else {
          // Nếu có lỗi trong quá trình tính phí, hiển thị thông báo lỗi
          document.getElementById("shippingCost").innerText = "Có lỗi xảy ra khi tính phí giao hàng.";
        }
      });
    });
  </script>
</head>
<body>
<h2>Tính phí giao hàng</h2>
<form id="shippingForm">
  <label for="pickupPoint">Điểm đặt hàng:</label>
  <input type="text" id="pickupPoint" name="pickupPoint" required>

  <label for="deliveryAddress">Địa chỉ giao hàng:</label>
  <input type="text" id="deliveryAddress" name="deliveryAddress" required>

  <button type="submit">Cập nhật phí ship</button>
</form>

<div id="shippingCost"></div>
</body>
</html>
