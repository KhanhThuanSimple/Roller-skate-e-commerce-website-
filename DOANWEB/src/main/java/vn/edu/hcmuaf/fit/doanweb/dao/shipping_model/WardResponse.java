package vn.edu.hcmuaf.fit.doanweb.dao.shipping_model;

public class WardResponse {
    private int code;
    private String message;
    private Ward[] data;

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

    public Ward[] getData() {
        return data;
    }

    public void setData(Ward[] data) {
        this.data = data;
    }
// Getters and setters

    public static class Ward {
        private String WardCode;
        private String WardName;
        private int DistrictID;

        public String getWardCode() {
            return WardCode;
        }

        public void setWardCode(String wardCode) {
            WardCode = wardCode;
        }

        public String getWardName() {
            return WardName;
        }

        public void setWardName(String wardName) {
            WardName = wardName;
        }

        public int getDistrictID() {
            return DistrictID;
        }

        public void setDistrictID(int districtID) {
            DistrictID = districtID;
        }
// Getters and setters
    }
}