<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

	<div class="container">
	
		<sec:authorize access="hasAuthority('ADMIN')">
			<div class="row">
				<div class="col-xs-6 leftControls">
				</div>
				<div class="col-xs-6 rightControls">
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/blogadmin/addPost/${requestScope.blog.blog_id}'" value="Post">
				</div>
			</div>
		</sec:authorize>
	
		<h2 class="notCard">${requestScope.blog.blog_name}</h2>
	
		<div class="row">
			<div class="col-xs-12 listMenu">
				<h3 class="notCard">Posts:</h3>
				<c:forEach items="${requestScope.posts}" var="post" varStatus="loop">
					<div class="topicRow">
						<sec:authorize access="isAuthenticated()">
	    					<div class="cardControl">
	    						<span>pos:  ${post.position}  </span>
						 		<span class="glyphicon glyphicon-edit editGlyph" data-postId="${post.post_id}"></span>
					 		</div>
						</sec:authorize>
						<div>
							<a href="${pageContext.request.contextPath}/blogadmin/editPost/${post.post_id}" class="listMenuItem">${post.post_name}</a>
						</div>					
					</div>
				 </c:forEach>				
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>


<script type="text/javascript">

$(document).on("click", ".editGlyph", function () {
	var post_id = $(this).attr("data-postId");
	$('#loading').show();
	window.open(ctx + "/blogadmin/editPost/"+post_id, "_self");
});

</script>

</html>