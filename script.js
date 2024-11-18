document.addEventListener('DOMContentLoaded', function () {
    // Lấy tất cả các nút "Xem Chi Tiết" và ảnh sản phẩm
    const viewDetailsBtns = document.querySelectorAll('.view-details');
    const productThumbs = document.querySelectorAll('.product-thumb');
    
    // Lấy modal và các phần tử đóng
    const modal = document.getElementById('productModal');
    const closeModalBtn = document.querySelector('.close');
  
    // Hàm mở modal
    function openModal() {
      modal.style.display = 'block';  // Hiển thị modal
    }
  
    // Hàm đóng modal
    function closeModal() {
      modal.style.display = 'none';  // Ẩn modal
    }
  
    // Mở modal khi click vào nút "Xem Chi Tiết"
    viewDetailsBtns.forEach(button => {
      button.addEventListener('click', openModal);
    });
  
    // Mở modal khi click vào ảnh sản phẩm
    productThumbs.forEach(thumb => {
      thumb.addEventListener('click', openModal);
    });
  
    // Đóng modal khi click vào nút "x"
    closeModalBtn.addEventListener('click', closeModal);
  
    // Đóng modal khi click ra ngoài modal
    window.addEventListener('click', function(event) {
      if (event.target === modal) {
        closeModal();
      }
    });
  });
  