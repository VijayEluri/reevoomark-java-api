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


    protected String jspBody = "";

    protected String variantName;

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
