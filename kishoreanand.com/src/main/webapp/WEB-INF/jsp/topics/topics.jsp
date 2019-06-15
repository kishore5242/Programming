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
	
		<sec:authorize access="isAuthenticated()">
			<div class="row">
				<div class="col-xs-6 leftControls">
				</div>
				<div class="col-xs-6 rightControls">
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/addTopic?stream_id=${requestScope.stream.stream_id}'" value="Add a new topic">
				</div>
			</div>
		</sec:authorize>
	
		<h2 class="notCard">${requestScope.stream.stream_name}</h2>
		<!-- <div class="controls">
			<div class="row cardControlRow">
				<div class="col-xs-12 center">
					
				</div>			
			</div>
		</div> -->
	
		<div class="row">
			<div class="col-xs-12 listMenu">
				<h3 class="notCard">Topics:</h3>
				<c:forEach items="${requestScope.topics}" var="topic" varStatus="loop">
					<div class="topicRow">
						<sec:authorize access="isAuthenticated()">
	    					<div class="cardControl">
						 		<span class="posNum">${topic.pos}</span>
						 		<span class="glyphicon glyphicon-edit editTopicGlyph" data-topicId="${topic.topic_id}"></span>
					 		</div>
						</sec:authorize>
						<div>
							<a href="${pageContext.request.contextPath}/cards?topic_id=${topic.topic_id}" class="listMenuItem">${topic.topic_name}</a>
						</div>					
					</div>
				 </c:forEach>				
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>


<script type="text/javascript">

$(document).on("click", ".editTopicGlyph", function () {
	var topic_id = $(this).attr("data-topicId");
	$('#loading').show();
	window.open(ctx + "/editTopic?topic_id="+topic_id, "_self");
});

</script>

</html>