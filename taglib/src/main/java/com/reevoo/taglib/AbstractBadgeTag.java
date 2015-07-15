package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import java.io.IOException;

/**
 * Superclass for all badge type tags which contains common functionality to all
 * badge type tags.
 */
public abstract class AbstractBadgeTag extends AbstractReevooTag {


    // will be initialized to the value of teh "variantName" attribute in the jsp tag.
    private String variantName;

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

    /**
     * Method called automatically by the jsp engine to set the value of the
     * "variantName" attribute in the jsp tag.
     * @param variantName
     */
    public void setVariantName(String variantName) {
        if (variantName != null && !variantName.trim().isEmpty()) {
            if (!variantName.endsWith("_variant")  && !variantName.equals("undecorated")) {
                this.variantName = variantName + "_variant";
            } else {
                this.variantName = variantName;
            }
        }
    }

    protected String getVariantName() {
        return variantName!=null && !variantName.trim().isEmpty()?" " + variantName:"";
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

}
