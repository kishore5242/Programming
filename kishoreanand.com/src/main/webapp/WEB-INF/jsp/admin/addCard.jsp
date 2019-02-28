<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<!-- jQuery-Based-HTML5-WYSIWYG -->
<link rel="stylesheet" href="/css/jquery.wysiwygEditor.css">
<!-- <script src="//code.jquery.com/jquery-2.1.4.js"></script> -->
<script src="/js/jquery.wysiwygEditor.js"></script>

</head>
<body>

	<div class="container">
		<h2>Add a Flashcard</h2>
		<form action="/saveFlashcard" method="post">
			<div class="form-group">
			  <label for="stream">Stream:</label>
			  <select class="form-control" id="stream" name="stream">
			    <option value="Java" selected="selected">Java</option>
			    <option value="JavaScript">JavaScript</option>
			  </select>
			</div>
			<div class="form-group">
				<label for="front">Front:</label> 
				<input type="text" class="form-control" id="front" placeholder="Front side of the card" name="front">
			</div>
			<div class="form-group">
				<label for="back">Back:</label> 
				<textarea rows="5" class="form-control" id="back" placeholder="Backside of the card" name="back"></textarea>
			</div>
			<div class="form-group">
				<label for="position">Position</label> 
				<input type="number" class="form-control" id="position" placeholder="Position of the card" name="position">
			</div>
			
			<div class="addCardDiv">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
			
		</form>
	</div>

	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">
	$('#back').wysiwygEditor();
</script>
</html>