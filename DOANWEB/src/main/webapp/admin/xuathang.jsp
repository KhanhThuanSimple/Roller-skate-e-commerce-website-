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
                            <th>ID đơn hàng</th>
                            <th>ID sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Hình ảnh</th>

                            <th>Giá xuất</th>
                            <th>Số Lượng Xuất</th>


                           
<%--                            <th>Thao tác</th>--%>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>01</td>
                            <td>TSM1</td>
                            <td>Flying Eagle</td>
                            <td class="image"> <img src="../image/CR3.png" alt=""></td>

                            <td>2.500.000</td>
                            <td>30</td>
                           

                            
<%--                            <td>--%>
<%--                                <button  onclick="openXuatHangForm()" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>--%>
<%--                                 <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"  style="flex:1; padding: 10px; cursor: pointer;"></i></button>--%>
<%--                            </td>--%>
                        </tr>

                        <!-- Các khách hàng sẽ được hiển thị ở đây -->
                    </tbody>
                    <div class="pagination flex-row">
                        <div class="flex-1">
                            <button id="prevPage" class= "button-black" type="button">Trước</button>
                            <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                            <button id="SauPage" class="button-black"type="button">Sau</button>
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