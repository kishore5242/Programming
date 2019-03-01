<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<!-- jQuery-Based-HTML5-WYSIWYG -->
<!-- 
<link rel="stylesheet" href="/css/jquery-te-1.4.0.css">
<script src="/js/jquery-te-1.4.0.min.js"></script> -->

<link rel="stylesheet" type="text/css" href="/css/simditor.css" />
<script type="text/javascript" src="/js/module.js"></script>
<script type="text/javascript" src="/js/hotkeys.js"></script>
<script type="text/javascript" src="/js/uploader.js"></script>
<script type="text/javascript" src="/js/simditor.js"></script>

</head>
<body>

	<div class="container">
		<h2>Edit Flashcard</h2>
		<form action="/updateFlashcard" method="post">
			<!-- <div class="form-group">
			  <label for="stream">Stream:</label>
			  <select class="form-control" id="stream" name="stream">
			    <option value="Java" selected="selected">Java</option>
			    <option value="JavaScript">JavaScript</option>
			  </select>
			</div> -->
			
			<div class="form-group">
				<input type="hidden" class="form-control" id="cardId" name="cardId" 
						value="${requestScope.flashcard.id}">
			</div>
			
			<div class="form-group">
				<label for="front">Stream:</label> 
				<input type="text" class="form-control" id="stream" name="stream" 
						value="${requestScope.flashcard.stream}" readonly="readonly">
			</div>
			
			
			<div class="form-group">
				<label for="front">Front:</label> 
				<input type="text" class="form-control" id="front" placeholder="Front side of the card" name="front" 
						value="${requestScope.flashcard.front}">
			</div>
			<div class="form-group">
				<label for="back">Back:</label> 
				<textarea rows="5" class="form-control" id="back" placeholder="Backside of the card" name="back">
					${requestScope.flashcard.back}
				</textarea>
			</div>
			<div class="form-group">
				<label for="position">Position</label> 
				<input type="number" class="form-control" id="position" placeholder="Position of the card" name="position"
						value="${requestScope.flashcard.position}">
			</div>
			
			<br>
			<br>
			
			<div class="row">
				<div class="col-xs-4">
					<button type="button" class="btn btn-danger deleteCardGlyph" data-toggle="modal" data-target="#deleteCardModal" 
									data-deleteId="${requestScope.flashcard.id}"
									data-deleteStream="${requestScope.flashcard.stream}"> Delete
					</button>
				</div>
				<div class="col-xs-4 center">
					<%-- <button class="btn btn-primary close-info" type="button" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard'">Add</button> --%>
				</div>
				<div class="col-xs-4">
					<button class="btn btn-success close-button" type="submit">Save</button>
				</div>			
			</div>
			
			
			
			<!-- <div class="addCardDiv">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div> -->

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
</script>
</html>