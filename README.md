# reevoomark-java-api

[![Build Status](https://api.travis-ci.org/reevoo/reevoomark-java-api.png)](https://travis-ci.org/reevoo/reevoomark-java-api)

## Description

The ReevooMark-Java-Api is a Java tag library for ReevooMark customers who want to quickly and easily integrate Reevoo content into their sites server-side.

## Other Languages

Tag libraries are also available for [.NET](https://github.com/reevoo/reevoomark-dotnet-api), [Ruby](https://github.com/reevoo/reevoomark-ruby-api) and [PHP](https://github.com/reevoo/reevoomark-php-api).

## Features

* Server-side inclusion of Reevoo content.
* Included CSS for display of Reevoo content.
* Server-side caching of content that respects the cache control rules set by Reevoo.
* Product and customer experience rating and conversation badges.
* Purchase tracking.

## Support

ReevooMark support can be obtained by emailing <operations@reevoo.com>.

There is also a [bug tracker](http://github.com/reevoo/reevoomark-java-api/issues) available.

## Installation

The easiest way to get the latest compiled version of the Reevoo taglib is to download it from sourceforge:

* Download the file from [sourceforge](https://sourceforge.net/projects/reevoomarkjavaapi/files/)
* Add this file to your projects classpath

If you use Maven for your project add the following dependency to your `pom.xml`:

```XML
  <dependency>
    <groupId>com.reevoo.taglib</groupId>
    <artifactId>reevoo-taglib</artifactId>
    <version>1.21</version>
  </dependency>
```

## Configuration

#### Global Tracking Reference

The different jsp tags allow you to explicitly specify your tracking reference (trkref) as an attribute every time you use one of them. But if you prefer, you can set the trkref value in a global property, in which case you would not have to specify it each time you use one of the jsp tags.

If you want to set the trkref value as a global property, you need to create a file named "reevooTablibConfig.properties" and place this file somewhere within you web application classpath. You can set your trkref value in a property within this file by the name "default.trkref".

For example, if your trkref is "REV" and you want to set it as a global property, create the "reevooTablibConfig.properties" file and add the following property to it.

```
  default.trkref=REV
```

Once you have done this, you can use all the jsp tags offered by the library without the need to specify the trkref attribute each time. For example you would use 

```JSP
  <reevoo:javascriptAssets/>
```

instead of

```JSP
  <reevoo:javascriptAssets trkref="REV"/>
```


#### Proxy Settings

To display embedded reviews, the tag library will need to make an httpClient request to our "mark.reevoo.com" domain from within your application server box. You need to make sure this domain is reachable from within the box. If you use a proxy you need to set the proxy host and port in the "reevooTaglibConfig.properties" file.

If you've already created the "reevooTaglibConfig.properties" file to add the "default.trkref" property as detailed in the previous section, then you just need to add the following two extra properties below. Otherwise, if you had not created the file before, just create it and add to it the two properties below. Make sure the file is added to the classpath of your web application.

```
  http.proxyHost=www.myproxyhost.com
  http.proxyPort=9999
``` 

Make sure to replace "www.myproxyhost.com" and "9999" for the real values of your proxy.

## Implementation

In any JSP page where you will be using Reevoo tags please ensure to include the taglib:

```JSP
  <%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
```

You should include the Reevoo specific CSS in your html header using the tag below

```JSP
 <reevoo:cssAssets/>
```

You should include the Reevoo specific JavaScript **at the bottom of your html body, just before closing the body tag**, using the tag below

```JSP
  <reevoo:javascriptAssets/>
```

As before you may set an explicit TRKREF if you haven't set the default one in the properties file.

```JSP
  <reevoo:javascriptAssets trkref="WAS" />
```

It also has support for multiple TRKREF'S.

```JSP
  <reevoo:javascriptAssets trkref="WAS,CYS,REV" />
```

### Standard Badges

#### Product Badge

To render "product badges" you can use any of the below.
The ```sku``` is compulsory but ```trkref``` and ```variantName``` are optional.

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:productBadge sku="<SKU>" />
  <reevoo:productBadge sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:productBadge sku="<SKU>" variantName="undecorated"/>
  <reevoo:productBadge sku="<SKU>" trkref="<TRKREF>" variantName="stars_only"/>
```

#### Conversations Badge

To render "conversations badges" you can use any of the below.
The ```sku``` is compulsory but ```trkref``` and ```variantName``` are optional.

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:conversationsBadge sku="<SKU>" />
  <reevoo:conversationsBadge sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:conversationsBadge sku="<SKU>" variantName="undecorated"/>
  <reevoo:conversationsBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Series Badges

#### Product Badges

To render "product series badges" you can use any of the below.
The ```sku``` is compulsory and should be set to the series id. The ```trkref``` and ```variantName``` are optional.

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:productSeriesBadge sku="<SKU>" />
  <reevoo:productSeriesBadge sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:productSeriesBadge sku="<SKU>" variantName="undecorated"/>
  <reevoo:productSeriesBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

#### Conversations Badges

To render "conversation series badges" you can use any of the below.
The ```sku``` is compulsory and should be set to the series id. The ```trkref``` and ```variantName``` are optional.

Make sure to replace `<SKU>`,`<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:conversationSeriesBadge sku="<SKU>" />
  <reevoo:conversationSeriesBadge sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:conversationSeriesBadge sku="<SKU>" variantName="undecorated"/>
  <reevoo:conversationSeriesBadge sku="<SKU>" trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Overall Service Rating Badges

To render "Overall Service Rating badges" you can use any of the below.
The ```trkref``` and ```variantName``` are optional.

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:overallServiceRatingBadge/>
  <reevoo:overallServiceRatingBadge trkref="<TRKREF>"/>
  <reevoo:overallServiceRatingBadge variantName="undecorated"/>
  <reevoo:overallServiceRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Customer Service Rating Badges

To render "Customer Service Rating badges" you can use any of the below.
The ```trkref``` and ```variantName``` are optional.

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:customerServiceRatingBadge/>
  <reevoo:customerServiceRatingBadge trkref="<TRKREF>"/>
  <reevoo:customerServiceRatingBadge variantName="undecorated"/>
  <reevoo:overallServiceRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Delivery Rating Badges

To render "Delivery Rating badges" you can use any of the below.
The ```trkref``` and ```variantName``` are optional.

Make sure to replace `<TRKREF>` and `<VARIANT_NAME>` with the appropriate values.

```JSP
  <reevoo:deliveryRatingBadge/>
  <reevoo:deliveryRatingBadge trkref="<TRKREF>"/>
  <reevoo:deliveryRatingBadge variantName="undecorated"/>
  <reevoo:deliveryRatingBadge trkref="<TRKREF>" variantName="<VARIANT_NAME>"/>
```

### Embedded Product Review Content

To render "embedded review content" you can use any of the below.
The ```sku``` attribute is compulsory but ```trkref```, ```locale```, ```numberOfReviews```  and ```paginated``` are optional.

Make sure to replace `<SKU>` and `<TRKREF>`, `<LOCALE>` and `<NUMBEROFREVIEWS>` with the appropriate values.
Any combination of the optional attributes is possible. Below we show some examples:

```JSP
  <reevoo:productReviews sku="<SKU>" />
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" locale="<LOCALE>" />
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" locale="en-GB" numberOfReviews="<NUMBEROFREVIEWS>"/>
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" numberOfReviews="<NUMBEROFREVIEWS>" paginated="true"/>
```

If you set the ```paginated``` attribute to true, the embedded reviews will show pagination links.


If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:productReviews sku="<SKU>">
    <p>Sorry we don't have any reviews available right now</p>
  </reevoo:productReviews>
```
### Embedded Customer Experience Review Content

To render "embedded customer experience review content" you can use any of the below.
The ```trkref```, ```locale```, ```numberOfReviews``` and ```paginated``` a attributes are optional.


Make sure to replace `<TRKREF>`, `<LOCALE>` and `<NUMBEROFREVIEWS>` with the appropriate values.
Any combination of the optional attributes is possible. Below we show some examples:

```JSP
  <reevoo:customerExperienceReviews />
  <reevoo:customerExperienceReviews trkref="<TRKREF>"/>
  <reevoo:customerExperienceReviews numberOfReviews="<NUMBEROFREVIEWS>"/>
  <reevoo:customerExperienceReviews locale="<LOCALE>"/>
  <reevoo:customerExperienceReviews trkref="<TRKREF>" locale="en-GB" numberOfReviews="<NUMBEROFREVIEWS>"/>
  <reevoo:customerExperienceReviews trkref="<TRKREF>" numberOfReviews="<NUMBEROFREVIEWS>" paginated="true"/>
```

If you set the ```paginated``` attribute to true, the embedded reviews will show pagination links.


If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:customerExperienceReviews sku="<SKU>">
    <p>Sorry we don't have any customer experience reviews available right now</p>
  </reevoo:customerExperienceReviews>
```

### Embedded Conversation Content

To render "embedded conversations content" you can use any of the below.
The ```sku``` attribute is compulsory but ```trkref``` and ```locale``` are optional.

Make sure to replace `<SKU>` , `<TRKREF>` and `<LOCALE>` with the appropriate values.
Any combination of the optional attributes is possible. Below we show some examples:

```JSP
  <reevoo:conversations sku="<SKU>" />
  <reevoo:conversations sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:conversations sku="<SKU>" trkref="<TRKREF>" locale="<LOCALE>"/>
```

If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:conversations sku="<SKU>">
    <p>Sorry we don't have any conversations available right now</p>
  </reevoo:conversations>
```


### Generic Mark Embeddable Content Tag

There is a generic tag that allows to specify the base url to call on the Reevoo server for generic embeddable content that is
not provided by any of the previous tags. The tag name is "mark" and you can use it in the following way:

Make sure to replace `<SKU>` and `<TRKREF>` with the appropriate values:

```JSP
  <reevoo:mark sku="<SKU>" trkref="<TRKREF>" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews.html" />
```

It is also possible to specify locale and the number of reviews you'd like in the baseURI:

```JSP
  <reevoo:mark sku="<SKU>" trkref="<TRKREF>" baseURI="http://mark.reevoo.com/reevoomark/fr-FR/10/embeddable_reviews.html" />
```

## Tracking

If you display the reviews in a tabbed display, or otherwise require visitors to your site to click an element before
seeing the embedded reviews, add the following onclick attribute to track the clickthroughs:

If your trkref value is for example "REV" you would add:

```HTML
  onclick="ReevooMark_REV.track_click_through(‘<SKU>’)”
```

If your trkref value is for example "PIU" you would add:

```HTML
  onclick="ReevooMark_PIU.track_click_through(‘<SKU>’)”
```

See how in examples above you need to put your trkref value as a suffix to the ReevooMark_ bit. Also remember to replace <SKU> by the sku of the product to which the reviews belong.

### Purchase Tracking

If your site includes online shopping functionality add Reevoo's purchase tracking to your store by including the following tag in your "Order Confirmation Page":

``` html
<reevoo:purchaseTrackingEvent trkref="REV" skus="999,222,3373" value="342.00"/>
```

In the tag above:
* Make sure to replace the value of the "trkref" attribute with your own trkref.
* Replace the value of the "skus" attribute with a comma separated list of all the skus that have been purchased as part of the order.
* Replace the value of the "value" attribute with the total value of the order, you don't need to include currency symbol.

All this tracking information will be available to you on your Reevoo Analytics account.

### Propensity to Buy Tracking

This type of tracking is used as a substitute of purchase tracking, detailed in the section above, for retailers that do not offer online purchase in their stores and therefore do not have an order confirmation page.

These retailers can use <reevoo:propensityToBuyTrackingEvent>, which can be added to any page they wish on the site.

To add a propensity to buy event to a page use the following tag:

``` html
<reevoo:propensityToBuyTrackingEvent trkref="REV" action="Requested Brochure" sku="234"/>
```

In the tag above:
* Make sure to replace the value of the "trkref" attribute with your own trkref.
* Replace the value of the "action" attribute with a string desccribing the type of event that you want to track, can be anything you want like "user visited the buy now page" or "user requested brochure" or "user requested a test drive", etc...
* The "sku" attribute is optional, you only have to include it if you want to link the tracking event to a specific product sku, otherwise just leave it empty.

All this tracking information will be available to you on your Google Analytics account. 

### OSGI Support

Our tag library jar can be deployed as an OSGI bundle as well.

In order to do that download the jar from the following sourceforge link and deploy it into your OSGI container: https://sourceforge.net/projects/reevoomarkjavaapi/files/latest/download?source=files

After deploying  the bundle, your client WAB bundle will need to require it by adding the following lines to the MANIFEST.MF file:

```
Import-Package:javax.servlet,javax.servlet.http
Require-Bundle: com.reevoo.taglib.reevoo-taglib
Require-TldBundle: com.reevoo.taglib.reevoo-taglib
```
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
