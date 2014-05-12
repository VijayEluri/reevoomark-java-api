package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

public class CustomerExperienceReviews extends AbstractReevooMarkClientTag {

    @Override
    protected String getContent() {
        return client.obtainReevooMarkData(trkref, null, TaglibConfig.getProperty("customer.experience.reviews.url"));
    }

}


