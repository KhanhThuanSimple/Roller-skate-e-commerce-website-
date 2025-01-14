<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>LienHe Page</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

  <link rel="stylesheet" href="./css/style.css" />
  <link rel="stylesheet" href="./css/lienhe.css">
</head>

<body>
<div id="wrapper">

  <jsp:include page="comon/header.jsp" />



</div>
<div class="contact-container flex-colunm">
  <div class="contact-banner">
    <img src="https://ocamcongnghiep.com/wp-content/uploads/2020/07/lien-he-banner-1400x339-1.jpg">
    <!-- <img src="image/" alt=""> -->
    <div class="contact-info">
      <div class="info">
        <div>
          <h2 class=" info-title">Thông tin liên hệ</h2>

        </div>
        <div class="content">
          <div class="item-content">
            <p>Địa chỉ chúng tôi</p>
            <div>Khu phố 6, Phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh.</div>
          </div>
          <div class="item-content">
            <p>Email chúng tôi</p>
            <div>Chăm sóc khách hàng: cskh@ghn.vn <br>
              Hỗ trợ kinh doanh: hotrokinhdoanh@ghn.vn</div>
          </div>
          <div class="item-content">
            <p>Điện thoại</p>
            <div>0383967879</div>
          </div>
          <div class="item-content">
            <p>Thời gian làm việc</p>
            <div>Thứ 2 đến Chủ Nhật từ 8h30 đến 21h00 (Trừ các ngày lễ)</div>
          </div>
        </div>

      </div>
      <div class="support">
        <div>
          <h2>Hỗ trợ tư vấn</h2>
        </div>
        <div class="form-support">
          <form action="post">
            <div>
              <input class="input-common" type="text" placeholder="Tên của bạn">
            </div>
            <div class="flex-row">
              <div class="flex-1">
                <input class="input-common" type="email" placeholder="Email của bạn">

              </div>
              <div class="flex-1">
                <input class="input-common" type="text" placeholder="Số điện thoại của bạn">
              </div>
            </div>
            <div>

              <textarea rows="4" class="input-common" placeholder="Nội dung cần tư vấn"></textarea>

            </div>
            <div>
              <button class="button-orange">Gửi cho chúng tôi</button>
            </div>

          </form>
        </div>

      </div>



    </div>
    <div class="contact-map">
      <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.2588533438734!2d106.78567241428766!3d10.86790596047152!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175276398969f7b%3A0x9672b7efd0893fc4!2zxJDhuqFpIEjhu41jIE7DtG5nIEzDom0!5e0!3m2!1svi!2s!4v1604590333753!5m2!1svi!2s"
              width="2048" height="350" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false"
              tabindex="0"></iframe>

    </div>
  </div>

  <jsp:include page="comon/footer.jsp" />

</div>
</body>

</html>