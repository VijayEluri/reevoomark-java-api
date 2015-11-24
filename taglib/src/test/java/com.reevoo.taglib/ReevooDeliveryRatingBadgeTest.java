package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooDeliveryRatingBadgeTest extends BasicTagTestCaseAdapter {

    private ReevooDeliveryRatingBadge deliveryTag = new ReevooDeliveryRatingBadge();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deliveryTag.setTrkref("FOO");
        setTag(deliveryTag);
    }

    @Test
    public void testFormatsTheCorrectAnchor() {
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"delivery\"></reevoo-customer-experience-badge>");
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile() {
        setTag(new ReevooDeliveryRatingBadge());
        processTagLifecycle();
        verifyOutput(String.format("<reevoo-customer-experience-badge trkref=\"%s\" score=\"delivery\"></reevoo-customer-experience-badge>", TaglibConfig.getProperty("default.trkref")));
    }

    @Test
    public void testThatIfVariantNamePresentItPrintsTheRightAnchorClass() {
        deliveryTag.setDynamicAttribute("", "variant", "undecorated");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"delivery\" variant=\"undecorated\"></reevoo-customer-experience-badge>");
    }


    @Test
    public void testThatTheTagBodyGoesAsTheAnchorBody() {
        setBody("click here");
        processTagLifecycle();
        verifyOutput("<reevoo-customer-experience-badge trkref=\"FOO\" score=\"delivery\">click here</reevoo-customer-experience-badge>");
    }
}
