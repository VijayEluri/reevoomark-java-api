package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding the reevoo css link.
 *
 * Usage:
 *
 *      <reevoo:cssAssets/>
 *
 */
public class ReevooCssAssets extends AbstractReevooTag {

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write("<link rel=\"stylesheet\" href=\"//mark.reevoo.com/stylesheets/reevoomark/embedded_reviews.css\" type=\"text/css\" />");
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}