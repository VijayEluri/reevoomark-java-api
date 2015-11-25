package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooCustomerServiceRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooCustomerServiceRatingBadge customerServiceTag = new ReevooCustomerServiceRatingBadge();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        customerServiceTag.setTrkref("FOO");
        setTag(customerServiceTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor() {
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"customer_service\"></reevoo-customer-experience-badge>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        setTag(new ReevooCustomerServiceRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<reevoo-customer-experience-badge trkref=\"%s\" score=\"customer_service\"></reevoo-customer-experience-badge>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass() {
        customerServiceTag.setDynamicAttribute("", "variant", "undecorated");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"customer_service\" variant=\"undecorated\"></reevoo-customer-experience-badge>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"customer_service\">click here</reevoo-customer-experience-badge>");
    }
}
