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
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingNumberOfReviews() {
        cxTag.setNumberOfReviews("10");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("reviews","10");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testTagCallsClientWithCorrectAttributesAndTheCXEndpointWhenUsingLocale() {
        cxTag.setDynamicAttribute("", "locale", "en-GB");
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "FOO");
        queryStringParams.put("locale","en-GB");
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
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
        queryStringParams.put("reviews", null);
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }

    @Test
    public void testCorrectQueryStringParamsAreSentWhenPaginationEnabled() {
        cxTag = new ReevooCustomerExperienceReviews();
        cxTag.setClient(markClient);
        cxTag.setPaginated(true);
        cxTag.setNumberOfReviews("5");
        setTag(cxTag);
        processTagLifecycle();
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", "REV");
        queryStringParams.put("page", null);
        queryStringParams.put("per_page", "5");
        queryStringParams.put("sort_by", "seo_boost");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");

        // when paginated and numberOfReviews missing we set the per_page param to default
        cxTag.setNumberOfReviews(null);
        processTagLifecycle();
        queryStringParams.put("per_page", "default");
        verify(markClient).obtainReevooMarkData("http://mark.reevoo.com/reevoomark/embeddable_customer_experience_reviews", queryStringParams, "");
    }


}
