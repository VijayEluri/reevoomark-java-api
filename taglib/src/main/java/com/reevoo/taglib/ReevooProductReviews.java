package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

/**
 * Tag for adding embeddable product reviews.
 *
 * Usage:
 *
 *      <reevoo:productReviews sku="167823"/>  // will use the default.trkref set in the configuration properties file.
 *      <reevoo:productReviews trkref="REV" sku="167823"/>
 *
 */
public class ReevooProductReviews extends AbstractReevooMarkClientTag {

    @Override
    public String getContent() {
       return client.obtainReevooMarkData(trkref, sku, buildUrl(TaglibConfig.getProperty("product.reviews.url")));
    }

}
