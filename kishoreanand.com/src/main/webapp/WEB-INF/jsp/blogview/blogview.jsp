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
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/blogadmin/addPost/${blog.blog_id}'" value="Post">
				</div>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="col-sm-3">
				<div class="listMenu">
					<div>
						<h3 class="notCard">${blog.blog_name}</h3>
					</div>
					<c:forEach items="${requestScope.posts}" var="post" varStatus="loop">
						<div class="topicRow">
							<sec:authorize access="hasAuthority('ADMIN')">
		    					<div class="cardControl">
		    						<span>pos:  ${post.position}  </span>
							 		<span class="glyphicon glyphicon-edit editGlyph" data-postId="${post.post_id}"></span>
						 		</div>
							</sec:authorize>
							<div>
								<c:choose>
								  <c:when test="${post.post_id eq displaypost.post_id}">
									<a href="${pageContext.request.contextPath}/bloguser/blog/${blog.blog_id}/${post.post_id}" class="listMenuItem selected">${post.post_name}</a> 
								  </c:when>
								  <c:otherwise>
									<a href="${pageContext.request.contextPath}/bloguser/blog/${blog.blog_id}/${post.post_id}" class="listMenuItem">${post.post_name}</a> 
								  </c:otherwise>
								</c:choose>
							</div>					
						</div>					
					 </c:forEach>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="moreInfo">
					<div class="thepost">
						<c:catch var="fileError">
							<c:import url="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/uploaded/${displaypost.post_html_path}"/>
						</c:catch>
						<c:if test="${not empty fileError}">
						  	<h3>No posts yet</h3>
						 </c:if>
					</div>
				</div>
				<div class="additionalInfo">
					<div>
				 		<span class="cardLeft"><b>Author: </b>${displaypost.post_author}</span> &nbsp;&nbsp;&nbsp;&nbsp;
				 		<span class="cardRight"><b>Modified: </b>${displaypost.post_modified}</span>
			 		</div>
				</div>
				<br>
				<jsp:include page="/WEB-INF/jsp/comment/client.jsp" />
				<br>
				<br>
				<br>
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