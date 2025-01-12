
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
            <h3>Quản lý Khách Hàng</h3>

            <table>
                <thead>
                <tr>
                    <th>ID Khách hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Email</th>
                    <th>Điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.username}</td>
                        <td>${customer.phone}</td>

                        <td> ${customer.address}</td>
                        <td>
                            <button onclick="openCustomerForm()" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                            <form action="${pageContext.request.contextPath}/admin/customer/delete" method="post" style="display:inline;">
                                <input type="hidden" name="uid" value="${customer.id}">
                                <button type="submit" style="border:none;background-color: unset">
                                    <i class="fa-solid fa-trash" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>




                </tbody>
                <div class="pagination flex-row">
                    <div class="flex-1">
                        <a href="${pageContext.request.contextPath}/admin/customer?page=${page-1}">
                            <button  id="prevPage" class= "button-black" type="button" >Trước</button>

                        </a>
                        <span id="pageNumber">${page}</span> / <span id="totalPages">${totalPage}</span>
                       <a href="${pageContext.request.contextPath}/admin/customer?page=${page+1}">
                           <button  id="SauPage" class="button-black"type="button" >Sau</button>

                       </a>
                    </div>
                    <button class="button-orange" onclick="openProductForm()">Thêm Khách Hàng</button>

                </div>

            </table>
        </section>
    </main>
</div>
<div id="productModal" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeProductForm()">&times;</span>

        <form method="post" action="${pageContext.request.contextPath}/admin/customer/add"class="flex-colunm" >
            <h2>Thêm Khách Hàng</h2>
            <div >
                <input name="name" class="input-common" type="text" placeholder=" Họ & tên">
            </div>
            <div>
                <input name="username" class="input-common"  type="email" placeholder=" Email">
            </div>
            <div>
                <input name="pass" class="input-common" type="password" placeholder="Mật khẩu">
            </div>
            <div>
                <input name="phone" class="input-common" type="password" placeholder="Số điện thoại">
            </div>
            <div>
                <textarea name="address" rows="3" class="input-common"  placeholder="Địa chỉ"></textarea>
            </div>
            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>
<div id="customerModal" class="modal">
    <div class="modal-content" >
        <span class="close-btn" onclick="closeCustomerForm()">&times;</span>

        <form method="post" class="flex-colunm" >
            <h2>Chỉnh sửa Khách Hàng</h2>
            <div >
                <input name="name" class="input-common" type="text" placeholder=" Họ & tên">
            </div>
            <div>
                <input name="username" class="input-common"  type="email" placeholder=" Email">
            </div>
            <div>
                <input name="pass" class="input-common" type="password" placeholder="Mật khẩu">
            </div>
            <div>
                <input name="phone" class="input-common" type="password" placeholder="Số điện thoại">
            </div>
            <div>
                <textarea name="address" rows="3" class="input-common"  placeholder="Địa chỉ"></textarea>
            </div>
            <div  class="flex-center">
                <button type="submit" class="button-orange">Lưu </button>
            </div>


        </form>
    </div>
</div>



<script src="admin.js"></script>

</body>

</html>