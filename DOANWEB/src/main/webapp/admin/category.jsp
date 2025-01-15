<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <th>ID</th>
                    <th>Loại sản phẩm</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>


                        <td>

                            <button onclick="openCategoryUpdateForm({
                                    id: ${category.id},
                                    name:'${category.name}'

                                    })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                            <form action="${pageContext.request.contextPath}/admin/category/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${category.id}">
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
                        <a href="${pageContext.request.contextPath}/admin/category?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/category?page=${page+1}">
                            <button  id="SauPage" class="button-black"type="button" >Sau</button>

                        </a>
                    </div>
                    <button class="button-orange" onclick="openCategorytForm()">Thêm Tài Khoản</button>
                </div>

            </table>
        </section>
    </main>
</div>

<div id="productModal" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeCategorytForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/category/insert"class="flex-colunm" >
            <h2>Thêm Loại Sản Phẩm</h2>


            <div>
                <input name="name" class="input-common"  type="text" placeholder=" Loại sản phẩm">
            </div>

            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>
<div id="modal-update-category" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeCustomerUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/category/update" class="flex-colunm" >
            <h2>Chỉnh sửa sản phẩm</h2>
            <input type="hidden" name="id">

            <div>
                <input name="type" class="input-common"  type="email" placeholder=" Loại sản phẩm">
            </div>

            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>




<script>

    function openCategorytForm() {
        document.getElementById("productModal").style.display = "block";
    }

    function closeCategorytForm() {
        document.getElementById("productModal").style.display = "none";
    }

    function openCategoryUpdateForm(category) {
        const form = document.getElementById("modal-update-category");
        form.querySelector('input[name="id"]').value = category.id;
        form.querySelector('input[name="type"]').value = category.name;

        form.style.display = "block";
    }

    function closeCustomerUpdateForm() {
        document.getElementById("modal-update-category").style.display = "none";
    }
</script>

</body>

</html>