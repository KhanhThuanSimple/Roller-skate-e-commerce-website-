<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đổi Mật Khẩu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/doimatkhau.css" /> <!-- giữ css đổi mật khẩu -->
</head>

<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />

    <main class="container">
        <div id="bodyCN">
            <div class="flex-container">
                <!-- Sidebar giống canhan.jsp -->
                <div class="left-cn">
                    <h3><i class="fas fa-user-cog"></i> QUẢN LÝ TÀI KHOẢN</h3>
                    <ul>
                        <li><a href="canhan"><i class="fas fa-user"></i> Hồ Sơ Cá Nhân</a></li>
                        <li><a href="changePassword" class="active"><i class="fas fa-lock"></i> Đổi Mật Khẩu</a></li>
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

                <!-- Nội dung chính -->
                <div class="right-cn">
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
                                    <input name="oldPassword" type="password" id="matKhauCu" placeholder="Nhập mật khẩu cũ" required />
                                </div>
                            </div>
                            <div class="mkGroup">
                                <label for="matKhauMoi">Mật khẩu mới</label>
                                <div class="input-wrapper">
                                    <input name="newPassword" type="password" id="matKhauMoi" placeholder="Nhập mật khẩu mới" required />
                                </div>
                            </div>
                            <div class="mkGroup">
                                <label for="xacNhanMK">Xác nhận mật khẩu</label>
                                <div class="input-wrapper">
                                    <input name="confirmPassword" type="password" id="xacNhanMK" placeholder="Xác nhận mật khẩu mới" required />
                                </div>
                            </div>

                            <c:if test="${not empty error}">
                                <div class="error-message" style="color: red; margin: 10px 0;">
                                        ${error}
                                </div>
                            </c:if>

                            <c:if test="${not empty success}">
                                <div class="success-message" style="color: green; margin: 10px 0;">
                                        ${success}
                                </div>
                            </c:if>

                            <button class="nutLuu" type="submit">Lưu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="comon/footer.jsp" />

    <!-- Modal đăng xuất giống canhan.jsp -->
    <div id="modal_dangxuat" class="modal_dangxuat">
        <div class="modal_overlay" onclick="dongChacChanDX()"></div>
        <div class="modal_body">
            <h1>Bạn chắc chắn muốn đăng xuất?</h1>
            <div class="button-dx">
                <button type="button" onclick="dongChacChanDX()">Không</button>
                <button type="button" id="btnConfirmLogout">OK</button>
            </div>
        </div>
    </div>
</div>

<script src="./js/logout.js"></script>

</body>
</html>