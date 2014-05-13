package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag for adding a product badge.
 *
 * Usage:
 *
 *      <reevoo:productBadge sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:productBadge trkref="REV" sku="167823"/>
 *
 */
public class ReevooProductBadge extends AbstractReevooTag {

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(String.format("<a class=\"reevoomark\" href=\"http://mark.reevoo.com/partner/%s/%s\"></a>",trkref,sku));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
