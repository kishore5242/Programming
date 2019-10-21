<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<!-- jQuery-Based-HTML5-WYSIWYG -->
<!-- 
<link rel="stylesheet" href="/css/jquery-te-1.4.0.css">
<script src="/js/jquery-te-1.4.0.min.js"></script> -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/simditor.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/simditor.js"></script>

</head>
<body>

	<div class="container">
		<h3 class="notCard">Edit Flashcard</h3>
		<form action="${pageContext.request.contextPath}/updateFlashcard" method="post">
			
			<div class="form-group">
				<input type="hidden" class="form-control" id="cardId" name="cardId" 
						value="${requestScope.flashcard.flashcard_id}">
			</div>
			
			<div class="form-group">
				<label for="topic_name">Topic:</label> 
				<input type="text" class="form-control" id="topic_name" name="topic_name" 
						value="${requestScope.flashcard.topic.topic_name}" readonly="readonly">
				<input type="hidden" class="form-control" id="topic_id" name="topic_id" 
						value="${requestScope.flashcard.topic.topic_id}">
			</div>
			
			
			<div class="form-group">
				<label for="front">Front:</label> 
				<textarea class="form-control" id="front" rows="2" name="front" placeholder="Front side of the card... (plain text)">${requestScope.flashcard.front}</textarea>
			</div>
			<div class="form-group">
				<label for="back">Back:</label> 
				<textarea rows="5" class="form-control" id="back" placeholder="Back side of the card..." name="back">
					${requestScope.flashcard.back}
				</textarea><br>
				<a href="${pageContext.request.contextPath}/files?blog_id=${requestScope.blog_id}" target="_blank">Upload an image and get link&nbsp;&nbsp;<img alt="external" src="${pageContext.request.contextPath}/img/global/icons/link-external.svg"></a>
			</div>
			<div class="form-group">
				<label for="position">Position</label> 
				<input type="number" class="form-control" id="position" placeholder="Position of the card" name="position"
						value="${requestScope.flashcard.position}">
			</div>
			
			<div class="form-group">
				<label for="radio">Color:</label>
				<div>
			      <ul id="selectable-1">
			         <li class="ui-widget-content default" data-val="#734d26">Default</li>
			         <li class="ui-widget-content green" data-val="#558000">Green</li>
			         <li class="ui-widget-content red" data-val="#cc3300">Red</li>
			         <li class="ui-widget-content grey" data-val="#4d4d4d">Grey</li>
			      </ul>
				</div> 
				 <input type="hidden" id="selectedColor" name="selectedColor" value="${requestScope.flashcard.color}">
			</div>
			
			
			<br>
			
			<div class="controls">
				<div class="row cardControlRow">
					<div class="col-xs-4">
						<input type="button" class="btn btn-danger deleteCardGlyph navButton" data-toggle="modal" data-target="#deleteCardModal" 
									data-deleteId="${requestScope.flashcard.flashcard_id}"
									data-deleteStream="${requestScope.flashcard.stream}" value="Delete">
					</div>
					<div class="col-xs-4 center">
						<%-- <button class="btn btn-primary close-info" type="button" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard'">Add</button> --%>
					</div>
					<div class="col-xs-4">
						<input class="btn btn-coffee close-button navButton" type="submit" value="Save">
					</div>			
				</div>
			</div>

		</form>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		
	</div>
	
			
	<!-- Delete Card Modal -->
	<div id="deleteCardModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Are you sure you want to delete this card?</h4>
	      </div>
	      <div class="modal-body">
	        <p>The card will be deleted permanently if clicked on 'Yes'</p>
	        <p>If you don't want to delete please click on 'Cancel'</p>
	      </div>
	      <div class="modal-footer">

		     <form action="${pageContext.request.contextPath}/deleteFlashcard" method="post">
				<div class="form-group">
					<input type="hidden" class="form-control" id="deleteId" name="deleteId">
					<input type="hidden" class="form-control" id="deleteStream" name="deleteStream">
					<input type="hidden" class="form-control" id="topic_id" name="topic_id" value="${requestScope.flashcard.topic.topic_id}">
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="submit" class="btn btn-danger">Yes</button>
			</form>
	        
	      </div>
	    </div>
	
	  </div>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

	//$("#back").jqte();
Simditor.locale = 'en-US';
var editor = new Simditor({
	  textarea: $('#back')
	  //optional options
	});	

$(function() {
    $( "#selectable-1" ).selectable({
    	selected: function(event, ui) { 
            $(ui.selected).addClass("ui-selected").siblings().removeClass("ui-selected");
            $('#selectedColor').val($(ui.selected).data('val'));
        }
      });
 });
	
$(document).on("click", ".deleteCardGlyph", function () {
	
    //get data-id attribute of the clicked element
    var deleteId = $(this).attr("data-deleteId");
    var deleteStream = $(this).attr("data-deleteStream");
    
    $("#deleteCardModal #deleteId").val(deleteId);
    $("#deleteCardModal #deleteStream").val(deleteStream);
});
	
</script>
</html>