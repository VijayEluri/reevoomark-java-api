package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;


public class ReevooPropensityToBuyTrackingEventTest extends BasicTagTestCaseAdapter {

    private ReevooPropensityToBuyTrackingEvent reevooTag = new ReevooPropensityToBuyTrackingEvent();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setTrkref("FOO");
        reevooTag.setAction("Checking Brochure");
        setTag(reevooTag);
    }

    @Test
    public void testTagOutputsCorrectTrkref() {
        processTagLifecycle();
        verifyOutputContains("FOO");
    }

    @Test
    public void testTagOutputsGlobalCTAWhenNoSkuProvided() {
        processTagLifecycle();
        verifyOutputContains("Global CTA");
    }

    @Test
    public void testTagOutputsCorrectActionValue() {
        processTagLifecycle();
        verifyOutputContains("Checking Brochure");
    }

    @Test
    public void testTagOutputsSkuWhenSkuProvided() {
        reevooTag.setSku("AAA");
        processTagLifecycle();
        verifyOutputContains("AAA");
    }

    @Test
    public void testTagOutputsTheTrackingEventScript() {
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getPropensityToBuyScript(), "FOO", "Checking Brochure", "Global CTA"));
    }
}
