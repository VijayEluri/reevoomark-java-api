<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="trkref" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="sku" required="false" %>

<c:if test="${empty sku}">
  <c:set var="sku" value="Global CTA" />
</c:if>

<script type="text/javascript" charset="utf-8">
  if (typeof afterReevooMarkLoaded === 'undefined') {
    var afterReevooMarkLoaded = [];
  }
  afterReevooMarkLoaded.push(
    function(){
      ReevooApi.load('${trkref}', function(retailer){
        retailer.Tracking.ga_track_event("Propensity to buy", "${action}", "${sku}");
        retailer.track_exit();
      });
    }
  );
</script>

