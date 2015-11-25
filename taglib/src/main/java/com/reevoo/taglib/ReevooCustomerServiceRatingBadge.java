package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding an customer service rating badge.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:customerServiceRatingBadge/>  // will use the default.trkref set in the configuration properties file.
 * <reevoo:customerServiceRatingBadge trkref="REV"/>
 * <reevoo:customerServiceRatingBadge trkref="REV" variantName="undecorated"/>
 */
public class ReevooCustomerServiceRatingBadge extends AbstractBadgeTag {

    @Override
    public String getContent() {
        return String.format("<reevoo-customer-experience-badge trkref=\"%s\" score=\"customer_service\"%s>%s</reevoo-customer-experience-badge>",
            trkref, concatenateDynamicAttributes(), getBodyContentString());
    }

}

