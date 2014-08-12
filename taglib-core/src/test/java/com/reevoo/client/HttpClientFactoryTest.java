package com.reevoo.client;

import java.io.IOException;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.apache.commons.httpclient.HttpClient;

public class HttpClientFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
    }

    @Test
    public void testWhenProxyIsSetThroughEnvironmentVariables() {
        System.setProperty("http.proxyHost", "http://example-proxy.com");
        System.setProperty("http.proxyPort", "8080");

        HttpClient client = HttpClientFactory.build(10, null, null);

        assertEquals(client.getHostConfiguration().getProxyHost(), "http://example-proxy.com");
        assertEquals(client.getHostConfiguration().getProxyPort(), 8080);
    }

    @Test
    public void testWhenProxyIsSetThroughMethodParams() {
        HttpClient client = HttpClientFactory.build(10, "http://example-proxy.com", "9999");

        assertEquals(client.getHostConfiguration().getProxyHost(), "http://example-proxy.com");
        assertEquals(client.getHostConfiguration().getProxyPort(), 9999);
    }

    @Test
    public void testProxySettingThroughMethodTakePriorityOnProxySettingsThroughEnvironmentVariables() {
        System.setProperty("http.proxyHost", "http://example-proxy.com");
        System.setProperty("http.proxyPort", "8080");

        HttpClient client = HttpClientFactory.build(10, "http://another-proxy.com", "7777");

        assertEquals(client.getHostConfiguration().getProxyHost(), "http://another-proxy.com");
        assertEquals(client.getHostConfiguration().getProxyPort(), 7777);
    }



    @Test
    public void testWhenProxyIsNotSet() {
        HttpClient client = HttpClientFactory.build(10, null, null);

        assertNull(client.getHostConfiguration().getProxyHost());
        assertEquals(client.getHostConfiguration().getProxyPort(), -1);
    }
}
