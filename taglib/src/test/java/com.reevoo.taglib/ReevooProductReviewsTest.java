package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;


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
        queryStringParams.put("locale", null);
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocale() {
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        queryStringParams.put("locale", "fr-FR");
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        queryStringParams.put("locale", null);
        queryStringParams.put("reviews", "10");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndEndpointWhenUsingLocaleAndNumberOfReviews() {
        productReviewsTag.setNumberOfReviews("10");
        productReviewsTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", "12345");
        queryStringParams.put("locale","fr-FR");
        queryStringParams.put("reviews","10");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
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
        queryStringParams.put("locale", null);
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testCorrectQueryStringParamsAreSentWhenPaginationEnabled() {
        productReviewsTag = new ReevooProductReviews();
        productReviewsTag.setClient(markClient);
        productReviewsTag.setPaginated(true);
        productReviewsTag.setNumberOfReviews("5");
        productReviewsTag.setLocale("en-GB");
        productReviewsTag.setSku("123");
        setTag(productReviewsTag);
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("sku", "123");
        queryStringParams.put("locale", "en-GB");
        queryStringParams.put("page", null);
        queryStringParams.put("per_page", "5");
        queryStringParams.put("sort_by", "seo_boost");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");

        // when paginated and numberOfReviews missing we set the per_page param to default
        productReviewsTag.setNumberOfReviews(null);
        processTagLifecycle();
        queryStringParams.put("per_page", "default");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }

    @Test
    public void testDynamicAttributes() {
        productReviewsTag = new ReevooProductReviews();
        productReviewsTag.setClient(markClient);
        productReviewsTag.setLocale("en-GB");
        productReviewsTag.setDynamicAttribute("", "model", "fiesta");
        productReviewsTag.setDynamicAttribute("","modelVariant","studio");
        productReviewsTag.setDynamicAttribute("","manufacturer","ford");
        productReviewsTag.setDynamicAttribute("","doors","5");
        productReviewsTag.setDynamicAttribute("","fuelType","PETROL");
        setTag(productReviewsTag);
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("manufacturer", "ford");
        queryStringParams.put("model", "fiesta");
        queryStringParams.put("model_variant", "studio");
        queryStringParams.put("doors", "5");
        queryStringParams.put("fuel_type", "PETROL");
        queryStringParams.put("sku", null);
        queryStringParams.put("locale", "en-GB");
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_reviews", queryStringParams, "");
    }


}
