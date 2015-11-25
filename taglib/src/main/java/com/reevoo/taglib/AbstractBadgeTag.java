package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Superclass for all badge type tags which contains common functionality to all
 * badge type tags.
 */
public abstract class AbstractBadgeTag extends AbstractReevooTag {

    @Override
    public int doEndTag() throws JspException {
        String content = getContent();
        try {
            if (content != null) {
                pageContext.getOut().write(content);
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }

    protected String getBaseUrl() {
        return TaglibConfig.getProperty("reevoo.badges.base.url");
    }

    /**
     * Abstract method that should return the content to replace the jsp tab by when fully evaluated.
     * @return String
     */
    protected abstract String getContent();

    /**
     * Returns the content between the opening and closing of the tag in the jsp, if there is any.
     * @return String
     */
    protected String getBodyContentString() {
        BodyContent bodyContent = getBodyContent();
        return bodyContent != null ? bodyContent.getString() : "";
    }

    protected String concatenateDynamicAttributes() {
        StringBuilder tagBuilder = new StringBuilder();
        for (Map.Entry<String,String> entry : this.dynamicAttrs.entrySet()) {
          tagBuilder.append(String.format(" %s=\"%s\"", keyName(entry.getKey()), entry.getValue()));
        };
        return tagBuilder.toString();
    }

    /**
     * Some attributes have a different name in the jsp tag and in the web component so
     * we need to do the conversion for those.
     */
    private String keyName(String key) {
        if (key.equals("variantName")) {
            key = "variant";
        }
        return toCamelCase(key);
    }

}
