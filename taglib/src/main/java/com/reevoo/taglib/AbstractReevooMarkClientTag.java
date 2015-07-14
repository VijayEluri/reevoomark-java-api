package com.reevoo.taglib;

import com.reevoo.client.ReevooMarkClient;
import com.reevoo.utils.TaglibConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
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
    public int doStartTag() throws JspException {
        request = (HttpServletRequest)pageContext.getRequest();
        queryStringParams = buildQueryStringParamsMap();
        String content = getContent();
        try {
            if (content != null) {
                pageContext.getOut().write(content);
                return SKIP_BODY;
            } else {
                return EVAL_BODY_INCLUDE;
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
     * Builds and return a Map with all the query string parameters to be sent along with the url
     * in the HttpClient call.
     * @return
     */
    private Map<String,String> buildQueryStringParamsMap() {
        Map<String, String> queryStringParams = new LinkedHashMap<String,String>();
        queryStringParams.put("trkref", trkref);
        queryStringParams.put("sku",sku);
        queryStringParams.put("locale",this.locale);
        if (this.paginated) {
            queryStringParams.put("page", request.getParameter("reevoo_page"));
            queryStringParams.put("per_page", (this.numberOfReviews==null || this.numberOfReviews.trim().equals(""))?"default":this.numberOfReviews);
            queryStringParams.put("sort_by" , (request.getParameter("reevoo_sort_by")==null || request.getParameter("reevoo_sort_by").trim().equals(""))?"seo_boost":request.getParameter("reevoo_sort_by"));
            if (request.getParameter("reevoo_filter")!=null && !request.getParameter("reevoo_filter").trim().isEmpty()) {
                queryStringParams.put("filter", request.getParameter("reevoo_filter"));
            }
        } else {
            // for non paginated reviews the number of reviews to show is sent to the server
            // with the parameter "reviews" instead of the parameter "per_page".
            queryStringParams.put("reviews",this.numberOfReviews);
        }
        return queryStringParams;
    }

}
