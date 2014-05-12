<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1" %>
<html>
  <head>
    <title>Test page</title>
    <reevoo:assets trkref="REV" />
  </head>

  <body>
    <h1>Test page</h1>

    <h2>ReevooMark Badge</h2>
    <reevoo:productBadge trkref="REV" sku="10" />

    <h2>CX Badge</h2>
    <reevoo:customerExperienceBadge trkref="REV"/>

    <h2>Valid:</h2>
    <reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews" />

    <h2>Valid(slim):</h2>
    <reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews/slim.html" />

    <h2>Valid(AAO):</h2>
    <reevoo:mark sku="167823" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_conversations" />

    <h2>404:</h2>
    <reevoo:mark sku="no-a-real-sku" trkref="REV" baseURI="http://mark.reevoo.com/reevoomark/embeddable_reviews.html" />

    <h2>Connect Failure:</h2>
    <reevoo:mark sku="10" trkref="REV" baseURI="http://mark.reevoo.com:1/reevoomark/embeddable_reviews.html" />
  </body>
</html>
