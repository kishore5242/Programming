<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

    <%
        response.setStatus(301);
        response.setHeader("Location", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/?notfound");
        response.setHeader("Connection", "close");
    %>

<%-- 
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="alert alert-danger center">
					<img src="${pageContext.request.contextPath}/img/global/x.svg" class="error-img" alt="Error" >
					<strong>404</strong> <br><br>
					Page you are looking for does not exist!
				</div>
			</div>
		</div>
	</div>
 --%>
 
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>