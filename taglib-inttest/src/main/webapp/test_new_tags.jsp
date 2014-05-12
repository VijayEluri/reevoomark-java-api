<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
<head>
    <title>Test New Tags Page</title>
    <reevoo:assets trkref="REV" />
</head>

<body>
<h1>Test page</h1>

<h2>Valid product reviews using specific tag</h2>
<reevoo:productReviews sku="10"/>

<h2>Valid conversations using specific tag</h2>
<reevoo:conversations sku="167823"/>

<h2>Valid customer experience reviews</h2>
<reevoo:customerExperienceReviews/>






</body>
</html>
