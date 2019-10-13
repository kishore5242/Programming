<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/jsp/header/header.jsp" />
</head>
<body>

	<div class="container">
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div>
					<h2>Uploaded file path</h2>
					
					<c:set var="req" value="${pageContext.request}" />
				    
				    <input type="text" id="fileUrl" value="${req.scheme}://${req.serverName}${pageContext.request.contextPath}/uploaded/${filepath}" style="width:70%;">
				   	<button onclick="copyToClipboard()">Copy Url</button> &nbsp;&nbsp;
				    <a id="fileUrlTest" href="${pageContext.request.contextPath}/uploaded/${filepath}" target="_blank">
				    	Test Url
				    </a> 

				</div>
			</div>
		</div>

	</div>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>

<script type="text/javascript">

function copyToClipboard() {
	  /* Get the text field */
	  var copyText = document.getElementById("fileUrl");

	  /* Select the text field */
	  copyText.select();
	  copyText.setSelectionRange(0, 99999); /*For mobile devices*/

	  /* Copy the text inside the text field */
	  document.execCommand("copy");

	  /* Alert the copied text */
	  alert("Copied the text: " + copyText.value);
	}
</script>

</html>