package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooCustomerExperienceBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooCustomerExperienceBadge reevooTag = new ReevooCustomerExperienceBadge();

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
        verifyOutput("<a href=\"http://mark.reevoo.com/retailer/FOO\" class=\"reevoo_reputation\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooCustomerExperienceBadge());
        processTagLifecycle();
        verifyOutput(String.format("<a href=\"http://mark.reevoo.com/retailer/%s\" class=\"reevoo_reputation\"></a>",TaglibConfig.getProperty("default.trkref")));
    }
}