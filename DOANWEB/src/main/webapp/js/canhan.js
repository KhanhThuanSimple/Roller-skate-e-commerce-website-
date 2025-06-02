// Mở modal xác nhận đăng xuất
function moChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "block";
}

// Đóng modal xác nhận đăng xuất
function dongChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "none";
}

// Xử lý khi user chọn chắc chắn đăng xuất
document.addEventListener('DOMContentLoaded', function () {
    const btnChacChanDX = document.getElementById('chacChanDX');
    if (btnChacChanDX) {
        btnChacChanDX.addEventListener('click', function () {
            // Chuyển hướng về servlet logout
            window.location.href = `${window.location.origin}${window.location.pathname.replace(/\/[^\/]*$/, '')}/logout`;
        });
    }
});

// Mở modal lưu thông tin hồ sơ
function moNutLuu() {
    document.getElementById("modal_luu").style.display = "block";
}

// Đóng modal lưu thông tin hồ sơ
function dongNutLuu() {
    document.getElementById("modal_luu").style.display = "none";
}
// Mở modal xác nhận đăng xuất
function moChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "block";
}

// Đóng modal xác nhận đăng xuất
function dongChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "none";
}

// Xử lý nút "OK" trong modal đăng xuất
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('btnConfirmLogout').addEventListener('click', function () {
        // Chuyển hướng đến URL logout servlet
        // Lấy context path động (nếu app không deploy ở root)
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/',1));
        window.location.href = contextPath + "/logout";
    });
});
