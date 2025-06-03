package vn.edu.hcmuaf.fit.doanweb.dao.model.order;

public class FailedOrder {
    private int userId;
    private String name, phone, province, district, ward, addressDetail, note, discountCode, paymentMethod, failReason;
    private double shippingFee, totalAmount;

    public FailedOrder(int userId, String name, String phone, String province, String district, String ward, String addressDetail, String note, String discountCode, String paymentMethod, String failReason, double shippingFee, double totalAmount) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.addressDetail = addressDetail;
        this.note = note;
        this.discountCode = discountCode;
        this.paymentMethod = paymentMethod;
        this.failReason = failReason;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
    }

    public FailedOrder() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
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

    @Override
    public String toString() {
        return "FailedOrder{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", note='" + note + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", failReason='" + failReason + '\'' +
                ", shippingFee=" + shippingFee +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
