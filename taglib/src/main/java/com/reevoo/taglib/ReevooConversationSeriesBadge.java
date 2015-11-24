package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding a product badge.
 *
 * Usage:
 *
 *      <reevoo:conversationsSeriesBadge sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:conversationsSeriesBadge trkref="REV" sku="167823"/>
 *      <reevoo:conversationsSeriesBadge trkref="REV" sku="167823" variantName="undecorated">conversations series</reevoo:conversationsSeriesBadge>
 *
 */
public class ReevooConversationSeriesBadge extends AbstractBadgeTag {

    @Override
    public String getContent() {
        return String.format("<a class=\"reevoomark reevoo-conversations%s\" href=\"%s/partner/%s/series:%s\">%s</a>",
            getVariantName(),getBaseUrl(), trkref, dynamicAttrs.get("sku"), getBodyContentString());
    }

}
