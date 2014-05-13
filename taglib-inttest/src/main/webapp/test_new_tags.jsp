<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
<head>
    <title>Test New Tags Page</title>
    <reevoo:assets trkref="REV,CYS" />
</head>

<body>

<h2>ReevooMark Badge with default trkref</h2>
<reevoo:productBadge sku="10" />

<h2>ReevooMark Badge with explicit trkref</h2>
<reevoo:productBadge trkref="CYS" sku="006566" />

<h2>Customer Experience Badge with default trkref</h2>
<reevoo:customerExperienceBadge/>

<h2>Customer Experience Badge with explicty trkref</h2>
<reevoo:customerExperienceBadge trkref="CYS"/>

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

</body>
</html>
