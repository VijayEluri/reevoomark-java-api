package com.reevoo.utils;
import junit.framework.Assert;
import org.junit.Test;


public class TaglibConfigTest {

    @Test
    public void weCanAccessTheValueOfExistingPropertiesIn() {
        Assert.assertEquals(TaglibConfig.getProperty("trkref"),"REV");
    }

    @Test
    public void ifThePropertyDoesNotExistWeGetNull() {
        Assert.assertEquals(TaglibConfig.getProperty("whatever"),null);
    }

    @Test
    public void ifTheCustomerProvidesTheirOwnPropertiesFileWeGivePreferenceToTheCustomerValues() {
        Assert.assertEquals(TaglibConfig.getProperty("conversations.url"),"customer_overridden_url");
    }

}
