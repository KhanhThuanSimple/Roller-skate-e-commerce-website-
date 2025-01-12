

// Mở cửa sổ thêm sản phẩm
function openProductForm() {
    document.getElementById("productModal").style.display = "block";
}
function openProductFormDetail() {
    document.getElementById("productModalDetail").style.display = "block";
}




// Đóng cửa sổ thêm sản phẩm
function closeProductForm() {
    document.getElementById("productModal").style.display = "none";
}
function closeProductFormDetail(){
    document.getElementById("productModalDetail").style.display = "none";
}


function openPayForm(){
    document.getElementById("overlay").style.display = "block";
    document.getElementById("payModal").style.display = "block";
}

function closePayForm() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("payModal").style.display = "none";
}
document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('form').addEventListener('submit', function(event) {

        //  closeProductForm(); // Gọi hàm đóng form khi form được gửi
    });
});

function openCustomerForm() {
    console.log("Opening user form..."); // Xác thực xem hàm có được gọi không
    document.getElementById("customerModal").style.display = "block";
}
function closeCustomerForm() {
    document.getElementById("customerModal").style.display = "none";
}


function  openEditProductForm() {
    console.log("Opening user form..."); // Xác thực xem hàm có được gọi không
    document.getElementById("productEditModal").style.display = "block";
}
function closeEditProductForm() {
    document.getElementById("productEditModal").style.display = "none";
}

function  openNhapHangForm() {
    console.log("Opening user form..."); // Xác thực xem hàm có được gọi không
    document.getElementById("nhapHangModal").style.display = "block";
}
function closeNhapHangForm() {
    document.getElementById("nhapHangModal").style.display = "none";
}

function  openXuatHangForm() {
    console.log("Opening user form..."); // Xác thực xem hàm có được gọi không
    document.getElementById("xuatHangModal").style.display = "block";
}
function closeXuatHangForm() {
    document.getElementById("xuatHangModal").style.display = "none";
}

