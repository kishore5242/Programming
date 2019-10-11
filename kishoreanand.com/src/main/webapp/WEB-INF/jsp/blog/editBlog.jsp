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
		<h3 class="notCard">Edit Blog:</h3>
		<form action="${pageContext.request.contextPath}/blogadmin/updateBlog" method="post">
			<div class="form-group">
				<label for="topicName">Blog name:</label> 
				<input type="text" class="form-control" id="blog_name" placeholder="Enter blog name" value="${requestScope.blog.blog_name}" name="blog_name" required="required">
			</div>
			<input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${requestScope.blog.blog_id}">
			
			
			<div class="controls">
				<div class="row cardControlRow">
					<div class="col-xs-4">
						<input type="button" class="btn btn-danger deleteStreamGlyph navButton" data-toggle="modal" data-target="#deleteModal" value="Delete">
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
			
	<!-- Delete Topic Modal -->
	<div id="deleteModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Are you sure you want to delete this blog?</h4>
	      </div>
	      <div class="modal-body">
	        <p>The blog will be deleted permanently if clicked on 'Yes'</p>
	        <p>If you don't want to delete please click on 'Cancel'</p>
	      </div>
	      <div class="modal-footer">

		     <form action="${pageContext.request.contextPath}/blogadmin/deleteBlog" method="post">
				<div class="form-group">
					<input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${requestScope.blog.blog_id}">
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