<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Trang Cá Nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/canhan.css" />
    <style>
        /* Tinh chỉnh thêm */
        .left-cn ul {
            padding-left: 0;
            list-style-type: none;
        }
        .left-cn ul li {
            margin-bottom: 12px;
        }
        .left-cn ul li a {
            display: block;
            padding: 10px 12px;
            color: #333;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.25s ease;
        }
        .left-cn ul li a.active,
        .left-cn ul li a:hover {
            background-color: #007bff;
            color: white;
        }

        .flex-avtar {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 25px;
        }
        .avatar img {
            border-radius: 50%;
            width: 70px;
            height: 70px;
            object-fit: cover;
        }
        .In4Avt .username {
            font-weight: 700;
            font-size: 1.2rem;
            color: #222;
        }
        .edit-profile {
            font-size: 0.9rem;
            color: #007bff;
            text-decoration: none;
            margin-top: 4px;
            display: inline-block;
            transition: color 0.3s ease;
        }
        .edit-profile:hover {
            color: #0056b3;
        }

        .formThongTinCaNhan .form-group {
            margin-bottom: 15px;
        }
        .formThongTinCaNhan label {
            font-weight: 600;
            color: #555;
            display: inline-block;
            width: 140px;
        }
        .formThongTinCaNhan span {
            font-size: 1rem;
            color: #333;
        }

        /* Responsive */
        @media (max-width: 600px) {
            .flex-container {
                flex-direction: column;
            }
            .left-cn, .right-cn {
                width: 100% !important;
            }
        }
    </style>
</head>

<body>
<div id="wrapper">
    <jsp:include page="comon/header.jsp" />

    <main class="container">
        <div id="bodyCN">
            <div class="flex-container" style="gap: 20px;">
                <div class="left-cn" style="min-width: 220px;">
                    <div class="flex-avtar">
                        <div class="avatar">
                            <img src="https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg"
                                 alt="Avatar" />
                        </div>

                        <div class="In4Avt">
                            <span class="username">${user.name}</span><br />
                            <a href="#" class="edit-profile"><i class="fas fa-pencil-alt"></i> Chỉnh sửa</a>
                        </div>
                    </div>

                    <h3><i class="fas fa-user-cog"></i> QUẢN LÝ TÀI KHOẢN</h3>
                    <ul>
                        <li><a href="canhan" class="active"><i class="fas fa-user"></i> Hồ Sơ Cá Nhân</a></li>
                        <li><a href="changePassword"><i class="fas fa-lock"></i> Đổi Mật Khẩu</a></li>
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

                <div class="right-cn" style="flex-grow:1; max-width: 600px;">
                    <div class="head-rightcn">
                        <p>Hồ Sơ Của Tôi</p>
                        <div class="pHoSo">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
                        <hr class="nganCachHead" />
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
    </main>

    <jsp:include page="comon/footer.jsp" />

    <!-- Modal đăng xuất giống changePassword.jsp -->
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
