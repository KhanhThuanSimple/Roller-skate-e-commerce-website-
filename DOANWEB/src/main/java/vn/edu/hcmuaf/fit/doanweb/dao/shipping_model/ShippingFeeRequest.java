package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;

public class ShippingFeeRequest {
    private int from_district_id;
    private int to_district_id;
    private String to_ward_code;
    private String From_ward_code;

    private int weight;
    private int length;
    private int width;
    private int height;
    private int insurance_value;
    private int service_id;
    private int service_type_id;

    public ShippingFeeRequest() {
    }

    public ShippingFeeRequest(int from_district_id, int to_district_id, String to_ward_code, String from_ward_code, int weight, int length, int width, int height, int insurance_value, int service_id, int service_type_id) {
        this.from_district_id = from_district_id;
        this.to_district_id = to_district_id;
        this.to_ward_code = to_ward_code;
        From_ward_code = from_ward_code;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.insurance_value = insurance_value;
        this.service_id = service_id;
        this.service_type_id = service_type_id;
    }

    public int getFrom_district_id() {
        return from_district_id;
    }

    public void setFrom_district_id(int from_district_id) {
        this.from_district_id = from_district_id;
    }

    public int getTo_district_id() {
        return to_district_id;
    }

    public void setTo_district_id(int to_district_id) {
        this.to_district_id = to_district_id;
    }

    public String getTo_ward_code() {
        return to_ward_code;
    }

    public void setTo_ward_code(String to_ward_code) {
        this.to_ward_code = to_ward_code;
    }

    public String getFrom_ward_code() {
        return From_ward_code;
    }

    public void setFrom_ward_code(String from_ward_code) {
        From_ward_code = from_ward_code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getInsurance_value() {
        return insurance_value;
    }

    public void setInsurance_value(int insurance_value) {
        this.insurance_value = insurance_value;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }
}
//package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;
//
//
//public class ShippingFeeRequest {
//    private int fromDistrictId;
//    private String fromWardCode;
//    private int toDistrictId;
//    private String toWardCode;
//    private int weight;
//    private int serviceTypeId;
//    private int serviceId;
//    private int insuranceValue;
//    private String coupon;
//
//    public int getFromDistrictId() {
//        return fromDistrictId;
//    }
//
//    public void setFromDistrictId(int fromDistrictId) {
//        this.fromDistrictId = fromDistrictId;
//    }
//
//    public String getFromWardCode() {
//        return fromWardCode;
//    }
//
//    public void setFromWardCode(String fromWardCode) {
//        this.fromWardCode = fromWardCode;
//    }
//
//    public int getToDistrictId() {
//        return toDistrictId;
//    }
//
//    public void setToDistrictId(int toDistrictId) {
//        this.toDistrictId = toDistrictId;
//    }
//
//    public String getToWardCode() {
//        return toWardCode;
//    }
//
//    public void setToWardCode(String toWardCode) {
//        this.toWardCode = toWardCode;
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//
//    public int getServiceTypeId() {
//        return serviceTypeId;
//    }
//
//    public void setServiceTypeId(int serviceTypeId) {
//        this.serviceTypeId = serviceTypeId;
//    }
//
//    public int getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(int serviceId) {
//        this.serviceId = serviceId;
//    }
//
//    public int getInsuranceValue() {
//        return insuranceValue;
//    }
//
//    public void setInsuranceValue(int insuranceValue) {
//        this.insuranceValue = insuranceValue;
//    }
//
//    public String getCoupon() {
//        return coupon;
//    }
//
//    public void setCoupon(String coupon) {
//        this.coupon = coupon;
//    }
//}
