<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý kho hàng</title>
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
        <section id="stocks">
            <h3>Quản lý kho hàng</h3>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>ID sản phẩm </th>
                    <th>Tên sản phẩm</th>
                    <th>Ảnh</th>
                    <th>Số lượng tồn kho</th>
<%--                    <th>Thao tác</th>--%>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="stock" items="${stocks}">
                    <tr>
                        <td>${stock.id}</td>
                        <td>${stock.product_id}</td>

                        <td>${stock.product_name}</td>
                        <td>    <img src="${stock.img}" style="width: 50px ; height: 50px ; border-radius: 5px"></td>
                        <td>${stock.quantity_stock}</td>
                    </tr>
                </c:forEach>
                <!-- Các khách hàng sẽ được hiển thị ở đây -->
                </tbody>
                <div class="pagination flex-row">
                    <div class="flex-1">
                        <a href="${pageContext.request.contextPath}/admin/stock?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/stock?page=${page+1}">
                            <button  id="SauPage" class="button-black"type="button" >Sau</button>

                        </a>
                    </div>
                    <button class="button-orange" onclick="openStockForm()">Thêm sản phẩm</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="stockModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeStockForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/stock/add" class="flex-colunm">
            <h2>Thêm màn hình</h2>
            <div>
                <input name="product_id" class="input-common" type="number" placeholder=" Mã sản phẩm">
            </div>
            <div>
                <input name="product_name" class="input-common" type="text" placeholder=" Tên sản phẩm">
            </div>
            <div>
                <input name="quantity_stock" class="input-common" type="text" placeholder=" Số lượng tồn kho">
            </div>



            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu</button>
            </div>


        </form>
    </div>
</div>

<div id="modal-update-stock" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeStockUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/stock/edit" class="flex-colunm">
            <h2>Chỉnh sửa </h2>
            <input type="hidden" name="id">
            <div>
                <input name="product_id" class="input-common" type="number" placeholder=" Mã sản phẩm">
            </div>
            <div>
                <input name="product_name" class="input-common" type="text" placeholder=" Tên sản phẩm">
            </div>
            <div>
                <input name="quantity_stock" class="input-common" type="text" placeholder=" Số lượng tồn kho">
            </div>

            <button class="button-orange" type="submit">Lưu </button>
    </div>


    </form>
</div>
</div>



<script>
    function openStockForm() {
        document.getElementById("stockModal").style.display = "block";
    }

    function closeStockForm() {
        document.getElementById("stockModal").style.display = "none";
    }

    function openStockUpdateForm(stock) {
        const form = document.getElementById("modal-update-stock");
        form.querySelector('input[name="id"]').value = stock.id;
        form.querySelector('input[name="product_id"]').value = stock.product_id;
        form.querySelector('input[name="product_name"]').value = stock.product_name;
        form.querySelector('input[name="quantity_stock"]').value = stock.quantity_stock;

        form.style.display = "block";
    }

    function closeStockUpdateForm() {
        document.getElementById("modal-update-stock").style.display = "none";
    }
</script>
</body>

