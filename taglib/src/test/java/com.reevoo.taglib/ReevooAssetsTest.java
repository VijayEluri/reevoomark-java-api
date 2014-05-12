package com.reevoo.taglib;

import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;



public class ReevooAssetsTest extends BasicTagTestCaseAdapter{

    private ReevooAssets reevooTag = new ReevooAssets();

    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        reevooTag.setTrkref("FOO");
        setTag(reevooTag);
    }

    @Test
    public void testTagIncludesJsCustomisedForTheRetailer()
    {
        processTagLifecycle();
        verifyOutputContains("FOO");
    }

    @Test
    public void testIncludesCorrectHTMLLoader()
    {
        processTagLifecycle();
        verifyOutput(String.format(ReevooAssets.ASSETLOADER,"FOO"));
    }
}
