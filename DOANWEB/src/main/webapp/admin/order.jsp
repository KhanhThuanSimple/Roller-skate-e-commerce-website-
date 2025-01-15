<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <th>Điện Thoại</th>
                            <th>Địa Chỉ</th>

                            <th>Phương Thức Thanh Toán</th>
                            <th>Tổng tiền</th>
                            <th>Status</th>
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

                            <td>

                                <button onclick="openProductUpdateForm({
                                        id: ${order.id},
                                        user_id:'${order.user_id}',
                                        name:'${order.name}',
                                        phone:'${order.phone}',
                                        address:'${order.address}',
                                        paymentMethod:'${order.paymentMethod}'
                                        totalAmount:'${order.totalAmount}',

                                        status:'${order.status}'
                                        })" style="border:none;background-color: unset"><i class="fa-solid fa-pen-to-square" style="flex:1; padding: 10px; cursor: pointer;"></i></button>


                                <form action="${pageContext.request.contextPath}/admin/order/delete" method="post"
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
                            <button id="prevPage" class="button-black" type="button">Trước</button>
                            <span id="pageNumber">1</span> / <span id="totalPages">1</span>
                            <button id="SauPage" class="button-black" type="button">Sau</button>
                        </div>

                    </div>

                </table>
            </section>
            <div id="productModal" class="modal">
                <div class="modal-order">
                    <span class="close-btn" onclick="closeProductForm()">&times;</span>

                    <form action="post" class="flex-colunm">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Hình ảnh</th>

                                    <th>Giá</th>


                                    <th>Số lượng </th>
                                    <th>Hành động</th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>TSM1</td>
                                    <td>Flying Eagle</td>
                                    <td class="image"> <img src="../image/CR3.png" alt=""></td>

                                    <td>2.500.000</td>

                                    <td>120</td>

                                    <td>
                                        <button style="border:none;background-color: unset" type="button"  onclick="openProductFormDetail()">
                                            <i class="fa-solid fa-pen-to-square"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i>
                                        </button>
                                        <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>TSM2</td>
                                    <td>Giày patin TSM2</td>
                                    <td class="image"> <img src="../image/MS1.png" alt=""></td>
                                    <td>1.200.000</td>

                                    <td>120</td>

                                    <td>
                                        <button style="border:none;background-color: unset" type="button"  onclick="openProductFormDetail()">
                                            <i class="fa-solid fa-pen-to-square"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i>
                                        </button>
                                        <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>TSM3</td>
                                    <td>Giày patin TSM3</td>
                                    <td class="image"> <img src="../image/EGLS1.png" alt=""></td>
                                    <td>1.260.000</td>

                                    <td>120</td>
                                    <td>
                                        <button style="border:none;background-color: unset" type="button"  onclick="openProductFormDetail()">
                                            <i class="fa-solid fa-pen-to-square"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i>
                                        </button>
                                        <button 
                                            style="border:none;background-color: unset"><i class="fa-solid fa-trash"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                    </td>
                                <tr>
                                    <td>S6S</td>
                                    <td>Giày patin S6S</td>
                                    <td class="image"> <img src="../image/CR2.png" alt=""></td>
                                    <td>2.590.000</td>

                                    <td>200</td>

                                    <td>
                                        <button style="border:none;background-color: unset" type="button"  onclick="openProductFormDetail()">
                                            <i class="fa-solid fa-pen-to-square"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i>
                                        </button>
                                        <button style="border:none;background-color: unset"><i class="fa-solid fa-trash"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>M52</td>
                                    <td>Giày patin M52</td>
                                    <td class="image"> <img src="../image/CR3.png" alt=""></td>
                                    <td>1.990.000</td>

                                    <td>100</td>

                                    <td>
                                        <button style="border:none;background-color: unset" type="button"  onclick="openProductFormDetail()">
                                            <i class="fa-solid fa-pen-to-square"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i>
                                        </button>
                                        <button 
                                            style="border:none;background-color: unset"><i class="fa-solid fa-trash"
                                                style="flex:1; padding: 10px; cursor: pointer;"></i></button>
                                    </td>
                                </tr>
                                <!-- Các khách hàng sẽ được hiển thị ở đây -->
                            </tbody>
                         

                        </table>


                    </form>
                </div>

            </div>
            <div id="productModalDetail" class="modal">
                <div class="modal-content">
                    <span class="close-btn" onclick="closeProductFormDetail()">&times;</span>

                    <form action="post" class="flex-colunm">
                        <h2>Chỉnh sửa sản phẩm</h2>
                        <div>
                            <input class="input-common" type="text" placeholder=" Họ & tên ">
                        </div>
                        <div>
                            <input class="input-common" type="email" placeholder=" Email">
                        </div>
                        <div>
                            <input class="input-common" type="password" placeholder="Mật khẩu">
                        </div>
                        <div class="flex-center">
                            <button class="button-orange">Lưu sản phẩm</button>
                        </div>


                    </form>
                </div>
            </div>
        </main>
    </div>



    <script src="admin.js"></script>
</body>

</html>