<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<%
    // Lấy cateID từ URL
    String cateID = request.getParameter("category");
    request.setAttribute("cateID", cateID); // Gán cateID vào request attribute
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/sanpham.css">
</head>

<body>
<div id="wrapper">

    <jsp:include page="comon/header.jsp"/>

    <div id="main-container">

        <div id="main-contain-product">

            <div class="healine">
                <h3>SẢN PHẨM </h3>
            </div>
            <div class="sort-buttons">
                <button>Sắp xếp</button>
                <div class="dropdown-content">
                    <a href="/DOANWEB/product?${not empty cateID ? 'category=' += cateID += '&' : ''}sort=asc">Giá: Thấp-Cao</a>
                    <a href="/DOANWEB/product?${not empty cateID ? 'category=' += cateID += '&' : ''}sort=desc">Giá: Cao-Thấp</a>
                </div>
            </div>
            <ul class="product">

                <c:if test="${not empty products}">
                    <c:forEach var="p" items="${products}">
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <c:choose>
                                        <c:when test="${p.favorited}">
                                            <!-- Sản phẩm đã có trong danh sách yêu thích, hiển thị nút xóa yêu thích -->
                                            <form action="${pageContext.request.contextPath}/favorite" method="post" style="display: inline;">
                                                <input type="hidden" name="productId" value="${p.id}">
                                                <input type="hidden" name="action" value="remove">
                                                <button type="submit" style="border: none; background: none; cursor: pointer;">
                                                    <!-- Trái tim đầy (đã yêu thích) -->
                                                    <i class="fa-solid fa-heart" style="color: #e74c3c; font-size: 20px;padding: 20px;margin-left: 145px"></i>
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Sản phẩm chưa có trong danh sách yêu thích, hiển thị nút thêm yêu thích -->
                                            <form action="${pageContext.request.contextPath}/favorite" method="post" style="display: inline;">
                                                <input type="hidden" name="productId" value="${p.id}">
                                                <input type="hidden" name="action" value="add">
                                                <button type="submit" style="border: none; background: none; cursor: pointer;">
                                                    <!-- Trái tim viền (chưa yêu thích) -->
                                                    <i class="fa-regular fa-heart" style="color: #e74c3c; font-size: 20px;padding: 20px;margin-left: 145px"></i>
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>






                                    <a href="" class="product-thumb">
                                        <img src="${p.img}" alt=""/>
                                    </a>
                                    <div class="button-container">
                                        <a href="add-cart?pid=${p.id}" class="buy-now">Thêm Vào Giỏ Hàng</a>
                                        <a href="detail?pid=${p.id}" class="view-details">Xem Chi Tiết Sản Phẩm</a>
                                    </div>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-cat">${p.name}</a>
                                    <a href="" class="product-name">${p.title}</a>
                                    <div class="product-price">
                                        <fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0" />đ
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>

            <div class="pagination">
                <c:if test="${endP > 0}">
                    <c:forEach begin="1" end="${endP}" var="i">
                        <c:url var="pageUrl" value="http://localhost:8080/DOANWEB/product">
                            <c:if test="${not empty cateID}">
                                <c:param name="category" value="${cateID}"/>
                            </c:if>
                            <c:if test="${not empty param.sort}">
                                <c:param name="sort" value="${param.sort}"/> <!-- Giữ lại sort nếu có -->
                            </c:if>
                            <c:if test="${not empty param.addCart}">
                                <c:param name="addCart" value="${param.addCart}"/> <!-- Giữ lại addCart nếu có -->
                            </c:if>
                            <c:param name="index" value="${i}"/>
                        </c:url>
                        <a href="${pageUrl}" class="page-item ${tag == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>


        <script src="js/script.js"></script>
    </div>
    <jsp:include page="comon/footer.jsp"/>

</div>
</body>

</html>