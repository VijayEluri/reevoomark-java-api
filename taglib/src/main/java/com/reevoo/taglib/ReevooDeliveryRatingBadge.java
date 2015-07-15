package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding an delivery rating badge.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:deliveryRatingBadge/>  // will use the default.trkref set in the configuration properties file.
 * <reevoo:deliveryRatingBadge trkref="REV"/>
 * <reevoo:deliveryRatingBadge trkref="REV" variantName="undecorated"/>
 */
public class ReevooDeliveryRatingBadge extends AbstractBadgeTag {

    @Override
    public String getContent() {
        return String.format("<a href=\"%s/retailer/%s\" class=\"reevoo_reputation delivery%s\">%s</a>",
            getBaseUrl(), trkref, getVariantName(), getBodyContentString());
    }

}
