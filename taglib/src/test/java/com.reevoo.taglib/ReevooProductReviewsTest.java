package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;

import java.util.LinkedHashMap;
import java.util.Map;


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
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocale() {
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/fr-FR/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/10/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocaleAndNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyMap(), anyString())).thenReturn("FOO");
        processTagLifecycle();
        verifyOutput("FOO");
    }

    @Test
    public void testTagReturnsTagBodyWhenNoResponseFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyMap(), anyString())).thenReturn(null);
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
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesWhenPaginated() {
        productReviewsTag.setPaginated(true);
        productReviewsTag.setNumberOfReviews("5");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        queryStringParams.put("page", null);
        queryStringParams.put("per_page", "5");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

}
