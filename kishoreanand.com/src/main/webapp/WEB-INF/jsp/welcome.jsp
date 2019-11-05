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
		
		<c:if test="${param.notfound != null}" >
			<div id="alertMessage" class="alertMessage">
				<br>
				<div class="alert alert-danger" role="alert">
				  <b>404</b> Page you were looking for do not exist!
				</div>
			</div>
			<script type="text/javascript">
				$(function() {
				    // setTimeout() function will be fired after page is loaded
				    // it will wait for 5 sec. and then will fire
				    // $("#successMessage").hide() function
				    setTimeout(function() {
				        $("#alertMessage").hide('blind', {}, 500)
				    }, 3000);
				});
			</script>
		</c:if>

		<div class="row">
			<h2 class="notCard">${message}</h2>
		</div>
		 
		<div class="row">
		  	<div class="col-sm-6">
				<div class="thumbnailDiv" onclick="window.location.href='${pageContext.request.contextPath}/streams'">
					<div class="thumbnailWrap">
						<img alt="flashcards" src="${pageContext.request.contextPath}/img/global/thumbnails/flashcards-tn.png">
					</div>
					<div><h4>Flashcards</h4></div>
				</div>
		 	</div>
		 	
			<c:forEach items="${sessionScope.blogs}" var="blog" varStatus="loop">
				<div class="col-sm-6">
					<div class="thumbnailDiv" onclick="window.location.href='${pageContext.request.contextPath}/bloguser/blog/${blog.blog_id}'">
						<div class="thumbnailWrap">
							<img alt="blog" src="${pageContext.request.contextPath}/img/global/thumbnails/blogger-tn.png">
						</div>
						<div><h4>${blog.blog_name}</h4></div>
					</div>
			 	</div>
			</c:forEach>
		 	
		</div>
		
		
	</div>

	<jsp:include page="footer/footer.jsp" />
</body>
</html>
