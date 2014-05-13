package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Abstract superclass that contains common functionality to "all" of reevoo tag libs.
 * All of the tags in the tag library should be a direct or indirect descendant of this class.
 */
public abstract class AbstractReevooTag extends SimpleTagSupport {

    /**
     * Default value of the trkref attribute will be the one in the configuration file.
     * But if the user includes the attribute in the jsp tag, the user defined value will
     * override the default one for that specific tag instance.
     */
    protected String trkref = TaglibConfig.getProperty("default.trkref");

    protected String sku = null;

    /**
     * Called automatically by the jps engine when it finds a tag that explicitly includes a "trkref" attribute.
     * @param trkref
     */
    public void setTrkref(String trkref) {
        if (trkref != null && !trkref.isEmpty()) {
            this.trkref = trkref;
        }
    }

    /**
     * Called automatically by the jsp engine when it finds a tag that explicitly includes a "sku" attribute.
     * @param sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

}
