package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ReevooCustomerExperienceBadge extends SimpleTagSupport {

    private String trkref;

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(String.format("<a href=\"http://mark.reevoo.com/retailer/%s\" class=\"reevoo_reputation\"></a>",trkref));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public void setTrkref(String trkref) {
        this.trkref = trkref;
    }

}
