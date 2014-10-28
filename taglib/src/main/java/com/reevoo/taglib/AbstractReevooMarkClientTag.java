package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract superclass that contains common functionality to "all" of reevoo the tag libs which
 * need to use a <@link>ReevooMarkClient</@link> instance to get content from the reevoo servers.
 */
public abstract class AbstractReevooMarkClientTag extends AbstractReevooTag {

    protected ReevooMarkClient client = new ReevooMarkClient(
        Integer.valueOf(TaglibConfig.getProperty("http.timeout")),
        TaglibConfig.getProperty("http.proxyHost"),
        TaglibConfig.getProperty("http.proxyPort")
    );
    protected String locale;
    protected String numberOfReviews;
    protected boolean paginated;
    protected String pageNumber;
    protected Map<String,String> queryStringParams;

    @Override
    public void doTag() throws JspException {
        queryStringParams = getQueryStringParams();
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

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public void setPaginated(boolean paginated) { this.paginated = paginated; }

    protected void setClient(ReevooMarkClient client) {
        this.client = client;
    }

    protected abstract String getContent();

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

    protected String customParams(String custsomParamsPropertySelector) {
        return TaglibConfig.getProperty(custsomParamsPropertySelector);
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
        if (!this.paginated && this.numberOfReviews != null &&
            !this.numberOfReviews.trim().isEmpty()) {
            numberOfReviews += this.numberOfReviews + "/";
        }
        return numberOfReviews;
    }

    private Map<String,String> getQueryStringParams() {
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", trkref);
        queryStringParams.put("sku",sku);
        if (paginated) {
            queryStringParams.put("page", ((HttpServletRequest) ((PageContext) getJspContext()).
                getRequest()).getParameter("reevoo_page"));
            queryStringParams.put("per_page", numberOfReviews);
            try {
                queryStringParams.put("client_url", URLEncoder.encode(getClientUrl((HttpServletRequest)
                    ((PageContext) getJspContext()).getRequest()),"UTF-8"));
            } catch (Exception e) {}
        }
        return queryStringParams;
    }

    private String getClientUrl(HttpServletRequest request) {
        String uri = request.getScheme() + "://" + request.getServerName() +
            (("http".equals(request.getScheme()) && request.getServerPort() == 80 ||
                "https".equals(request.getScheme()) &&
                    request.getServerPort() == 443) ? "" : ":" + request.getServerPort()) +
            (request.getRequestURI() != null? request.getRequestURI(): "") +
            (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        return uri;
    }


}
