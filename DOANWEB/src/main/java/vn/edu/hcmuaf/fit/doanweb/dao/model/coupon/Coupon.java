package vn.edu.hcmuaf.fit.doanweb.dao.model.coupon;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    private int id;
    private String couponCode;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minOrderAmount;
    private Date expireDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(BigDecimal minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Coupon(int id, String couponCode, String discountType, BigDecimal discountValue, BigDecimal minOrderAmount, Date expireDate, String status) {
        this.id = id;
        this.couponCode = couponCode;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minOrderAmount = minOrderAmount;
        this.expireDate = expireDate;
        this.status = status;
    }

    public Coupon() {
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", couponCode='" + couponCode + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", minOrderAmount=" + minOrderAmount +
                ", expireDate=" + expireDate +
                ", status='" + status + '\'' +
                '}';
    }
}
