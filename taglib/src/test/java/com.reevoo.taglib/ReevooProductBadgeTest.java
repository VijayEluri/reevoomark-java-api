package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
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

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        reevooTag = new ReevooProductBadge();
        reevooTag.setSku("ABC123");
        setTag(reevooTag);
        processTagLifecycle();
        verifyOutput(String.format("<a class=\"reevoomark\" href=\"http://mark.reevoo.com/partner/%s/ABC123\"></a>", TaglibConfig.getProperty("default.trkref")));
    }
}

