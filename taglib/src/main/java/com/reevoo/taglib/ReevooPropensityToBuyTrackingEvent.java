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
 *
 * The "action" attribute is a label given by the tag user to the propensity to buy tracking event. Can be any string.
 */
public class ReevooPropensityToBuyTrackingEvent extends AbstractReevooTag {

    @Override
    public int doStartTag() throws JspException {
        try {
            String sku = dynamicAttrs.get("sku") ;
            pageContext.getOut().write(
                String.format(TaglibConfig.getPropensityToBuyScript(), trkref, dynamicAttrs.get("action"),
                    sku != null && !sku.trim().equals("") ? sku : "Global CTA")
            );
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
