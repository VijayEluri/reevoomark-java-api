package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;

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

    /**
     * Builds the complete url to call through the ReevooMarkClient. The url is based on one of the properties
     * in the TaglibConfig with some substitutions applied to it depending on whether the user has specified
     * locale and number or reviews.
     *
     * @param urlPropertySelector Name of the property in TaglibConfig that provides the base url.
     * @return The complete url to call through ReevooMarkClient.
     */
    protected String buildUrl(String urlPropertySelector) {
        return String.format(TaglibConfig.getProperty(urlPropertySelector), getUrlLocaleComponent(), getUrlNumberOfReviewsComponent());
    }

    private String getUrlLocaleComponent() {
        String locale = "";
        if (this.locale != null && !this.locale.trim().isEmpty()) {
            locale = "/" + this.locale;
        }
        return locale;
    }

    private String getUrlNumberOfReviewsComponent() {
        String numberOfReviews = "/";
        if (this.numberOfReviews != null && !this.numberOfReviews.trim().isEmpty()) {
            numberOfReviews += this.numberOfReviews + "/";
        }
        return numberOfReviews;
    }


}
