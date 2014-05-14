package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import org.junit.Before;
import org.junit.Test;
import com.mockrunner.tag.*;



public class ReevooJavascriptAssetsTest extends BasicTagTestCaseAdapter{

    private ReevooJavascriptAssets reevooTag = new ReevooJavascriptAssets();

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
        verifyOutput(String.format(TaglibConfig.getSingletrkrefMarkloaderScript(),"FOO"));
    }


    @Test
    public void testIncludesCorrectMultipleTrkrefLoaderScript()
    {
        reevooTag.setTrkref("FOO,BAR,CYS");
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getMultitrkrefMarkloaderScript(),"FOO,BAR,CYS"));
    }

    @Test
    public void testIfTrkrefNotSpecifyItWillUseTheOneDefinedInTheConfigurationFile()
    {
        setTag(new ReevooJavascriptAssets());
        processTagLifecycle();
        verifyOutput(String.format(TaglibConfig.getSingletrkrefMarkloaderScript(),"REV"));
    }
}
