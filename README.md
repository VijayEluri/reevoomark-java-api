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
    <version>1.6</version>
  </dependency>
```

## Configuration

The library comes with a number of default configuration values used by some or all of the different tags.

```
  default.trkref=REV
  reevoo.badges.base.url=//mark.reevoo.com
  product.reviews.url=http://mark.reevoo.com/reevoomark%s%sembeddable_reviews
  conversations.url=http://mark.reevoo.com/reevoomark%s%sembeddable_conversations
  customer.experience.reviews.url=http://mark.reevoo.comreevoomark%s%sembeddable_customer_experience_reviews
```

The url properties point to the various endpoints in the Reevoo architecture and should not require changes from
the customer.

You should change the ```default.trkref``` to your provided TRKREF given to by us. This will then be used throughout
your web server wherever you use a Reevoo asset without specifying an alternative TRKREF.

For example:

```JSP
  <reevoo:javascriptAssets/>
```

This will initialize our Reevoo JavaScript with the ```default.trkref```. However if you want to set it you can
with the following

```JSP
  <reevoo:javascriptAssets trkref="WAH"/>
```

You may also override the ```default.trkref``` by creating a properties file named: ```reevooTaglibConfig.properties```.
This file must be added to the classpath of your application server, for example by adding the file to the folder WEB-INF/classes.

```
  default.trkref=WAH
```

## Implementation

In any JSP page where you will be using Reevoo tags please ensure to include the taglib:

```JSP
  <%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
```

You should include the Reevoo specific CSS in your header

```JSP
 <reevoo:cssAssets/>
```

You should include the Reevoo specific JavaScript at the bottom of your body

```JSP
  <reevoo:javascriptAssets/>
```

As before you may set an explicit TRKREF if you wish.

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

### Embedded Review Content

To render "embedded review content" you can use any of the below.
The ```sku``` attribute is compulsory but ```trkref```, ```locale``` and ```numberOfReviews``` are optional.

If you wish to use ```numberOfReviews``` you must include ```locale``` as well.

Make sure to replace `<SKU>` and `<TRKREF>`, `<LOCALE>` and `<NUMBEROFREVIEWS>` with the appropriate values.

```JSP
  <reevoo:productReviews sku="<SKU>" />
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>"/>
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" locale="<LOCALE>" />
  <reevoo:productReviews sku="<SKU>" trkref="<TRKREF>" locale="<LOCALE>" numberOfReviews="<NUMBEROFREVIEWS>"/>
```

#### Overall rating

The overall rating section at the top of inline reviews contains an overall score, a summary and the score breakdowns.

#### Fallback

If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:productReviews sku="<SKU>">
    <p>Sorry we don't have any reviews available right now</p>
  </reevoo:productReviews>
```

### Embedded Conversation Content

To render "embedded conversations content" you can use any of the below.
The ```sku``` attribute is compulsory but ```trkref```is optional.

Make sure to replace `<SKU>` and `<TRKREF>` with the appropriate values.

```JSP
  <reevoo:conversations sku="<SKU>" />
  <reevoo:conversations sku="<SKU>" trkref="<TRKREF>"/>
```

#### Fallback

If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:conversations sku="<SKU>">
    <p>Sorry we don't have any conversations available right now</p>
  </reevoo:conversations>
```

### Embedded Customer Experience Review Content

To render "embedded customer experience review content" you can use any of the below.
The ```trkref``` and ```numberOfReviews``` are optional.

Make sure to replace `<TRKREF>` and `<NUMBEROFREVIEWS>` with the appropriate values.

```JSP
  <reevoo:customerExperienceReviews />
  <reevoo:customerExperienceReviews trkref="<TRKREF>"/>
  <reevoo:customerExperienceReviews numberOfReviews="<NUMBEROFREVIEWS>"/>
  <reevoo:customerExperienceReviews trkref="<TRKREF>" numberOfReviews="<NUMBEROFREVIEWS>"/>
```

#### Fallback

If you would like to fall back to some content when Reevoo content is not available, just specify it within the tag:

```JSP
  <reevoo:customerExperienceReviews sku="<SKU>">
    <p>Sorry we don't have any customer experience reviews available right now</p>
  </reevoo:customerExperienceReviews>
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

### Proxy Settings

If you would like to use a proxy server to display the content, you need to set both ```http.proxyHost``` and
```http.proxyPort``` when running your Java application. These are then used to pass proxy requests onto our
servers.

### Rendering Issues

Any changes to the visibility settings of the 'traffic reviews solution' will require you to call the code below to
ensure the correct formatting is applied.

NOTE: This assumes you are using the latest version of the Reevoo JS library.

```Javascript
  ReevooMark.auto_scale()
```

## Tracking

If you display the reviews in a tabbed display, or otherwise require visitors to your site to click an element before
seeing the embedded reviews, add the following onclick attribute to track the clickthroughs:

```HTML
  onclick="ReevooMark.track_click_through(‘<SKU>’)”
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
