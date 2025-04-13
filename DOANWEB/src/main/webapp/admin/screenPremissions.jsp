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
                    <th>Mã màn hình</th>
                    <th>Tên màn hình</th>
                    <th>Mã quyền</th>
                    <th>Xem</th>
                    <th>Thêm</th>
                    <th>Xóa</th>
                    <th>Sửa</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="screenPremissions" items="${screenPremissionss}">
                    <tr>
                        <td>${screenPremissions.codeScreen}</td>
                        <td>${screenPremissions.nameScreen}</td>

                        <td>${screenPremissions.nameRights}</td>

                        <td>
                            <input type="checkbox" name="read_${screenPremissions.id}" value="1"
                                   <c:if test="${screenPremissions.read == 1}">checked</c:if> >

                        </td>
                        <td>
                            <input type="checkbox" name="add_${screenPremissions.id}" value="1"
                                   <c:if test="${screenPremissions.add == 1}">checked</c:if> >

                        </td>
                        <td>
                            <input type="checkbox" name="delete_${screenPremissions.id}" value="1"
                                   <c:if test="${screenPremissions.delete == 1}">checked</c:if> >

                        </td>
                        <td>
                            <input type="checkbox" name="edit_${screenPremissions.id}" value="1"
                                   <c:if test="${screenPremissions.edit == 1}">checked</c:if> >

                        </td>


                        <td style="display: flex; justify-content: center; align-items: center; gap: 10px;">
                            <button onclick="openScreenPremissionsUpdateForm({
                                    id: ${screenPremissions.id},
                                    idScreen:'${screenPremissions.idScreen}',
                                    nameScreen:'${screenPremissions.nameScreen}',
                                    codeScreen:'${screenPremissions.codeScreen}',
                                    idRights:'${screenPremissions.idRights}',
                                    nameRights:'${screenPremissions.nameRights}',
                                    read:'${screenPremissions.read}',
                                    add:'${screenPremissions.add}',
                                    delete:'${screenPremissions.delete}',
                                    edit:'${screenPremissions.edit}'

                                    })" style="border:none;background-color: unset">
                                <i class="fa-solid fa-pen-to-square"
                                   style="flex:1; padding: 10px; cursor: pointer;"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>


            </table>
        </section>
    </main>
</div>


<div id="modal-detail-rights" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeDetailRights()">&times;</span>
        <input type="hidden" name="id">
        <input type="hidden" name="idScreen">
        <input type="hidden" name="idRights">
        <div >
            <input name="codeScreen" class="input-common" type="text" placeholder="Mã màn hình">
        </div>
        <div >
            <input name="nameScreen" class="input-common" type="text" placeholder="Tên màn hình">
        </div>
        <div >
            <input name="nameRights" class="input-common" type="text" placeholder="Tên quyền">
        </div>


        <table>
            <thead>
            <tr>
                <th>Xem</th>
                <th>Thêm</th>
                <th>Xóa</th>
                <th>Chỉnh sửa</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>
                    <input type="checkbox" name="read" value="view">
                </td>
                <td>
                    <input type="checkbox" name="add" value="add">
                </td>
                <td>
                    <input type="checkbox" name="delete" value="delete">
                </td>
                <td>
                    <input type="checkbox" name="edit" value="edit">
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: right; margin-top: 10px;">
            <button class="button-orange" onclick="saveChanges()">Lưu</button>
        </div>
    </div>
</div>

<div id="modal-update-screenPremissions" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeScreenPremissionsUpdateForm()">&times;</span>
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/admin/screenPremissions/edit">
                <span class="close-btn" onclick="closeDetailRights()">&times;</span>
                <input type="hidden" name="id">
                <input type="hidden" name="idScreen">
                <input type="hidden" name="idRights">
                <div style="display: flex;gap:15px; margin-bottom: 20px">
                    <div >
                        <input disabled name="codeScreen" class="input-common" type="text" placeholder="Mã màn hình">
                    </div>
                    <div >
                        <input disabled name="nameScreen" class="input-common" type="text" placeholder="Tên màn hình">
                    </div>
                    <div >
                        <input disabled name="nameRights" class="input-common" type="text" placeholder="Tên quyền">
                    </div>
                </div>



                <table>
                    <thead>
                    <tr>
                        <th>Xem</th>
                        <th>Thêm</th>
                        <th>Xóa</th>
                        <th>Chỉnh sửa</th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td>
                            <input type="checkbox" name="read" value="1">
                        </td>
                        <td>
                            <input type="checkbox" name="add" value="1">
                        </td>
                        <td>
                            <input type="checkbox" name="delete" value="1">
                        </td>
                        <td>
                            <input type="checkbox" name="edit" value="1">
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div style="text-align: right; margin-top: 10px;">
                    <button class="button-orange" type="submit">Lưu</button>
                </div>
            </form>

        </div>
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
        form.querySelector('input[name="id"]').value = screenPremissions.id;
        form.querySelector('input[name="idScreen"]').value = screenPremissions.idScreen;
        form.querySelector('input[name="read"]').checked  = screenPremissions.read== 1;
        form.querySelector('input[name="add"]').checked  = screenPremissions.add==1;
        form.querySelector('input[name="delete"]').checked  = screenPremissions.delete==1;
        form.querySelector('input[name="edit"]').checked  = screenPremissions.edit==1;

        form.querySelector('input[name="nameScreen"]').value = screenPremissions.nameScreen;
        form.querySelector('input[name="codeScreen"]').value = screenPremissions.codeScreen;
        form.querySelector('input[name="idRights"]').value = screenPremissions.idRights;
        form.querySelector('input[name="nameRights"]').value = screenPremissions.nameRights;
        form.style.display = "block";
    }

    function closeScreenPremissionsUpdateForm() {
        document.getElementById("modal-update-screenPremissions").style.display = "none";
    }

    function openDetailRights() {
        document.getElementById("modal-detail-rights").style.display = "block";
    }

    function closeDetailRights() {
        document.getElementById("modal-detail-rights").style.display = "none";
    }
</script>
<script>
    function saveChanges() {
        // Logic để lưu các thay đổi
      //  alert("Lưu thay đổi thành công!");
    }
</script>
</body>

</html>