package com.reevoo.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.*;
import com.reevoo.client.*;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;

public class ReevooMarkClientTest {

  private class ReevooMarkTestClient extends ReevooMarkClient {
    public ReevooMarkTestClient(int connectTimeout) {
      super(connectTimeout);
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
    c = new ReevooMarkTestClient(2000);
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

    assertEquals((double)expected_time.getTime(), (double)cache_expires.getTime(), 1000);
  }
}