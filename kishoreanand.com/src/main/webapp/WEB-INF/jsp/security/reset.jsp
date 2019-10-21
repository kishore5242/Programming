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
            <form action="${pageContext.request.contextPath}/reset-email" method="post">
                <fieldset>
                    <h3 class="notCard">Reset Password via Email</h3>

                    <c:if test="${param.noUsername ne null}">
                        <div class="alert alert-danger">
                            Account(Email) does not exist! <br>
                            <a class="navLink" href="${pageContext.request.contextPath}/register">Please click here to register with us</a>
                        </div>
                    </c:if>
                    <c:if test="${param.userNotEnabled ne null}">
                        <div class="alert alert-danger">
                            Account is not activated yet. Please check your inbox for activation email. 
                            <br>
                        	<b>Or </b> try to<a href="${pageContext.request.contextPath}/register"> register</a> once again
                        </div>
                        
                    </c:if>
                    <div class="form-group">
                        <input type="email" name="username" id="username" class="form-control input-lg"
                               placeholder="Enter your email" required="required"/>
                    </div>

                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-block btn-coffee" value="Confirm"/>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
