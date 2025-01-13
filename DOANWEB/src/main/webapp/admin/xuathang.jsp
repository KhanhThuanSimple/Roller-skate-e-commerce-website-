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
                <h3>Quản Lý Xuất Hàng</h3>

                <table>
                    <thead>
                        <tr>
                            <th>ID </th>
                            <th>ID sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Hình ảnh</th>

                            <th>Giá xuất</th>
                            <th>Số Lượng Xuất</th>


                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="export" items="${exports}">
                        <tr>
                            <td>${export.id}</td>
                            <td>${export.productId}</td>
                            <td>${export.productName}</td>
                            <td>${export.image}</td>
                            <td>${export.salePrice}</td>
                            <td>${export.quantity}</td>


                        </tr>
                    </c:forEach>

                        <!-- Các khách hàng sẽ được hiển thị ở đây -->
                    </tbody>
                    < <div class="pagination flex-row">
                    <div class="flex-1">
                        <a href="${pageContext.request.contextPath}/admin/export?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/export?page=${page+1}">
                            <button  id="SauPage" class="button-black"type="button" >Sau</button>

                        </a>
                    </div>

                </div>

                </table>
            </section>
        </main>
    </div>

    <!-- Product Form Modal -->


    <script src="admin.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
    const icon = document.querySelector('.fa-image');
    const fileInput = document.getElementById('avatar');

    icon.addEventListener('click', function() {
        fileInput.click();
    });
});

    </script>
</body>

</html>