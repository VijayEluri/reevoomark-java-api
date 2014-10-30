package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

/**
 * Tag for adding embeddable customer experience reviews.
 *
 * Usage:
 *
 *      <reevoo:customerExperienceReviews/> // will use the default.trkref set in the configuration properties file.
 *      <reevoo:customerExperienceReviews trkref="CYS"/>
 *
 */
public class ReevooCustomerExperienceReviews extends AbstractReevooMarkClientTag {

    @Override
    protected String getContent() {
        return client.obtainReevooMarkData(TaglibConfig.getProperty("customer.experience.reviews.url"),
            queryStringParams, TaglibConfig.getProperty("customer.experience.reviews.custom"));
    }

}


