#reevoomark-java-api

[![Build Status](https://api.travis-ci.org/reevoo/reevoomark-java-api.png)](https://travis-ci.org/reevoo/reevoomark-java-api)

##Description

The reevoomark-java-api is a Java tag library for ReevooMark and Reevoo Essentials customers who want to quickly and easily integrate Reevoo content in to their sites server-side.

##Other Languages
Tag libraries are also available for [.NET](https://github.com/reevoo/reevoomark-dotnet-api) and [PHP](https://github.com/reevoo/reevoomark-php-api).

##Features

* Server-side inclusion of Reevoo content.
* Included CSS for display of Reevoo content.
* Server-side caching of content that respects the cache control rules set by Reevoo.

##Support
For ReevooMark and Reevoo Essentials customers, support can be obtained by emailing <operations@reevoo.com>.

There is also a [bug tracker](http://github.com/reevoo/reevoomark-java-api/issues) available.

##Installation

As Github no longer supports hosting of binary archives. The easiest way to get the latest compiled version of the Reevoo taglib is to download it from sourceforge:

* Download the file from [sourceforge](https://sourceforge.net/projects/reevoomarkjavaapi/files/)
* Add this file to your projects classpath


If you use Maven for your project add the following dependency to your `pom.xml`:

``` xml
<dependency>
    <groupId>com.reevoo.taglib</groupId>
    <artifactId>reevoo-taglib</artifactId>
    <version>1.6</version>
</dependency>
```

##Configuration

The library comes with a number of default configuration values used by some or all of the different tags. This configuation values are:

``` html
default.trkref=REV
reevoo.badges.base.url=//mark.reevoo.com
product.reviews.url=http://mark.reevoo.com/reevoomark%s%sembeddable_reviews
conversations.url=http://mark.reevoo.com/reevoomark%s%sembeddable_conversations
customer.experience.reviews.url=http://mark.reevoo.comreevoomark%s%sembeddable_customer_experience_reviews
```

The url properties are to configure the different endpoint in the reevoo servers where the tags will connect to get the reviews content, and the customer will not need to change these values.

The "default.trkref" property however, is the trkref value that will be used by default every time the customer uses a tag without explicitly specifying the trkref attribute. For example, if the customer uses the following tag in their jsp page:

``` html
<reevoo:javascriptAssets/>
```

Then the markloader script will be initialized with the default.trkref value, which is "REV". The customer has the option of explicitly specifying the trckref value in the tag, like below:


``` html
<reevoo:javascriptAssets trkref="WAH"/>
```
or they can override the default trkref value by creating a properties file with the name "reevooTaglibConfig.properties" and overriding the "default.trkref" with their own trkref, for example if the customer trkref is WAH, the would put the following content in the file:

``` html
default.trkref=WAH
```

The customer needs to make sure the "reevooTaglibConfig.properties" file, if they decide to create one, is added to the classpath of their application server, for example by adding the file to the folder WEB-INF/classes of the web application where they are using the reevo tags library. If they do this they will not need to specify the "trkref" attribute explicitly in the different tags, the tags will use the value specified in the file by default.


##Implementation

In any jsp page where you will be using reevoo tags, make sure to include the taglib like below:

``` java
<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
```

Include the reevoo CSS like below:

``` html
 <reevoo:cssAssets/>
```

Include your customer specific Reevoo JavaScript:

``` html
<reevoo:javascriptAssets/>
```
or if you don't want to use the default trkref and want to specify it explicitly:

``` html
<reevoo:javascriptAssets trkref="WAS" />
```

or you can have multiple trkrefs if you need by adding them all separated by comma like below:

``` html
<reevoo:javascriptAssets trkref="WAS,CYS,REV" />
```

### Product Badges

To render "product badges" you can use any of the below, sku is compulsory but trkref and variantName are optional:

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropiate value.

``` html
<reevoo:productBadge sku="<SKU>" />
<reevoo:productBadge sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:productBadge sku="<SKU>" variantName="undecorated"/>
<reevoo:productBadge sku="<SKU>" trkref="<TRKREF>" variantName="stars_only"/>
```

To render "product series badges" you can use any of the below, sku is compulsory and should be set to the series id. The trkref and variantName are optional:

``` html
<reevoo:productSeriesBadge sku="<SKU>" />
<reevoo:productSeriesBadge sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:productSeriesBadge sku="<SKU>" variantName="undecorated"/>
<reevoo:productSeriesBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Conversations Badges

To render "conversations badges" you can use any of the below, sku is compulsory but trkref and variantName are optional:

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropiate value.

``` html
<reevoo:conversationsBadge sku="<SKU>" />
<reevoo:conversationsBadge sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:conversationsBadge sku="<SKU>" variantName="undecorated"/>
<reevoo:conversationsBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

To render "conversation series badges" you can use any of the below, sku is compulsory and should be set to the series id. The trkref and variantName are optional:

``` html
<reevoo:conversationSeriesBadge sku="<SKU>" />
<reevoo:conversationSeriesBadge sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:conversationSeriesBadge sku="<SKU>" variantName="undecorated"/>
<reevoo:conversationSeriesBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Overal Service Rating Badges

To render "Overal Service Rating badges" you can use any of the below, trkref and variantName are optional:

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropiate value.

``` html
<reevoo:overallServiceRatingBadge/>
<reevoo:overallServiceRatingBadge trkref="<TRKREF>"/>
<reevoo:overallServiceRatingBadge variantName="undecorated"/>
<reevoo:overallServiceRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Customer Service Rating Badges

To render "Customer Service Rating badges" you can use any of the below, trkref and variantName are optional:

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropiate value.

``` html
<reevoo:customerServiceRatingBadge/>
<reevoo:customerServiceRatingBadge trkref="<TRKREF>"/>
<reevoo:customerServiceRatingBadge variantName="undecorated"/>
<reevoo:overallServiceRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Delivery Rating Badges

To render "Delivery Rating badges" you can use any of the below, trkref and variantName are optional:

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropiate value.

``` html
<reevoo:deliveryRatingBadge/>
<reevoo:deliveryRatingBadge trkref="<TRKREF>"/>
<reevoo:deliveryRatingBadge variantName="undecorated"/>
<reevoo:deliveryRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```


### Embedded Review Content

Render embedded review content. Make sure to replace `<SKU>` and `<TRKREF>` with the appropriate values. The sku attribute is compulsory but trkref, locale and numberOfReviews are optional:

``` html
<reevoo:productReviews sku="<SKU>" />
<reevoo:productReviews sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:productReviews sku="<SKU>" locale="fr-FR"/>
<reevoo:productReviews sku="<SKU>" numberOfReviews="10"/>
<reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" locale="fr-FR" numberOfReviews="10"/>
```

If you would like to fall back to some content when reevoo content is not
available, just specify it within the tag:

``` java
<reevoo:productReviews sku="<SKU>">
  <p>Sorry we don't have any reviews available right now</p>
</reevoo:productReviews>
```

### Embedded Consversations Content

Render embedded conversations content. Make sure to replace `<SKU>` and `<TRKREF>` with the appropriate values. The sku attribute is compulsory but trkref, locale and numberOfReviews are optional:

``` html
<reevoo:conversations sku="<SKU>" />
<reevoo:conversations sku="<SKU>" trkref="<TRKREF>"/>
<reevoo:conversations sku="<SKU>" locale="fr-FR"/>
<reevoo:conversations sku="<SKU>" numberOfReviews="10"/>
<reevoo:conversations sku="<SKU>" trkref="<TRKREF>" locale="fr-FR" numberOfReviews="10"/>
```

If you would like to fall back to some content when reevoo content is not
available, just specify it within the tag:

``` java
<reevoo:conversations sku="<SKU>">
  <p>Sorry we don't have any conversations available right now</p>
</reevoo:conversations>
```

### Embedded Customer Experience Review Content

Render embedded customer experience review content. Make sure to replace ``<TRKREF>` with the appropriate values. The trkref and locale are optional:

``` html
<reevoo:customerExperienceReviews"/>
<reevoo:customerExperienceReviews trkref="<TRKREF>"/>
<reevoo:customerExperienceReviews locale="fr-FR"/>
<reevoo:customerExperienceReviews trkref="<TRKREF>" locale="fr-FR"/>
```


If you would like to fall back to some content when reevoo content is not
available, just specify it within the tag:

``` java
<reevoo:customerExperienceReviews sku="<SKU>">
  <p>Sorry we don't have any conversations available right now</p>
</reevoo:customerExperienceReviews>
```


### Generic Mark Embeddable Content Tag


There is a generic tag that allows to specify the base url to call on the reevoo server for generic embeddable content that is 
not provided by any of the previous tags. The tag name is "mark" and you can use it in the following way:

Make sure to replace `<SKU>` and `<TRKREF>` with the appropriate values:

``` java
<reevoo:mark sku="<SKU>" trkref="<TRKREF>" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews.html" />
```

It is also possible to specify locale and the number of reviews you'd like in the baseURI:

``` java
<reevoo:mark sku="<SKU>" trkref="<TRKREF>" baseURI="http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_reviews.html" />
```

If you would like to fall back to some content when reevoo content is not
available, just specify it within the tag:

``` java
<reevoo:mark sku="<SKU>" trkref="<TRKREF>" baseURI="http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_reviews.html">
  <p>Sorry we don't have any reviews available right now</p>
</reevoo:mark>
```

### Proxy Settings

If you would like to use a proxy server to display the content, you need to set both ```http.proxyHost``` and
```http.proxyPort``` when running your Java application. These are then used to pass proxy requests onto our
servers.

### Rendering Issues

Any changes to the visiblity settings of the 'traffic reviews solution' will require you to call the code below to ensure the correct formatting is applied.

NOTE: This assumes you are using the latest version of the Reevoo JS library.

``` javascript
ReevooMark.auto_scale()
```

## Tracking

If you display the reviews in a tabbed display, or otherwise require visitors to your site to click an element before seeing the embedded reviews, add the following onclick attribute to track the clickthroughs:

``` html
  onclick="ReevooMark.track_click_through(‘<SKU>’)”
```

## Overall rating

The overall rating section at the top of inline reviews contains an overall score, a summary and the score breakdowns.

##License

This software is released under the MIT license.  Only certified ReevooMark partners
are licensed to display Reevoo content on their sites.  Contact <sales@reevoo.com> for
more information.

(The MIT License)

Copyright (c) 2008 - 2010:

* [Reevoo](http://www.reevoo.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
