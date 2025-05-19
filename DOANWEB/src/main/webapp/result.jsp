<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả đánh giá</title>
    <meta charset="UTF-8">
    <style>
        .rating { color: gold; font-size: 24px; }
    </style>
</head>
<body>
<h1>Kết quả đánh giá</h1>
<p><strong>Review của bạn:</strong> ${reviewText}</p>
<p><strong>Điểm đánh giá:</strong>
    <span class="rating">
            <% for (int i = 0; i < (Integer) request.getAttribute("rating"); i++) { %>
                ★
            <% } %>
        </span>
    (${rating} sao)
</p>
<a href="review.jsp">Quay lại</a>
</body>
</html>