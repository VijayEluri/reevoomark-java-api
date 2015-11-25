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

<h2>NON-PAGINATED Product Reviews default number of reviews </h2>
<reevoo:productReviews trkref="REV" sku="10"  />

<h2>PAGINATED Product Reviews default number of reviews </h2>
<reevoo:productReviews trkref="REV" sku="10" paginated="true"/>

<h2>NON-PAGINATED Product Reviews custom number of reviews </h2>
<reevoo:productReviews trkref="REV" sku="10" numberOfReviews="6"  />

<h2>PAGINATED Product Reviews custom number of reviews </h2>
<reevoo:productReviews trkref="REV" sku="10" numberOfReviews="6" paginated="true"/>

<h2>PAGINATED with a locale </h2>
<reevoo:productReviews trkref="PIU" locale="es-ES" sku="349" numberOfReviews="6" paginated="true"/>


<h2>NON-PAGINATED with zero reviews</h2>
<reevoo:productReviews sku="10" locale="fr-FR" />

<h2>PAGINATED with zero reviews</h2>
<reevoo:productReviews sku="10" locale="fr-FR" />

<h2>Unsuccessful Product Reviews should show the fallback text</h2>
<reevoo:productReviews sku="10" locale="fr-FR" numberOfReviews="23">THIS IS THE FALLBACK TEXT</reevoo:productReviews>

<h2>AUTOMOTIVE</h2>
<reevoo:productReviews trkref="FMC" model="fiesta" doors="3" manufacturer="ford" paginated="true" modelVariant="studio" />

<reevoo:javascriptAssets trkref="FMC,REV,CYS,EBU,PIU,HYU"/>

</body>
</html>
