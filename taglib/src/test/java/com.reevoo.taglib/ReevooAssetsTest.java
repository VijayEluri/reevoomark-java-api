package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
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
    public void testIncludesCorrectSingleTrkrefLoaderScript()
    {
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getProperty("singletrackref.markloader"),"FOO"));
    }


    @Test
    public void testIncludesCorrectMultipleTrkrefLoaderScript()
    {
        reevooTag.setTrkref("FOO,BAR,CYS");
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getProperty("multitrackref.markloader"),"FOO,BAR,CYS"));
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooAssets());
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getProperty("singletrackref.markloader"),TaglibConfig.getProperty("default.trkref")));
    }
}
