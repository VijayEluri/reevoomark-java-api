package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.servlet.jsp.JspException;
import java.io.IOException;


/**
 * Tag for adding markloader script and css.
 *
 * Usage:
 *
 *      <reevoo:assets/> // will use the default.trkref set in the configuration properties file.
 *      <reevoo:assets trkref="REV"/>
 *      <reevoo:assets trkref="REV,CYS"/> // you can specify multiple trkrefs separated by commas.
 *
 */
public class ReevooAssets extends AbstractReevooTag {

    public void doTag() throws JspException {
        try {
            getJspContext().getOut().write(getMarkloaderScript());
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    private String getMarkloaderScript() {
        String script = TaglibConfig.getProperty("singletrackref.markloader");
        if (this.trkref.contains(",")) {
            script = TaglibConfig.getProperty("multitrackref.markloader");
        }
        return String.format(script,trkref);
    }

}
