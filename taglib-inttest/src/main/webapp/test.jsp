<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
  <head>
    <title>Test page</title>
  </head>

  <body>
    <h1>Test page</h1>

    <h2>Valid:</h2>
    <reevoo:mark sku="10" trkref="REV" baseURI="http://mark.local/reevoomark/first_two_reviews.html" />

    <h2>404:</h2>
    <reevoo:mark sku="no-a-real-sku" trkref="REV" baseURI="http://mark.local/reevoomark/first_two_reviews.html" />

    <h2>Connect Failure:</h2>
    <reevoo:mark sku="10" trkref="REV" baseURI="http://mark.local:1/reevoomark/first_two_reviews.html" />
  </body>
</html>
