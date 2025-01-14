document.addEventListener('DOMContentLoaded', function () {
    // Xóa sản phẩm
    document.querySelectorAll('.delete-item').forEach(function (button) {
        button.addEventListener('click', function () {
            const productId = this.getAttribute('data-product-id');
            // Gửi yêu cầu xóa sản phẩm
            deleteProduct(productId);
        });
    });

    // Xóa đơn hàng
    document.querySelectorAll('.delete-order').forEach(function (button) {
        button.addEventListener('click', function () {
            const productId = this.getAttribute('data-product-id');
            // Gửi yêu cầu xóa đơn hàng
            deleteOrder(productId);
        });
    });
});

function deleteProduct(productId) {
    // Thực hiện yêu cầu AJAX hoặc điều hướng tới servlet để xóa sản phẩm
}

function deleteOrder(productId) {
    // Thực hiện yêu cầu AJAX hoặc điều hướng tới servlet để xóa đơn hàng
}