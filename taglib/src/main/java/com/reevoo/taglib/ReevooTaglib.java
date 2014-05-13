/**
 * Copyright reevoo
 */
package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Taglib that acts as the bridge between the reevoo java template classes, designed
 * to return the reevoo include onto the page.
 */
public class ReevooTaglib extends AbstractReevooMarkClientTag {

    private String baseURI = "http://mark.reevoo.com/reevoomark/first_two_reviews.html";

    @Override
    protected String getContent() {
      return client.obtainReevooMarkData(trkref, sku, baseURI);
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }
}
