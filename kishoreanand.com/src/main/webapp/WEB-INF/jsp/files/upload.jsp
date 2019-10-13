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
		<br>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<form class="upload-form" method="POST" action="${pageContext.request.contextPath}/files/upload" enctype="multipart/form-data">
				    <input type="file" class="upload-file" name="file" data-max-size="512000" /><br/><br/>
				    <input type="submit" value="Submit" />
				    
				    <input type="hidden" name="blog_id" id="blog_id" value="${blog_id}">
				    <input type="hidden" name="post_id" id="post_id" value="${post_id}">
				</form>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

</script>

</html>