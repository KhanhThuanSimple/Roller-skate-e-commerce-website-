<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
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
    <form action="post" class="flex-colunm">
        <div class="logo">
            <img src="./image/logo1.png" alt="logo">

        </div>
        <div>
            <h2>Quên mật khẩu</h2>
        </div>

        <div>
            <input class="input-common" type="email" placeholder="Nhập email để lấy lại mật khẩu">
        </div>

        <div class="flex-center">


            <button type="button" onclick="window.location.href='forgot-password-new.jsp'" class="button-orange">Gưỉ mail</button>


        </div>
        <div class="flex-row">

            <a class="text-link flex-1" href="login"> Trở lại đăng nhập.</a>

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
            <p>Điện thoại: 123-456-789</p>
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
