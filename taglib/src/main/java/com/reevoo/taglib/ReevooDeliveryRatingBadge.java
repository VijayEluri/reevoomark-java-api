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
      return String.format("<reevoo-customer-experience-badge trkref=\"%s\" score=\"delivery\"%s>%s</reevoo-customer-experience-badge>",
          trkref, concatenateDynamicAttributes(), getBodyContentString());
    }

}
