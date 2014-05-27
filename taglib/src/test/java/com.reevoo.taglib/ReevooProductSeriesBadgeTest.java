package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooProductSeriesBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooProductSeriesBadge reevooTag = new ReevooProductSeriesBadge();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setTrkref("FOO");
        reevooTag.setSku("ABC123");
        setTag(reevooTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor() {
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/FOO/series:ABC123\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        reevooTag = new ReevooProductSeriesBadge();
        reevooTag.setSku("ABC123");
        setTag(reevooTag);
        processTagLifecycle();
        verifyOutput(String.format("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/%s/series:ABC123\"></a>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass() {
        reevooTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark undecorated\" href=\"//test.reevoo.com/partner/FOO/series:ABC123\"></a>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/FOO/series:ABC123\">click here</a>");
    }
}

