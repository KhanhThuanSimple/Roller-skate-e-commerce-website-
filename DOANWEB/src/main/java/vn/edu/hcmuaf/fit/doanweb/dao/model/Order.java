package vn.edu.hcmuaf.fit.doanweb.dao.model;

public class Order {
    private String name;
    private String email;
    private String address;
    private String phone;
    private double totalAmount;
    private double shippingFee;
    private double discountAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Order(String name, double discountAmount, double shippingFee, double totalAmount, String phone, String address, String email) {
        this.name = name;
        this.discountAmount = discountAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", totalAmount=" + totalAmount +
                ", shippingFee=" + shippingFee +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
