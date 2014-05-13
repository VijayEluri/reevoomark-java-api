package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding an customer service rating badge.
 *
 * Usage:
 *
 *      <reevoo:customerServiceRatingBadge/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:customerServiceRatingBadge trkref="REV"/>
 *      <reevoo:customerServiceRatingBadge trkref="REV" variantName="undecorated"/>
 *
 */
public class ReevooCustomerServiceRatingBadge extends AbstractBadgeTag {

    public void doTag() throws JspException {
        super.doTag();
        try {
            getJspContext().getOut().write(String.format("<a href=\"%s/retailer/%s\" class=\"reevoo_reputation customer_service%s\">%s</a>",
                    getBaseUrl(), trkref, getVariantName(),jspBody));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}

