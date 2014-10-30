package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

import javax.swing.text.html.HTML;

/**
 * Tag for adding embeddable conversations.
 *
 * Usage:
 *
 *      <reevoo:conversations sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:conversations trkref="REV" sku="167823"/>
 *
 */
public class ReevooConversations extends AbstractReevooMarkClientTag {

    // will be initialized to the value of the "numberOfConversations" attribute in the jsp tag.
    protected String numberOfConversations;

    /**
     * Method called automatically by the jps engine to set the value of the
     * "numberOfConversations" attribute in the jsp tag
     * @param numberOfConversations
     */
    public void setNumberOfConversations(String numberOfConversations) {
        this.numberOfConversations = numberOfConversations;
    }


    @Override
    protected String getContent() {
        queryStringParams.put("conversations", this.numberOfConversations);
        return client.obtainReevooMarkData(TaglibConfig.getProperty("conversations.url"), queryStringParams);
    }

}
