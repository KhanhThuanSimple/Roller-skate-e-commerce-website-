<%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title> Lich Su Mua Hang Page</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="./css/donhang.css">
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
          <div class="order-history">
            <h2>Đơn Hàng Của Bạn</h2>
            <!-- đơn 1 -->
            <div class="order-item">
              <div class="order-info">
                <p>Mã đơn hàng: <strong>#12345</strong></p>
                <p>Ngày đặt: <strong>12/11/2024</strong></p>
                <p>Tổng tiền: <strong>1.500.000 đ</strong></p>
                <p>Trạng thái: <span class="status pending">Đang xử lý</span></p>
              </div>
              <button class="btn-detail" onclick="moCTDH1()">Xem chi tiết</button>
            </div>
            <!-- đơn 2 -->
            <div class="order-item">
              <div class="order-info">
                <p>Mã đơn hàng: <strong>#32378</strong></p>
                <p>Ngày đặt: <strong>02/04/2024</strong></p>
                <p>Tổng tiền: <strong>1.200.000 đ</strong></p>
                <p>Trạng thái: <span class="status delivered">Đã giao</span></p>
              </div>
              <button class="btn-detail" onclick="moCTDH2()">Xem chi tiết</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>

  <!-- Footer Section -->
  <jsp:include page="comon/footer.jsp" />


  <!--  Phần chi tiết đơn hàng  -->
  <!-- đơn 1 -->
  <div id="modal-CTDH1" class="modal_chitietdh">
    <div class="modal_overlay"></div>
    <div class="modal_body_CTDH">
      <h1>Chi Tiết Đơn Hàng</h1>
      <div class="order-info">
        <p><strong>Mã đơn hàng:</strong> #12345</p>
        <p><strong>Tên người nhận:</strong> Huyền Như</p>
        <p><strong>Số điện thoại:</strong> *******331</p>
        <p><strong>Ngày đặt:</strong> 12/11/2024</p>
        <p><strong>Địa chỉ:</strong> Cư Xá D Đại học Nông Lâm TPHCM</p>
        <p><strong>Tổng tiền:</strong> 1.500.000 đ</p>
      </div>
      <table class="product-table">
        <thead>
        <tr>
          <th>Sản phẩm</th>
          <th>Số lượng</th>
          <th>Giá</th>
          <th>Tổng</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>Giày Patin Người Lớn Cougar MZS 307C 3 Màu Đen/ Trắng/ Đỏ</td>
          <td>1</td>
          <td>1.100.000 đ</td>
          <td>1.100.000 đ</td>
        </tr>
        <tr>
          <td>Bánh giày thay thế</td>
          <td>1</td>
          <td>400.000 đ</td>
          <td>400.000 đ</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
          <td colspan="3"><strong>Tổng cộng:</strong></td>
          <td><strong>1.500.000 đ</strong></td>
        </tr>
        </tfoot>
      </table>
      <!--  Quay lại trang đơn hàng của bạn-->
      <button class="back-button" onclick="dongCTDH1()">Quay lại</button>
    </div>
  </div>

  <!-- đơn 2-->
  <div id="modal-CTDH2" class="modal_chitietdh">
    <div class="modal_overlay"></div>
    <div class="modal_body_CTDH">
      <h1>Chi Tiết Đơn Hàng</h1>
      <div class="order-info">
        <p><strong>Mã đơn hàng:</strong> #32378</p>
        <p><strong>Tên người nhận:</strong>Trung Nam</p>
        <p><strong>Số điện thoại:</strong> *******176</p>
        <p><strong>Ngày đặt:</strong> 02/04/2024</p>
        <p><strong>Địa chỉ:</strong> Phường Bình Trị Đông B Quận Bình Tân TPHCM</p>
        <p><strong>Tổng tiền:</strong> 1.200.000 đ</p>
      </div>
      <table class="product-table">
        <thead>
        <tr>
          <th>Sản phẩm</th>
          <th>Số lượng</th>
          <th>Giá</th>
          <th>Tổng</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>Giày Patin Người Lớn Cougar MZS 307C 3 Màu Đen/ Trắng/ Đỏ</td>
          <td>1</td>
          <td>1.200.000 đ</td>
          <td>1.200.000 đ</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
          <td colspan="3"><strong>Tổng cộng:</strong></td>
          <td><strong>1.200.000 đ</strong></td>
        </tr>
        </tfoot>
      </table>
      <!--  Quay lại trang đơn hàng của bạn-->
      <button class="back-button" onclick="dongCTDH2()">Quay lại</button>
    </div>
  </div>
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
<script src="js/canhan.js"></script>
<script src="js/donhang.js"></script>
</body>

</html>