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
		<h3 class="notCard">Edit Topic</h3>
		<form action="/updateTopic" method="post">
			<div class="form-group">
				<label for="topicName">Topic name:</label> 
				<input type="text" class="form-control" id="topic_name" placeholder="Enter topic name" value="${requestScope.topic.topic_name}" name="topic_name" required="required">
				<input type="hidden" class="form-control" id="topic_id" value="${requestScope.topic.topic_id}" name="topic_id">
			</div>
			<div class="form-group">
				<label for="pos">position:</label> 
				<input type="number" class="form-control" id="pos" placeholder="Enter position of the topic" value="${requestScope.topic.pos}" name="pos" required="required">
			</div>
			<input type="hidden" class="form-control" id="stream_id" name="stream_id" value="${requestScope.topic.stream.stream_id}">
			
			
			<div class="controls">
				<div class="row cardControlRow">
					<div class="col-xs-4">
						<button type="button" class="btn btn-danger deleteTopicGlyph" data-toggle="modal" data-target="#deleteTopicModal"> Delete
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
			
		</form>
		<br>
		<br>
		<br>
		<br>
		<br>
		
	</div>
			
	<!-- Delete Topic Modal -->
	<div id="deleteTopicModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Are you sure you want to delete this topic?</h4>
	      </div>
	      <div class="modal-body">
	        <p>The topic will be deleted permanently if clicked on 'Yes'</p>
	        <p>If you don't want to delete please click on 'Cancel'</p>
	      </div>
	      <div class="modal-footer">

		     <form action="/deleteTopic" method="post">
				<div class="form-group">
					<input type="hidden" class="form-control" id="stream_id" name="stream_id" value="${requestScope.topic.stream.stream_id}">
					<input type="hidden" class="form-control" id="topic_id" name="topic_id" value="${requestScope.topic.topic_id}">
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