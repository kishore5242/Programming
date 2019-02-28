<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>KishoreAnand.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- common styles -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

<!-- context path variable -->
<%-- <c:url value="${pageContext.request.contextPath}" var="contextPath"/> --%>

<!-- common JavaScripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>

<body>
	<!-- nav bar -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- 
			<div class="navbar-header">
				<a class="navbar-brand" href="#">&lt;/&gt;</a>
			</div>
			 -->
			<ul class="nav navbar-nav">		
		      <li><a href="${pageContext.request.contextPath}/">Home</a></li>
		      <li class="dropdown">
		      	<a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/cards">
		      		Flash Cards <span class="caret"></span>
		      	</a>
		        <ul class="dropdown-menu">
		          <li><a onclick="openPage('/cards/java');">Java</a></li>
		          <%-- <li><a href="${pageContext.request.contextPath}/cards/javascript">JavaScript</a></li> --%>
		          <%-- <li><a href="${pageContext.request.contextPath}/cards/spring">Spring</a></li> --%>
		        </ul>
		      </li>
		      <%--<li><a href="${pageContext.request.contextPath}/tests">Tests</a></li>--%>
		      <li><a href="${pageContext.request.contextPath}/about">About</a></li>
		    </ul>
		</div>
	</nav>
	
		
	<!-- Delete Card Modal -->
	<div id="deleteCardModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Are you sure you want to delete this card?</h4>
	      </div>
	      <div class="modal-body">
	        <p>The card will be deleted permanently if clicked on 'Yes'</p>
	        <p>If you don't want to delete please click on 'Cancel'</p>
	      </div>
	      <div class="modal-footer">

		     <form action="/deleteFlashcard" method="post">
				<div class="form-group">
					<input type="hidden" class="form-control" id="deleteId" name="deleteId">
					<input type="hidden" class="form-control" id="deleteStream" name="deleteStream">
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button type="submit" class="btn btn-danger">Yes</button>
			</form>
	        
	      </div>
	    </div>
	
	  </div>
	</div>
	
	
	<div id="loading" hidden="true"></div>
	
</body>

<script type="text/javascript">
    $(document).ready(function () {
        var url = window.location;
        $('ul.nav a[href="'+ url +'"]').parent().addClass('active');
        $('ul.nav a').filter(function() {
             return this.href == url;
        }).parent().addClass('active');
    });
</script>

</html>
