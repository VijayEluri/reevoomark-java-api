package com.reevoo.taglib;

import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;

public class ReevooCssAssetsTest extends BasicTagTestCaseAdapter {

    private ReevooCssAssets cssLink = new ReevooCssAssets();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setTag(cssLink);
    }

    @Test
    public void testTheCssLinkIsLoadedCorrectly() {
        processTagLifecycle();
        verifyOutput("<link rel=\"stylesheet\" href=\"//mark.reevoo.com/stylesheets/reevoomark/embedded_reviews.css\" type=\"text/css\" />");
    }
}