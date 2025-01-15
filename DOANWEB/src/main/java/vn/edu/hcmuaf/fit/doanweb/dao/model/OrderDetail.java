package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class OrderDetail {
    private Order order;
    private Product product;
    private OrderItems orderItem;

    // Constructor
    public OrderDetail(Order order, Product product, OrderItems orderItem) {
        this.order = order;
        this.product = product;
        this.orderItem = orderItem;
    }

    // Getters and Setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItems getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItems orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", product=" + product +
                ", orderItem=" + orderItem +
                '}';
    }
}
