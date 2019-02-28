<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

	<h2>Java</h2>

	<div class="collapse-group" id="collapseGroup1">
	
		<div class="controls">
			<div class="container">
				<div class="row">
					<div class="col-xs-4">
						<button class="btn btn-warning open-button" type="button">Open all</button>
					</div>
					<div class="col-xs-4 center">
						<button class="btn btn-primary close-info" type="button" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard'">Add</button>
					</div>
					<div class="col-xs-4">
						<button class="btn btn-success close-button" type="button">Close all</button>
					</div>			
				</div>
			</div>
		</div>

		 <c:forEach items="${requestScope.allFlashcards}" var="flashcard" varStatus="loop">
		 	<c:if test="${flashcard.stream eq 'Java'}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title trigger collapsed" data-toggle="collapse" data-target="#collapse${loop.index}">
							${flashcard.front}
							<span class="panelFrontRight">
								<span class="posNum">${flashcard.position}</span>
								<span class="glyphicon glyphicon-pencil editCardGlyph"
									data-cardId="${flashcard.id}">
								</span>
							</span>
						</h4>
					</div>
					<div id="collapse${loop.index}" class="panel-collapse collapse">
						<div class="panel-body">
							<div class="card-back-text">
								${flashcard.back}							
							</div>
						</div>
					</div>
				</div>	 	
		 	</c:if>		
		 </c:forEach>

	</div>

	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

</html>