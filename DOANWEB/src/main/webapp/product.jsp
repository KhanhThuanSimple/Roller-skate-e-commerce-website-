<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                    <div class="product-price">${p.price}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>


            <div class="pagination">
                <c:if test="${endP > 0}">
                    <div class="pagination">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <c:url var="pageUrl" value="http://localhost:8080/DOANWEB/product">
                                <c:if test="${not empty cateID}">
                                    <c:param name="category" value="${cateID}"/>
                                </c:if>
                                <c:if test="${not empty param.sort}">
                                    <c:param name="sort" value="${param.sort}"/> <!-- Giữ lại sort nếu có -->
                                </c:if>
                                <c:param name="index" value="${i}"/>
                            </c:url>
                            <a href="${pageUrl}" class="page-item ${tag == i ? 'active' : ''}">${i}</a>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>


        <script src="js/script.js"></script>
    </div>
    <jsp:include page="comon/footer.jsp"/>

</div>
</body>

</html>