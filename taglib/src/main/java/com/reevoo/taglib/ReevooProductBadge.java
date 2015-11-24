package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding a product badge.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:productBadge sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 * <reevoo:productBadge trkref="REV" sku="167823"/>
 * <reevoo:productBadge trkref="REV" sku="167823" variantName="undecorated"/>
 */
public class ReevooProductBadge extends AbstractBadgeTag {

    @Override
    public String getContent() {
        return String.format("<reevoo-reviewable-badge trkref=\"%s\"%s>%s</reevoo-reviewable-badge>",
             trkref, concatenateDynamicAttributes(), getBodyContentString());
    }

}
