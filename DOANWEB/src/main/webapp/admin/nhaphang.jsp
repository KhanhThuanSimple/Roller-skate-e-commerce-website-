<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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




    <main class="main-content">

        <section id="customers">
            <h3>Quản Lý Nhập Hàng</h3>

            <table>
                <thead>
                <tr>
                    <th>ID </th>
                    <th>ID sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Hình ảnh</th>

                    <th>Giá Nhập</th>
                    <th>Số Lượng Nhập</th>



                    <th>Thao tác</th>

                </tr>
                </thead>
                <c:forEach var="importOrders" items="${imports}">
                    <tr>
                        <td>${importOrders.id}</td>
                        <td>${importOrders.product_id}</td>
                        <td>${importOrders.product_name}</td>
                        <td>
                            <img src="${importOrders.image}" style="width: 50px ; height: 50px ; border-radius: 5px">
                        </td>

                        <td>${importOrders.purchase_price}</td>
                        <td>${importOrders.quantity}</td>

                        <td>

                            <button onclick="openImportUpdateForm({
                                    id: ${importOrders.id},
                                    product_id:'${importOrders.product_id}',

                                    purchase_price:'${importOrders.purchase_price}',
                                    quantity:'${importOrders.quantity}'
                                    })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                            <form action="${pageContext.request.contextPath}/admin/import/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${importOrders.id}">
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
                        <a href="${pageContext.request.contextPath}/admin/import?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/import?page=${page+1}">
                            <button  id="SauPage" class="button-black"type="button" >Sau</button>

                        </a>
                    </div>
                    <button class="button-orange" onclick="openImportForm()">Thêm sản phẩm</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="productModal" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeImportForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/import/insert"  class="flex-colunm" >
            <h2>Thêm sản phẩm</h2>
            <div >
                <input name="product_id"  class="input-common" type="text" placeholder=" ID sản phẩm">
            </div>

            <div>
                <input name="purchase_price" class="input-common" type="number" placeholder="Giá nhập hàng">
            </div>

            <div>
                <input name="quantity" class="input-common" type="number" placeholder="Số lượng nhập">

            </div>

            <div  class="flex-center ">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>

    </div>
</div>
<div id="modal-update-import" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeImportUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/import/update" class="flex-colunm" >
            <h2>Chỉnh sửa sản phẩm</h2>
            <input type="hidden" name="id">
            <div >
                <input name="product_id"  class="input-common" type="text" placeholder=" ID sản phẩm">
            </div>

            <div>
                <input class="input-common" type="number" name="purchase_price" placeholder="Giá nhập hàng">
            </div>

            <div>
                <input class="input-common" type="number" name="quantity" placeholder="Số lượng nhập">

            </div>

            <div  class="flex-center ">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>

    </div>
</div>

<%--<script src="admin.js"></script>--%>


<script>
    document.addEventListener('DOMContentLoaded', function() {
        const icon = document.querySelector('.fa-image');
        const fileInput = document.getElementById('avatar');

        icon.addEventListener('click', function() {
            fileInput.click();
        });
    });

    function openImportForm() {
        document.getElementById("productModal").style.display = "block";
    }

    function closeImportForm() {
        document.getElementById("productModal").style.display = "none";
    }

    function openImportUpdateForm(importOrders) {
        const form = document.getElementById("modal-update-import");
        form.querySelector('input[name="id"]').value = importOrders.id;
        form.querySelector('input[name="product_id"]').value = importOrders.product_id;

        form.querySelector('input[name="purchase_price"]').value = importOrders.purchase_price;
        form.querySelector('input[name="quantity"]').value = importOrders.quantity;

        form.style.display = "block";
    }

    function closeImportUpdateForm() {
        document.getElementById("modal-update-import").style.display = "none";
    }
</script>
</body>

</html>