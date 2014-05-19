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
        verify(markClient).obtainReevooMarkData("FOO", "12345", "http://mark.reevoo.com/reevoomark/embeddable_reviews");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocale() {
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "12345", "http://mark.reevoo.com/reevoomark/fr-FR/embeddable_reviews");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "12345", "http://mark.reevoo.com/reevoomark/10/embeddable_reviews");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocaleAndNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", "12345", "http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_reviews");
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

    @Test
    public void testTagUsesDefaultTrkrefIfNoExplicitOneSpecified() {
        productReviewsTag = new ReevooProductReviews();
        productReviewsTag.setClient(markClient);
        setTag(productReviewsTag);
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("REV", null, "http://mark.reevoo.com/reevoomark/embeddable_reviews");
    }


}