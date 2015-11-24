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
        verifyOutput("<reevoo-reviewable-badge trkref=\"FOO\" sku=\"ABC123\"></reevoo-reviewable-badge>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        reevooTag = new ReevooProductBadge();
        reevooTag.setDynamicAttribute("", "sku", "ABC123");
        setTag(reevooTag);
        processTagLifecycle();
        verifyOutput(String.format("<reevoo-reviewable-badge trkref=\"%s\" sku=\"ABC123\"></reevoo-reviewable-badge>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfUndecoratedItPrintsTheRightAnchorClass() {
        reevooTag.setDynamicAttribute("", "variant", "undecorated");
        processTagLifecycle();
        verifyOutput("<reevoo-reviewable-badge trkref=\"FOO\" sku=\"ABC123\" variant=\"undecorated\"></reevoo-reviewable-badge>");
    }

    @Test
    public void testThatIfVariantNameOtherThanUndecoratedItPrintsTheRightAnchorClass() {
        reevooTag.setDynamicAttribute("", "variant", "search_page");
        processTagLifecycle();
        verifyOutput("<reevoo-reviewable-badge trkref=\"FOO\" sku=\"ABC123\" variant=\"search_page\"></reevoo-reviewable-badge>");
    }

    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<reevoo-reviewable-badge trkref=\"FOO\" sku=\"ABC123\">click here</reevoo-reviewable-badge>");
    }
}
