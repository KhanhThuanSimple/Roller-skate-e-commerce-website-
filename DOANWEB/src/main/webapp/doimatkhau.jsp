<%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title> Doi Mat Khau Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/doimatkhau.css">
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./style.css">
</head>
<style>
    .button-dx a{
        text-decoration: none;
        color: white;
        z-index: 10;
    }
</style>
<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />

    <!-- Main Content Section -->
    <main class="container">
        <!-- Nội dung chính của trang sẽ được đặt ở đây -->
        <div id="bodyCN">
            <div class="flex-container">
                <div class="left-cn">
                    <!-- Phan avatar cua KH da dang nhap-->
                    <div class="flex-avtar">
                        <div class="avatar">
                            <img src="https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg"
                                 alt="Avatar">
                        </div>

                        <div class="In4Avt">
                            <span class="username">user1</span>
                            <a href="canhan.jsp" class="suaHoSo">
                                <p>Sửa Hồ Sơ</p>
                            </a>
                        </div>
                    </div>


                    <h3>QUẢN LÝ TÀI KHOẢN</h3>
                    <ul>
                        <li><a href="canhan.jsp"> Hồ Sơ Cá Nhân</a></li>
                        <li><a href="doimatkhau.jsp"> Đổi Mật Khẩu</a></li>
                    </ul>
                    <h3>QUẢN LÝ GIAO DỊCH</h3>
                    <ul>
                        <li><a href="donhang.jsp">Đơn hàng của bạn</a></li>
                    </ul>
                    <button id="DangXuat" class="dangxuat" onclick="moChacChanDX()"> Đăng Xuất</button>
                </div>


                <div class="right-cn">
                    <!-- Hồ sơ cá nhân -->
                    <div class="head-rightcn">
                        <p>Đổi mật khẩu</p>
                        <div class="pmk">Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu với người khác</div>
                        <hr class="nganCachHead">
                    </div>

                    <div class="bot-rightcn">
                        <form method="post" action="changePassword">
                        <div class="mkGroup">
                            <label for="matKhauCu">Mật khẩu cũ</label>
                            <div class="input-wrapper">
                                <input name="oldPassword" type="password" id="matKhauCu" placeholder="Nhập mật khẩu...">
                            </div>
                        </div>
                        <div class="mkGroup">
                            <label for="matKhauMoi">Mật khẩu mới</label>
                            <div class="input-wrapper">
                                <input name="newPassword" type="password" id="matKhauMoi" placeholder="Nhập mật khẩu...">
                            </div>
                        </div>
                        <div class="mkGroup">
                            <label for="xacNhanMK">Xác nhận mật khẩu</label>
                            <div class="input-wrapper">
                                <input name="confirmPassword" type="password" id="xacNhanMK" placeholder="Nhập mật khẩu...">
                            </div>
                            <button class="nutLuu" type="submit">Lưu</button>

                        </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="doimatkhau.js"></script>

    <!-- Footer Section -->
    <jsp:include page="comon/footer.jsp" />

</div>
<div id="modal_dangxuat" class="modal_dangxuat">
    <div class="modal_overlay"></div>
    <div class="modal_body">
        <h1>Bạn chắc chắn muốn đăng xuất? </h1>
        <div class="button-dx">
            <button onclick="dongChacChanDX()">Không</button>
            <button>
                <a   href="${pageContext.request.contextPath}/logout">OK</a>
            </button>        </div>
    </div>
</div>
</body>
<script src="js/canhan.js"></script>

</html>
