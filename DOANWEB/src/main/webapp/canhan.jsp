<%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CaNhan Page</title>
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
                    <!-- Hồ sơ cá nhân - auto khi bật bào tcn-->
                    <div class="head-rightcn">
                        <p> Hồ Sơ Của Tôi </p>
                        <div class="pHoSo">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
                        <hr class="nganCachHead">
                    </div>

                    <div class="bot-rightcn">

                        <div class="formThongTinCaNhan">
                            <div class="form-group">
                                <label>Tên đăng nhập</label>
                                <span>User1</span>
                            </div>
                            <div class="form-group">
                                <label for="name">Tên</label>
                                <input type="text" id="name" value="Nhu">
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <span>ndangthihuyen@gmail.com</span>
                                <a href="#">Thay Đổi</a>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại</label>
                                <span>0372170331</span>
                                <a href="#">Thay Đổi</a>
                            </div>
                            <div class="form-group">
                                <label>Địa chỉ</label>
                                <span>B12/10/31 Ấp 2 Xã VLB Huyện BC TPHCM</span>
                                <a href="#">Thay Đổi</a>
                            </div>
                            <button class="nutLuu" onclick="moNutLuu()">Lưu</button>
                        </div>

                    </div>
                </div>


            </div>


        </div>
    </main>

    <!-- Footer Section -->
    <jsp:include page="comon/footer.jsp" />

</div>
<div id="modal_dangxuat" class="modal_dangxuat">
    <div class="modal_overlay"></div>
    <div class="modal_body">
        <h1>Bạn chắc chắn muốn đăng xuất? </h1>
        <div class="button-dx">
            <button onclick="dongChacChanDX()">Không</button>
            <button id="chacChanDX">Có</button>
        </div>
    </div>
</div>
</body>
<script src="js/canhan.js"></script>

</html>
