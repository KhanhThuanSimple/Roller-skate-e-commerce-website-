// phần "bạn chắc chắn đăng xuất?"
function moChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "block";
}

function dongChacChanDX() {
    document.getElementById("modal_dangxuat").style.display = "none";
}

// xử lí khi user chọn chắc chắn đăng xuất
document.getElementById('chacChanDX').addEventListener('click', function () {
    window.location.href = 'login.html';
});