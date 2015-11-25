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

<reevoo:purchaseTrackingEvent trkref="REV" skus="999,222,888" value="342.00"/>

<reevoo:propensityToBuyTrackingEvent trkref="REV" action="Requested_Brochure" sku="234"/>

<reevoo:propensityToBuyTrackingEvent trkref="REV" action="Searched_for_branch" />


<reevoo:javascriptAssets trkref="REV,CYS,EBU,PIU,HYU"/>
</body>
</html>
