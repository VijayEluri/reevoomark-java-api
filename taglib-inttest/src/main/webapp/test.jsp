<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test page</title>
    <reevoo:cssAssets/>
    <!--[if lte IE 8]><script src="//cdn.mark.reevoo.com/assets/ie8.js"></script><![endif]-->
</head>

<body>
<h1>Test page</h1>

<h2>Valid:</h2>
<reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews"/>

<h2>Valid(slim):</h2>
<reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews/slim.html"/>

<h2>Valid(AAO):</h2>
<reevoo:mark sku="167823" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_conversations"/>

<h2>404:</h2>
<reevoo:mark sku="no-a-real-sku" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews.html"/>

<h2>Connect Failure:</h2>
<reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com:1/reevoomark/embeddable_reviews.html"/>
<reevoo:javascriptAssets trkref="REV"/>

<h2>ReevooMark Badge with default trkref</h2>
<reevoo:productBadge sku="10"/>

</body>
</html>
