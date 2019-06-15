<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form action="/saveUser" method="post">
                <fieldset>
                    <!-- <h3 class="notCard">Account confirmation</h3> -->

                    <c:if test="${param.invalidToken ne null}">
                        <div class="alert alert-danger">
                            Invalid Token or Token might be expired. Please register again.
                        </div>
                        <br>
                        <a href="${pageContext.request.contextPath}/register">Register again</a>
                    </c:if>
                    <c:if test="${param.confirmed ne null}">
                        <div class="alert alert-success">
                            Account confirmed! Please login
                        </div>
                        <br>
		                <div>
							<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/login'" value="Login">
						</div>
                    </c:if>
					<c:if test="${param.registered ne null}">
                        <div class="alert alert-info">
                            Email has been sent to your address with confirmation link. Please click on that link to complete registration.
                        </div>
                    </c:if>
					<c:if test="${param.resetEmailSent ne null}">
                        <div class="alert alert-info">
                            Password reset link is sent to your Email address.
                        </div>
                    </c:if>                    
                    <c:if test="${param.passwordReset ne null}">
                        <div class="alert alert-info">
                        	Password reset successfully! Please login
                        </div>
                        <br>
		                <div>
							<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/login'" value="Login">
						</div>
                    </c:if>

                </fieldset>
            </form>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
