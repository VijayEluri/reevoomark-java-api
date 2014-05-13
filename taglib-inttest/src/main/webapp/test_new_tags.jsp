<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
<head>
    <title>Test New Tags Page</title>
    <reevoo:cssAssets/>
</head>

<body>

<h2>ReevooMark Badge with default trkref</h2>
<reevoo:productBadge sku="10" />

<h2>ReevooMark Badge with explicit trkref</h2>
<reevoo:productBadge trkref="CYS" sku="006566" />

<h2>Undecorated ReevooMark Badge</h2>
<reevoo:productBadge trkref="CYS" variantName="undecorated" sku="006566">Undecorated Body</reevoo:productBadge>

<h2>ReevooMark Badge with variant "anything"</h2>
<reevoo:productBadge trkref="CYS" variantName="anything" sku="006566"/>

<h2>Overall Service Rating Badge with default trkref</h2>
<reevoo:overallServiceRatingBadge/>

<h2>Overall Service Rating Badge with explicty trkref</h2>
<reevoo:overallServiceRatingBadge trkref="CYS"/>

<h2>Customer Service Rating Badge with default trkref</h2>
<reevoo:customerServiceRatingBadge/>

<h2>Customer Service Rating Badge with explicty trkref</h2>
<reevoo:customerServiceRatingBadge trkref="CYS"/>

<h2>Delivery Rating Badge with default trkref</h2>
<reevoo:deliveryRatingBadge/>

<h2>Delivery Rating Badge with explicty trkref</h2>
<reevoo:deliveryRatingBadge trkref="CYS"/>

<h2>Product Reviews with default trkref</h2>
<reevoo:productReviews sku="10"/>

<h2>Product Reviews with explicit trkref</h2>
<reevoo:productReviews trkref="CYS" sku="006566"/>

<h2>Conversations with default trkref</h2>
<reevoo:conversations sku="167823"/>

<h2>Conversations with explicit trkref</h2>
<reevoo:conversations trkref="CYS" sku="006566"/>

<h2>Customer Experience Reviews with default trkref</h2>
<reevoo:customerExperienceReviews/>

<h2>Customer Experience Reviews with explicity trkref</h2>
<reevoo:customerExperienceReviews trkref="CYS"/>

<h2>Series Product ReevooMark Badge</h2>
<reevoo:productSeriesBadge trkref="CYS" variantName="undecorated" sku="006566">Undecorated Body</reevoo:productSeriesBadge>


<reevoo:javascriptAssets trkref="REV,CYS" />
</body>
</html>
