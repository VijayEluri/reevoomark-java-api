package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Superclass for all badge type tags which contains common functionality to all
 * badge type tags.
 */
public class AbstractBadgeTag extends AbstractReevooTag {


    // will be initialized to the value of teh "variantName" attribute in the jsp tag.
    private String variantName;

    // will be initialized to the html contained in between the opening and closing of the jsp tag.
    protected String jspBody = "";

    public void doTag() throws JspException {
        try {
            StringWriter bodyContent = new StringWriter();
            if (getJspBody() != null) {
                getJspBody().invoke(bodyContent);
                jspBody = bodyContent.toString();
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
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


}
