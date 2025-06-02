document.addEventListener('DOMContentLoaded', function() {
    // Lấy các phần tử DOM
    const btnDangXuat = document.getElementById('DangXuat');
    const modal = document.getElementById('modal_dangxuat');
    const overlay = document.querySelector('.modal_overlay');
    const btnCancel = document.getElementById('btnCancelLogout');
    const btnConfirm = document.getElementById('btnConfirmLogout');

    // Mở modal
    if (btnDangXuat) {
        btnDangXuat.addEventListener('click', function(e) {
            e.preventDefault();
            modal.style.display = 'flex';
            document.body.style.overflow = 'hidden';
        });
    }

    // Đóng modal
    function closeModal() {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    }

    // Sự kiện đóng modal
    if (overlay) overlay.addEventListener('click', closeModal);
    if (btnCancel) btnCancel.addEventListener('click', closeModal);

    // Xử lý đăng xuất
    if (btnConfirm) {
        btnConfirm.addEventListener('click', function() {
            window.location.href = 'logout'; // Sử dụng base href
        });
    }

    // Ngăn sự kiện click lan truyền từ modal body
    const modalBody = document.querySelector('.modal_body');
    if (modalBody) {
        modalBody.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    }
});