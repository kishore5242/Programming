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
            <form action="${pageContext.request.contextPath}/login" method="post">
                <fieldset>
                    <h3 class="notCard">Login</h3>
                    
                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				      <div class="alert alert-danger">
				        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
				      </div>
				    </c:if>
                    
                    <c:if test="${param.logout ne null}">
                        <div class="alert alert-info">
                            You have been logged out.
                        </div>
                    </c:if>
                    <c:if test="${param.registered ne null}">
                        <div class="alert alert-success">
                            User registered successfully! Please login.
                        </div>
                    </c:if>

                    <div class="form-group">
                      	<label for="username">Email:</label>
                        <input type="text" name="username" id="username" class="form-control input-lg"
                               placeholder="Email Address" required="true" autofocus="true"/>
                    </div>
                    <div class="form-group">
                    	<label for="password">Password:</label>
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="Password" required="true"/>
                    </div>

                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-block btn-coffee" value="Sign In"/>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                        </div>
                    </div>
                </fieldset>
            </form>
            <br>
            <c:if test="${param.registered eq null}">
                <a class="navLink" href="${pageContext.request.contextPath}/register">New user? Register here</a>
            </c:if>
            <c:if test="${param.registered eq null}">
                <a class="navLink" href="${pageContext.request.contextPath}/reset-page">forgot password?</a>
            </c:if>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
