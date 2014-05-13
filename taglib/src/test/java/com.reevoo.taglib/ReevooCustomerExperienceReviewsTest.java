package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import com.mockrunner.tag.*;



public class ReevooCustomerExperienceReviewsTest extends BasicTagTestCaseAdapter {

    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooCustomerExperienceReviews cxTag = new ReevooCustomerExperienceReviews();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        cxTag.setTrkref("FOO");
        cxTag.setClient(markClient);
        setTag(cxTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpoint() {
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", null, TaglibConfig.getProperty("customer.experience.reviews.url"));
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
        cxTag = new ReevooCustomerExperienceReviews();
        cxTag.setClient(markClient);
        setTag(cxTag);
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData(TaglibConfig.getProperty("default.trkref"), null, TaglibConfig.getProperty("customer.experience.reviews.url"));
    }



}