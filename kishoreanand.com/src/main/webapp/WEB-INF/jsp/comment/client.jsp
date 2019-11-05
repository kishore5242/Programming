<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Comments client</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>

<style>
.comment {
	background-color: #f5f5f5;
	padding: 10px;
	border-radius: 10px;
}

.commenteditor {
	background-color: #ddffcc;
	padding: 5px 10px 10px 10px;
	border-radius: 10px;
	margin: 10px 0 20px 0;
}
</style>

<body>
	<br>
	
	<h4><u>Comments</u>:</h4>
    <div id="result">
    </div>
	
	<div id="replyeditorcontainer" style="display: none;">
		<div id='replyeditor' class="commenteditor">
			<form class="commenteditorform">
				<h4>Reply to this comment</h4>
				
				<input type="hidden" class="postid" name="postid" value="${displaypost.post_id}">
				
	    		<div class="form-group">
					<label for="exampleInputEmail1">Name</label>
				    <input type="text" class="form-control name" id="name" name="name" placeholder="Your name" required="required"">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label>
					<input type="email" class="form-control email" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required="required"">
					<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				</div>
				<div class="form-group">
					<textarea class="form-control replytextarea" id="value" name="value" rows="4" required="required"></textarea>
				</div>

				<input type="hidden" class="parent_id" name="parent_id" value="replyToId">
				
				<div class="text-center">
					<button type="button" class="btn btn-warning btn-sm" onclick="closeParent(this)">Cancel</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-success btn-sm">Submit</button>
				</div>

			</form>
		</div>
	</div>
    
    <div class="editorToggle">
		<h4><u>Leave a comment</u>:</h4>
		<div id='editor' class="commenteditor">
	    	<form class="commenteditorform">
	    		<br>
	    		<input type="hidden" class="postid" name="postid" value="${displaypost.post_id}">
	    		<div class="form-group">
					<label for="exampleInputEmail1">Name</label>
				    <input type="text" class="form-control name" id="name" name="name" placeholder="Your name" required="required"">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label>
					<input type="email" class="form-control email" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required="required"">
					<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				</div>
				<div class="form-group">
					<textarea class="form-control replytextarea" id="value" name="value" rows="4" required="required"></textarea>
				</div>
				<input type="hidden" class="parent_id" name="parent_id" value="0">
				<div class="text-center">
					<button type="submit" class="btn btn-success btn-sm">Submit</button>
				</div>
				<br>
			</form>
	    </div>
	</div>
    
</body>

<script type="text/javascript">

refreshComments();

var commentSubmitEvent = function(event){
    event.preventDefault();
    var formEl = $(this);
	var inputData = {
			postid: formEl.find('.postid').val(),
			value: formEl.find('.replytextarea').val(),
			email: formEl.find('.email').val(),
			name: formEl.find('.name').val(),
			parent_id: formEl.find('.parent_id').val()	
		};
    
	$.ajax({
		url: "${pageContext.request.contextPath}/comments",
		type: 'post',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(inputData),
		success: function(data){
			formEl.find('.replytextarea').val('')
			formEl.parent().hide(3000);
		    setTimeout(function() {
		    	formEl.parent().show(1000);
		    }, 5000);
		    hideLoader();
		},
		complete: function(data) {
			refreshComments();
        }
	});
	
};

$(document).ready(function(){

	$(".commenteditorform").submit(commentSubmitEvent);

});


function refreshComments(){
	$.get("${pageContext.request.contextPath}/comments?post_id=${displaypost.post_id}", function(data){
		// Display the returned data in browser
		
		var commHtml = '';
		
		if(Array.isArray(data) && data.length){
			
			for(i=0; i<data.length; i++){
				commHtml += formatHtmlValue(data[i])+"</br>";
			}
			
		} else if(Array.isArray(data)) {
			commHtml = 'No comments yet';
		}  else {
			commHtml = 'Unable to fetch comments';
		}
		
		$("#result").html(commHtml);
	});

	$('.editorToggle').show();
}


function formatHtmlValue(commentData){
	var pxIndent = commentData.indent * 50;
	return "<div class='comment' style='margin-left:"+pxIndent+"px'><pre><code>"+htmlEncode(commentData.value)+"</code></pre>"+
	"- <b>"+commentData.name+"</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' class='editorToggle' onclick='showCommentEditor("+commentData.id+",this)'>reply</a>"
	+"</div>";
}

function showCommentEditor(replyToId, el){

	var replyEditorHtml = $("#replyeditorcontainer").html();
	replyEditorHtml = replaceAll(replyEditorHtml, "replyToId", replyToId)
	
	$(el).parent().append(replyEditorHtml);

	//$(replyEditorHtml).insertAfter($(el).parent());
	
	$([document.documentElement, document.body]).animate({
        scrollTop: $(el).offset().top
    }, 500);
	$(el).find('.replytextarea').focus();	

	$('.editorToggle').hide();
	
	$(el).parent().find('.commenteditorform').submit(commentSubmitEvent);

}

function closeParent(el){
	//$(el).parents('#replyeditor').remove();
	$(el).parents('#replyeditor').hide('slow', function(){ $(el).parents('#replyeditor').remove(); });
	$('.editorToggle').show();
}

function escapeRegExp(string){
    return string.replace(/[.*+?^$(){}|[\]\\]/g, "\\$&");
}

function replaceAll(str, term, replacement) {
  return str.replace(new RegExp(escapeRegExp(term), 'g'), replacement);
}

function htmlEncode(value){
  // Create a in-memory element, set its inner text (which is automatically encoded)
  // Then grab the encoded contents back out. The element never exists on the DOM.
  return $('<textarea/>').text(value).html();
}

function htmlDecode(value){
  return $('<textarea/>').html(value).text();
}

</script>

</html>