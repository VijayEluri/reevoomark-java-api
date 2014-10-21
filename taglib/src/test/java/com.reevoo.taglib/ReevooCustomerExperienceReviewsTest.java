package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;

import java.util.LinkedHashMap;
import java.util.Map;


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
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingLocale() {
        cxTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/fr-FR/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingNumberOfReviews() {
        cxTag.setNumberOfReviews("10");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/10/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingLocaleAndNumberOfReviews() {
        cxTag.setNumberOfReviews("10");
        cxTag.setLocale("fr-FR");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_customer_experience_reviews", queryStringParams, "");
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
        cxTag = new ReevooCustomerExperienceReviews();
        cxTag.setClient(markClient);
        setTag(cxTag);
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("sku", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }

}
