<%@ attribute name="trkref" required="true" %>
<%@ attribute name="skus" required="true" %>
<%@ attribute name="value" required="true" %>

<script type="text/javascript" charset="utf-8">
  if (typeof afterReevooMarkLoaded === 'undefined') {
    var afterReevooMarkLoaded = [];
  }
  afterReevooMarkLoaded.push(
    function(){
      ReevooApi.load('${trkref}', function(retailer){
        retailer.track_purchase("${skus}".split(/[ ,]+/), "${value}");
      });
    }
  );
</script>


