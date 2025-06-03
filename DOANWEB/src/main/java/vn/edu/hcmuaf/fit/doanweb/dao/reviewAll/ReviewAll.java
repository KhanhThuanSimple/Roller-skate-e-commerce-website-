package vn.edu.hcmuaf.fit.doanweb.dao.reviewAll;

import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.Order;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderItems;
import vn.edu.hcmuaf.fit.doanweb.dao.reviews.Review;

public class ReviewAll {
    Review review;
    Product product;
    Order order;
    OrderItems orderItems;

    public ReviewAll(Review review, Product product) {
        this.review = review;
        this.product = product;
    }

    public ReviewAll(Review review, Product product, Order order, OrderItems orderItems) {
        this.review = review;
        this.product = product;
        this.order = order;
        this.orderItems = orderItems;
    }

    public ReviewAll() {
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "ReviewAll{" +
                "review=" + review +
                ", product=" + product +
                '}';
    }
}
