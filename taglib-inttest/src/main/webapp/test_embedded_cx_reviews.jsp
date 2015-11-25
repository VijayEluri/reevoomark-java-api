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

<h2>NON-PAGINATED CX Reviews default number of reviews </h2>
<reevoo:customerExperienceReviews trkref="PIU" />

<h2>PAGINATED CX Reviews default number of reviews </h2>
<reevoo:customerExperienceReviews trkref="PIU"  paginated="true"/>

<h2>NON-PAGINATED CX Reviews custom number of reviews </h2>
<reevoo:customerExperienceReviews trkref="PIU" numberOfReviews="6"  />

<h2>PAGINATED CX Reviews custom number of reviews </h2>
<reevoo:customerExperienceReviews trkref="PIU"  numberOfReviews="6" paginated="true"/>

<h2>PAGINATED CX Reviews Spanish locale </h2>
<reevoo:customerExperienceReviews trkref="PIU"  numberOfReviews="6" paginated="true" locale="es-ES"/>


<h2>Unsuccessful Product Reviews should show the fallback text</h2>
<reevoo:customerExperienceReviews trkref="PIU" numberOfReviews="23">THIS IS THE FALLBACK TEXT</reevoo:customerExperienceReviews>


<reevoo:javascriptAssets trkref="REV,CYS,EBU,PIU,HYU"/>
</body>
</html>

