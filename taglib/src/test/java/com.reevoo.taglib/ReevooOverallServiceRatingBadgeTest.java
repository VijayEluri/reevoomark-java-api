package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooOverallServiceRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooOverallServiceRatingBadge reevooTag = new ReevooOverallServiceRatingBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        reevooTag.setTrkref("FOO");
        setTag(reevooTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a href=\"//test.reevoo.com/retailer/FOO\" class=\"reevoo_reputation\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooOverallServiceRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<a href=\"//test.reevoo.com/retailer/%s\" class=\"reevoo_reputation\"></a>",TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass(){
        reevooTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a href=\"//test.reevoo.com/retailer/FOO\" class=\"reevoo_reputation undecorated\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody(){
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a href=\"//test.reevoo.com/retailer/FOO\" class=\"reevoo_reputation\">click here</a>");
    }
}