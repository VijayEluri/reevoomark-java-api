package com.reevoo.taglib;

import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooProductBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooProductBadge reevooTag = new ReevooProductBadge();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        reevooTag.setTrkref("FOO");
        reevooTag.setSku("ABC123");
        setTag(reevooTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor()
    {
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark\" href=\"http://mark.reevoo.com/partner/FOO/ABC123\"></a>");
    }
}
