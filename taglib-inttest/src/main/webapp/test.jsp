<%@ taglib prefix="reevoo" uri="http://reevoo.com/java-taglib/v1.4" %>
<html>
  <head>
    <title>Test page</title>
    <link rel="stylesheet" href="http://mark.reevoo.com/stylesheets/reevoomark/embedded_reviews.css" type="text/css" media="screen" title="no title" charset="utf-8">
  </head>

  <body>
    <h1>Test page</h1>

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
