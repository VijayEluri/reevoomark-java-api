package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooDeliveryRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooDeliveryRatingBadge deliveryTag = new ReevooDeliveryRatingBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        deliveryTag.setTrkref("FOO");
        setTag(deliveryTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation delivery\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooDeliveryRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<a href=\"//mark.reevoo.com/retailer/%s\" class=\"reevoo_reputation delivery\"></a>",TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        deliveryTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation delivery undecorated\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a href=\"//mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation delivery\">click here</a>");
    }
}