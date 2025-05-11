package vn.edu.hcmuaf.fit.doanweb.service.shipping;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import vn.edu.hcmuaf.fit.doanweb.dao.shipping_model.*;
import vn.edu.hcmuaf.fit.doanweb.log.Log;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GHNApiClient {
    private static final String GHN_API_URL = "https://online-gateway.ghn.vn/shiip/public-api";
    private static final String SHOP_ID = "5720510";
    private static final String API_KEY = "738f0c0a-1481-11f0-833d-ba06b9a114b5";
    private static final Gson gson = new Gson();

    private static <T> T callGHNApi(String endpoint, Object requestData, Class<T> responseType) throws Exception {
        String url = GHN_API_URL + endpoint;
        long startTime = System.currentTimeMillis();

        Log.info("Calling GHN API: " + url);

        // Sử dụng HttpGet nếu không có requestData, ngược lại dùng HttpPost
        HttpRequestBase httpRequest;
        if (requestData == null) {
            httpRequest = new HttpGet(url);
        } else {
            HttpPost httpPost = new HttpPost(url);
            String jsonRequest = gson.toJson(requestData);
            httpPost.setEntity(new StringEntity(jsonRequest, StandardCharsets.UTF_8));
            Log.debug("Request payload: " + jsonRequest);
            httpRequest = httpPost;
        }

        // Thiết lập headers
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setHeader("Token", API_KEY);
        httpRequest.setHeader("ShopId", SHOP_ID);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();

            if (entity == null) {
                throw new Exception("Empty response from GHN API");
            }

            String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            Log.debug("API Response: " + result);

            T apiResponse = gson.fromJson(result, responseType);
            long duration = System.currentTimeMillis() - startTime;

            Log.info("GHN API call completed in " + duration + "ms");
            return apiResponse;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            Log.error("GHN API call failed after " + duration + "ms. Error: " + e.getMessage());
            throw new Exception("Failed to call GHN API endpoint " + endpoint + ": " + e.getMessage(), e);
        }
    }

    public static ShippingFeeResponse calculateShippingFee(ShippingFeeRequest request) throws Exception {
        String url = GHN_API_URL + "/v2/shipping-order/fee";
        Log.info("Calculating shipping fee for request: " + request.toString());

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Token", API_KEY);
        httpPost.setHeader("ShopId", SHOP_ID);

        String jsonRequest = gson.toJson(request);
        httpPost.setEntity(new StringEntity(jsonRequest, StandardCharsets.UTF_8));

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                ShippingFeeResponse feeResponse = gson.fromJson(result, ShippingFeeResponse.class);

                if (feeResponse.getCode() == 200) {
                    Log.info("Successfully calculated shipping fee: " + feeResponse.getData().getTotal() + " VND");
                } else {
                    Log.warn("GHN API returned error: " + feeResponse.getMessage());
                }

                return feeResponse;
            }
        } catch (Exception e) {
            Log.error("Failed to call GHN API: " + e.getMessage());
            throw e;
        }

        throw new Exception("Failed to call GHN API");
    }

    public static ProvinceResponse getProvinces() throws Exception {
        final String endpoint = "/master-data/province";
        Log.debug("Fetching all provinces");
        return callGHNApi(endpoint, null, ProvinceResponse.class);
    }

    public static DistrictResponse getDistricts(int provinceId) throws Exception {
        final String endpoint = "/master-data/district";
        Log.debug(String.format("Fetching districts for provinceId: %d", provinceId));

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("province_id", provinceId);

        return callGHNApi(endpoint, requestData, DistrictResponse.class);
    }

    public static WardResponse getWards(int districtId) throws Exception {
        final String endpoint = "/master-data/ward";
        Log.debug(String.format("Fetching wards for districtId: %d", districtId));

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("district_id", districtId);

        return callGHNApi(endpoint, requestData, WardResponse.class);
    }
    public static DistrictInfoResponse getDistrictInfo(int districtId) throws Exception {
        final String endpoint = "/master-data/district";
        Log.debug(String.format("Fetching district info for districtId: %d", districtId));

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("district_id", districtId);

        return callGHNApi(endpoint, requestData, DistrictInfoResponse.class);
    }
}