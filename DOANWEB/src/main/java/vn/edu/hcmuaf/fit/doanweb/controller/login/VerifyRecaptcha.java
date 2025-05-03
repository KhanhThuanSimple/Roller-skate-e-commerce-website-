package vn.edu.hcmuaf.fit.doanweb.controller.login;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class VerifyRecaptcha {
    private static final String SECRET_KEY = "6LcukywrAAAAAAV_mMxURk0LraZYOi6JsR8gyDgJ";

    public static boolean verify(String gRecaptchaResponse, String remoteIp) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }

        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postData = "secret=" + SECRET_KEY
                    + "&response=" + gRecaptchaResponse
                    + "&remoteip=" + remoteIp;

            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            InputStream is = conn.getInputStream();
            Scanner sc = new Scanner(is);
            String json = sc.useDelimiter("\\A").next();
            sc.close();

            return json.contains("\"success\": true");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
