<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý quyền màn hình</title>
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
        <section id="screenPremissions">
            <h3>Quản lý quyền màn hình</h3>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã quyền </th>
                    <th>Mã màn hình</th>
                    <th>Xem</th>
                    <th>Thêm</th>
                    <th>Xóa </th>
                    <th>Sửa</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="screenPremissions" items="${screenPremissionss}">
                    <tr>
                        <td>${screenPremissions.id}</td>
                        <td>${screenPremissions.idRights}</td>
                        <td>${screenPremissions.idScreen}</td>
                        <td>${screenPremissions.read}</td>
                        <td>${screenPremissions.add}</td>
                        <td>${screenPremissions.delete}</td>
                        <td>${screenPremissions.edit}</td>

                        <td>
                            <button onclick="openScreenPremissionsUpdateForm({
                                    id: ${screenPremissions.id},
                                    idRights:'${screenPremissions.idRights}',
                                    idScreen:'${screenPremissions.idScreen}',
                                    read:'${screenPremissions.read}',
                                    add:'${screenPremissions.add}',
                                    delete:'${screenPremissions.delete}',
                                    edit:'${screenPremissions.edit}'
                                    })" style="border:none;background-color: unset">
                                <i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i>
                            </button>

                            <form action="${pageContext.request.contextPath}/admin/screenPremissions/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${screenPremissions.id}">
                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                                        style="border:none;background-color: unset">
                                    <i class="fa-solid fa-trash" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <div class="pagination flex-row">
                    <div class="flex-1">
                        <a href="${pageContext.request.contextPath}/admin/user?page=${page-1}">
                            <button id="prevPage" class="button-black" type="button">Trước</button>
                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/user?page=${page+1}">
                            <button id="SauPage" class="button-black" type="button">Sau</button>
                        </a>
                    </div>
                    <button class="button-orange" onclick="openScreenPremissionsForm()">Thêm màn hình</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="screenPremissionsModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeScreenPremissionsForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/screenPremissions/add" class="flex-colunm">
            <h2>Thêm màn hình</h2>
            <div>
                <input name="idRights" class="input-common" type="number" placeholder=" Mã quyền">
            </div>
            <div>
                <input name="idScreen" class="input-common" type="number" placeholder=" Mã màn hình">
            </div>
            <div>
                <input name="read" class="input-common" type="number" placeholder=" Xem">
            </div>
            <div>
                <input name="add" class="input-common" type="number" placeholder=" Thêm">
            </div>
            <div>
                <input name="delete" class="input-common" type="number" placeholder=" Xóa">
            </div>
            <div>
                <input name="edit" class="input-common" type="number" placeholder=" Sửa">
            </div>

            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu</button>
            </div>
        </form>
    </div>
</div>

<div id="modal-update-screenPremissions" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeScreenPremissionsUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/screenPremissions/edit" class="flex-colunm">
            <h2>Chỉnh sửa màn hình</h2>
            <input type="hidden" name="id">
            <div>
                <input name="idRights" class="input-common" type="number" placeholder=" Mã quyền">
            </div>
            <div>
                <input name="idScreen" class="input-common" type="number" placeholder=" Mã màn hình">
            </div>
            <div>
                <input name="read" class="input-common" type="number" placeholder=" Xem">
            </div>
            <div>
                <input name="add" class="input-common" type="number" placeholder=" Thêm">
            </div>
            <div>
                <input name="delete" class="input-common" type="number" placeholder=" Xóa">
            </div>
            <div>
                <input name="edit" class="input-common" type="number" placeholder=" Sửa">
            </div>

            <button class="button-orange" type="submit">Lưu </button>
        </form>
    </div>
</div>

<script>
    function openScreenPremissionsForm() {
        document.getElementById("screenPremissionsModal").style.display = "block";
    }

    function closeScreenPremissionsForm() {
        document.getElementById("screenPremissionsModal").style.display = "none";
    }

    function openScreenPremissionsUpdateForm(screenPremissions) {
        const form = document.getElementById("modal-update-screenPremissions");
        form.querySelector('input[name="idRights"]').value = screenPremissions.idRights;
        form.querySelector('input[name="idScreen"]').value = screenPremissions.idScreen;
        form.querySelector('input[name="read"]').value = screenPremissions.read;
        form.querySelector('input[name="add"]').value = screenPremissions.add;
        form.querySelector('input[name="delete"]').value = screenPremissions.delete;
        form.querySelector('input[name="edit"]').value = screenPremissions.edit;

        form.style.display = "block";
    }

    function closeScreenPremissionsUpdateForm() {
        document.getElementById("modal-update-screenPremissions").style.display = "none";
    }
</script>
</body>

</html>