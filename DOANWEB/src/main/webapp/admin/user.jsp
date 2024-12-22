<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page import="java.util.List" %>
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
                <h3>Quản lý Tài khoản</h3>

                <table>
                    <thead>
                        <tr>
                            <th>Họ & tên</th>
                            <th>Email</th>
                            <th>password</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.username}</td>
                            <td>3456</td>
                            <td> Linh Trung, TP.Thủ Đức, TP.HCM</td>
                            <td>
                                <button style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"  style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                        <!-- Các khách hàng sẽ được hiển thị ở đây -->
                    </tbody>
                    <div class="pagination flex-row">
                        <div class="flex-1">
                            <button id="prevPage" class= "button-black">Trước</button>
                            <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                            <button id="SauPage" class="button-black" type="button" >Sau</button>
                        </div>
                        <button class="button-orange" onclick="openProductForm()">Thêm Tài Khoản</button>
                    </div>

                </table>
            </section>
        </main>
    </div>

    <!-- Product Form Modal -->
    <div id="productModal" class="modal">
        <div class="modal-content" >
            <span class="close-btn" onclick="closeProductForm()">&times;</span>

            <form action="post" class="flex-colunm" >
                <h2>Thêm tài khoản</h2>
                <div >
                    <input  class="input-common" type="text" placeholder=" Họ &tên">
                </div>
                <div>
                    <input class="input-common"  type="email" placeholder=" Email">
                </div>
                <div>
                    <input class="input-common" type="password" placeholder="Mật khẩu">
                </div>
                <div  class="flex-center">
                    <button class="button-orange">Lưu tài khoản</button>
                </div>


            </form>
        </div>
    </div>

    <script src="admin.js"></script>
</body>

</html>