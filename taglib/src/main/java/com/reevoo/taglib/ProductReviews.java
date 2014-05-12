package com.reevoo.taglib;

import com.reevoo.utils.TaglibConfig;

public class ProductReviews extends AbstractReevooMarkClientTag {

    @Override
    public String getContent() {
       return client.obtainReevooMarkData(trkref, sku, TaglibConfig.getProperty("product.reviews.url"));
    }

}
