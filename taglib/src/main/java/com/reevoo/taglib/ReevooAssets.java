package com.reevoo.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ReevooAssets extends SimpleTagSupport {

    protected static String ASSETLOADER = "<script id=\"reevoomark-loader\">\n" +
            "  (function() {\n" +
            "      var script = document.createElement('script');\n" +
            "      script.type = 'text/javascript';\n" +
            "      script.src = '//cdn.mark.reevoo.com/assets/reevoo_mark.js';\n" +
            "      var s = document.getElementById('reevoomark-loader');\n" +
            "      s.parentNode.insertBefore(script, s);\n" +
            "    })();\n" +
            "    afterReevooMarkLoaded = [];\n" +
            "    afterReevooMarkLoaded.push(function(){\n" +
            "        ReevooApi.load('%s', function(retailer){\n" +
            "          retailer.init_badges();\n" +
            "          retailer.init_reevoo_reputation_badges();\n" +
            "        });\n" +
            "    });\n" +
            "</script>\n" +
            "<link rel=\"stylesheet\" href=\"http://mark.reevoo.com/stylesheets/reevoomark/embedded_reviews.css\" type=\"text/css\" />";

    private String trkref;



    public void doTag() throws JspException {
        try {
            String content = String.format(ASSETLOADER,trkref);
            getJspContext().getOut().write(content);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public void setTrkref(String trkref) {
        this.trkref = trkref;
    }
}
