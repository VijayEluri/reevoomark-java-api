package com.reevoo.taglib;

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
}