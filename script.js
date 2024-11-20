var modal = document.getElementById("productModal");

var quickOrderBtn = document.getElementById("quickOrderBtn");
var openModalBtn = document.getElementById("myBtn");

var closeModal = document.getElementsByClassName("close")[0];

quickOrderBtn.onclick = function() {
    modal.style.display = "block";
}

openModalBtn.onclick = function() {
    modal.style.display = "block";
}

closeModal.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
const pageItems = document.querySelectorAll('.page-item');

const currentPage = window.location.pathname.split('/').pop();

pageItems.forEach(item => {
  if (item.getAttribute('href') === currentPage) {
    item.classList.add('active'); 
  }
});
function sortProducts(order) {
    const products = [...document.querySelectorAll('.product li')];
    products.sort((a, b) => {
        const priceA = parseInt(a.querySelector('.product-price').textContent.replace(/[^0-9]/g, ''));
        const priceB = parseInt(b.querySelector('.product-price').textContent.replace(/[^0-9]/g, ''));
        return order === 'asc' ? priceA - priceB : priceB - priceA;
    });
    const productList = document.querySelector('.product');
    productList.innerHTML = '';
    products.forEach(product => productList.appendChild(product));
}
