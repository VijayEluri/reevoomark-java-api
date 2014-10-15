package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;


public class ReevooTaglibTest extends BasicTagTestCaseAdapter {

    /*
    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooTaglib reevooTag = new ReevooTaglib();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setSku("ABC123");
        reevooTag.setTrkref("FOO");
        reevooTag.setBaseURI("http://mark.reevoo.com/endpoint");
        reevooTag.setClient(markClient);
        setTag(reevooTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributes() {
        when(markClient.obtainReevooMarkData(anyString(), anyString(), anyString())).thenReturn("FOO");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "ABC123", "http://mark.reevoo.com/endpoint");
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyString(), anyString())).thenReturn("FOO");
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
    */

}
