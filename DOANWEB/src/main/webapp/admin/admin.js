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

function openUserForm() {
    console.log("Opening user form..."); // Xác thực xem hàm có được gọi không
    document.getElementById("userModal").style.display = "block";
}
function closeUserForm() {
    document.getElementById("userModal").style.display = "none";
}


