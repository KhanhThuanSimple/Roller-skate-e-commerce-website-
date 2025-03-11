<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lấy lại mật khẩu</title>
</head>
<body>
<h2>Lấy lại mật khẩu</h2>
<form action="sendPassword.jsp" method="post">
  <label for="email">Nhập email của bạn:</label>
  <input type="email" id="email" name="email" required>
  <input type="submit" value="Gửi lại mật khẩu">
</form>
</body>
</html>