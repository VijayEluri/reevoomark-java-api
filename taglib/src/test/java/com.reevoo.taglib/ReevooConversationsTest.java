package com.reevoo.taglib;
import com.reevoo.client.ReevooMarkClient;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import com.mockrunner.tag.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.LinkedHashMap;
import java.util.Map;


public class ReevooConversationsTest extends BasicTagTestCaseAdapter{

    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooConversations conversationsTag = new ReevooConversations();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        conversationsTag.setSku("12345");
        conversationsTag.setTrkref("FOO");
        conversationsTag.setClient(markClient);
        setTag(conversationsTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheConversationEndpoint() {
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        verify(markClient).obtainReevooMarkData(
            "http://mark.reevoo.com/reevoomark/embeddable_conversations",
            queryStringParams);
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(),anyMap())).thenReturn("FOO");
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

    @Test
    public void testTagUsesDefaultTrkrefIfNoExplicitOneSpecified() {
        conversationsTag = new ReevooConversations();
        conversationsTag.setClient(markClient);
        setTag(conversationsTag);
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_conversations", queryStringParams);
    }

}
