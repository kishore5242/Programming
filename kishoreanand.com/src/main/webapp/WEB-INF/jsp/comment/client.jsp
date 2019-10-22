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
	background-color: #ccffcc;
	padding: 10px;
	border-radius: 10px;
}

.commenteditor {
	background-color: #ccffff;
	padding: 5px 10px 10px 10px;
	border-radius: 10px;
	margin: 10px 0 20px 0;
}
</style>

<body>

	<h4>Comments:</h4>
    <div id="result">
    </div>
    
    <div id='editor' class="commenteditor">
    	<form class="commenteditorform">
	        <h4>Leave a comment</h4>
			<input type="hidden" class="postid" name="postid" value="${displaypost.post_id}">
			<input type="text" class="name" name="name" placeholder="Name" required="required"><br/><br/>
			<input type="text" class="email" name="email" placeholder="Email" required="required"><br/><br/>
			<textarea class="replytextarea" name="value" rows="4" cols="50" required="required"></textarea></br><br/>
			<input type="hidden" class="parent_id" name="parent_id" value="0">
			<input type="submit" value="submit">
		</form>
    </div>
	
	<div id="replyeditorcontainer" style="display: none;">
		<div id='replyeditor' class="commenteditor">
			<form class="commenteditorform">
				<h4>Reply to the comment</h4>
				<input type="hidden" class="postid" name="postid" value="${displaypost.post_id}">
				<input type="text" class="name" name="name" placeholder="Name" required="required"><br/><br/>
				<input type="text" class="email" name="email" placeholder="Email" required="required"><br/><br/>
				<textarea class="replytextarea" name="value" rows="4" cols="50" required="required"></textarea></br><br/>
				<input type="hidden" class="parent_id" name="parent_id" value="replyToId">
				<input type="button" value="cancel" onclick="closeParent(this)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="Submit">
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
			postid: formEl.children('.postid').val(),
			value: formEl.children('.replytextarea').val(),
			email: formEl.children('.email').val(),
			name: formEl.children('.name').val(),
			parent_id: formEl.children('.parent_id').val()	
		};
    
	$.ajax({
		url: "${pageContext.request.contextPath}/comments",
		type: 'post',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(inputData),
		success: function(data){
			refreshComments();
			formEl.children('.replytextarea').val('')
			formEl.parent().hide(1000);
		    setTimeout(function() {
		    	formEl.parent().show(1000);
		    }, 5000);
		}
	});
	
};

$(document).ready(function(){

	$(".commenteditorform").submit(commentSubmitEvent);

});


function refreshComments(){
	$.get("${pageContext.request.contextPath}/comments", function(data){
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
}


function formatHtmlValue(commentData){
	var pxIndent = commentData.indent * 50;
	return "<div class='comment' style='margin-left:"+pxIndent+"px'><pre><code>"+htmlEncode(commentData.value)+"</code></pre>"+
	"</br>- <b>"+commentData.name+"</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='showCommentEditor("+commentData.id+",this)'>reply</a>"
	+"</div>";
}

function showCommentEditor(replyToId, el){

	var replyEditorHtml = $("#replyeditorcontainer").html();
	replyEditorHtml = replaceAll(replyEditorHtml, "replyToId", replyToId)
	
	$(el).parent().append(replyEditorHtml);
	$([document.documentElement, document.body]).animate({
        scrollTop: $(el).offset().top
    }, 500);
	$(el).find('.replytextarea').focus();	
	
	$(el).parent().find('.commenteditorform').submit(commentSubmitEvent);

}

function closeParent(el){
	$(el).parent().remove();
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