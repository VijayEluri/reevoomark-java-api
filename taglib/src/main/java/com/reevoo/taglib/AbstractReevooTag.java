package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;

/**
 * Abstract superclass that contains common functionality to "all" of reevoo tag libs.
 * All of the tags in the tag library should be a direct or indirect descendant of this class.
 */
public abstract class AbstractReevooTag extends BodyTagSupport implements DynamicAttributes {

    /**
     * Default value of the trkref attribute will be the one in the configuration file.
     * But if the user includes the attribute in the jsp tag, the user defined value will
     * override the default one for that specific tag instance.
     */
    protected String trkref = TaglibConfig.getProperty("default.trkref");

    /**
     * Map with all the dynamic attributes.
     */
    protected Map<String,String> dynamicAttrs = new LinkedHashMap<String,String>();

    /**
     * Called automatically by the jps engine when it finds a tag that explicitly includes a "trkref" attribute.
     * @param trkref
     */
    public void setTrkref(String trkref) {
        if (trkref != null && !trkref.isEmpty()) {
            this.trkref = trkref;
        }
    }

   /**
    * Called automatically by the jsp engine when it finds a tag that explicitly includes a dynamic attribute.
    * @param uri
    * @param name This is the attribute name
    * @param value This is the attribute value.
    */
    public void setDynamicAttribute(String uri, String name, Object value) {
      dynamicAttrs.put(name, value.toString());
    }

    /**
     * Transforms attribute name from camel case to snake case.
     */
    protected String toCamelCase(String attributeName) {
      return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, attributeName);
    }

}
