<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
<script src="/js/jquery.ui.touch-punch.js"></script>
</head>
<body>



<div class="container">

	<div class="controls">
		<div class="row cardControlRow">
			<div class="col-xs-6 leftControls">
				<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/topics?stream_id=${requestScope.topic.stream.stream_id}'" value="&lt; ${requestScope.topic.stream.stream_name}">
			</div>
			<div class="col-xs-6 rightControls">
				<sec:authorize access="isAuthenticated()">
					<input type="button" class="btn btn-coffee navButton" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard?topic_id=${requestScope.topic.topic_id}'" value="Add a card &gt;">
				</sec:authorize>
			</div>			
		</div>
	</div>

	<div class="row cardControlRow">
		<div class="col-xs-12">
			<h2 class="notCard">${requestScope.topic.topic_name}</h2>
		</div>		
	</div>
		
	<div class="collapse-group" id="collapseGroup1">
		
		<c:forEach items="${requestScope.flashcards}" var="flashcard" varStatus="loop">
	 		
			<div class="panel panel-default">
				<div class="panel-heading trigger collapsed" data-toggle="collapse" data-parent="#collapseGroup1" data-target="#collapse${loop.index}"
					style="background-color: ${flashcard.color};">
					<h4 class="panel-title">
						${flashcard.front}
					</h4>
				</div>
				<div id="collapse${loop.index}" class="panel-collapse collapse">
					<div class="panel-body">
						<div class="card-back-text">
							${flashcard.back}
						</div>
						<sec:authorize access="isAuthenticated()">
					 		<div class="cardControl">
						 		<span class="cardPosNum">pos: ${flashcard.position}</span>
						 		<span class="glyphicon glyphicon-edit editCardGlyph" data-cardId="${flashcard.flashcard_id}"></span>
					 		</div>
				 		</sec:authorize>
					</div>
				</div>
				<input class="flashcardId" type="hidden" value="${flashcard.flashcard_id}">
			</div>	
		 </c:forEach>
		
	</div>
	
	<div class="controls">
		<div class="row cardControlRow">
			<div class="col-xs-4">
				<!-- <button class="btn btn-warning open-button" type="button">Open all</button> -->
			</div>
			<div class="col-xs-4 center">
				<%-- <button class="btn btn-primary close-info" type="button" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard?topic_id=${requestScope.topic.topic_id}'">Add</button> --%>
			</div>
			<div class="col-xs-4">
				<!-- <button class="btn btn-success close-button" type="button">Close all</button> -->
			</div>			
		</div>
	</div>
	
	<sec:authorize access="isAuthenticated()">
		<button id="saveChangesButton" style="display: none;" class="btn btn-primary close-info floatingButton" hidden="hidden" type="button" onclick="saveOrder()">Save changes</button>
	</sec:authorize>
	
	<button id="saveChangesButton" style="display: none;" class="btn btn-primary close-info floatingButton" hidden="hidden" type="button" onclick="saveOrder()">Save changes</button>

</div>

<script type="text/javascript">

$(document).on("click", ".editCardGlyph", function () {
	var cardId = $(this).attr("data-cardId");
	$('#loading').show();
	window.open(ctx + "/editFlashcard?cardId="+cardId, "_self");
});

window.touchPunchDelay = 500;
$("#collapseGroup1").sortable({
	update: function(event, ui) {
		$('#saveChangesButton').show();
    }
});

function saveOrder() {
	
	var orderArray = [];
	
	$('.flashcardId').each(function(index) {
		console.log(index + ':' + $(this).val());
		orderArray.push(index + ':' + $(this).val());
	});
	
	$.ajax({
		  url: ctx + "/saveOrder",
		  type:"POST",
		  data: {
				'orderArray' : orderArray
			},
		  beforeSend: function(){
			  showLoader();
		  },
		  success: function(data, status) {
				if(data == 'success'){
					$('#saveChangesButton').hide();
				} else {
				    $('#saveChangesButton').html(data);
					$('#saveChangesButton').css('background-color', 'red');
				}
				
			},
		  complete: function(){
			  hideLoader();
		  }
		});
	
}

$("#collapseGroup1").disableSelection();

</script>


<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

</html>