package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


/**
 * Tag for adding a propensity to buy tracking events to the page.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:propensityToBuyTrackingEvent trkref="REV" action="Requested Brochure" sku="234"/>
 */
public class ReevooPropensityToBuyTrackingEvent extends AbstractReevooTag {

    /**
     * A label given by the tag user to the propensity to buy tracking event. Can be any string.
     */
    protected String action = null;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(
                String.format(TaglibConfig.getPropensityToBuyScript(), trkref, action,
                    sku != null && !sku.trim().equals("") ? sku : "Global CTA")
            );
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    /**
     * Called automatically by the jsp engine when it finds a tag that explicitly includes a "action" attribute.
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

}
