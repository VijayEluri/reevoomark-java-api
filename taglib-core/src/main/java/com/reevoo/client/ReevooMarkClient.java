package com.reevoo.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * Hook between reevoo REST endpoint, and the rest of the general taglib code
 */
public class ReevooMarkClient {
    protected HttpClient client;
    private Properties config;
    private int failedRequestCacheTime;
    private int circuitBreakerWaitTime;
    private int circuitBreakerLimit;

    public ReevooMarkClient(Properties config) {
        this.config = config;
        this.client = HttpClientFactory.build(
                Integer.valueOf(config.getProperty("http.timeout")),
                config.getProperty("http.proxyHost"),
                config.getProperty("http.proxyPort"));

        this.failedRequestCacheTime = Integer.valueOf(config.getProperty("http.failed_request.cache_time.in_seconds"));
        this.circuitBreakerWaitTime = Integer.valueOf(config.getProperty("http.circuit_breaker.wait_time.in_seconds"));
        this.circuitBreakerLimit = Integer.valueOf(config.getProperty("http.circuit_breaker.number_of_retries"));
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
            return cachedResponse.getValue();
        }

        try {
            client.executeMethod(request);
        } catch (Exception e) {
            return  cacheFailedRequest(cacheKey, cachedResponse, 500).getValue();
        }

        int status = request.getStatusCode();
        String content;
        if (status == 200) {
            content = getStringContent(request.getResponseBodyAsStream());
        } else {
            content = null;
        }

        if (status >= 400) {
            return cacheFailedRequest(cacheKey, cachedResponse, status).getValue();
        }

        int time_to_live = secondsToLive(request);
        int review_count = extractReviewCountHeader(request);
        Cache.put(cacheKey, ReevooMarkRecord.createRecord(content, time_to_live, status, review_count));
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
                    queryString = String.format("%s%s%s=%s", queryString, getQueryStringSeparator(queryString),
                        entry.getKey(), URLEncoder.encode(entry.getValue(), "ISO-8859-1"));
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
            return "";
        } else {
            return "&";
        }
    }

    /**
     * Puts a ReevooMarkRecord instance in the cache for a failed request.
     *
     * @param cachedResponse The ReevooMarkRecord instance to cache.
     * @param status Response error code for the failed request.
     */
    private ReevooMarkRecord cacheFailedRequest(String cacheKey, ReevooMarkRecord cachedResponse, int status) {
        ReevooMarkRecord cacheEntry =
                ReevooMarkRecord.createRecord(cachedResponse, timeToCacheFailedRequest(cachedResponse), status);
        Cache.put(cacheKey, cacheEntry);
        return cacheEntry;
    }

    /**
     * Checks whether the specified ReevooMarkRecord instance has reached the configured
     * maximum number of consecutive failed attempts to get the embedded content from Reevoo servers.
     *
     * @param cachedResponse ReevooMarkRecord instance to check
     * @return true if limit reach; false otherwise
     */
    private Boolean maxConsecutiveFailedAttemptsReached(ReevooMarkRecord cachedResponse) {
        return cachedResponse != null &&
                cachedResponse.getConsecutiveFailedAttempts() >= this.circuitBreakerLimit;
    }

    /**
     * Returns the time to cache a failed request.
     * @param cachedResponse ReevooMarkRecord instance.
     * @return The time to cache the failed request in seconds.
     */
    private int timeToCacheFailedRequest(ReevooMarkRecord cachedResponse) {
        int timeToCache = this.failedRequestCacheTime;
        if (maxConsecutiveFailedAttemptsReached(cachedResponse)) {
            timeToCache = this.circuitBreakerWaitTime;
        }
        return timeToCache;
    }

    static String getStringContent(InputStream input) throws java.io.IOException {
        BufferedReader reader = null;
        StringBuilder out = new StringBuilder();
        if (input != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
        return out.toString();
    }

}
