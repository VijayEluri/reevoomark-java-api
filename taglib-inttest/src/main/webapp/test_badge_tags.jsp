<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<!DOCTYPE html>
<html>
<head>
  <title>Test New Tags Page</title>
  <reevoo:cssAssets/>
  <!--[if lte IE 8]><script src="//cdn.mark.reevoo.com/assets/ie8.js"></script><![endif]-->
</head>

<body>

<h2>ReevooMark Badge</h2>
<reevoo:productBadge sku="10"/>

<h2>Undecorated ReevooMark Badge</h2>
<reevoo:productBadge trkref="CYS" variant="undecorated" sku="006566">Click to see reviews</reevoo:productBadge>

<h2>ReevooMark Badge (variant="each_page")</h2>
<reevoo:productBadge trkref="EBU" variant="search_page" sku="582929"/>

<h2>Overall Service Rating Badge</h2>
<reevoo:overallServiceRatingBadge trkref="CYS"/>

<h2>Customer Service Rating Badge</h2>
<reevoo:customerServiceRatingBadge trkref="PIU"/>

<h2>Delivery Rating Badge</h2>
<reevoo:deliveryRatingBadge trkref="EBU"/>

<h2>Series Product Badge</h2>
<reevoo:productBadge trkref="HYU" sku="series:i20"/>

<h2>Conversation Badge</h2>
<reevoo:conversationsBadge trkref="REV" sku="167823"/>

<h2>Conversation Series Badge</h2>
<reevoo:conversationsBadge trkref="HYU" sku="series:i20"/>

<h2>Automotive product badge</h2>
<reevoo:productBadge trkref="FMC" manufacturer="ford" model="fiesta" modelVariant="studio" doors="3"/>

<h2>Automotive conversation Badge</h2>
<reevoo:conversationsBadge trkref="FMC" manufacturer="ford" model="fiesta" modelVariant="studio" doors="3"/>

<reevoo:javascriptAssets trkref="REV,CYS,EBU,PIU,HYU"/>
</body>
</html>
