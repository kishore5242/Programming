<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<!-- jQuery-Based-HTML5-WYSIWYG -->
<!-- <link rel="stylesheet" href="/css/jquery-te-1.4.0.css">
<script src="/js/jquery-te-1.4.0.min.js"></script> -->


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/simditor.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/module.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hotkeys.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/simditor.js"></script>

</head>
<body>

	<div class="container">
		<h3 class="notCard">Add a Flashcard</h3>
		<form action="${pageContext.request.contextPath}/saveFlashcard" method="post">
			<div class="form-group">
			  <label for="topic_name">Topic:</label>
			  <input type="text" class="form-control" id="topic_name" value="${requestScope.topic.topic_name}" name="topic_name" readonly="readonly">
			  <input type="hidden" class="form-control" id="topic_id" value="${requestScope.topic.topic_id}" name="topic_id">
			</div>
			<div class="form-group">
				<label for="front">Front:</label> 
				<textarea class="form-control" id="front" rows="2" name="front" placeholder="Front side of the card... (plain text)" required="required"></textarea>
			</div>
			<div class="form-group">
				<label for="back">Back:</label> 
				<textarea rows="5" class="form-control" id="back" placeholder="Back side of the card..." name="back"></textarea>
			</div>
			<!-- <div class="javascriptCode"></div> -->
			
			<div class="form-group">
				<label for="position">Position:</label> 
				<input type="number" class="form-control" id="position" placeholder="Position of the card" name="position" required="required">
			</div>
			
			<div class="form-group">
				<label for="radio">Color:</label>
				<div>
			      <ul id="selectable-1">
			         <li class="ui-widget-content ui-selected default" data-val="#734d26">Default</li>
			         <li class="ui-widget-content green" data-val="#558000">Green</li>
			         <li class="ui-widget-content red" data-val="#cc3300">Red</li>
			         <li class="ui-widget-content grey" data-val="#4d4d4d">Grey</li>
			      </ul>
				</div> 
				 <input type="hidden" id="selectedColor" name="selectedColor" value="#734d26">
			</div>
			
			<div class="addCardDiv">
				<input type="submit" class="btn btn-coffee navButton" value="Submit">
			</div>
			
		</form>
		<br>
		<br>
		<br>
		<br>
		<br>
		
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

</script>
</html>