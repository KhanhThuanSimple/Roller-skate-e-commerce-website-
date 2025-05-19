package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;

public class DistrictResponse {
    private int code;
    private String message;
    private District[] data;

    // Getters and setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public District[] getData() {
        return data;
    }

    public void setData(District[] data) {
        this.data = data;
    }

    public static class District {
        private int DistrictID;
        private String DistrictName;
        private int ProvinceID;

        // Getters and setters

        public int getDistrictID() {
            return DistrictID;
        }

        public void setDistrictID(int districtID) {
            DistrictID = districtID;
        }

        public String getDistrictName() {
            return DistrictName;
        }

        public void setDistrictName(String districtName) {
            DistrictName = districtName;
        }

        public int getProvinceID() {
            return ProvinceID;
        }

        public void setProvinceID(int provinceID) {
            ProvinceID = provinceID;
        }
    }
}