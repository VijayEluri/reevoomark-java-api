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
 *
 * The "skus" attribute will be a comma separated string with all the skus that have been purchased.
 *
 * The "value" attribute will be the total value of all the products purchased.
 */
public class ReevooPurchaseTrackingEvent extends AbstractReevooTag {

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(
                String.format(TaglibConfig.getPurchaseTrackingScript(), trkref, dynamicAttrs.get("skus"), dynamicAttrs.get("value"))
            );
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
