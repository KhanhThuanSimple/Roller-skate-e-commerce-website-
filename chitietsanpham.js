const imgs = document.querySelectorAll('.img-select a');
const imgBtns = [...imgs];
let imgId = 1;

imgBtns.forEach((imgItem) => {
    imgItem.addEventListener('click', (event) => {
        event.preventDefault();
        imgId = imgItem.dataset.id;
        slideImage(); // Hiển thị hình ảnh tương ứng
    });
});

function slideImage() {
    const displayWidth = document.querySelector('.img-showcase img:first-child').clientWidth;
    document.querySelector('.img-showcase').style.transform = `translateX(${- (imgId - 1) * displayWidth}px)`;
}

// Đóng modal khi nhấn vào phần mờ (background)
window.addEventListener('click', (event) => {
    const modal = document.querySelector('.modal');
    if (event.target === modal) {  // Nếu người dùng click vào nền mờ
        modal.style.display = 'none'; // Đóng modal
    }
});


// Đóng modal khi nhấn vào nút 'Tiếp tục mua hàng'
const continueShoppingBtn = document.querySelector('.btn-continue-shopping');
if (continueShoppingBtn) {
    continueShoppingBtn.addEventListener('click', () => {
        const modal = document.querySelector('.modal');
        modal.style.display = 'none'; // Đóng modal
    });
}

window.addEventListener('resize', slideImage);
document.getElementById("first").addEventListener("click", function() {
    window.location.href = "giohang.html";  // Chuyển hướng đến trang giohang.html
});
document.getElementById("thanhtoan").addEventListener("click", function() {
    window.location.href = "thanhtoan.html";  // Chuyển hướng đến trang thantoan.html
});

document.getElementById("last").addEventListener("click", function() {
    document.getElementById("productModal").style.display = "block";  // Mở modal
});

document.querySelector(".close").addEventListener("click", function() {
    document.getElementById("productModal").style.display = "none";  // Đóng modal
});

window.addEventListener("click", function(event) {
    const modal = document.getElementById("productModal");
    if (event.target === modal) {
        modal.style.display = "none"; // Đóng modal
    }
});
