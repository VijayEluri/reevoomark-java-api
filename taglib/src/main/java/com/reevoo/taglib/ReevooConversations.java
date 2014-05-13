package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

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

    @Override
    protected String getContent() {
        return client.obtainReevooMarkData(trkref, sku, TaglibConfig.getProperty("conversations.url"));
    }

}
