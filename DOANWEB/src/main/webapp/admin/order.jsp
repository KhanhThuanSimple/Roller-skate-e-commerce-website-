<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Shop Giày Partin</title>
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>

<body>
    <div class="container">
        <!-- Sidebar -->
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <main class="main-content">
            <!-- Section: Khách hàng -->
            <section id="customers">
                <h3>Quản lý Đơn Hàng</h3>

                <table>
                    <thead>
                        <tr>
                            <th>ID </th>
                            <th>ID Khách Hàng</th>
                            <th>Tên khách hàng</th>

                            <th>Điện Thoại</th>
                            <th>Địa Chỉ</th>

                            <th>Phương Thức Thanh Toán</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                            <th>Trạng thái xuất kho</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.user_id}</td>
                            <td>${order.name}</td>
                            <td>${order.phone}</td>
                            <td>${order.address}</td>
                            <td>${order.paymentMethod}</td>
                            <td>${order.totalAmount}</td>
                            <td>${order.status}</td>
                            <td>${""}</td>
<%--                            oder.status_export--%>

                            <td style="display: flex; gap: 10px">

                            <form method="post" action="order-detail?pid=${order.id}">
                                <button style="border:none;background-color: unset" type="submit">
                                    <i class="fa-regular fa-eye" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                </button>
                            </form>
<%--                        test="${order.status_export != 'Đã xuất kho' --%>
                                <c:if test="${true}">
                                    <form action="${pageContext.request.contextPath}/admin/order/export?id=${order.id}" method="post"
                                          style="display:inline;">
                                        <button  class="button-orange " type="submit" onclick="return confirm('Xác nhận xuất hàng !')"
                                        >
                                            Xuất hàng
                                        </button>
                                    </form>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                    <!-- Các khách hàng sẽ được hiển thị ở đây -->

                       
                    </tbody>

                    <div class="pagination flex-row">
                        <div class="flex-1">
                            <button id="prevPage" class="button-black" type="button">Trước</button>
                            <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                            <button id="SauPage" class="button-black" type="button">Sau</button>
                        </div>

                    </div>

                </table>
            </section>
            <div id="orderDetailModal" class="modal">
                <div class="modal-order">
                    <span class="close-btn" onclick="closeOrderDetailForm()">&times;</span>

                    <form action="post" class="flex-colunm">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID </th>
                                    <th>ID đơn hàng</th>
                                    <th>Số lượng</th>

                                    <th>Giá</th>

                                    <th>Hành động</th>

                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="orderDetail" items="${orderDetails}">
                                <tr>
                                    <td>${orderDetail.id}</td>
                                    <td>${orderDetail.order_id}</td>
                                    <td>${orderDetail.product_id}</td>
                                    <td>${orderDetail.quantity}</td>
                                    <td>${orderDetail.price}</td>


                                    <td>

                                        <button onclick="openOrderDetailUpdateForm({
                                                id: ${orderDetail.id},
                                                order_id:'${orderDetail.order_id}',
                                                product_id:'${orderDetail.product_id}',
                                                quantity:'${orderDetail.quantity}',
                                                price:'${orderDetail.price}'
                                                })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                                        <c:if test="${permission.delete == 1  }">
                                            <form action="${pageContext.request.contextPath}/admin/order/delete" method="post" style="display:inline;">
                                                <input type="hidden" name="uid" value="${orderDetail.id}">
                                                <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                                                        style="border:none;background-color: unset">
                                                    <i class="fa-solid fa-trash" style="flex:1; padding: 10px; cursor: pointer;"></i>
                                                </button>
                                            </form>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                         

                        </table>


                    </form>
                </div>

            </div>
            <div id="model-update-orderDetail" class="modal">
                <div class="modal-content">
                    <span class="close-btn" onclick="closeOrderDetailUpdateForm()">&times;</span>

                    <form method="post" action="${pageContext.request.contextPath}/admin/order/update" class="flex-colunm">
                        <h2>Chỉnh sửa sản phẩm</h2>
                        <input type="hidden" name="id">
                        <div>
                            <input  name="name" class="input-common" type="text" placeholder=" Tên sản phẩm">
                        </div>



                        <div>
                            <input name="title" class="input-common" type="text" placeholder="Tiêu đề">
                        </div>
                        <div>
                            <input name="description" class="input-common" type="text" placeholder="Mô tả">
                        </div>
                        <div>
                            <input name="cateID" class="input-common" type="text" placeholder="ID loại sản phẩm">
                        </div>
                        <div>
                            <input name="offer" class="input-common" type="text" placeholder="Khuyến mãi">
                        </div>
                        <div>

                            <i class="fa-regular fa-image" style="font-size: xx-large; color: #a3a3a3; cursor: pointer;"></i>
                            <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg"
                                   style="visibility: hidden;" />
                        </div>
                        <div class="flex-center">
                            <button type="submit" class="button-orange">Lưu sản phẩm<button>
                        </div>

                    </form>
                </div>
            </div>
            <div id="model-update-order" class="modal">
                <div class="modal-content">
                    <span class="close-btn" onclick="closeOrderUpdateForm()">&times;</span>

                    <form method="post" action="${pageContext.request.contextPath}/admin/order/update" class="flex-colunm">
                        <h2>Chỉnh sửa đơn hàng</h2>
                        <input type="hidden" name="id">
                        <div>
                            <input name="user_id" class="input-common" type="number" placeholder=" ID khách hàng">
                        </div>
                        <div>
                            <input name="name" class="input-common" type="text" placeholder=" Tên khách hàng">
                        </div>

                        <div>
                            <input name="phone" class="input-common" type="number" placeholder="Số điện thoại">
                        </div>
                        <div>
                            <textarea name="address" rows="3" class="input-common" placeholder="Địa chỉ"></textarea>
                        </div>

                        <div>
                            <input name="paymentMethod" class="input-common" type="text" placeholder="Phương thức thanh toán">
                        </div>

                        <div>
                            <input name="totalAmount" class="input-common" type="text" placeholder="Tổng tiền">
                        </div>

                        <div>
                            <input name="status" class="input-common" type="text" placeholder="Status">
                        </div>

                        <div class="flex-center">
                            <button class="button-orange" type="submit">Lưu tài khoản</button>
                        </div>


                    </form>
                </div>
            </div>
        </main>
    </div>



    <script src="admin.js"></script>
    <script>
        function openOrderDetailForm() {
            document.getElementById("orderDetailModal").style.display = "block";
        }

        function closeOrderDetailForm() {
            document.getElementById("orderDetailModal").style.display = "none";
        }

        function openOrderUpdateForm(order) {
            const form = document.getElementById("model-update-order");
            form.querySelector('input[name="id"]').value = order.id;
            form.querySelector('input[name="user_id"]').value = order.name;
            form.querySelector('input[name="name"]').value = order.username;
            form.querySelector('input[name="phone"]').value = order.phone;
            form.querySelector('textarea[name="address"]').value = order.address;
            form.querySelector('input[name="paymentMethod"]').value = order.username;
            form.querySelector('input[name="totalAmount"]').value = order.phone;
            form.querySelector('input[name="status"]').value = order.phone;


            form.style.display = "block";
        }

        function closeOrderUpdateForm() {
            document.getElementById("model-update-order").style.display = "none";
        }






        function openProductUpdateForm(product) {
            const form = document.getElementById("modal-update-orderDetail");
            form.querySelector('input[name="id"]').value = product.id;
            form.querySelector('input[name="name"]').value = product.name;
            form.querySelector('input[name="img"]').value = product.imag;
            form.querySelector('input[name="title"]').value = product.title;
            form.querySelector('in[name="description"]').value = product.description;
            form.querySelector('in[name="cateID"]').value = product.cateID;

            form.querySelector('in[name="offer"]').value = product.offer;
            form.style.display = "block";
        }

        function closeUpdateProductForm() {
            document.getElementById("modal-update-orderDetail").style.display = "none";
        }

    </script>
</body>

</html>