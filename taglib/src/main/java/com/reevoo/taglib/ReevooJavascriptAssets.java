package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


/**
 * Tag for adding markloader script.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:javascriptAssets/> // will use the default.trkref set in the configuration properties file.
 * <reevoo:javascriptAssets trkref="REV"/>
 * <reevoo:javascriptAssets trkref="REV,CYS"/> // you can specify multiple trkrefs separated by commas.
 */
public class ReevooJavascriptAssets extends AbstractReevooTag {

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(getMarkloaderScript());
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    private String getMarkloaderScript() {
        String script = TaglibConfig.getSingletrkrefMarkloaderScript();
        if (this.trkref.contains(",")) {
            script = TaglibConfig.getMultitrkrefMarkloaderScript();
        }
        return String.format(script, trkref);
    }

}
