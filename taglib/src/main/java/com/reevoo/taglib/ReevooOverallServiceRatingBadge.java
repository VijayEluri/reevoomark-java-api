package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding an overall service rating badge.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:overallServiceRatingBadge/>  // will use the default.trkref set in the configuration properties file.
 * <reevoo:overallServiceRatingBadge trkref="REV"/>
 * <reevoo:overallServiceRatingBadge trkref="REV" variantName="undecorated"/>
 */
public class ReevooOverallServiceRatingBadge extends AbstractBadgeTag {

    public void doTag() throws JspException {
        super.doTag();
        try {
            getJspContext().getOut().write(String.format("<a href=\"%s/retailer/%s\" class=\"reevoo_reputation%s\">%s</a>",
                    getBaseUrl(), trkref, getVariantName(), jspBody));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
