<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-6">
				<h4>Please refer below flashcards to brush up your knowledge</h4>
				<ul class="listMenu">
					<li><a href="${pageContext.request.contextPath}/cards/java">Java</a></li>
					<%-- <li><a href="${pageContext.request.contextPath}/cards/javascript">JavaScript</a></li> --%>
					<%-- <li><a href="${pageContext.request.contextPath}/cards/spring">Spring</a></li> --%>
				</ul>
			</div>
			<div class="col-xs-6">
				<div class="moreInfo">
					<h4>Why Flashcards?</h4>
					<p class="info">Flashcards exercise the mental process of active recall
						<br><a href="https://en.wikipedia.org/wiki/Flashcard" target="_blank">more info</a>	
					</p>				
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>