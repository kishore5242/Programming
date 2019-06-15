<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<div class="container">

    <div class="starter-template">
        <h3 class="notCard">403 - Access is denied</h3>
        <div>Sorry <sec:authentication property="principal.username"/>! you do not have permission to access this page.</div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
