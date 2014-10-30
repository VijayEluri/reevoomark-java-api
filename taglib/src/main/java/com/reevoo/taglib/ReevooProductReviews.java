package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

/**
 * Tag for adding embeddable product reviews.
 * <p/>
 * Usage:
 * <p/>
 * <reevoo:productReviews sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 * <reevoo:productReviews trkref="REV" sku="167823"/>
 */
public class ReevooProductReviews extends AbstractReevooMarkClientTag {

    @Override
    public String getContent() {
        return client.obtainReevooMarkData(TaglibConfig.getProperty("product.reviews.url"),
            queryStringParams, TaglibConfig.getProperty("product.reviews.custom"));
    }

}
