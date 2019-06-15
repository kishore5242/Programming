<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />

<!-- jQuery-Based-HTML5-WYSIWYG -->
<!-- <link rel="stylesheet" href="/css/jquery-te-1.4.0.css">
<script src="/js/jquery-te-1.4.0.min.js"></script> -->


<link rel="stylesheet" type="text/css" href="/css/simditor.css" />
<script type="text/javascript" src="/js/module.js"></script>
<script type="text/javascript" src="/js/hotkeys.js"></script>
<script type="text/javascript" src="/js/uploader.js"></script>
<script type="text/javascript" src="/js/simditor.js"></script>


</head>
<body>

	<div class="container">
		<h3 class="notCard">Add new Stream</h3>
		<form action="/saveStream" method="post">
			<div class="form-group">
				<label for="streamName">Stream name:</label> 
				<input type="text" class="form-control" id="streamName" placeholder="Enter stream name" name="streamName" required="required">
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
</html>