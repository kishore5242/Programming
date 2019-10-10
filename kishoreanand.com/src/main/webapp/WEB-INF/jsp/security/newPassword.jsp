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
            <form action="${pageContext.request.contextPath}/reset-password" method="post">
                <fieldset>
                    <h3 class="notCard">Enter your new password</h3>
                    
                    <c:if test="${param.invalidToken ne null}">
                        <div class="alert alert-danger">
                            Invalid token. Please request for a new one <a href="${pageContext.request.contextPath}/reset-page">here</a>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="Password" required="required" minlength="6"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control input-lg"
                               placeholder="Confirm Password" required="required"/>
                    </div>

					<input type="hidden" name="token" id="token" class="form-control input-lg" value="${requestScope.token}"/>

                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-block btn-coffee" value="Reset"/>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

</div>

<script type="text/javascript">

///////// password and confirm password ////////////

$('#password').on('change', function(){
	validatePassword();
});

$('#confirmPassword').on('keyup', function(){
	validatePassword();
});

function validatePassword() {
	var password = $('#password').val();
	var confirmPassword = $('#confirmPassword').val();
	
	if(password != confirmPassword){
		$('#confirmPassword').get(0).setCustomValidity("Passwords don't match");
	} else {
		$('#confirmPassword').get(0).setCustomValidity("");
	}
}

//////////////////////////////////////////////////

</script>

<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>
