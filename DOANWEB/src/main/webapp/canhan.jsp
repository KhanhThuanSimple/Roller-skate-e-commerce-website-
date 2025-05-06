<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Trang Cá Nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">

</head>

<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />

    <!-- Main Content Section -->
    <main class="container">
        <div id="bodyCN">
            <div class="flex-container">
                <div class="left-cn">
                    <!-- Phần avatar -->
                    <div class="flex-avtar">
                        <div class="avatar">
                            <img src="https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg"
                                 alt="Avatar">
                        </div>

                        <div class="In4Avt">
                            <span class="username">${user.name}</span>
                            <a href="#" class="edit-profile"><i class="fas fa-pencil-alt"></i> Chỉnh sửa</a>
                        </div>
                    </div>

                    <!-- Menu quản lý -->
                    <h3><i class="fas fa-user-cog"></i> QUẢN LÝ TÀI KHOẢN</h3>
                    <ul>
                        <li><a href="canhan" class="active"><i class="fas fa-user"></i> Hồ Sơ Cá Nhân</a></li>
                        <form method="get" action="changePassword">
                            <li><a href="changePassword"><i class="fas fa-lock"></i> Đổi Mật Khẩu</a></li>
                        </form>
                    </ul>

                    <h3><i class="fas fa-exchange-alt"></i> QUẢN LÝ GIAO DỊCH</h3>
                    <ul>
                        <li><a href="lichsu"><i class="fas fa-clipboard-list"></i> Đơn hàng của bạn</a></li>
                        <li><a href="listFavorites"><i class="fas fa-heart"></i> Sản phẩm yêu thích</a></li>
                        <li><a href="danhgia"><i class="fas fa-heart"></i> Sản phẩm đã mua</a></li>

                    </ul>

                    <button id="DangXuat" class="dangxuat" onclick="moChacChanDX()">
                        <i class="fas fa-sign-out-alt"></i> Đăng Xuất
                    </button>
                </div>

                <div class="right-cn">
                    <!-- Thông tin cá nhân -->
                    <div class="head-rightcn">
                        <p>Hồ Sơ Của Tôi</p>
                        <div class="pHoSo">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
                        <hr class="nganCachHead">
                    </div>

                    <div class="bot-rightcn">
                        <div class="formThongTinCaNhan">
                            <div class="form-group">
                                <label><i class="fas fa-user-tag"></i> Tên đăng nhập</label>
                                <span>${user.username}</span>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-signature"></i> Tên</label>
                                <span>${user.name}</span>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-envelope"></i> Email</label>
                                <span>${user.username}</span>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-phone"></i> Số điện thoại</label>
                                <span>${user.phone}</span>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-map-marker-alt"></i> Địa chỉ</label>
                                <span>${user.address}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<%--        <div class="edit-button-container" style="text-align: right; margin-top: 30px;">--%>
<%--            <button class="edit-button" onclick="location.href='#'">--%>
<%--                <i class="fas fa-edit"></i> Chỉnh sửa hồ sơ--%>
<%--            </button>--%>
<%--        </div>--%>

    </main>

    <!-- Footer Section -->
    <jsp:include page="comon/footer.jsp" />

    <!-- Modal đăng xuất -->
    <div id="modal_dangxuat" class="modal_dangxuat">
        <div class="modal_overlay"></div>
        <div class="modal_body">
            <h1>Bạn chắc chắn muốn đăng xuất?</h1>
            <div class="button-dx">
                <button onclick="dongChacChanDX()">Không</button>
                <button>
                    <a href="${pageContext.request.contextPath}/logout">OK</a>
                </button>
            </div>
        </div>
    </div>
</div>

<script src="./js/canhan.js"></script>
</body>
</html>