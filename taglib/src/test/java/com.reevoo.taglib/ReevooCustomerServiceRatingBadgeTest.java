package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooCustomerServiceRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooCustomerServiceRatingBadge customerServiceTag = new ReevooCustomerServiceRatingBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        customerServiceTag.setTrkref("FOO");
        setTag(customerServiceTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation customer_service\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooCustomerServiceRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<a href=\"//mark.reevoo.com/retailer/%s\" class=\"reevoo_reputation customer_service\"></a>",TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        customerServiceTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation customer_service undecorated\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation customer_service\">click here</a>");
    }
}