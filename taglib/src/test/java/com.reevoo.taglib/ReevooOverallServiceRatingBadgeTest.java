package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooOverallServiceRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooOverallServiceRatingBadge reevooTag = new ReevooOverallServiceRatingBadge();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        reevooTag.setTrkref("FOO");
        setTag(reevooTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor() {
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\"></reevoo-customer-experience-badge>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        setTag(new ReevooOverallServiceRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<reevoo-customer-experience-badge trkref=\"%s\"></reevoo-customer-experience-badge>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass() {
        reevooTag.setDynamicAttribute("", "variant", "undecorated");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" variant=\"undecorated\"></reevoo-customer-experience-badge>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\">click here</reevoo-customer-experience-badge>");
    }
}
