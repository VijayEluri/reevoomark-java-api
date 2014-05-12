package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public abstract class AbstractReevooMarkClientTag extends SimpleTagSupport {

    protected ReevooMarkClient client = new ReevooMarkClient(2000); //2s timeout
    protected String trkref = TaglibConfig.getProperty("trkref");
    protected String sku = null;

    @Override
    public void doTag() throws JspException {
        String content = getContent();
        try {
            if (content != null) {
                getJspContext().getOut().write(content);
            } else {
                if (getJspBody() != null) {
                    getJspBody().invoke(null);
                }
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    protected abstract String getContent();

    public void setSku(String sku) {
        this.sku = sku;
    }

    protected void setClient(ReevooMarkClient client) {
        this.client = client;
    }

}
