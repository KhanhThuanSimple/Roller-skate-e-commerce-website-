// Ẩn hiện phần nhập vào khung nhập mật khẩu

document.addEventListener("DOMContentLoaded", function () {
    const toggleButtons = document.querySelectorAll(".togglePassword");

    toggleButtons.forEach(button => {
        button.addEventListener("click", function () {
            const targetInput = document.getElementById(this.dataset.target);
            if (targetInput.type === "password") {
                targetInput.type = "text";
                this.textContent = "Ẩn";
            } else {
                targetInput.type = "password";
                this.textContent = "Hiện";
            }
        });
    });
});
