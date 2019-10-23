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
		<h3 class="notCard">Post an article</h3>
		
		<div class="row">
			<div class="col-xs-12">
				<form action="${pageContext.request.contextPath}/blogadmin/savePost" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="post_name">Post name:</label> 
						<input type="text" class="form-control" id="post_name" placeholder="Enter post name" name="post_name" required="required">
					</div>
					<div class="form-group">
						<label for="htmlfile">Upload HTML:</label><br>
						<input class="form-control" type="file" id="file" name="file" required="required" accept=".md, .html, .htm"/>
						
					</div>
					<div class="form-group">
						<label for="position">Position:</label><br>
						<input class="form-control" type="number" id="position" name="position" required="required" value="0"/>
					</div>					

					<input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${requestScope.blog_id}">
					
					<div class="addCardDiv">
						<input type="submit" class="btn btn-coffee navButton" value="Submit">
					</div>
					
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<jsp:include page="reference.jsp" /> 
			</div>
		</div>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		
	</div>

	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>