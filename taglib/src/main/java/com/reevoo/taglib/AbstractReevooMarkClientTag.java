package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Abstract superclass that contains common functionality to "all" of reevoo the tag libs which
 * need to use a <@link>ReevooMarkClient</@link> instance to get content from the reevoo servers.
 */
public abstract class AbstractReevooMarkClientTag extends AbstractReevooTag {

    protected ReevooMarkClient client = new ReevooMarkClient(2000); //2s timeout
    protected String locale;
    protected String numberOfReviews;

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

    protected void setClient(ReevooMarkClient client) {
        this.client = client;
    }

    protected abstract String getContent();

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    protected String buildUrl(String url) {
        String urlLocale = this.locale!=null && !locale.trim().isEmpty()?("/" + locale ):"";
        String urlNumberOfReviews = this.numberOfReviews!=null && !numberOfReviews.trim().isEmpty()?("/" + numberOfReviews + "/"):"/";
        return String.format(url,urlLocale,urlNumberOfReviews);
    }



}
