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
		<sec:authorize access="hasRole('ADMIN')">
			<div class="row">
				<div class="col-xs-6 leftControls">
					<%-- <input type="button" class="btn navButton" onclick="window.location.href='${pageContext.request.contextPath}/addStream'" value="Add a new stream"> --%>
				</div>
				<div class="col-xs-6 rightControls">
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/blogadmin/addPost'" value="Post">
				</div>
			</div>
		</sec:authorize>

		<div class="row">
			<div class="col-xs-3">
				<div class="listMenu">
					<div>
						<h3 class="notCard">${blog.blog_name}</h3>
					</div>
					<c:forEach items="${requestScope.posts}" var="post" varStatus="loop">
						<div class="topicRow">
							<div>
								<a href="${pageContext.request.contextPath}/showPost?post_id=${post.post_id}" class="listMenuItem">${post.post_name}</a>
							</div>					
						</div>					
					 </c:forEach>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="moreInfo">
					<%-- 
					<h4 class="notCard">${post.post_name}</h4>
					<p class="info">
						This post is created by user - ${post.post_author}
					</p>	
					 --%>			
				</div>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">



</script>

</html>