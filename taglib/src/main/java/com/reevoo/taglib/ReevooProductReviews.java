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
        // The way the routes are setup in mark, when number of reviews are specified
        // then locale is compulsory. So in order to account for the user specifying
        // a number of reviews but not a locale, will setup the value of the locale
        // attribute to 'en-GB' by default.
        if ((this.locale == null || this.locale.trim().isEmpty()) &&
            this.numberOfReviews != null && !this.numberOfReviews.trim().isEmpty()) {
            this.locale = "en-GB";
        }
        return client.obtainReevooMarkData(buildUrl("product.reviews.url"), queryStringParams,
            TaglibConfig.getProperty("product.reviews.custom"));
    }

}
