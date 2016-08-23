package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;

import java.util.LinkedHashMap;
import java.util.Map;


public class ReevooTaglibTest extends BasicTagTestCaseAdapter {

    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooTaglib reevooTag = new ReevooTaglib();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setDynamicAttribute("", "sku", "ABC123");
        reevooTag.setTrkref("FOO");
        reevooTag.setBaseURI("https://mark.reevoo.com/endpoint");
        reevooTag.setClient(markClient);
        setTag(reevooTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributes() {
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "ABC123");
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("https://mark.reevoo.com/endpoint", queryStringParams);
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyMap())).thenReturn("FOO");
        processTagLifecycle();
        verifyOutput("FOO");
    }

    @Test
    public void testTagReturnsTagBodyWhenNoResponseFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyMap())).thenReturn(null);
        setBody("There are no reviews");
        processTagLifecycle();
        verifyOutput("There are no reviews");
    }

}
