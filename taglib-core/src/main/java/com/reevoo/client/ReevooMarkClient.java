package com.reevoo.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Hook between reevoo REST endpoint, and the rest of the general taglib code
 */
public class ReevooMarkClient {
    protected HttpClient client;

    public ReevooMarkClient(int connectTimeout, String proxyHost, String proxyPort) {
        this.client = HttpClientFactory.build(connectTimeout, proxyHost, proxyPort);
    }

    public String obtainReevooMarkData(String baseURI, Map<String,String> queryStringParams) {
      return obtainReevooMarkData(baseURI, queryStringParams, null);
    }

    public String obtainReevooMarkData(String baseURI, Map<String,String> queryStringParams, String customParams) {
        GetMethod method = null;
        try {
            method = new GetMethod(baseURI);
            method.setFollowRedirects(true);
            method.setQueryString(generateReevooMarkQueryParams(queryStringParams, customParams));
            return obtainReevooMarkData(method);
        } catch (Exception e) {
            return null;
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
    }

    String obtainReevooMarkData(GetMethod request) throws IOException {
        String cacheKey = request.getURI().getURI();
        ReevooMarkRecord cachedResponse = Cache.get(cacheKey);

        if (cachedResponse != null && cachedResponse.fresh()) {
            return cachedResponse.value;
        }

        try {
            client.executeMethod(request);
        } catch (Exception e) {
            return cachedResponse != null ? cachedResponse.value : null;
        }

        int status = request.getStatusCode();
        int time_to_live = secondsToLive(request);
        int review_count = extractReviewCountHeader(request);
        String content;
        if (status == 200) {
            content = request.getResponseBodyAsString();
        } else {
            content = null;
        }

        if (status >= 500 && cachedResponse != null) {
            Cache.put(cacheKey, new ReevooMarkRecord(cachedResponse, time_to_live));
            return cachedResponse.value;
        }

        Cache.put(cacheKey, new ReevooMarkRecord(content, time_to_live, status, review_count));
        return content;
    }

    private int secondsToLive(HttpMethod request) {
        int max_age = extractMaxAgeHeaderValue(request);
        int age = extractAgeHeaderValue(request);
        return max_age - age;
    }

    private int extractMaxAgeHeaderValue(HttpMethod request) {
        Header header = request.getResponseHeader("Cache-Control");
        if (header != null) {
            String value = header.getValue();
            Matcher m = Pattern.compile(".*max\\-age\\=([0-9]+).*", Pattern.CASE_INSENSITIVE).matcher(value);
            if (m.matches()) {
                return safeStringToInt(m.group(1));
            }
        }
        return 0;
    }

    private int extractAgeHeaderValue(HttpMethod request) {
        Header header = request.getResponseHeader("Age");
        if (header != null) {
            return safeStringToInt(header.getValue());
        }
        return 0;
    }

    private int extractReviewCountHeader(HttpMethod request) {
        Header header = request.getResponseHeader("X-Reevoo-ReviewCount");
        if (header != null) {
            return safeStringToInt(header.getValue());
        }
        return 0;
    }

    private int safeStringToInt(String s) {
        try {
            return s == null ? null : Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String generateReevooMarkQueryParams(Map<String,String> queryStringParams, String customParams) throws java.io.UnsupportedEncodingException {
        String queryString = "";
        if (queryStringParams != null && !queryStringParams.isEmpty()) {
            for (Map.Entry<String, String> entry : queryStringParams.entrySet()) {
                if (entry.getKey() != null && !entry.getKey().isEmpty() && entry.getValue() != null && !entry.getValue().isEmpty()) {
                    queryString = String.format("%s%s%s=%s", queryString, getQueryStringSeparator(queryString), entry.getKey(), entry.getValue());
                }
            }
        }
        if (customParams != null && !customParams.isEmpty()) {
            queryString = String.format("%s%s%s", queryString, getQueryStringSeparator(queryString), customParams);
        }
        return queryString;
    }

    private String getQueryStringSeparator(String queryString) {
        if (queryString.isEmpty()) {
            return "?";
        } else {
            return "&";
        }
    }

}
