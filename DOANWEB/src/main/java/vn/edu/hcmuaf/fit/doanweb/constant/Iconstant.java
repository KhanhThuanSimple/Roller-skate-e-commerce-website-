package vn.edu.hcmuaf.fit.doanweb.constant;

public class Iconstant {
    public static final String GOOGLE_CLIENT_ID = "862213844351-dv5kqbmf8vik0mvbkl27lrqgntgevp91.apps.googleusercontent.com";

    public static final String GOOGLE_CLIENT_SECRET = "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"; // nên giữ riêng tư nếu sản phẩm thật

    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/DOANWEB/loginServlet";

    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
}
