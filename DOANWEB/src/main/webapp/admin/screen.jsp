<%@ page import="vn.edu.hcmuaf.fit.doanweb.dao.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý màn hình</title>
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
    <section id="screen">
      <h3>Quản lý màn hình</h3>

      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Mã màn hình </th>
          <th>Tên màn hình</th>
          <th>Thao tác</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="screen" items="${screens}">
          <tr>
            <td>${screen.id}</td>
            <td>${screen.idScreen}</td>
            <td>${screen.nameScreen}</td>


            <td>

              <button onclick="openScreenUpdateForm({
                      id: ${screen.id},
                      idScreen:'${screen.idScreen}',
                      nameScreen:'${screen.nameScreen}'

                      })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


              <form action="${pageContext.request.contextPath}/admin/screen/delete" method="post"
                    style="display:inline;">
                <input type="hidden" name="uid" value="${screen.id}">
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
          <button class="button-orange" onclick="openScreenForm()">Thêm màn hình</button>
        </div>

      </table>
    </section>
  </main>
</div>

<!-- Product Form Modal -->
<div id="screenModal" class="modal">
  <div class="modal-content">
    <span class="close-btn" onclick="closeScreenForm()">&times;</span>

    <form method="post" action="${pageContext.request.contextPath}/admin/screen/add" class="flex-colunm">
      <h2>Thêm màn hình</h2>
      <div>
        <input name="idScreen" class="input-common" type="text" placeholder=" Mã màn hình">
      </div>
      <div>
        <input name="nameScreen" class="input-common" type="text" placeholder=" Tên màn hình">
      </div>


      <div class="flex-center">
        <button type="submit" class="button-orange">Lưu</button>
      </div>


    </form>
  </div>
</div>

<div id="modal-update-screen" class="modal">
  <div class="modal-content">
    <span class="close-btn" onclick="closeScreenUpdateForm()">&times;</span>

    <form method="post" action="${pageContext.request.contextPath}/admin/screen/edit" class="flex-colunm">
      <h2>Chỉnh sửa màn hình</h2>
      <input type="hidden" name="id">
      <div>
        <input name="idScreen" class="input-common" type="text" placeholder=" Mã màn hình ">
      </div>
      <div>
        <input name="nameScreen" class="input-common" type="text" placeholder=" Tên màn hình ">
      </div>


        <button class="button-orange" type="submit">Lưu </button>
      </div>


    </form>
  </div>
</div>



<script>
  function openScreenForm() {
    document.getElementById("screenModal").style.display = "block";
  }

  function closeScreenForm() {
    document.getElementById("screenModal").style.display = "none";
  }

  function openScreenUpdateForm(screen) {
    const form = document.getElementById("modal-update-screen");
    form.querySelector('input[name="id"]').value = screen.id;
    form.querySelector('input[name="idScreen"]').value = screen.idScreen;
    form.querySelector('input[name="nameScreen"]').value = screen.nameScreen;

    form.style.display = "block";
  }

  function closeScreenUpdateForm() {
    document.getElementById("modal-update-screen").style.display = "none";
  }
</script>
</body>

</html>