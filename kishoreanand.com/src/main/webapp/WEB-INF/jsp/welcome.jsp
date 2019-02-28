<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<jsp:include page="header/header.jsp" />

	<div class="container">
		<div class="welcomeDiv">
			<h1>${message}</h1>
			<h2>Here you can level up your java programming skills</h2>
			<br>
			<button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/cards'">Start &gt;&gt;</button>
		</div>
	</div>

	<jsp:include page="footer/footer.jsp" />
</body>
</html>
