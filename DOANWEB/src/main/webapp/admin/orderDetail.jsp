<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Đơn Hàng</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="./css/canhan.css">
    <link rel="stylesheet" href="./css/style.css">

    <!-- Thư viện xuất file -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
    <!-- Thư viện FileSaver -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>
    <!-- Thư viện html-docx từ unpkg (phiên bản ổn định) -->
    <script src="https://unpkg.com/html-docx-js@0.3.1/dist/html-docx.js"></script>

    <style>
        .order-summary {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .total-amount {
            font-weight: bold;
            color: #28a745;
        }

        .export-buttons {
            margin-top: 20px;
            text-align: right;
        }

        .export-buttons button {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <%@ include file="sidebar.jsp" %>
    <main class="main-content">
        <div class="container mt-5" style="flex-direction: column !important;">
            <!-- Tiêu đề -->
            <div class="text-center mb-4">
                <h1 class="text-primary">Chi Tiết Đơn Hàng</h1>
                <p class="text-muted">Thông tin chi tiết về đơn hàng của bạn</p>
            </div>

            <!-- Phần nội dung chi tiết đơn hàng -->
            <div id="order-details">
                <div class="order-summary">
                    <h4>Mã Đơn Hàng: <span class="text-primary">${list[0].order.id}</span></h4>
                    <p>Tên Khách Hàng: <strong>${list[0].order.name}</strong></p>
                    <p>Trạng Thái: <span class="badge bg-success">Đã Giao Hàng</span></p>
                </div>

                <!-- Bảng sản phẩm -->
                <div class="mt-4">
                    <h4 class="mb-3">Danh Sách Sản Phẩm</h4>
                    <table class="table table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>Tên Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Đơn Giá (VNĐ)</th>
                            <th>Phương Thức Thanh Toán</th>
                            <th>Thành Tiền (VNĐ)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="totalAmount" value="0" />
                        <c:forEach var="p" items="${list}">

                            <tr>
                                <td>${p.product.title}</td>
                                <td>${p.orderItem.quantity}</td>
                                <td>${p.orderItem.price}</td>
                                <td>${p.order.paymentMethod}</td>
                                <td>${p.orderItem.price * p.orderItem.quantity}</td>
                                <c:set var="totalAmount" value="${totalAmount + p.orderItem.price * p.orderItem.quantity}" />
                            </tr>

                        </c:forEach>
                        </tbody>
                        <tfoot class="table-light">
                        <tr>
                            <td colspan="4" class="text-end">Tổng Cộng</td>
                            <td class="total-amount">${totalAmount}</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <!-- Nút xuất file -->
            <div class="export-buttons">
                <button class="btn btn-primary" onclick="exportToPDF()">Xuất PDF</button>
                <button class="btn btn-secondary" onclick="exportToWord()">Xuất Word</button>
            </div>
        </div>
    </main>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Xuất PDF
    function exportToPDF() {
        const element = document.getElementById('order-details');
        const opt = {
            margin: 1,
            filename: 'ChiTietDonHang_${list[0].order.id}.pdf',
            image: { type: 'jpeg', quality: 0.98 },
            html2canvas: { scale: 2 },
            jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' }
        };
        html2pdf().set(opt).from(element).save();
    }

    // Xuất Word
    function exportToWord() {
        try {
            // Kiểm tra và log trạng thái thư viện
            console.log('htmlDocx available:', typeof htmlDocx !== 'undefined');
            console.log('saveAs available:', typeof saveAs !== 'undefined');

            if (typeof htmlDocx === 'undefined') {
                // Nếu thư viện chưa sẵn sàng, thử tải lại
                alert('Đang tải thư viện... Vui lòng nhấn nút "Xuất Word" lần nữa sau 2 giây.');

                // Tải thư viện động
                const script = document.createElement('script');
                script.src = 'https://unpkg.com/html-docx-js@0.3.1/dist/html-docx.js';
                script.onload = function() {
                    console.log('Đã tải thư viện htmlDocx thành công!');
                };
                document.head.appendChild(script);

                return;
            }

            // Lấy phần tử chứa chi tiết đơn hàng
            const element = document.getElementById('order-details');
            if (!element) {
                console.error('Không tìm thấy phần tử có ID "order-details".');
                alert('Lỗi: Không tìm thấy nội dung đơn hàng.');
                return;
            }

            console.log(element.querySelector('table').outerHTML)

            // Lấy mã đơn hàng
            const orderId = '${list[0].order.id}';
            const   table_data=element.querySelector('table').outerHTML
            // Chuẩn bị nội dung HTML đơn giản hơn

            let htmlContent = `
                <html>
                    <head>
                        <meta charset="UTF-8">
                        <style>
                            body { font-family: Arial, sans-serif; margin: 20px; }
                            table { width: 100%; border-collapse: collapse; }
                            th, td { border: 1px solid #000; padding: 8px; }
                            th { background-color: #f2f2f2; }
                        </style>
                    </head>
                    <body>
                        <h2>Chi Tiết Đơn Hàng #${list[0].order.id}</h2>
                        <div>
                            <p><strong>Tên Khách Hàng:</strong> ${list[0].order.name}</p>
                            <p><strong>Trạng Thái:</strong> Đã Giao Hàng</p>
                        </div>
                         <div class="mt-4">
                    <h4 class="mb-3">Danh Sách Sản Phẩm</h4>
                    <table class="table table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>Tên Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Đơn Giá (VNĐ)</th>
                            <th>Phương Thức Thanh Toán</th>
                            <th>Thành Tiền (VNĐ)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="totalAmount" value="0" />
                        <c:forEach var="p" items="${list}">

                            <tr>
                                <td>${p.product.title}</td>
                                <td>${p.orderItem.quantity}</td>
                                <td>${p.orderItem.price}</td>
                                <td>${p.order.paymentMethod}</td>
                                <td>${p.orderItem.price * p.orderItem.quantity}</td>
                                <c:set var="totalAmount" value="${totalAmount + p.orderItem.price * p.orderItem.quantity}" />
                            </tr>

                        </c:forEach>
                        </tbody>
                        <tfoot class="table-light">
                        <tr>
                            <td colspan="4" class="text-end">Tổng Cộng</td>
                            <td class="total-amount">${totalAmount}</td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                    </body>
                </html>
            `;

            // Chuyển đổi HTML sang Word document
            console.log('Bắt đầu chuyển đổi HTML sang Word...', htmlContent);
            const converted = htmlDocx.asBlob(htmlContent);
            console.log('Chuyển đổi thành công!');

            // Tải xuống file
            console.log('Đang tải xuống file...');
            saveAs(converted, `ChiTietDonHang_${orderId}.docx`);

        } catch (error) {
            console.error('Lỗi chi tiết khi xuất file Word:', error);
            alert('Có lỗi xảy ra khi xuất file Word. Vui lòng xem Console (F12) để biết chi tiết.');
        }
    }
</script>
</body>
</html>