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

    @Override
    public int doStartTag() throws JspException {
       try {
           pageContext.getOut().write(String.format(
               "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\" />",
               TaglibConfig.getProperty("reevoo.css.url")));
           return SKIP_BODY;
        } catch (IOException e) {
           throw new JspException(e);
        }
    }

}
