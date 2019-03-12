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
					<%-- <input type="button" class="btn navButton" onclick="window.location.href='${pageContext.request.contextPath}/addStream'" value="Add a new stream"> --%>
				</div>
				<div class="col-xs-6 rightControls">
					<input type="button" class="btn btn-info navButton" onclick="window.location.href='${pageContext.request.contextPath}/addStream'" value="Add a new stream">
				</div>
			</div>
		</sec:authorize>
		<div class="row">
			<div class="col-xs-12">
				<h3 class="notCard">Streams:</h3>

				<div class="listMenu">
					<c:forEach items="${requestScope.streams}" var="stream" varStatus="loop">
						<div class="topicRow">
							<sec:authorize access="isAuthenticated()">
		    					<div class="cardControl">
							 		<span class="posNum">${topic.pos}</span>
							 		<span class="glyphicon glyphicon-edit editStreamGlyph" data-streamId="${stream.stream_id}"></span>
						 		</div>
							</sec:authorize>
							<div>
								<a href="${pageContext.request.contextPath}/topics?stream_id=${stream.stream_id}" class="listMenuItem">${stream.stream_name}</a>
							</div>					
						</div>					
					 </c:forEach>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="moreInfo">
					<h4 class="notCard">Why Flashcards?</h4>
					<p class="info">Flashcards exercise the mental process of active recall
						<br><a href="https://en.wikipedia.org/wiki/Flashcard" target="_blank">more info</a>	
					</p>				
				</div>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

$(document).on("click", ".editStreamGlyph", function () {
	var stream_id = $(this).attr("data-streamId");
	$('#loading').show();
	window.open(ctx + "/editStream?stream_id="+stream_id, "_self");
});

</script>

</html>