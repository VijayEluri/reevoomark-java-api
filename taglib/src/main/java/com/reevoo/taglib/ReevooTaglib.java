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
public class ReevooTaglib extends SimpleTagSupport {

    private String sku = null;
    private String trkref = null;
    private String baseURI = "http://mark.reevoo.com/reevoomark/first_two_reviews.html";
    private ReevooMarkClient client = new ReevooMarkClient(2000); //2s timeout

    @Override
    public void doTag() throws JspException {
      String content = client.obtainReevooMarkData(trkref, sku, baseURI);
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
    
    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setTrkref(String trkref) {
        this.trkref = trkref;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    protected void setClient(ReevooMarkClient client) {
        this.client = client;
    }
}
