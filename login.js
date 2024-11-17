document.addEventListener('DOMContentLoaded', function() {
    // Lấy các phần tử cần thiết
    const loginForm = document.querySelector('form');
    const emailInput = document.querySelector('input[type="email"]');
    const passwordInput = document.querySelector('input[type="password"]');
    const loginButton = document.querySelector('.button-orange');

    // Sự kiện khi bấm vào nút Đăng nhập
    loginButton.addEventListener('click', function(event) {
        event.preventDefault(); // Ngăn form submit tự động
        
        const email = emailInput.value.trim();
        const password = passwordInput.value.trim();
        
        // Kiểm tra email và mật khẩu có được nhập hay không
        if (email === '' || password === '') {
            alert('Vui lòng nhập đầy đủ email và mật khẩu.');
            return;
        }

        // Kiểm tra định dạng email
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email)) {
            alert('Vui lòng nhập địa chỉ email hợp lệ.');
            return;
        }

        // Xử lý đăng nhập (đây chỉ là ví dụ đơn giản, thực tế cần kết nối server)
        if (email === 'test@example.com' && password === 'password') {
            alert('Đăng nhập thành công!');
            window.location.href = 'index.html'; // Chuyển hướng sau khi đăng nhập thành công
        } else {
            alert('Email hoặc mật khẩu không đúng.');
        }
    });
});
