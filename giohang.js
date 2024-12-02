document.getElementById('XemThemSP').addEventListener('click', function () {
    window.location.href = 'sanpham.html';
});

// Xử lý nút thanh toán
document.getElementById('ThanhToan').addEventListener('click', function () {
    window.location.href = 'thanhtoan.html';
});

document.addEventListener("DOMContentLoaded", () => {
    // Hàm xử lý nút giảm số lượng
    function giamSoLuongSP(button) {
        const cartItem = button.closest(".cart-item");
        const quantityInput = cartItem.querySelector(".qty-input");
        const unitPrice = getUnitPrice(cartItem);
        let quantity = parseInt(quantityInput.value);

        if (quantity > 1) {
            quantity -= 1;
            quantityInput.value = quantity;
            updateTotalPrice(cartItem, quantity, unitPrice);
            updateGrandTotal();
        }
    }

    // Hàm xử lý nút tăng số lượng
    function tangSoLuongSP(button) {
        const cartItem = button.closest(".cart-item");
        const quantityInput = cartItem.querySelector(".qty-input");
        const unitPrice = getUnitPrice(cartItem);
        let quantity = parseInt(quantityInput.value);

        quantity += 1;
        quantityInput.value = quantity;
        updateTotalPrice(cartItem, quantity, unitPrice);
        updateGrandTotal();
    }

    // Hàm tính và cập nhật tổng tiền của một sản phẩm
    function updateTotalPrice(cartItem, quantity, unitPrice) {
        const totalPriceElement = cartItem.querySelector(".total-price");
        const totalPrice = unitPrice * quantity;
        totalPriceElement.textContent = `${totalPrice.toLocaleString()}đ`;
    }

    // Hàm lấy đơn giá từ một sản phẩm
    function getUnitPrice(cartItem) {
        const priceElement = cartItem.querySelector(".price");
        return parseFloat(priceElement.textContent.replace(/[^\d]/g, ""));
    }

    // Hàm tính và cập nhật tổng tiền cho tất cả các sản phẩm
    function updateGrandTotal() {
        let grandTotal = 0;
        document.querySelectorAll(".cart-item").forEach(cartItem => {
            const quantity = parseInt(cartItem.querySelector(".qty-input").value);
            const unitPrice = getUnitPrice(cartItem);
            grandTotal += quantity * unitPrice;
        });
        document.querySelector(".grand-total").textContent = `${grandTotal.toLocaleString()}đ`;
    }

    // Hàm xử lý xóa sản phẩm
    function handleDelete(button) {
        const cartItem = button.closest(".cart-item");
        cartItem.remove();
        updateGrandTotal();
    }

    // Gán sự kiện cho các nút và ô nhập
    function initializeCart() {
        // Gán sự kiện cho nút giảm
        document.querySelectorAll(".cart-item .qty-btn:first-child").forEach(button => {
            button.addEventListener("click", () => giamSoLuongSP(button));
        });

        // Gán sự kiện cho nút tăng
        document.querySelectorAll(".cart-item .qty-btn:last-child").forEach(button => {
            button.addEventListener("click", () => tangSoLuongSP(button));
        });

        // Gán sự kiện cho nút xóa
        document.querySelectorAll(".cart-item .delete-item").forEach(button => {
            button.addEventListener("click", () => handleDelete(button));
        });

        // Cập nhật tổng tiền ban đầu
        updateGrandTotal();
    }

    // Khởi chạy
    initializeCart();
});
