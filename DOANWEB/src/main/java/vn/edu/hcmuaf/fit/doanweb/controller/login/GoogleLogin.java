package vn.edu.hcmuaf.fit.doanweb.controller.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.hcmuaf.fit.doanweb.constant.Iconstant;

import java.io.IOException;

public class GoogleLogin {

    // Lấy access token từ mã code (authorization code)
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String jsonResponse = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        JsonObject json = new Gson().fromJson(jsonResponse, JsonObject.class);

        if (!json.has("access_token")) {
            // Log lỗi chi tiết trước khi throw
            System.err.println("Google OAuth Token Error response: " + jsonResponse);
            throw new RuntimeException("Không thể lấy access token từ Google: " + jsonResponse);
        }

        return json.get("access_token").getAsString();
    }

    // Lấy thông tin người dùng từ access token
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String jsonResponse = Request.Get(link).execute().returnContent().asString();
        return new Gson().fromJson(jsonResponse, GoogleAccount.class);
    }
}
