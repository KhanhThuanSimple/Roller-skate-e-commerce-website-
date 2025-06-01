//package vn.edu.hcmuaf.fit.doanweb.controller.login;
//
//
//import org.json.JSONObject;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//
//public class VerifyRecaptcha {
//    private static final String SECRET_KEY = "6LeLFDIrAAAAANHOj4Sw-7YFAw6gP1dYd2qZBOOr";
//    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
//    private static final int TIMEOUT_MS = 5000; // 5 giây timeout
//
//    public static boolean verify(String gRecaptchaResponse, String remoteIp) {
//        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
//            return false;
//        }
//
//        HttpURLConnection conn = null;
//        try {
//            // Tạo connection
//            URL url = new URL(VERIFY_URL);
//            conn = (HttpURLConnection) url.openConnection();
//
//            // Cấu hình connection
//            conn.setRequestMethod("POST");
//            conn.setConnectTimeout(TIMEOUT_MS);
//            conn.setReadTimeout(TIMEOUT_MS);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestProperty("Accept", "application/json");
//
//            // Tạo dữ liệu POST
//            String postData = "secret=" + SECRET_KEY +
//                    "&response=" + gRecaptchaResponse +
//                    "&remoteip=" + remoteIp;
//
//            // Gửi request
//            try (OutputStream os = conn.getOutputStream()) {
//                byte[] input = postData.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            // Đọc response
//            int responseCode = conn.getResponseCode();
//            if (responseCode != HttpURLConnection.HTTP_OK) {
//                return false;
//            }
//
//            // Parse JSON response
//            StringBuilder response = new StringBuilder();
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//            }
//
//            JSONObject json = new JSONObject(response.toString());
//            return json.getBoolean("success");
//
//        } catch (Exception e) {
//            // Ghi log lỗi thay vì printStackTrace
//            System.err.println("Lỗi xác minh reCAPTCHA: " + e.getMessage());
//            return false;
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//    }
//}