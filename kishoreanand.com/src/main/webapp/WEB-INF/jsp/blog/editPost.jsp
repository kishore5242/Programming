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
		<h3 class="notCard">Edit Post</h3>
		<form action="${pageContext.request.contextPath}/blogadmin/updatePost" method="post" enctype="multipart/form-data">
		
			<div class="form-group">
				<label for="post_name">Post name:</label> 
				<input type="text" class="form-control" id="post_name" placeholder="Enter post name" value="${requestScope.post.post_name}" name="post_name" required="required">
				<input type="hidden" class="form-control" id="post_id" value="${requestScope.post.post_id}" name="post_id">
			</div>
			<div class="form-group">
				<label for="downloadfile">Download HTML:</label> <br>
				<a href="${pageContext.request.contextPath}/uploaded/${requestScope.post.post_html_path}" download>Click here to download the existing HTML file</a>
			</div>
			<div class="form-group">
				<label for="htmlfile">Upload HTML:</label><br>
				<input class="form-control" type="file" id="htmlfile" name="htmlfile" accept=".md, .html, .htm"/>
			</div>	
			<div class="form-group">
				<label for="position">Position:</label><br>
				<input class="form-control" type="number" id="position" name="position" required="required" value="${requestScope.post.position}"/>
			</div>
			<input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${requestScope.post.blog.blog_id}">
			
			
			<div class="controls">
				<div class="row cardControlRow">
					<div class="col-xs-4">
						<button type="button" class="btn btn-danger deleteTopicGlyph" data-toggle="modal" data-target="#deleteModal"> Delete
						</button>
					</div>
					<div class="col-xs-4 center">
						<%-- <button class="btn btn-primary close-info" type="button" onclick="window.location.href='${pageContext.request.contextPath}/addFlashcard'">Add</button> --%>
					</div>
					<div class="col-xs-4">
						<button class="btn btn-coffee close-button" type="submit">Save</button>
					</div>			
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">
					<jsp:include page="reference.jsp" /> 
				</div>
			</div>
			
		</form>
		<br>
		<br>
		<br>
		<br>
		<br>
		
	</div>
			
	<!-- Delete Topic Modal -->
	<div id="deleteModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Are you sure you want to delete this post?</h4>
	      </div>
	      <div class="modal-body">
	        <p>The post will be deleted permanently if clicked on 'Yes'</p>
	        <p>If you don't want to delete please click on 'Cancel'</p>
	      </div>
	      <div class="modal-footer">

		     <form action="${pageContext.request.contextPath}/blogadmin/deletePost" method="post">
				<div class="form-group">
					<input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${requestScope.post.blog.blog_id}">
					<input type="hidden" class="form-control" id="post_id" name="post_id" value="${requestScope.post.post_id}">
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
</html>