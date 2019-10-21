<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h3 class="notCard">Admin area</h3>
				<ul class="pageLinks">
					<sec:authorize access="isAuthenticated()">
		     			<li><a href="${pageContext.request.contextPath}/streams"><img alt="external" src="${pageContext.request.contextPath}/img/global/icons/pencil.svg">&nbsp;&nbsp;Manage my Flashcards</a></li>
		     		</sec:authorize>
		     		<sec:authorize access="hasAuthority('ADMIN')">
						<li><a href="${pageContext.request.contextPath}/blogadmin"><img alt="external" src="${pageContext.request.contextPath}/img/global/icons/pencil.svg">&nbsp;&nbsp;Manage public Blogs</a></li>
					</sec:authorize>
		     	</ul>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

$(document).on("click", ".editStreamGlyph", function () {
	var stream_id = $(this).attr("data-streamId");
	$('#loading').show();
	window.open(ctx + "/editStream?stream_id="+stream_id, "_self");
});

</script>

</html>