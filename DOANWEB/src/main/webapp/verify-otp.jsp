<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Xác minh OTP</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
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
        button {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        .error {
            color: red;
            text-align: center;
            margin-top: 15px;
        }
        .resend {
            text-align: center;
            margin-top: 20px;
        }
        .resend button {
            background-color: #2196F3;
        }
        #countdown {
            margin-top: 10px;
            font-size: 14px;
            color: #555;
        }
    </style>
</head>
<body>
<h2>Nhập mã OTP đã gửi về email</h2>
<form action="verify-otp" method="post" id="otpForm">
    <div class="otp-inputs">
        <input type="text" name="otp1" maxlength="1" pattern="[0-9]" inputmode="numeric" required autofocus />
        <input type="text" name="otp2" maxlength="1" pattern="[0-9]" inputmode="numeric" required />
        <input type="text" name="otp3" maxlength="1" pattern="[0-9]" inputmode="numeric" required />
        <input type="text" name="otp4" maxlength="1" pattern="[0-9]" inputmode="numeric" required />
        <input type="text" name="otp5" maxlength="1" pattern="[0-9]" inputmode="numeric" required />
        <input type="text" name="otp6" maxlength="1" pattern="[0-9]" inputmode="numeric" required />
    </div>
    <button type="submit">Xác minh</button>
</form>

<p class="error">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>

<div class="resend">
    <button id="resendBtn" style="display: none;">Gửi lại mã OTP</button>
    <div id="countdown">Bạn có thể gửi lại sau <span id="timer">60</span> giây</div>
</div>

<script>
    const inputs = document.querySelectorAll('.otp-inputs input');

    inputs.forEach((input, index) => {
        input.addEventListener('input', () => {
            if (input.value.length === 1 && index < inputs.length - 1) {
                inputs[index + 1].focus();
            }
        });

        input.addEventListener('keydown', (e) => {
            if (e.key === "Backspace" && input.value.length === 0 && index > 0) {
                inputs[index - 1].focus();
            }
        });
    });

    document.getElementById('otpForm').addEventListener('submit', function(e) {
        let otp = '';
        inputs.forEach(input => {
            otp += input.value;
        });

        if (otp.length !== 6 || !/^\d{6}$/.test(otp)) {
            alert('Vui lòng nhập đủ 6 chữ số mã OTP.');
            e.preventDefault();
            return;
        }

        let hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'otp';
        hiddenInput.value = otp;
        this.appendChild(hiddenInput);
    });

    // Đếm ngược resend
    let countdown = 60;
    const timerEl = document.getElementById('timer');
    const countdownEl = document.getElementById('countdown');
    const resendBtn = document.getElementById('resendBtn');

    const interval = setInterval(() => {
        countdown--;
        timerEl.textContent = countdown;
        if (countdown <= 0) {
            clearInterval(interval);
            countdownEl.style.display = 'none';
            resendBtn.style.display = 'inline-block';
        }
    }, 1000);

    // Gửi lại OTP
    resendBtn.addEventListener('click', () => {
        resendBtn.disabled = true;
        resendBtn.textContent = "Đang gửi lại...";

        fetch("resend-otp", {
            method: "POST"
        })
            .then(response => response.text())
            .then(msg => {
                alert(msg); // Hoặc hiển thị đẹp hơn nếu muốn
                resendBtn.disabled = false;
                resendBtn.textContent = "Gửi lại mã OTP";

                // Reset lại đếm ngược
                countdown = 60;
                timerEl.textContent = countdown;
                countdownEl.style.display = 'block';
                resendBtn.style.display = 'none';

                const newInterval = setInterval(() => {
                    countdown--;
                    timerEl.textContent = countdown;
                    if (countdown <= 0) {
                        clearInterval(newInterval);
                        countdownEl.style.display = 'none';
                        resendBtn.style.display = 'inline-block';
                    }
                }, 1000);
            })
            .catch(error => {
                alert("Lỗi khi gửi lại OTP. Vui lòng thử lại.");
                resendBtn.disabled = false;
                resendBtn.textContent = "Gửi lại mã OTP";
            });
    });
</script>
</body>
</html>
