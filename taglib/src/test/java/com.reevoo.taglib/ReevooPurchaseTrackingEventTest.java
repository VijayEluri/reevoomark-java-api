package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;


public class ReevooPurchaseTrackingEventTest extends BasicTagTestCaseAdapter {

    private ReevooPurchaseTrackingEvent reevooTag = new ReevooPurchaseTrackingEvent();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setTrkref("FOO");
        reevooTag.setSkus("AAA,BBB,CCC");
        reevooTag.setValue("234");
        setTag(reevooTag);
    }

    @Test
    public void testTagOutputsCorrectTrkref() {
        processTagLifecycle();
        verifyOutputContains("FOO");
    }

    @Test
    public void testTagOutputsAllSkus() {
        processTagLifecycle();
        verifyOutputContains("AAA");
        verifyOutputContains("BBB");
        verifyOutputContains("CCC");
    }

    @Test
    public void testTagOutputsTheTrackingEventScript() {
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getPurchaseTrackingScript(), "FOO", "AAA,BBB,CCC", "234"));
    }
}
