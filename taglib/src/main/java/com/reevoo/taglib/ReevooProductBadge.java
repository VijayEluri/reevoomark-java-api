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
        return String.format("<a class=\"reevoomark%s\" href=\"%s/partner/%s/%s\">%s</a>",
            getVariantName(), getBaseUrl(), trkref, dynamicAttrs.get("sku"), getBodyContentString());
    }

}
