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

// Thêm sự kiện submit form sản phẩm
document.getElementById("productForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    // Lấy giá trị từ form
    const productName = document.getElementById("productName").value;
    const productPrice = document.getElementById("productPrice").value;
    const productCategory = document.getElementById("productCategory").value;
    const productDescription = document.getElementById("productDescription").value;

    // Thực hiện gửi dữ liệu hoặc lưu vào database (Ví dụ: sử dụng Ajax hoặc gửi đến API)
    console.log("Tên sản phẩm:", productName);
    console.log("Giá:", productPrice);
    console.log("Danh mục:", productCategory);
    console.log("Mô tả:", productDescription);
    
    // Đóng form sau khi thêm sản phẩm
    closeProductForm();
});
