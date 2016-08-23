/**
 * Copyright reevoo
 */
package com.reevoo.taglib;

/**
 * Taglib that acts as the bridge between the reevoo java template classes, designed
 * to return the reevoo include onto the page.
 */
public class ReevooTaglib extends AbstractReevooMarkClientTag {

    private String baseURI = "https://mark.reevoo.com/reevoomark/first_two_reviews.html";

    @Override
    protected String getContent() {
        return client.obtainReevooMarkData(baseURI, queryStringParams);
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }
}
