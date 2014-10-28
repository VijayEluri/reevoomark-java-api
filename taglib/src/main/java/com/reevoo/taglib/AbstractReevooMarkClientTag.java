package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.RequestUtils;
import com.reevoo.utils.StringUtils;
import com.reevoo.utils.TaglibConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract superclass that contains common functionality to "all" of reevoo the tag libs which
 * need to use a <@link>ReevooMarkClient</@link> instance to get content from the reevoo servers.
 */
public abstract class AbstractReevooMarkClientTag extends AbstractReevooTag {

    // will be initialized to the value of the "locale" attribute in the jsp tag.
    protected String locale;

    // will be initialized to the value of the "numberOfReviews" attribute in the jsp tag.
    protected String numberOfReviews;

    // will be initialized to the value of the "paginated" attribute in the jsp tag.
    protected boolean paginated;

    // Object used to make http client request to Reevoo servers to get embedded content.
    protected ReevooMarkClient client;

    // Map to be initialized with all the query string parameters and their values to
    // be sent along with the http client request.
    protected Map<String,String> queryStringParams;

    private HttpServletRequest request;

    public AbstractReevooMarkClientTag() {
        this.client = new ReevooMarkClient(
            Integer.valueOf(TaglibConfig.getProperty("http.timeout")),
            TaglibConfig.getProperty("http.proxyHost"),
            TaglibConfig.getProperty("http.proxyPort")
        );
    }

    /**
     * Method called automatically by the jsp engine to set the value of
     * the "locale" attribute in the jsp tag.
     * @param locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Method called automatically by the jps engine to set the value of the
     * "numberOfReviews" attribute in the jsp tag
     * @param numberOfReviews
     */
    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    /**
     * Method called automatically by the jsp engine to set the value of the
     * "paginated" attribute in the jsp tag.
     * @param paginated
     */
    public void setPaginated(
        boolean paginated) { this.paginated = paginated;
    }


    @Override
    public void doTag() throws JspException {
        request = ((HttpServletRequest) ((PageContext) getJspContext()).getRequest());
        queryStringParams = buildQueryStringParamsMap();
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

    /**
     * Sets the reevoo http client to use. Needed only so we can mock this
     * object from the junit tests.
     * @param client
     */
    protected void setClient(ReevooMarkClient client) {
        this.client = client;
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
        return String.format(TaglibConfig.getProperty(urlPropertySelector),
            buildLocaleUrlComponent(), buildNumberOfReviewsUrlComponent());
    }

    private String buildNumberOfReviewsUrlComponent() {
        String numberOfReviewsUrlPart = "/";
        if (this.numberOfReviews != null &&
            !this.numberOfReviews.trim().isEmpty()) {
            numberOfReviewsUrlPart += this.numberOfReviews + "/";
        }
        return numberOfReviewsUrlPart;
    }

    private String buildLocaleUrlComponent() {
        String locale = "";
        if (this.locale != null && !this.locale.trim().isEmpty()) {
            locale = "/" + this.locale;
        }
        return locale;
    }

    /**
     * Builds and return a Map with all the query string parameters to be sent along with the url
     * in the HttpClient call.
     * @return
     */
    private Map<String,String> buildQueryStringParamsMap() {
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", trkref);
        queryStringParams.put("sku",sku);
        if (this.paginated) {
            queryStringParams.put("page", request.getParameter("reevoo_page"));
            queryStringParams.put("per_page", StringUtils.isEmpty(this.numberOfReviews)?"default":this.numberOfReviews);
            queryStringParams.put("client_url", RequestUtils.buildEncodedClientRequestUrl(request,"UTF-8"));
        }
        return queryStringParams;
    }


}
