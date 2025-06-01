package vn.edu.hcmuaf.fit.doanweb.dao.reviews;

import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

public class Review {

        private int id;
        private int userId;
        private int productId;
        private int rating;
        private String comment;
        private String createdAt;
    private String userName;

//        private Product product; // liên kết nếu muốn truy xuất thêm thông tin sản phẩm?

        public Review() {}

        public Review(int id, int userId, int productId, int rating, String comment, String createdAt) {
            this.id = id;
            this.userId = userId;
            this.productId = productId;
            this.rating = rating;
            this.comment = comment;
            this.createdAt = createdAt;
        }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
