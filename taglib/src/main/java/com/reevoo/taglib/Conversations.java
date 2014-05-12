package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

public class Conversations extends AbstractReevooMarkClientTag {

    @Override
    protected String getContent() {
        return client.obtainReevooMarkData(trkref, sku, TaglibConfig.getProperty("conversations.url"));
    }

}
