<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý quyền</title>
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
        <section id="rights">
            <h3>Quản lý màn hình</h3>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    >
                    <th>Thao tác</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach var="rights" items="${rightss}">
                    <tr>
                        <td>${rights.id}</td>
                        <td>${rights.name}</td>


                        <td style="display: flex; justify-content: center; align-items: center; gap: 10px;">

                            <button onclick="openRightsUpdateForm({
                                    id: ${rights.id},
                                    name:'${rights.name}',

                                    })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square"
                                                                                       style="flex:1; padding: 10px; cursor: pointer;"></i>
                            </button>


                            <form action="${pageContext.request.contextPath}/admin/rights/delete" method="post"
                                  style="display:inline;">
                                <input type="hidden" name="uid" value="${rights.id}">
                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                                        style="border:none;background-color: unset">
                                    <i class="fa-solid fa-trash" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                </button>
                            </form>

                            <form method="post" action="screenPremissions?pid=${rights.id}">
                                <button style="border:none;background-color: unset" type="submit">
                                    <i class="fa-regular fa-eye" style="flex:1; padding: 10px; cursor: pointer;"></i>
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
                            <button id="prevPage" class="button-black" type="button">Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                        <a href="${pageContext.request.contextPath}/admin/user?page=${page+1}">
                            <button id="SauPage" class="button-black" type="button">Sau</button>

                        </a>
                    </div>
                    <button class="button-orange" onclick="openRightsForm()">Thêm</button>
                </div>

            </table>
        </section>
    </main>
</div>

<!-- Product Form Modal -->
<div id="rightsModal" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closerightsForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/rights/insert" class="flex-colunm">
            <h2>Thêm màn hình</h2>
            <div>
                <input name="id" class="input-common" type="text" placeholder=" Mã quyền">
            </div>
            <div>
                <input name="name" class="input-common" type="text" placeholder=" Chức vụ">
            </div>


            <div class="flex-center">
                <button type="submit" class="button-orange">Lưu</button>
            </div>


        </form>
    </div>
</div>

<div id="modal-update-rights" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeRightsUpdateForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/rights/edit" class="flex-colunm">
            <h2>Chỉnh sửa màn hình</h2>
            <input type="hidden" name="id">
            <div>
                <input name="id" class="input-common" type="text" placeholder=" Mã quyền">
            </div>
            <div>
                <input name="name" class="input-common" type="text" placeholder=" Chức vụ ">
            </div>

        </form>


        <button class="button-orange" type="submit">Lưu</button>
    </div>
</div>




<div id="modal-detail-rights" class="modal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeDetailRights()">&times;</span>
        <table>
            <thead>
            <tr>
                <th>Tên màn hình</th>
                <th>Xem</th>
                <th>Thêm</th>
                <th>Xóa</th>
                <th>Chỉnh sửa</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="rights" items="${rightss}">
                <tr>
                    <td>${rights.name}</td>
                    <td>
                        <input type="checkbox" name="view_${rights.id}" value="view">
                    </td>
                    <td>
                        <input type="checkbox" name="add_${rights.id}" value="add">
                    </td>
                    <td>
                        <input type="checkbox" name="delete_${rights.id}" value="delete">
                    </td>
                    <td>
                        <input type="checkbox" name="edit_${rights.id}" value="edit">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="text-align: right; margin-top: 10px;">
            <button class="button-orange" onclick="saveChanges()">Lưu</button>
        </div>
    </div>
</div>




<script>
    function openRightsForm() {
        document.getElementById("rightsModal").style.display = "block";
    }

    function closeRightsForm() {
        document.getElementById("rightsModal").style.display = "none";
    }


    function openRightsUpdateForm(rights) {
        const form = document.getElementById("modal-update-rights");
        form.querySelector('input[name="id"]').value = rights.id;
        form.querySelector('input[name="name"]').value = rights.name;


        form.style.display = "block";
    }

    function closeRightsUpdateForm() {
        document.getElementById("modal-update-rights").style.display = "none";
    }
</script>
</body>

</html>