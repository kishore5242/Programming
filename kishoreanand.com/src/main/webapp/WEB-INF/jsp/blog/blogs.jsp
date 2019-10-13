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
					<%-- <input type="button" class="btn navButton" onclick="window.location.href='${pageContext.request.contextPath}/addStream'" value="Add a new stream"> --%>
				</div>
				<div class="col-xs-6 rightControls">
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/blogadmin/addBlog'" value="Create Blog">
				</div>
			</div>
		</sec:authorize>
		<div class="row">
			<div class="col-xs-12">
				<h3 class="notCard">Blogs:</h3>

				<div class="listMenu">
					<c:forEach items="${requestScope.blogs}" var="blog" varStatus="loop">
						<div class="topicRow">
							<sec:authorize access="isAuthenticated()">
		    					<div class="cardControl">
							 		<span class="glyphicon glyphicon-edit editStreamGlyph" data-blogId="${blog.blog_id}"></span>
						 		</div>
							</sec:authorize>
							<div>
								<a href="${pageContext.request.contextPath}/blogadmin/posts/${blog.blog_id}" class="listMenuItem">${blog.blog_name}</a>
							</div>					
						</div>					
					 </c:forEach>
				</div>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

$(document).on("click", ".editStreamGlyph", function () {
	var blog_id = $(this).attr("data-blogId");
	$('#loading').show();
	window.open(ctx + "/blogadmin/editBlog/"+blog_id, "_self");
});

</script>

</html>