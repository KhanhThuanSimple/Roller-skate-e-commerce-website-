<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>register</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/login.css">
    <script src="./login.js"></script>
</head>

<body>
<div id="wrapper"></div>



<div class="login-container flex-colunm">
    <img class="img-banner" src="image/login.jpg">
    <div class="mask"></div>
    <form method="post" action="register" class="flex-colunm">
        <div class="logo">
            <img src="./image/logo1.png" alt="logo">

        </div>
        <div>
            <h2>Đăng kí tài khoản</h2>
        </div>
        <div>
            <input class="input-common" type="text" placeholder="Họ & tên" name="name">
        </div>
        <div>
            <input class="input-common" type="email" placeholder="Email" name="uname">
        </div>
        <div>
            <input class="input-common" type="password" placeholder="Mật khẩu" name="pass">
        </div>
        <div>
            <input class="input-common" type="password" placeholder="Nhập lại mật khẩu" name="repass">
        </div>
        <div>
            <input class="input-common" type="text" placeholder="Số điện thoại" name="phone" >
        </div>
        <div>
            <textarea rows="2" class="input-common" placeholder="Địa chỉ" name="address"></textarea>
        </div>
        <div class="flex-center">

            <button type="submit" onclick="window.location.href='login.jsp'" class="button-orange">Đăng kí</button>


        </div>
        <div class="flex-row">
            <a class="text-link flex-1" href="forgot-password.jsp"> Quên mật khẩu?</a>
            <a class="text-link flex-1" href="login.jsp"> Đăng nhập.</a>

        </div>
    </form>
</div>


<footer id="footer">
    <div class="footer-container">
        <div class="footer-section">
            <h4>Chính sách</h4>
            <p>Chính sách chăm sóc khách hàng.<br><a href="chinhsachtrahang.html">Chính sách đổi trả.</a><br><a
                    href="chinhsachthanhtoan.html">Chính sách thanh toán.</a></p>
        </div>
        <div class="footer-section">
            <h4>Liên hệ:</h4>
            <p>Email: NTN@company.com</p>
            <p>Điện thoại: 0383967879</p>
        </div>
        <div class="footer-section">
            <h4>Theo dõi chúng tôi</h4>
            <div class="social-link">
                <p><a href="https://www.facebook.com" target="_blank"><i class="fa-brands fa-facebook"></i></a></p>
                <p><a href="https://www.twitter.com" target="_blank"><i class="fa-brands fa-twitter"></i></a></p>
                <p><a href="https://www.instagram.com" target="_blank"><i class="fa-brands fa-instagram"></i></a>
                </p>
            </div>
        </div>
    </div>
</footer>
</div>
</body>

</html>
