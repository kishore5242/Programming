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
		<h3 class="notCard">Add a new Topic</h3>
		<form action="${pageContext.request.contextPath}/saveTopic" method="post">
			<div class="form-group">
				<label for="topicName">Topic name:</label> 
				<input type="text" class="form-control" id="topicName" placeholder="Enter topic name" name="topicName" required="required">
			</div>
			<div class="form-group">
				<label for="pos">position:</label> 
				<input type="number" class="form-control" id="pos" placeholder="Enter position of the topic" name="pos" required="required">
			</div>
			<input type="hidden" class="form-control" id="stream_id" name="stream_id" value="${requestScope.stream_id}">
			
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