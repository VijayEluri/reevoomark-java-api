package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ReevooProductBadge extends SimpleTagSupport {

    private String trkref;
    private String sku;



    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(String.format("<a class=\"reevoomark\" href=\"http://mark.reevoo.com/partner/%s/%s\"></a>",trkref,sku));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public void setTrkref(String trkref) {
        this.trkref = trkref;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
