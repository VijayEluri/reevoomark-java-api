package com.reevoo.utils;
import junit.framework.Assert;
import org.junit.Test;


public class TaglibConfigTest {

    @Test
    public void weCanAccessTheValueOfExistingPropertiesIn() {
        Assert.assertEquals(TaglibConfig.getProperty("default.trkref"),"REV");
    }

    @Test
    public void ifThePropertyDoesNotExistWeGetEmptyString() {
        Assert.assertEquals(TaglibConfig.getProperty("whatever"),"");
    }

    @Test
    public void ifTheCustomerProvidesTheirOwnPropertiesFileWeGivePreferenceToTheCustomerValues() {
        Assert.assertEquals(TaglibConfig.getProperty("conversations.url"),"customer_overridden_url");
    }

}
