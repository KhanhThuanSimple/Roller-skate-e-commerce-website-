<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/login.css" />
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border-radius: 10px;
            width: 400px;
            max-width: 90%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: black;
        }

        .otp-inputs {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin: 20px 0;
        }

        .otp-inputs input {
            width: 40px;
            height: 40px;
            font-size: 24px;
            text-align: center;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .resend {
            text-align: center;
            margin-top: 20px;
        }

        #countdown {
            margin-top: 10px;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
<div class="login-container">
    <img class="img-banner" src="./image/login.jpg" alt="banner">
    <div class="mask"></div>

    <%
        String error = (String) request.getAttribute("error");
        String uname = request.getParameter("uname");
        Boolean showOtp = (Boolean) request.getAttribute("otpModal");
        String emailOtp = (String) request.getAttribute("emailForOtp");

        if (error == null) error = "";
        if (uname == null) uname = "";
        if (showOtp == null) showOtp = false;
        if (emailOtp == null) emailOtp = "";
    %>

    <form method="post" action="login" id="loginForm">
        <div class="logo">
            <img src="./image/logo1.png" alt="logo" />
        </div>

        <h2>Đăng nhập</h2>

        <input class="input-common" type="text" placeholder="Email" value="<%=uname%>" name="uname" required />
        <input class="input-common" type="password" placeholder="Mật khẩu" name="pass" required />

        <!-- Google reCAPTCHA -->
        <div class="g-recaptcha" data-sitekey="6LeLFDIrAAAAAOExThqa_90MZ3f3yYZzLu3_ux8m"></div>

        <p class="error-message"><%=error%></p>

        <button type="submit" class="button-orange">Đăng nhập</button>

        <div class="divider">hoặc</div>

        <div class="social-login">
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/DOANWEB/loginServlet&response_type=code&client_id=862213844351-dv5kqbmf8vik0mvbkl27lrqgntgevp91.apps.googleusercontent.com&approval_prompt=force"
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

<%--<!-- Modal OTP -->--%>
<%--<div id="otpModal" class="modal">--%>
<%--    <div class="modal-content">--%>
<%--        <span class="close">&times;</span>--%>
<%--        <h2>Nhập mã OTP</h2>--%>
<%--        <p>Mã OTP đã được gửi đến email của bạn</p>--%>

<%--        <form id="otpForm" method="post" action="verify-otp">--%>
<%--            <div class="otp-inputs">--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required autofocus />--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required />--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required />--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required />--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required />--%>
<%--                <input type="text" maxlength="1" pattern="[0-9]" required />--%>
<%--            </div>--%>
<%--            <input type="hidden" name="email" id="otpEmail" value="<%=emailOtp%>" />--%>
<%--            <button type="submit" class="button-orange">Xác nhận</button>--%>
<%--        </form>--%>

<%--        <div class="resend">--%>
<%--            <button id="resendOtpBtn" class="text-link">Gửi lại mã OTP</button>--%>
<%--            <div id="countdown">Bạn có thể gửi lại sau <span id="timer">60</span> giây</div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<footer id="footer">
    <div class="footer-container">
        <!-- Nội dung footer giữ nguyên -->
    </div>
</footer>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script>
    // Di chuyển focus giữa các ô OTP
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
    //
    // // Modal
    // const otpModal = document.getElementById('otpModal');
    // const closeBtn = document.querySelector('.close');
    //
    // closeBtn.onclick = function () {
    //     otpModal.style.display = 'none';
    // }
    // window.onclick = function (event) {
    //     if (event.target === otpModal) {
    //         otpModal.style.display = 'none';
    //     }
    // }
    //
    // // Gửi lại OTP
    // let countdown;
    // const resendBtn = document.getElementById('resendOtpBtn');
    // const countdownEl = document.getElementById('countdown');
    // const timerEl = document.getElementById('timer');
    //
    // function startCountdown() {
    //     let timeLeft = 60;
    //     countdownEl.style.display = 'block';
    //     resendBtn.style.display = 'none';
    //
    //     countdown = setInterval(() => {
    //         timeLeft--;
    //         timerEl.textContent = timeLeft;
    //         if (timeLeft <= 0) {
    //             clearInterval(countdown);
    //             countdownEl.style.display = 'none';
    //             resendBtn.style.display = 'block';
    //         }
    //     }, 1000);
    // }

    resendBtn.addEventListener('click', function () {
        const email = document.getElementById('otpEmail').value;

        fetch('resend-otp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            },
            body: new URLSearchParams({ 'email': email })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Mã OTP mới đã được gửi đến email của bạn.');
                    startCountdown();
                } else {
                    alert(data.message || 'Lỗi khi gửi lại mã OTP.');
                }
            })
            .catch(error => {
                console.error('Lỗi:', error);
                alert('Đã xảy ra lỗi khi gửi lại OTP.');
            });
    });

    // Gộp các ô OTP thành 1 trước khi submit form
    document.getElementById('otpForm').addEventListener('submit', function(e) {
        e.preventDefault();
        let otp = '';
        otpInputs.forEach(input => {
            otp += input.value;
        });
        if (otp.length !== 6) {
            alert('Vui lòng nhập đầy đủ 6 chữ số OTP');
            return;
        }
        // Tạo input ẩn hoặc cập nhật giá trị hidden otp
        let hiddenOtp = document.createElement('input');
        hiddenOtp.type = 'hidden';
        hiddenOtp.name = 'otp';
        hiddenOtp.value = otp;

        this.appendChild(hiddenOtp);
        this.submit();
    });

    // Hiển thị modal nếu backend yêu cầu
    window.addEventListener("DOMContentLoaded", () => {
        <% if (Boolean.TRUE.equals(request.getAttribute("otpModal"))) { %>
        const otpModal = document.getElementById('otpModal');
        otpModal.style.display = 'block';
        startCountdown(); // Thêm dòng này

        const otpError = "<%= request.getAttribute("otpError") != null ? request.getAttribute("otpError") : "" %>";
        if (otpError) {
            const errorElem = document.createElement('p');
            errorElem.style.color = 'red';
            errorElem.style.textAlign = 'center';
            errorElem.textContent = otpError;
            otpModal.querySelector('.modal-content').insertBefore(errorElem, otpModal.querySelector('form'));
        }
        <% } %>
    });
</script>
</body>
</html>
