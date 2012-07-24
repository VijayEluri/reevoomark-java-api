/**
 * Copyright reevoo
 */
package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Taglib that acts as the bridge between the reevoo java template classes, designed
 * to return the reevoo include onto the page.
 */
public class ReevooTaglib extends BodyTagSupport {

    private String sku = null;
    private String trkref = null;
    private String baseURI = "http://mark.reevoo.com/reevoomark/first_two_reviews.html";
    private ReevooMarkClient client = new ReevooMarkClient(2000); //2s timeout

    @Override
    public int doStartTag() throws JspException {
      String content = client.obtainReevooMarkData(trkref, sku, baseURI);
      if (content == null){
          return SKIP_BODY;
      }else{
          try{ pageContext.getOut().write(content); }catch(IOException e){ throw new JspException(e); }
          return EVAL_BODY_INCLUDE;
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
}
