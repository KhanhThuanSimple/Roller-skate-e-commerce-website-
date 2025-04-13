<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title> Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/login.css">

</head>

<body>
<div id="wrapper"></div>



<div class="login-container flex-colunm">
    <img class="img-banner" src="./image/login.jpg">
    <div class="mask"></div>

    <%
        String error = (String) request.getAttribute("error");
        String uname = (String) request.getParameter("uname");
        if(error==null) error ="";
        if (uname==null) uname ="";
    %>


    <form method="post" action="login" class="flex-colunm">
        <div class="logo">
            <img src="./image/logo1.png" alt="logo">

        </div>
        <div>
            <h2>Đăng nhập</h2>
        </div>
        <div>
            <input class="input-common" type="text" placeholder="Email" value="<%=uname%>" name="uname">
        </div>
        <div>
            <input class="input-common" type="password" placeholder="Mật khẩu" name="pass">
        </div>

        <p>
            <%=error%>
        </p>
        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/DOANWEB/login&response_type=code&client_id=839747132596-cgvivf8uqtd2512b4fnev7rjjqh2l8lg.apps.googleusercontent.com&approval_prompt=force"
           class="flex items-center justify-center gap-2 px-4 py-2 bg-white border border-gray-300 rounded-lg shadow-md text-gray-700 hover:bg-gray-100 transition duration-200"
           style="text-decoration: none;">
            <i class="fa-brands fa-google text-red-500 text-lg"></i>
            <span>Đăng nhập với Google</span>
        </a>

        <div class="flex-center">

            <button type="submit" class="button-orange">Đăng
                nhập</button>


        </div>


        <div class="flex-row">
         <a class="text-link flex-1" href="forgot-password.jsp"> Quên mật khẩu?</a>

            <a class="text-link flex-1" href="register.jsp"> Đăng kí tài khoản.</a>

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