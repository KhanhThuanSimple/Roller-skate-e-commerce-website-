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
    <style>
        /* Cải tiến giao diện nhưng giữ nguyên logic */
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 0 15px;
        }

        #bodyCN {
            background-color: #f8f9fa;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            overflow: hidden;
        }

        .flex-container {
            display: flex;
            flex-wrap: wrap;
        }

        .left-cn {
            flex: 0 0 250px;
            background-color: #fff;
            padding: 20px;
            border-right: 1px solid #eaeaea;
        }

        .right-cn {
            flex: 1;
            background-color: #fff;
            padding: 30px;
        }

        .flex-avtar {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #f0f0f0;
        }

        .avatar {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            overflow: hidden;
            margin-right: 15px;
        }

        .avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .In4Avt .username {
            font-weight: 600;
            font-size: 16px;
            color: #333;
        }

        .left-cn h3 {
            font-size: 14px;
            color: #666;
            text-transform: uppercase;
            margin: 25px 0 15px;
            letter-spacing: 0.5px;
        }

        .left-cn ul {
            list-style: none;
            padding: 0;
            margin: 0 0 20px;
        }

        .left-cn ul li {
            margin-bottom: 8px;
        }

        .left-cn ul li a {
            display: block;
            padding: 8px 0;
            color: #333;
            text-decoration: none;
            transition: all 0.2s;
            font-size: 14px;
        }

        .left-cn ul li a:hover {
            color: #1890ff;
            padding-left: 5px;
        }

        .dangxuat {
            width: 100%;
            padding: 10px;
            background-color: #f53d2d;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            margin-top: 10px;
            transition: background-color 0.2s;
        }

        .dangxuat:hover {
            background-color: #e62e1e;
        }

        .button-dx a {
            text-decoration: none;
            color: white;
            z-index: 10;
        }

        .head-rightcn p {
            font-size: 24px;
            font-weight: 600;
            margin: 0 0 5px;
            color: #333;
        }

        .pHoSo {
            color: #666;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .nganCachHead {
            border: none;
            height: 1px;
            background-color: #eaeaea;
            margin: 20px 0;
        }

        .formThongTinCaNhan {
            display: grid;
            grid-template-columns: 1fr;
            gap: 20px;
        }

        .form-group {
            display: flex;
            align-items: center;
        }

        .form-group label {
            flex: 0 0 150px;
            color: #666;
            font-size: 14px;
        }

        .form-group span {
            flex: 1;
            color: #333;
            font-size: 15px;
        }

        /* Modal đăng xuất */
        .modal_dangxuat {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            display: none;
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }

        .modal_overlay {
            position: absolute;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
        }

        .modal_body {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            z-index: 1;
            width: 400px;
            max-width: 90%;
            text-align: center;
        }

        .modal_body h1 {
            font-size: 18px;
            margin-bottom: 25px;
            color: #333;
        }

        .modal_body button {
            padding: 8px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
        }

        .modal_body button:first-child {
            background-color: #f0f0f0;
            color: #333;
        }

        .modal_body button:last-child {
            background-color: #f53d2d;
            color: white;
        }

        @media (max-width: 768px) {
            .flex-container {
                flex-direction: column;
            }

            .left-cn {
                border-right: none;
                border-bottom: 1px solid #eaeaea;
            }

            .form-group {
                flex-direction: column;
                align-items: flex-start;
            }

            .form-group label {
                margin-bottom: 5px;
            }
        }
    </style>
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
                        </div>
                    </div>

                    <!-- Menu quản lý -->
                    <h3>QUẢN LÝ TÀI KHOẢN</h3>
                    <ul>
                        <li><a href="canhan">Hồ Sơ Cá Nhân</a></li>
                        <form method="get" action="changePassword">
                            <li><a href="changePassword">Đổi Mật Khẩu</a></li>
                        </form>
                    </ul>

                    <h3>QUẢN LÝ GIAO DỊCH</h3>
                    <ul>
                        <li><a href="lichsu">Đơn hàng của bạn</a></li>
                        <li><a href="listFavorites">Sản phẩm yêu thích</a></li>
                    </ul>

                    <button id="DangXuat" class="dangxuat" onclick="moChacChanDX()">Đăng Xuất</button>
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
                                <label>Tên đăng nhập</label>
                                <span>${user.username}</span>
                            </div>
                            <div class="form-group">
                                <label>Tên</label>
                                <span>${user.name}</span>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <span>${user.username}</span>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <span>${user.phone}</span>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <span>${user.address}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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