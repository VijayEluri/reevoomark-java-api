package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooProductBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooProductBadge reevooTag = new ReevooProductBadge();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setTrkref("FOO");
        reevooTag.setDynamicAttribute("", "sku", "ABC123");
        setTag(reevooTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor() {
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/FOO/ABC123\"></a>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        reevooTag = new ReevooProductBadge();
        reevooTag.setDynamicAttribute("", "sku", "ABC123");
        setTag(reevooTag);
        processTagLifecycle();
        verifyOutput(String.format("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/%s/ABC123\"></a>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfUndecoratedItPrintsTheRightAnchorClass() {
        reevooTag.setVariantName("undecorated");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark undecorated\" href=\"//test.reevoo.com/partner/FOO/ABC123\"></a>");
    }

    @Test
    public void testThatIfVariantNameOtherThanUndecoratedItPrintsTheRightAnchorClass() {
        reevooTag.setVariantName("search_page");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark search_page_variant\" href=\"//test.reevoo.com/partner/FOO/ABC123\"></a>");
    }

    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<a class=\"reevoomark\" href=\"//test.reevoo.com/partner/FOO/ABC123\">click here</a>");
    }
}
