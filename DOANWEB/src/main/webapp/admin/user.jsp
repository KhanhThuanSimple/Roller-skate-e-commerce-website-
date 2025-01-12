<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý User</title>
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
            <h3>Quản lý Tài khoản</h3>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Họ & tên</th>
                    <th>Email</th>
                    <th>password</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.phone}</td>
                        <td>${user.address}</td>

                        <td>

                            <button onclick="openUserUpdateForm({
                                    id: ${user.id},
                                    name:'${user.name}',
                                    username:'${user.username}',
                                    phone:'${user.phone}',
                                    address:'${user.address}'
                                    })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                            <form action="${pageContext.request.contextPath}/admin/user/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${user.id}">
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
                        <a href="${pageContext.request.contextPath}/admin/user?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/user?page=${page+1}">
                            <button  id="SauPage" class="button-black"type="button" >Sau</button>

                        </a>
                    </div>
                    <button class="button-orange" onclick="openUserForm()">Thêm Tài Khoản</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="productModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeUserForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/user/insert" class="flex-colunm">
            <h2>Thêm tài khoản Admin</h2>
            <div>
                <input name="name" class="input-common" type="text" placeholder=" Họ & tên">
            </div>
            <div>
                <input name="username" class="input-common" type="email" placeholder=" Email">
            </div>
            <div>
                <input name="pass" class="input-common" type="password" placeholder="Mật khẩu">
            </div>
            <div>
                <input name="phone" class="input-common" type="number" placeholder="Số điện thoại">
            </div>
            <div>
                <textarea name="address" rows="3" class="input-common" placeholder="Địa chỉ"></textarea>
            </div>
            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu</button>
            </div>


        </form>
    </div>
</div>

<div id="modal-update-user" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeUserUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/user/update" class="flex-colunm">
            <h2>Chỉnh sửa tài khoản</h2>
            <input type="hidden" name="id">
            <div>
                <input name="name" class="input-common" type="text" placeholder=" Họ &tên">
            </div>
            <div>
                <input name="username" class="input-common" type="email" placeholder=" Email">
            </div>

            <div>
                <input name="phone" class="input-common" type="number" placeholder="Số điện thoại">
            </div>
            <div>
                <textarea name="address" rows="3" class="input-common" placeholder="Địa chỉ"></textarea>
            </div>
            <div class="flex-center">
                <button class="button-orange" type="submit">Lưu tài khoản</button>
            </div>


        </form>
    </div>
</div>



<script>
    function openUserForm() {
        document.getElementById("userModal").style.display = "block";
    }

    function closeUserForm() {
        document.getElementById("userModal").style.display = "none";
    }

    function openUserUpdateForm(user) {
        const form = document.getElementById("modal-update-user");
        form.querySelector('input[name="id"]').value = user.id;
        form.querySelector('input[name="name"]').value = user.name;
        form.querySelector('input[name="username"]').value = user.username;
        form.querySelector('input[name="phone"]').value = user.phone;
        form.querySelector('textarea[name="address"]').value = user.address;
        form.style.display = "block";
    }

    function closeUserUpdateForm() {
        document.getElementById("modal-update-user").style.display = "none";
    }
</script>
</body>

</html>