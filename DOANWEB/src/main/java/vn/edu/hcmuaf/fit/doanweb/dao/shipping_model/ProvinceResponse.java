package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;

public class ProvinceResponse {
    private int code;
    private String message;
    private Province[] data;

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

    public Province[] getData() {
        return data;
    }

    public void setData(Province[] data) {
        this.data = data;
    }

    public static class Province {
        private int ProvinceID;
        private String ProvinceName;

        // Getters and setters

        public int getProvinceID() {
            return ProvinceID;
        }

        public void setProvinceID(int provinceID) {
            ProvinceID = provinceID;
        }

        public String getProvinceName() {
            return ProvinceName;
        }

        public void setProvinceName(String provinceName) {
            ProvinceName = provinceName;
        }
    }
}
