package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;

public class DistrictInfoResponse {
    private int code;
    private String message;
    private DistrictInfoData data;

    public DistrictInfoData getData() {
        return data;
    }

    public void setData(DistrictInfoData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DistrictInfoData {
        private int DistrictID;
        private int ProvinceID;
        private String DistrictName;
        // ... các trường khác nếu cần

        public String getDistrictName() {
            return DistrictName;
        }

        public void setDistrictName(String districtName) {
            DistrictName = districtName;
        }

        public int getDistrictID() {
            return DistrictID;
        }

        public void setDistrictID(int districtID) {
            DistrictID = districtID;
        }

        public int getProvinceID() {
            return ProvinceID;
        }

        public void setProvinceID(int provinceID) {
            ProvinceID = provinceID;
        }

        // Getters and Setters
    }
}
