<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
<head>
  <title>Test New Tags Page</title>
  <reevoo:cssAssets/>
</head>

<body>

<h2>ReevooMark Badge</h2>
<reevoo:productBadge sku="10"/>

<h2>Undecorated ReevooMark Badge</h2>
<reevoo:productBadge trkref="CYS" variantName="undecorated" sku="006566">Click to see reviews</reevoo:productBadge>

<h2>ReevooMark Badge (variant="each_page")</h2>
<reevoo:productBadge trkref="EBU" variantName="search_page" sku="582929"/>

<h2>Overall Service Rating Badge</h2>
<reevoo:overallServiceRatingBadge trkref="CYS"/>

<h2>Customer Service Rating Badge</h2>
<reevoo:customerServiceRatingBadge trkref="PIU"/>

<h2>Delivery Rating Badge</h2>
<reevoo:deliveryRatingBadge trkref="EBU"/>

<h2>Series Product Badge</h2>
<reevoo:productSeriesBadge trkref="HYU" sku="i20"/>

<h2>Conversation Badge</h2>
<reevoo:conversationsBadge trkref="REV" sku="167823"/>

<h2>Conversation Series Badge</h2>
<reevoo:conversationSeriesBadge trkref="HYU" sku="i20"/>

<h2>NON-PAGINATED Product Reviews</h2>
<reevoo:productReviews sku="10" />

<h2>PAGINATED Product Reviews</h2>
<reevoo:productReviews sku="10" paginated="true" numberOfReviews="4"/>

<h2>Product Reviews with zero reviews</h2>
<reevoo:productReviews sku="10" locale="fr-FR" />

<h2>Unsuccessful Product Reviews should show the fallback text</h2>
<reevoo:productReviews sku="10" locale="fr-FR" numberOfReviews="23">THIS IS THE FALLBACK TEXT</reevoo:productReviews>


<h2>Conversations</h2>
<reevoo:conversations sku="167823"/>

<h2>Customer Experience Reviews</h2>
<reevoo:customerExperienceReviews trkref="CYS" numberOfReviews="10"/>

<h2>Tracking events</h2>

<reevoo:purchaseTrackingEvent trkref="REV" skus="999,222,888" value="342.00"/>

<reevoo:propensityToBuyTrackingEvent trkref="REV" action="Requested_Brochure" sku="234"/>

<reevoo:propensityToBuyTrackingEvent trkref="REV" action="Searched_for_branch" />


<reevoo:javascriptAssets trkref="REV,CYS,EBU,PIU,HYU"/>
</body>
</html>
