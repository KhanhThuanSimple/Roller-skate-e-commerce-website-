package vn.edu.hcmuaf.fit.doanweb.controller.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import vn.edu.hcmuaf.fit.doanweb.constant.Iconstant;
import vn.edu.hcmuaf.fit.doanweb.dao.login.GoogleAccount;  // Đảm bảo import đúng GoogleAccount từ dao.login

import java.io.IOException;

public class GoogleLogin {

    // Lấy token từ mã code (authorization code)
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // Tạo yêu cầu POST gửi mã code tới Google để lấy access_token
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
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

        // Phân tích JSON phản hồi từ Google để lấy access_token
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        // Kiểm tra nếu access_token tồn tại trong phản hồi, nếu không thì trả về lỗi
        if (jobj.has("access_token")) {
            return jobj.get("access_token").getAsString();
        } else {
            // Nếu không có access_token trong phản hồi, trả về lỗi
            throw new IOException("Không thể lấy access token từ Google: " + jobj.toString());
        }
    }

    // Lấy thông tin người dùng từ Google bằng access_token
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        // Gửi yêu cầu GET đến Google API để lấy thông tin người dùng
        String response = Request.Get(link).execute().returnContent().asString();

        // Kiểm tra xem API trả về thông tin hợp lệ không
        if (response == null || response.isEmpty()) {
            throw new IOException("Không có phản hồi hợp lệ từ Google API.");
        }

        // Phân tích JSON phản hồi và chuyển nó thành đối tượng GoogleAccount
        GoogleAccount googleAccount = new Gson().fromJson(response, GoogleAccount.class);

        // Kiểm tra thông tin người dùng có đầy đủ không, nếu không có thể ném lỗi
        if (googleAccount != null && googleAccount.getId() != null && !googleAccount.getId().isEmpty()) {
            return googleAccount;
        } else {
            // Nếu không có thông tin người dùng hợp lệ, ném ngoại lệ
            throw new IOException("Không thể lấy thông tin người dùng từ Google: " + response);
        }
    }
}
