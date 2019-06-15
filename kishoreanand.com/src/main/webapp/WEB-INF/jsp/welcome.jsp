<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript">
/////////////// remove 8443 from url /////////////////////////////////
if(window.location.href.includes("kishoreanand.com:8443")){
window.location.href = window.location.href.replace('kishoreanand.com:8443','kishoreanand.com');
}
//////////////////////////////////////////////////////////////////////
</script>
</head>
<body>
	<jsp:include page="header/header.jsp" />

	<div class="container">
		<div class="welcomeDiv">
			<h1 class="notCard">${message}</h1>
			<h2 class="notCard">Here you can add/maintain flashcards ${user}</h2>
			<br>
			<sec:authorize access="!isAuthenticated()">
				<div>
					<input type="button" class="btn btn-success navButton" onclick="window.location.href='${pageContext.request.contextPath}/streams'" value="Browse &gt;&gt;">
				</div>
				<div>
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/login'" value="Login">
				</div>
		    </sec:authorize>
		    <sec:authorize access="isAuthenticated()">
			    <div>
					<input type="button" class="btn btn-success navButton" onclick="window.location.href='${pageContext.request.contextPath}/streams'" value="My flashcards &gt;&gt;">
			    </div>
		    </sec:authorize>
		
		</div>
	</div>

	<jsp:include page="fc_tutorial.jsp" />

	<jsp:include page="footer/footer.jsp" />
</body>
</html>
