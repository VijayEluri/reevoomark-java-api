package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding a product badge.
 *
 * Usage:
 *
 *      <reevoo:conversationsBadge sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:conversationsBadge trkref="REV" sku="167823"/>
 *      <reevoo:conversationsBadge trkref="REV" sku="167823" variantName="undecorated">conversations</reevoo:conversationsBadge>
 *
 */
public class ReevooConversationsBadge extends AbstractBadgeTag {

    @Override
    public String getContent() {
        return String.format("<a class=\"reevoomark reevoo-conversations%s\" href=\"%s/partner/%s/%s\">%s</a>",
            getVariantName(),getBaseUrl(),trkref,sku,getBodyContentString());
    }

}
