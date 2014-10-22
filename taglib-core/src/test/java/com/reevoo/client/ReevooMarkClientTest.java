package com.reevoo.client;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ReevooMarkClientTest {


    private class ReevooMarkTestClient extends ReevooMarkClient {
        public ReevooMarkTestClient(int connectTimeout, String proxyHost, String proxyPort) {
            super(connectTimeout, proxyHost, proxyPort);
        }

        public void setClient(HttpClient client) {
            this.client = client;
        }
    }

    private ReevooMarkTestClient c;
    private GetMethod m;
    private HttpClient h;

    private void setFreshCache(int status) {
        Cache.put("http://www.example.org/reevoomark", new ReevooMarkRecord("cached_response", 60, status, 100));
    }

    private void setStaleCache(int status) {
        Cache.put("http://www.example.org/reevoomark", new ReevooMarkRecord("cached_response", -60, status, 100));
    }

    private String getCache() {
        return Cache.get("http://www.example.org/reevoomark").value;
    }

    private boolean cacheEmpty() {
        return Cache.get("http://www.example.org/reevoomark") == null;
    }

    private void p(String str) {
        System.out.println(str);
    }

    @Before
    public void setUp() throws Exception {
        m = mock(GetMethod.class);
        when(m.getURI()).thenReturn(new URI("http://www.example.org/reevoomark"));
        when(m.getResponseBodyAsString()).thenReturn("fresh_response");
        when(m.getResponseHeader("X-Reevoo-ReviewCount")).thenReturn(new Header("X-Reevoo-ReviewCount", "8"));
        h = mock(HttpClient.class);
        when(h.executeMethod(m)).thenReturn(1);
        c = new ReevooMarkTestClient(2000, null, null);
        c.setClient(h);
    }

    @After
    public void tearDown() throws Exception {
        Cache.invalidate();
    }

    @Test
    public void testCachedResponse() throws IOException {
        setFreshCache(200);
        String data = c.obtainReevooMarkData(m);
        assertEquals("cached_response", data);
    }

    @Test
    public void testWithStaleCacheAnd200Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(200);
        int[] cachedStatusCodes = {200, 404, 500};
        for (int i = 0; i < cachedStatusCodes.length; i++) {
            setStaleCache(cachedStatusCodes[i]);
            String data = c.obtainReevooMarkData(m);
            assertEquals("fresh_response", data);
            assertEquals("fresh_response", getCache());
        }
    }

    @Test
    public void testEmptyCacheAnd200Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(200);
        String data = c.obtainReevooMarkData(m);
        assertEquals("fresh_response", data);
        assertEquals("fresh_response", getCache());
    }

    @Test
    public void testWithStaleCacheAnd404Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(404);
        int[] cachedStatusCodes = {200, 404, 500};
        for (int i = 0; i < cachedStatusCodes.length; i++) {
            setStaleCache(cachedStatusCodes[i]);
            String data = c.obtainReevooMarkData(m);
            assertNull(data);
            assertNull(getCache());
        }
    }

    @Test
    public void testEmptyCacheAnd404Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(404);
        String data = c.obtainReevooMarkData(m);
        assertNull(data);
        assertNull(getCache());
    }

    @Test
    public void testWithStaleCacheAnd500Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(500);
        int[] cachedStatusCodes = {200, 404, 500};
        for (int i = 0; i < cachedStatusCodes.length; i++) {
            setStaleCache(cachedStatusCodes[i]);
            String data = c.obtainReevooMarkData(m);
            assertEquals("cached_response", data);
            assertEquals("cached_response", getCache());
        }
    }

    @Test
    public void testEmptyCacheAnd500Fresh() throws IOException {
        when(m.getStatusCode()).thenReturn(500);
        String data = c.obtainReevooMarkData(m);
        assertNull(data);
        assertNull(getCache());
    }

    @Test
    public void testWithStaleCacheAndConnectionError() throws IOException {
        doThrow(new IOException()).when(h).executeMethod(m);
        int[] cachedStatusCodes = {200, 404, 500};
        for (int i = 0; i < cachedStatusCodes.length; i++) {
            setStaleCache(cachedStatusCodes[i]);
            String data = c.obtainReevooMarkData(m);
            assertEquals("cached_response", data);
            assertEquals("cached_response", getCache());
        }
    }

    @Test
    public void testEmptyCacheAndConnnectionError() throws IOException {
        doThrow(new IOException()).when(h).executeMethod(m);
        when(m.getStatusCode()).thenReturn(500);
        String data = c.obtainReevooMarkData(m);
        assertNull(data);
        assertTrue(cacheEmpty());
    }

    @Test
    public void testCacheRespectsResponseHeaders() throws IOException {
        when(m.getResponseHeader("Cache-Control")).thenReturn(new Header("Cache-Control", "max-age=6"));
        when(m.getResponseHeader("Age")).thenReturn(new Header("Age", "1"));
        when(m.getStatusCode()).thenReturn(200);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        Date expected_time = cal.getTime();

        String data = c.obtainReevooMarkData(m);
        Date cache_expires = Cache.get("http://www.example.org/reevoomark").getExpirationTime();

        assertEquals((double) expected_time.getTime(), (double) cache_expires.getTime(), 1000);
    }

    private String buildParams(String trkref, String sku, String customParms) throws Exception {
        Map<String,String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("sku",sku);
        queryStringParams.put("retailer",trkref);

        Class argClasses[] = new Class[2];
        argClasses[0] = Map.class;
        argClasses[1] = String.class;

        Object params[] = new Object[2];
        params[0] = queryStringParams;
        params[1] = customParms;
        Method method = ReevooMarkClient.class.getDeclaredMethod("generateReevooMarkQueryParams", argClasses);

        method.setAccessible(true);
        return (String) method.invoke(c, params);

    }

    @Test
    public void testURLEscaping() throws Exception {
        assertEquals("?sku=SKU%3Bparts&retailer=TRKREF", buildParams("TRKREF", "SKU;parts", ""));
        assertEquals("?sku=SKU%2Fparts&retailer=TRKREF", buildParams("TRKREF", "SKU/parts", ""));
        assertEquals("?sku=SKU%26parts&retailer=TRKREF", buildParams("TRKREF", "SKU&parts", ""));
        assertEquals("?sku=SKU%A3parts&retailer=TRKREF&disable_product_microdata=true", buildParams("TRKREF", "SKU£parts", "disable_product_microdata=true"));
        assertEquals("?sku=SKU+parts&retailer=TRKREF&enable_awesome=true&pink=false", buildParams("TRKREF", "SKU parts", "enable_awesome=true&pink=false"));
        assertEquals("?sku=SKU%2Bparts&retailer=TRKREF", buildParams("TRKREF", "SKU+parts", ""));
        assertEquals("?sku=SKU%25parts&retailer=TRKREF", buildParams("TRKREF", "SKU%parts", ""));
        assertEquals("?retailer=TRKREF", buildParams("TRKREF", null, ""));
    }

    @Test
    public void ifResponse200ReturnResponseBodyIndependentlyOfTheNumberOfReviews() throws IOException {
        when(m.getStatusCode()).thenReturn(200);
        when(m.getResponseHeader("X-Reevoo-ReviewCount")).thenReturn(
                new Header("X-Reevoo-ReviewCount", "0"),
                new Header("X-Reevoo-ReviewCount", "-2"),
                new Header("X-Reevoo-ReviewCount", "10"));
        for (int i=0; i<3; i++) {
            String data = c.obtainReevooMarkData(m);
            assertEquals("fresh_response", data);
        }

    }

}
