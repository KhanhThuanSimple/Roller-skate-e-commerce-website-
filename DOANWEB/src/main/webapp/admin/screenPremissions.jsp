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
        <section id="screen-premissions">
            <h3>Quản lý quyền màn hình</h3>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã quyênf </th>
                    <th>Mã màn hình</th>
                    <th>Xem</th>
                    <th>Thêm</th>
                    <th>Xóa </th>
                    <th>Sửa</th>
                    <th>Thao tác</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="screen-premissions" items="${screen-premissionss}">
                    <tr>
                        <td>${screen-premissions.id}</td>
                        <td>${screen-premissions.idRights}</td>
                        <td>${screen-premissions.idScreen}</td>
                        <td>${screen-premissions.read}</td>
                        <td>${screen-premissions.add}</td>
                        <td>${screen-premissions.delete}</td>
                        <td>${screen-premissions.edit}</td>


                        <td>

                            <button onclick="openScreenUpdateForm({
                                    id: ${screen-premissions.id},
                                    idRights:'${screen-premissions.idScreen}',
                                    idScreen:'${screen-premissions.idScreen}',
                                    read:'${screen-premissions.read}',
                                    add:'${screen-premissions.add}',
                                    delete:'${screen-premissions.delete}',
                                    edit:'${screen-premissions.edit}'


                                    })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                            <form action="${pageContext.request.contextPath}/admin/screen-premissions/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${screen-premissions.id}">
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
                    <button class="button-orange" onclick="openScreenPremissionsForm()">Thêm màn hình</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="screen-premissionsModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeScreenPremissionsForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/screen-premissions/add" class="flex-colunm">
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

<div id="modal-update-screen-premissions" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeScreenPremissionsUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/screen-premissions/edit" class="flex-colunm">
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
    </div>


    </form>
</div>
</div>



<script>
    function openScreenPremissionsForm() {
        document.getElementById("screen-premissionsModal").style.display = "block";
    }

    function closeScreenPrimessionsForm() {
        document.getElementById("screen-premissionsModal").style.display = "none";
    }

    function openScreenPremissionsUpdateForm(screen-premissions) {
        const form = document.getElementById("modal-update-screen-premissions");
        form.querySelector('input[name="idRights"]').value = screen-premissions.id;
        form.querySelector('input[name="idScreen"]').value = screen.idScreen;
        form.querySelector('input[name="nameScreen"]').value = screen.nameScreen;

        form.style.display = "block";
    }

    function closeScreenUpdateForm() {
        document.getElementById("modal-update-screen-premissions").style.display = "none";
    }
</script>
</body>

</html>