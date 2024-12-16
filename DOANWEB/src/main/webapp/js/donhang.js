// Mở cửa sổ xem chi tiết đơn hàng
function moCTDH1() {
    document.getElementById("modal-CTDH1").style.display = "block";
}
function moCTDH2() {
    document.getElementById("modal-CTDH2").style.display = "block";
}

// Đóng cửa sổ xem chi tiết đơn hàng
function dongCTDH1() {
    document.getElementById("modal-CTDH1").style.display = "none";
}
function dongCTDH2() {
    document.getElementById("modal-CTDH2").style.display = "none";
}

// Đóng modal khi click ngoài modal
window.onclick = function(event) {
    const modal1 = document.getElementById("modal-CTDH1");
    const modal2 = document.getElementById("modal-CTDH2");

    const overlay1 = document.querySelector("#modal-CTDH1 .modal_overlay"); // Lấy overlay của modal 1
    const overlay2 = document.querySelector("#modal-CTDH2 .modal_overlay"); // Lấy overlay của modal 2

    // Kiểm tra xem click có xảy ra ngoài modal (nhấp vào overlay) không
    if (event.target === overlay1) {
        dongCTDH1(); // Đóng modal 1 khi click ngoài modal
    } else if (event.target === overlay2) {
        dongCTDH2(); // Đóng modal 2 khi click ngoài modal
    }
};
