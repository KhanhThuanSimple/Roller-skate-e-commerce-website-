// Lấy phần modal
var modal = document.getElementById("productModal");

// Lấy nút "Đặt hàng nhanh" và nút "Open Modal"
var quickOrderBtn = document.getElementById("quickOrderBtn");
var openModalBtn = document.getElementById("myBtn");

// Lấy phần "close" để đóng modal
var closeModal = document.getElementsByClassName("close")[0];

// Mở modal khi nhấn vào nút "Đặt hàng nhanh"
quickOrderBtn.onclick = function() {
    modal.style.display = "block";
}

// Mở modal khi nhấn vào nút "Open Modal"
openModalBtn.onclick = function() {
    modal.style.display = "block";
}

// Đóng modal khi nhấn vào dấu 'x'
closeModal.onclick = function() {
    modal.style.display = "none";
}

// Đóng modal khi nhấn ra ngoài modal
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
