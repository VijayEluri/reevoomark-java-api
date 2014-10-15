package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mockrunner.tag.*;


public class ReevooCustomerExperienceReviewsTest extends BasicTagTestCaseAdapter {

    /*
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
        verify(markClient).obtainReevooMarkData("FOO", null, "http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingLocale() {
        cxTag.setLocale("fr-FR");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", null, "http://mark.reevoo.com/reevoomark/fr-FR/embeddable_customer_experience_reviews", "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingNumberOfReviews() {
        cxTag.setNumberOfReviews("10");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", null, "http://mark.reevoo.com/reevoomark/10/embeddable_customer_experience_reviews", "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingLocaleAndNumberOfReviews() {
        cxTag.setNumberOfReviews("10");
        cxTag.setLocale("fr-FR");
        processTagLifecycle();
        verify(markClient).obtainReevooMarkData("FOO", null, "http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_customer_experience_reviews", "");
    }

    @Test
    public void testTagRespondsWithContentFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyString(), anyString(), anyString())).thenReturn("FOO");
        processTagLifecycle();
        verifyOutput("FOO");
    }

    @Test
    public void testTagReturnsTagBodyWhenNoResponseFromClient() {
        when(markClient.obtainReevooMarkData(anyString(), anyString(), anyString(), anyString())).thenReturn(null);
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
        verify(markClient).obtainReevooMarkData("REV", null, "http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", "");
    }
    */

}
