package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


/**
 * Tag for adding a purchase tracking event to the page.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:purchaseTrackingEvent trkref="REV" skus="999,222,3373" value="342.00"/>
 */
public class ReevooPurchaseTrackingEvent extends AbstractReevooTag {

    /**
     * This variable will be populated automatically with the value of the "skus" attribute, if
     * such attribute is found in the jsp tag, or left empty otherwise. The value will be a comma
     * separated string with all the skus that have been purchased.
     */
    protected String skus = null;

    /**
     * This variable will be populated automatically with the value of the "value" attribute, if
     * such attribute is found in the jsp tag, or left empty otherwise. The value will be the total
     * value of all the products purchased.
     */
    protected String value = null;


    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(
                String.format(TaglibConfig.getPurchaseTrackingScript(), trkref, skus, value)
            );
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    /**
     * Called automatically by the jsp engine when it finds a tag that explicitly includes a "skus" attribute.
     * @param skus
     */
    public void setSkus(String skus) {
        this.skus = skus;
    }

    /**
     * Called automatically by the jsp engine when it finds a tag that explicitly includes a "value" attribute.
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
