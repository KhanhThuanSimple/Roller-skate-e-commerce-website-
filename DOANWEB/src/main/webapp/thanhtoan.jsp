<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/12/2024
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ThanhToan</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/lienhe.css">
    <link rel="stylesheet" href="./css/thanhtoan.css" />
    <link rel="stylesheet" href="./css/style.css">

</head>

<body>
<div class="container flex-colunm">
    <jsp:include page="comon/header.jsp"/>

    <div class="main">
        <div class="flex-row thanhtoan">

            <div class=" thanhtoan-left flex-colunm flex-1">
                <div class=" flex-colunm">
                    <div>
                        <h2 class="thanhtoan-title"> THÔNG TIN THANH TOÁN</h2>

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

                                <textarea rows="4" class="input-common" placeholder="Địa chỉ"></textarea>

                            </div>


                        </form>
                    </div>


                </div>
                <div class="flex-colunm">
                    <div>
                        <h2 class="thanhtoan-title">THÔNG TIN BỔ SUNG</h2>
                    </div>
                    <div>
                        <label for="element_id">Ghi chú đơn hàng(tùy chọn)</label>
                        <!-- <input type="text" id="element_id" name="element_name"> -->
                        <textarea rows="4" class="input-common"
                                  placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay chỉ dẫn địa điểm giao hàng cụ thể hơn"></textarea>

                    </div>
                </div>
            </div>
            <div class="thanhtoan-right flex-1 flex-colunm ">
                <div class="thanhtoan-title">
                    <h2 class="thanhtoan-title ">ĐƠN HÀNG CỦA BẠN</h2>
                </div>
                <div class="info-product flex-colunm ">
                    <div class="flex-row">
                        <div class="flex-1">
                            <h3>SẢN PHẨM</h3>
                        </div>
                        <div class="flex-1">
                            <h3>TẠM TÍNH</h3>
                        </div>
                    </div>
                    <div class="item-content flex-row">
                        <div class="flex-1 ">
                            <h4>Giày Patin Trẻ em Centosy <br> Lion 3 Màu Đen/Hồng <br> Xanh X1 <br> Màu sắc: Đỏ
                                <br> Kích cở: L
                            </h4>

                        </div>
                        <div class="flex-1">
                            <h4> 1.760.000</h4>
                        </div>
                    </div>
                    <div class="flex-row">
                        <div class="flex-1">
                            <h4>Tạm tính</h4>
                        </div>
                        <div class="flex-1">
                            <h5 style="color: red;">1.760.000</h5>
                        </div>
                    </div>
                    <div class="flex-row">
                        <div class="flex-1">
                            <h4>Tổng</h4>
                        </div>
                        <div class="flex-1">
                            <h4 style="color: red;">1.760.000</h4>
                        </div>

                    </div>
                </div>
                <div>
                    <div>
                        <div class="form-group-thanhtoan flex-row">
                            <!-- <h4>Phương thức thanh toán</h4> -->
                            <label class="input-common" for="payment-method">Phương thức thanh toán</label>
                            <select class="input-common" id="payment-method" name="payment-method" required>
                                <option class="input-common" value="">Chọn phương thức thanh toán</option>
                                <option class="input-common" value="credit-card">Thẻ tín dụng</option>
                                <option class="input-common" value="paypal">Thanh toán khi nhận hàng</option>
                                <option class="input-common" value="bank-transfer">Chuyển khoản ngân hàng</option>
                            </select>
                        </div>
                    </div>
                    <div id="overlay" class="overlay" style="display: none;"></div>

                    <div class="thanhtoan-bt">
                        <button class="button-orange" onclick="openPayForm()">Đặt hàng</button>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div id="payModal" class="success-message modal-content" style="display: none;">

        <h2>Mua hàng thành công!</h2>
        <p>Đơn hàng của bạn đã được đặt thành công. Cảm ơn bạn!</p>>
        <button  type="button" onclick="window.location.href='index.html'" class="close-button">Đóng</button>

    </div>





    <jsp:include page="comon/footer.jsp"/>
</div>
<script src="js/thanhtoan.js"> </script>






</body>

</html>