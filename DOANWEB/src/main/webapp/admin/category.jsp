<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Shop Giày Partin</title>
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
<div class="container">
    <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>


    <!-- Main Content -->
    <main class="main-content">
        <!-- Section: Khách hàng -->
        <section id="customers">
            <h3>Quản Lý Loại Sản Phẩm</h3>

            <table>
                <thead>
                <tr>
                    <th>ID </th>
                    <th>ID sản phẩm</th>
                    <th>Loại sản phẩm</th>
                    <th>Thao tác</th>


                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>01</td>
                    <td>TSM1</td>
                    <td>Flying Eagle</td>




                    <td>
                        <button  onclick="openCategoryForm()" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                        <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"  style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                    </td>
                </tr>


                <div class="pagination flex-row">
                    <div class="flex-1">
                        <button id="prevPage" class="button-black" type="button">Trước</button>
                        <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                        <button id="SauPage" class="button-black" type="button">Sau</button>
                    </div>
                    <button class="button-orange" onclick="openProductForm()">Thêm Loại Sản Phẩm</button>
                </div>

            </table>
        </section>
    </main>
</div>

<div id="productModal" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeProductForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/category/add"class="flex-colunm" >
            <h2>Thêm Loại Sản Phẩm</h2>
            <div >
                <input name="id-product" class="input-common" type="text" placeholder=" ID sản phẩm">
            </div>
            <div>
                <input name="type-product" class="input-common"  type="email" placeholder=" Loại sản phẩm">
            </div>

            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>
<div id="modal-update-category" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeCategoryUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/category/update" class="flex-colunm" >
            <h2>Chỉnh sửa sản phẩm</h2>
            <input type="hidden" name="id">
            <div >
                <input name="id-product" class="input-common" type="text" placeholder=" ID sản phẩm">
            </div>
            <div>
                <input name="type-product" class="input-common"  type="email" placeholder=" Loại sản phẩm">
            </div>

            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>



<script src="admin.js"></script>
<script>

    function openProductForm() {
        document.getElementById("productModal").style.display = "block";
    }

    function closeProductForm() {
        document.getElementById("productModal").style.display = "none";
    }

    function openCategoryUpdateForm(category) {
        const form = document.getElementById("modal-update-category");
        form.querySelector('input[name="id-product"]').value = category.id;
        form.querySelector('input[name="type-product"]').value = category.name;

        form.style.display = "block";
    }

    function closeCustomerUpdateForm() {
        document.getElementById("modal-update-category").style.display = "none";
    }
</script>

</body>

</html>