<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Shop Giày Partin</title>
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>

<body>
<div class="container">
    <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>

    <!-- Main Content -->
    <main class="main-content">
        <!-- Section: Khách hàng -->
        <section id="customers">
            <h3>Quản Lý Sản Phẩm</h3>

            <table>
                <thead>
                <tr>
                    <th>ID sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Hình ảnh</th>

                    <th>Giá</th>
                    <th>Tiêu đề</th>


                    <th>Mô tả</th>
                    <th>ID loại sản phâm</th>
                    <th>Quà tặng</th>
                    <th>Thao tác</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>

                        <td>
                            <img src="${product.img}" style="width: 50px ; height: 50px ; border-radius: 5px">
                        </td>

                        <td>${product.price}</td>
                        <td>${product.title}</td>
                        <td>
                            <div style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap">${product.description}</div>
                        </td>
                        <td>${product.cateId}</td>

                        <td>
                            <div style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap">${product.offer}</div>
                        </td>


                        <td>

                            <button
                                    type="button" onclick="openProductUpdateForm({
                                    id: ${product.id},
                                    name:'${product.name}',
                                    price:${product.price},
                                    description:'',
                                    title:'${product.title}',
                                    cateId:${product.cateId},
                                    offer:''
                                    })" style="border:none;background-color: unset">
                                <i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;">

                                </i>
                            </button>


                            <form action="${pageContext.request.contextPath}/admin/product/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${product.id}">
                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                                        style="border:none;background-color: unset">
                                    <i class="fa-solid fa-trash" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>


                <!-- Các khách hàng sẽ được hiển thị ở đây -->
                </tbody>
                <div class="pagination flex-row">
                    <div class="flex-1">
                        <button id="prevPage" class="button-black" type="button">Trước</button>
                        <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                        <button id="SauPage" class="button-black" type="button">Sau</button>
                    </div>
                    <button class="button-orange" onclick="openProductForm()">Thêm Sản Phẩm</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="productModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeProductrForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/product/insert" class="flex-colunm">
            <h2>Thêm sản phẩm</h2>

            <div>
                <input name="name" class="input-common" type="text" placeholder=" Tên sản phẩm">
            </div>
            <div>
                <input name="price" class="input-common" type="text" placeholder=" Giá ">
            </div>

            <div>
                <input name="title" class="input-common" type="text" placeholder="Tiêu đề">
            </div>
            <div>
                <input name="description" class="input-common" type="text" placeholder="Mô tả">
            </div>
            <div>
                <input name="cateID" class="input-common" type="number" placeholder="ID loại sản phẩm">
            </div>
            <div>
                <input name="offer" class="input-common" type="text" placeholder="Khuyến mãi">
            </div>
            <div>
                <input name="img" class="input-common" type="text" placeholder="Link ảnh">
            </div>
            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu sản phẩm
                    <button>
            </div>


        </form>

    </div>
</div>
<div id="modal-update-product" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeProductUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/product/update" class="flex-colunm">
            <h2>Chỉnh sửa sản phẩm</h2>
            <input type="hidden" name="id">
            <div>
                <input name="name" class="input-common" type="text" placeholder=" Tên sản phẩm">
            </div>
            <div>
                <input name="price" class="input-common" type="text" placeholder=" Giá ">
            </div>

            <div>
                <input name="title" class="input-common" type="text" placeholder="Tiêu đề">
            </div>
            <div>
                <input name="description" class="input-common" type="text" placeholder="Mô tả">
            </div>
            <div>
                <input name="cateID" class="input-common" type="number" placeholder="ID loại sản phẩm">
            </div>
            <div>
                <input name="offer" class="input-common" type="text" placeholder="Khuyến mãi">
            </div>
            <div>
                <input name="img" class="input-common" type="text" placeholder="Link ảnh">
            </div>
            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu sản phẩm
                    <button>
            </div>


        </form>

    </div>
</div>

<script src="admin.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const icon = document.querySelector('.fa-image');
        const fileInput = document.getElementById('avatar');

        icon.addEventListener('click', function () {
            fileInput.click();
        });
    });


    function openProductForm() {
        document.getElementById("productModal").style.display = "block";
    }

    function closeProductrForm() {
        document.getElementById("productModal").style.display = "none";
    }

    function openProductUpdateForm(product) {
        console.log("haha")
        const form = document.getElementById("modal-update-product");
        form.querySelector('input[name="id"]').value = product.id;
        form.querySelector('input[name="name"]').value = product.name;
        form.querySelector('input[name="img"]').value = product.img;
        form.querySelector('input[name="title"]').value = product.title;
        form.querySelector('input[name="description"]').value = product.description;
        form.querySelector('input[name="cateID"]').value = product.cateId;
        form.querySelector('input[name="price"]').value = product.price;
        form.querySelector('input[name="id"]').value = product.id;

        form.querySelector('input[name="offer"]').value = product.offer;
        form.style.display = "block";
    }

    function closeProductUpdateForm() {
        document.getElementById("modal-update-product").style.display = "none";
    }

</script>
</body>

</html>