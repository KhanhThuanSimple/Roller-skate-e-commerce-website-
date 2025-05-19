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

</head>

<body>
<div id="wrapper"></div>

<div class="login-container">
    <img class="img-banner" src="./image/login.jpg">
    <div class="mask"></div>

    <%
        String error = (String) request.getAttribute("error");
        String uname = (String) request.getParameter("uname");
        String showOtpModal = (String) request.getAttribute("showOtpModal");
        if(error == null) error = "";
        if(uname == null) uname = "";
        if(showOtpModal == null) showOtpModal = "false";
    %>

    <form method="post" action="login" id="loginForm">
        <div class="logo">
            <img src="./image/logo1.png" alt="logo">
        </div>

        <h2>Đăng nhập</h2>

        <input class="input-common" type="text" placeholder="Email" value="<%=uname%>" name="uname">
        <input class="input-common" type="password" placeholder="Mật khẩu" name="pass">

        <!-- Google reCAPTCHA -->
        <div class="g-recaptcha" data-sitekey="6LeLFDIrAAAAAOExThqa_90MZ3f3yYZzLu3_ux8m"></div>

        <p class="error-message"><%=error%></p>

        <button type="submit" class="button-orange">Đăng nhập</button>

        <div class="divider">hoặc</div>

        <div class="social-login">
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/DOANWEB/home&response_type=code&client_id=862213844351-dv5kqbmf8vik0mvbkl27lrqgntgevp91.apps.googleusercontent.com&approval_prompt=force"
               class="social-btn google-btn">
                <i class="fab fa-google"></i>
                <span>Đăng nhập với Google</span>
            </a>

            <a href="#" onclick="loginWithFacebook()" class="social-btn facebook-btn">
                <i class="fab fa-facebook-f"></i>
                <span>Đăng nhập với Facebook</span>
            </a>
        </div>

        <div class="flex-row">
            <a class="text-link" href="forgot-password.jsp">Quên mật khẩu?</a>
            <a class="text-link" href="register.jsp">Đăng ký tài khoản</a>
        </div>
    </form>
</div>

<

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
                <p><a href="https://www.instagram.com" target="_blank"><i class="fa-brands fa-instagram"></i></a></p>
            </div>
        </div>
    </div>
</footer>

<!-- Google reCAPTCHA script -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<script>
    // Tự động chuyển focus khi nhập OTP
    const otpInputs = document.querySelectorAll('.otp-inputs input');

    otpInputs.forEach((input, index) => {
        input.addEventListener('input', () => {
            if (input.value.length === 1 && index < otpInputs.length - 1) {
                otpInputs[index + 1].focus();
            }
        });

        input.addEventListener('keydown', (e) => {
            if (e.key === "Backspace" && input.value.length === 0 && index > 0) {
                otpInputs[index - 1].focus();
            }
        });
    });

    // Khi submit OTP form, gom 6 ô thành 1 chuỗi otp gửi server
    document.getElementById('otpForm').addEventListener('submit', function(e) {
        e.preventDefault();
        let otp = '';
        otpInputs.forEach(input => {
            otp += input.value;
        });

        if (otp.length !== 6 || !/^\d{6}$/.test(otp)) {
            document.querySelector('.otp-error').textContent = 'Vui lòng nhập đủ 6 chữ số mã OTP.';
            return;
        }

        // Tạo input ẩn để gửi giá trị otp gộp
        let hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'otp';
        hiddenInput.value = otp;

        this.appendChild(hiddenInput);

        // Gửi form
        this.submit();
    });

    // Nếu có lỗi OTP, hiển thị modal
    <% if(request.getAttribute("otpError") != null) { %>
    document.getElementById('otpModal').style.display = 'flex';
    <% } %>
</script>
</body>
</html>