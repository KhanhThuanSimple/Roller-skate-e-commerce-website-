<%--
  Created by IntelliJ IDEA.
  User: Huyền Như
  Date: 12/22/2024
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Gioithieu Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/gioithieu.css">
    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
<div id="wrapper">
    <!-- Header Section -->
    <jsp:include page="comon/header.jsp" />

    <!-- Main Content Section -->
    <main class="container">
        <!-- Nội dung chính của trang sẽ được đặt ở đây -->
        <div id="bodyGT">
            <h4>
                1. GIỚI THIỆU
            </h4>
            <p>
                Công ty TNHH NTN Việt Nam được thành lập từ năm 2018 - là doanh nghiệp chuyên cung cấp các sản phẩm
                giày
                Patin.</p>
            <p> Với niềm đam mê trượt patin cháy bỏng, khát khao được kinh doanh, yêu thích dạy trẻ nhỏ mà NTN đã
                không
                ngừng hoàn thiện mỗi ngày nhằm đem đến các dịch vụ hỗ trợ khách hàng tốt nhất trong những năm
                vừa
                qua. Công ty mở rộng và phát triển với hệ thống 10 cửa hàng trên khắp các quận của TP Hồ Chí
                Minh.</p>
            <h4>
                2. LỊCH SỬ HÌNH THÀNH VÀ PHÁT TRIỂN CỦA CÔNG TY
            </h4>
            <div class="timeline">
                <div class="event">
                    <div class="date">12/11/2018</div>
                    <div class="description"><span class="highlight">Công ty TNHH NTN Việt Nam</span> được thành
                        lập,
                        trụ sở chính tại Khu Phố 6, Phường Linh Trung TP Thủ Đức
                    </div>
                </div>
                <div class="event">
                    <div class="date">12/2018</div>
                    <div class="description">NTN chính thức trở thành đơn vị phân phối độc quyền các sản phẩm của
                        <span class="highlight">thương hiệu Cougar</span> tại thị trường Việt Nam
                    </div>
                </div>
                <div class="event">
                    <div class="date">10/10/2020</div>
                    <div class="description"><span class="highlight">Giải đua NTN Cup 2020</span> được tổ chức tại
                        TPHCM
                        với
                        quy mô lên đến 100 vận động viên
                    </div>
                </div>
                <div class="event">
                    <div class="date">07/2021</div>
                    <div class="description"><span class="highlight">Giày Patin NTN</span> chính thức được xuất khẩu
                        sang
                        thị trường Trung Quốc và Thái Lan, nâng tầm thương hiệu Việt ra thị trường quốc tế
                    </div>
                </div>
                <div class="event">
                    <div class="date">10/10/2022</div>
                    <div class="description"><span class="highlight">Giải đua NTN Cup 2022</span> được tổ chức lần 2
                        tại
                        Nhà
                        Văn hóa Sinh Viên TP Thủ Đức
                    </div>
                </div>
                <div class="event">
                    <div class="date">02/2023</div>
                    <div class="description"><span class="highlight">Khai trương chi nhánh Cát Lái - Quận 2 - TPHCM
                            </span>,
                        nâng tổng số cửa hàng lên 10 cửa hàng
                    </div>
                </div>
                <div class="event">
                    <div class="date">09/05/2024</div>
                    <div class="description"><span class="highlight">NTN</span> chính thức phát động phong trào tổ
                        chức
                        <span class="highlight">Giải đua patin thường niên TPHCM</span>
                    </div>
                </div>
                <div class="event">
                    <div class="date">10/10/2024</div>
                    <div class="description"><span class="highlight">Giải đua NTN Cup 2024</span> được tổ chức lần 3
                        tại
                        Nhà Văn hóa Thanh Niên Quận 1 TPHCM
                    </div>
                </div>

            </div>
            <h4>3. TẦM NHÌN, SỨ MỆNH VÀ GIÁ TRỊ CỐT LÕI</h4>
            <p><span class="highlight">Tầm nhìn:</span> NTN định hướng phát triển thành hãng thể thao hàng đầu khu
                vực,
                không ngừng đổi mới, sáng tạo, để kiến tạo các sản phẩm đẳng cấp, góp phần nâng cao đời sống sức
                khỏe,
                tinh thần của người Việt và nâng tầm vị thế thương hiệu trên trường quốc tế
            </p>
            <p><span class="highlight">Sứ mệnh:</span> Trở thành đơn vị số một trong lĩnh vực sản xuất, kinh
                doanh, phân phối các sản phẩm thể thao đạt tiêu chuẩn Quốc tế với giá tốt nhất trên thị trường. Tăng
                tình gắn kết gia đình, góp phần kết nối và nâng cao sức khỏe cộng đồng, nâng tầm vóc Việt.
            </p>
            <p><span class="highlight">Giá trị cốt lõi: </span></p>
            <p>- Tín: Đặt chữ “tín”lên hàng đầu</p>
            <p>- Tâm: Lấy chữ “tâm” làm nền tảng kinh doanh</p>
            <p> - Trí: Đề cao doanh nghiệp học tập </p>
            <p>- Nhân: Coi người lao động là tài sản quý giá nhất của DN</p>
            <h4>4. SLOGAN</h4>
            <div class="sloganBig">
                <div class="slogan">
                    <h5>CAM KẾT</h5>
                    <p>Cam kết mang đến các sản phẩm và dịch vụ chất lượng, mang lại giá trị gia tăng và giải quyết
                        vấn
                        đề
                        của khách hàng. Chịu trách nhiệm với đối tác và Công ty.</p>
                </div>
                <div class="slogan">
                    <h5>CHÍNH TRỰC</h5>
                    <p>Luôn trung thành với chuẩn mực nghề nghiệp của mình trong việc ra quyết định, đàm phán và
                        truyền
                        thông.</p>
                </div>
                <div class="slogan">
                    <h5>HỌC TẬP</h5>
                    <p>Không ngừng học tập, đề cao sự tự học, đổi mới và sáng tạo của mỗi cá nhân.</p>
                    <p>NTN phát triển sự tò mò, đam mê học hỏi, sẵn sàng đón nhận kiến thức, kỹ năng mới để nâng cao
                        chất lượng học tập
                    </p>
                </div>
                <div class="slogan">
                    <h5>PHÁT TRIỂN</h5>
                    <p>Với mục tiêu “phát triển bền vững” NTN luôn giữ đúng cam kết với các khách hàng và đối tác
                        tạo ra
                        các giá trị bền vững.</p>
                    <p>Là nơi mà bất kỳ nhân sự nào cũng có những lộ trình nghề nghiệp phát triển song hành cùng sự
                        phát
                        triển của NTN.</p>
                </div>
            </div>

            <h4>5. MỤC TIÊU TƯƠNG LAI</h4>
            <p>- Phát triển đội ngũ nhân viên, bao gồm nhân viên tư vấn, nhân viên giao nhận để đảm bảo khách hàng
                được
                sử dụng sản phẩm tốt nhất, phù hợp với nhu cầu, đồng thời nhận được sự hỗ trợ kịp thời & chuyên
                nghiệp
                nhất.</p>
            <p>- Nâng cao dịch vụ khách hàng: Các sản phẩm đồ thể thao thông thường được sử dụng trong một khoảng
                thời
                gian dài. Trong quá trình sử dụng, khách hàng có thể gặp phải những vấn đề liên quan đến về kỹ
                thuật,
                hướng dẫn sử dụng,... đội ngũ chăm sóc khách hàng luôn sẵn sàng hỗ trợ khách hàng.</p>
            <p>- Mở rộng hệ thống, phủ sóng rộng khắp các tỉnh thành, mang tới những sân chơi thể thao lành mạnh,
                hữu
                hiệu.</p>

        </div>

    </main>

    <!-- Footer Section -->
    <jsp:include page="comon/footer.jsp" />

</div>
</body>

</html>