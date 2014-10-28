package com.reevoo.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Utility class with HttpRequest related helper methods.
 */
public class RequestUtils {

    /**
     * Extracts and returns the exact url used by the client from the HttpServletRequest object.
     * @param request HttpServletRequest object.
     * @return String with the exact url used by the client that triggered the current request.
     */
    public static String buildClientRequestUrl(HttpServletRequest request) {
        String uri = "";
        try {
            uri = getHostFromRequest(request) +
                  getPortFromRequest(request)  +
                  (request.getRequestURI() != null? request.getRequestURI(): "") +
                  (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        } catch (Exception e) {}
        return uri;
    }

    /**
     * Extracts and returns the exact url used by the client from the HttpServletRequest object url encoded
     * in the provided encoding.
     * @param request
     * @return
     */
    public static String buildEncodedClientRequestUrl(HttpServletRequest request, String encoding) {
        String encodedClientUrl = "";
        try {
            encodedClientUrl = URLEncoder.encode(buildClientRequestUrl(request), encoding);
        } catch (Exception e) {};
        return encodedClientUrl;
    }

    /**
     * Returns the host part of the requet url, complete with protocol
     * @param request
     * @return
     */
    public static String getHostFromRequest(HttpServletRequest request) {
        return request.getScheme() + "://" +request.getServerName();
    }

    /**
     * Returns a string representation of the port used in the request url (including the colon symbol),
     * or empty if the default port for the protocol has been used.
     * @param request
     * @return
     */
    public static String getPortFromRequest(HttpServletRequest request) {
        return  ("http".equals(request.getScheme()) && request.getServerPort() == 80 ||
          "https".equals(request.getScheme()) && request.getServerPort() == 443) ? "" :
          ":" + request.getServerPort();
    }

}
