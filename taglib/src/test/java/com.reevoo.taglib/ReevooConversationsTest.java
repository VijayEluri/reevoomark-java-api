package com.reevoo.taglib;
import com.reevoo.client.ReevooMarkClient;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import com.mockrunner.tag.*;



public class ReevooConversationsTest extends BasicTagTestCaseAdapter{

    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooConversations conversationsTag = new ReevooConversations();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        conversationsTag.setSku("ABC123");
        conversationsTag.setTrkref("FOO");
        conversationsTag.setClient(markClient);
        setTag(conversationsTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheConversationEndpoint() {
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "ABC123", conversationsTag.buildUrl(TaglibConfig.getProperty("conversations.url")));
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(),anyString(),anyString())).thenReturn("FOO");
        processTagLifecycle();
        verifyOutput("FOO");
    }

    @Test
    public void testTagReturnsTagBodyWhenNoResponseFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyString(), anyString())).thenReturn(null);
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
        verify(markClient).obtainReevooMarkData(TaglibConfig.getProperty("default.trkref"), null, conversationsTag.buildUrl(TaglibConfig.getProperty("conversations.url")));

    }



}