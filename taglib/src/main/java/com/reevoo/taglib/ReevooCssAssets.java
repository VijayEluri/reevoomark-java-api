package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Tag for adding the reevoo css link.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:cssAssets/>
 */
public class ReevooCssAssets extends AbstractReevooTag {

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(String.format(
                "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\" />",
                TaglibConfig.getProperty("reevoo.css.url")));
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

}
