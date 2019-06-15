<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<div class="container-fluid footerNav">
        <footer>
        <!-- this is footer -->
        <sec:authorize access="isAuthenticated()">
  			<span>
                <sec:authentication property="principal.username"/>
                <%-- | <sec:authentication property="principal.authorities"/> --%>
                | <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </span>
		</sec:authorize>

        </footer>
</div>

<script type="text/javascript">

	var ctx = '${pageContext.request.contextPath}';
	
	function openPage(uri) {
		$('#loading').show();
		window.open(ctx + uri, "_self");
	}
	
	
	$(".open-button").on("click", function() {
	  $(this).closest('.collapse-group').find('.collapse').collapse('show');
	});

	$(".close-button").on("click", function() {
	  $(this).closest('.collapse-group').find('.collapse').collapse('hide');
	});
	
	
	$('button[type="submit"]').on("click", function () {
		showLoader();
	});
	
	
</script>

</html>