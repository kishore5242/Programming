<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<div class="container">

	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="alert alert-danger center">
					<img src="${pageContext.request.contextPath}/img/global/x.svg" class="error-img" alt="Error" >
					<strong>403</strong> <br><br>
					<div>Sorry <sec:authentication property="principal.username"/>! you do not have permission to access this page.</div>
				</div>
			</div>
		</div>
	</div>

</div>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
