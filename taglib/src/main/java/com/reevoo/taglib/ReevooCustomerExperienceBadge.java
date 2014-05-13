package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag for adding a customer experience badge.
 *
 * Usage:
 *
 *      <reevoo:customerExperienceBadge/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:customerExperienceBadge trkref="REV"/>
 *
 */
public class ReevooCustomerExperienceBadge extends AbstractReevooTag {

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(String.format("<a href=\"http://mark.reevoo.com/retailer/%s\" class=\"reevoo_reputation\"></a>",trkref));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
