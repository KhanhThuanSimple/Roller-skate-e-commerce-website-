<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đánh giá sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="./css/style.css">
    <style>
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .product-img {
            width: 100%;
            height: 200px;
            object-fit: contain;
            border-radius: 5px;
        }
        .rating-stars {
            color: #ffc107;
            font-size: 1.5rem;
        }
        .review-form {
            margin-top: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 5px;
        }
        .empty-message {
            text-align: center;
            padding: 50px;
            color: #6c757d;
        }
        .nav-tabs .nav-link {
            color: #495057;
            font-weight: 500;
        }
        .nav-tabs .nav-link.active {
            color: #0d6efd;
            font-weight: 600;
        }
        .title-center {
            text-align: center;
            margin-bottom: 30px;
        }
        .tab-content {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="comon/header.jsp" />
<div class="container my-5">
    <h2 class="title-center mb-4">Đánh giá sản phẩm đã mua</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <c:if test="${not empty reviewSuccess}">
        <div class="alert alert-success">${reviewSuccess}</div>
        <c:remove var="reviewSuccess" scope="session"/>
    </c:if>

    <c:if test="${not empty reviewError}">
        <div class="alert alert-danger">${reviewError}</div>
        <c:remove var="reviewError" scope="session"/>
    </c:if>

    <ul class="nav nav-tabs justify-content-center" id="reviewTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="all-tab" data-bs-toggle="tab" data-bs-target="#all" type="button" role="tab">Tất cả sản phẩm</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="reviewed-tab" data-bs-toggle="tab" data-bs-target="#reviewed" type="button" role="tab">Đã đánh giá</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="not-reviewed-tab" data-bs-toggle="tab" data-bs-target="#not-reviewed" type="button" role="tab">Chưa đánh giá</button>
        </li>
    </ul>

    <div class="tab-content" id="reviewTabsContent">
        <div class="tab-pane fade show active" id="all" role="tabpanel" aria-labelledby="all-tab">
            <c:choose>
                <c:when test="${not empty productsToReview}">
                    <div class="row">
                        <c:forEach items="${productsToReview}" var="product">
                            <div class="col-md-6">
                                <div class="product-card">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <img src="${product.img}" alt="${product.name}" class="product-img">
                                        </div>
                                        <div class="col-md-8">
                                            <h4>${product.title}</h4>
                                            <p class="text-danger fw-bold">
                                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫"/>
                                            </p>

                                            <form action="danhgia" method="post" class="review-form">
                                                <input type="hidden" name="productId" value="${product.id}">

                                                <div class="mb-3">
                                                    <label class="form-label">Đánh giá của bạn:</label>
                                                    <div class="rating-stars">
                                                        <input type="radio" id="star5-${product.id}" name="rating" value="1" required>
                                                        <label for="star5-${product.id}"><i class="fas fa-star"></i></label>
                                                        <input type="radio" id="star4-${product.id}" name="rating" value="2">
                                                        <label for="star4-${product.id}"><i class="fas fa-star"></i></label>
                                                        <input type="radio" id="star3-${product.id}" name="rating" value="3">
                                                        <label for="star3-${product.id}"><i class="fas fa-star"></i></label>
                                                        <input type="radio" id="star2-${product.id}" name="rating" value="4">
                                                        <label for="star2-${product.id}"><i class="fas fa-star"></i></label>
                                                        <input type="radio" id="star1-${product.id}" name="rating" value="5">
                                                        <label for="star1-${product.id}"><i class="fas fa-star"></i></label>
                                                    </div>
                                                </div>

                                                <div class="mb-3">
                                                    <label for="comment-${product.id}" class="form-label">Nhận xét:</label>
                                                    <textarea class="form-control" id="comment-${product.id}"
                                                              name="comment" rows="3" required></textarea>
                                                </div>

                                                <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-message">
                        <i class="fas fa-box-open fa-3x mb-3"></i>
                        <h4>Không có sản phẩm nào cần đánh giá</h4>
                        <p>Bạn chưa mua sản phẩm nào hoặc đã đánh giá tất cả sản phẩm đã mua.</p>
                        <a href="home" class="btn btn-primary">Tiếp tục mua sắm</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="tab-pane fade" id="reviewed" role="tabpanel" aria-labelledby="reviewed-tab">
            <!-- Content for reviewed products -->
            <c:choose>
                <c:when test="${not empty reviewedProducts}">
                    <div class="row">
                        <!-- Display reviewed products here -->
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-message">
                        <i class="fas fa-check-circle fa-3x mb-3"></i>
                        <h4>Chưa có sản phẩm nào đã đánh giá</h4>
                        <p>Bạn chưa đánh giá sản phẩm nào.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="tab-pane fade" id="not-reviewed" role="tabpanel" aria-labelledby="not-reviewed-tab">
            <!-- Content for not reviewed products -->
            <c:choose>
                <c:when test="${not empty notReviewedProducts}">
                    <div class="row">
                        <!-- Display not reviewed products here -->
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-message">
                        <i class="fas fa-check fa-3x mb-3"></i>
                        <h4>Tất cả sản phẩm đã được đánh giá</h4>
                        <p>Bạn đã đánh giá tất cả sản phẩm đã mua.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="comon/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Highlight star when hover
    document.querySelectorAll('.rating-stars label').forEach(label => {
        label.addEventListener('mouseover', function() {
            const stars = this.parentElement.querySelectorAll('label');
            const index = Array.from(stars).indexOf(this);

            stars.forEach((star, i) => {
                if (i <= index) {
                    star.querySelector('i').classList.add('fas');
                    star.querySelector('i').classList.remove('far');
                } else {
                    star.querySelector('i').classList.add('far');
                    star.querySelector('i').classList.remove('fas');
                }
            });
        });
    });

    // Reset stars when mouse leaves
    document.querySelectorAll('.rating-stars').forEach(container => {
        container.addEventListener('mouseleave', function() {
            const checked = this.querySelector('input:checked');
            const stars = this.querySelectorAll('label');

            if (!checked) {
                stars.forEach(star => {
                    star.querySelector('i').classList.add('far');
                    star.querySelector('i').classList.remove('fas');
                });
            } else {
                const index = Array.from(this.querySelectorAll('input')).indexOf(checked);
                stars.forEach((star, i) => {
                    if (i <= index) {
                        star.querySelector('i').classList.add('fas');
                        star.querySelector('i').classList.remove('far');
                    } else {
                        star.querySelector('i').classList.add('far');
                        star.querySelector('i').classList.remove('fas');
                    }
                });
            }
        });
    });

    // Click star to select rating
    document.querySelectorAll('.rating-stars input').forEach(input => {
        input.addEventListener('change', function() {
            const stars = this.parentElement.querySelectorAll('label');
            const index = Array.from(this.parentElement.querySelectorAll('input')).indexOf(this);

            stars.forEach((star, i) => {
                if (i <= index) {
                    star.querySelector('i').classList.add('fas');
                    star.querySelector('i').classList.remove('far');
                } else {
                    star.querySelector('i').classList.add('far');
                    star.querySelector('i').classList.remove('fas');
                }
            });
        });
    });
</script>
</body>
</html>