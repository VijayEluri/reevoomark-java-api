package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import com.mockrunner.tag.*;



public class ReevooProductReviewsTest extends BasicTagTestCaseAdapter {

    private ReevooMarkClient markClient = mock(ReevooMarkClient.class);
    private ReevooProductReviews productReviewsTag = new ReevooProductReviews();


    @Before
    public void setUp() throws Exception {
        super.setUp();
        productReviewsTag.setTrkref("FOO");
        productReviewsTag.setSku("12345");
        productReviewsTag.setClient(markClient);
        setTag(productReviewsTag);
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheProductReviewsEndpoint() {
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "12345", productReviewsTag.buildUrl(TaglibConfig.getProperty("product.reviews.url")));
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
        productReviewsTag = new ReevooProductReviews();
        productReviewsTag.setClient(markClient);
        setTag(productReviewsTag);
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData(TaglibConfig.getProperty("default.trkref"), null, productReviewsTag.buildUrl(TaglibConfig.getProperty("product.reviews.url")));
    }



}