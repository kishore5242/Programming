<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="moreInfo">
		<h3 class="notCard text-center"><u>You just need to login</u></h3><br>
		<div class="row">
		  <div class="col-sm-6">
		  	<h4 class="notCard">1. Login to manage your own flashcards</h4>
		     <sec:authorize access="!isAuthenticated()">
				<div class="text-center">
					<input type="button" class="btn btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/login'" value="Login">
					<br><br>
				</div>
		     </sec:authorize>		  
		  </div>
		  <div class="col-sm-6">
		  	<h4 class="notCard">2. Create Collections and Topics</h4>
		  	<img class="tutorial-img" alt="Create collections and topics" src="${pageContext.request.contextPath}/img/fc_tutorial/fc-s-t.png">
		  </div>
		  <div class="col-sm-6">
		  	<h4 class="notCard">3. Add cards</h4>
		  	<img class="tutorial-img" alt="Create streams and topics" src="${pageContext.request.contextPath}/img/fc_tutorial/fc-mod.PNG">
		  </div>
		  <div class="col-sm-6">
		  	<h4 class="notCard">4. tap to view back side of a card</h4>
		  	<img class="tutorial-img" alt="Create streams and topics" src="${pageContext.request.contextPath}/img/fc_tutorial/fc-tap.png">
		  </div>
		  <div class="col-sm-6">
		  	<h4 class="notCard">5. Drag and Drop to reposition</h4>
		  	<img class="tutorial-img" alt="Create streams and topics" src="${pageContext.request.contextPath}/img/fc_tutorial/fc-d-d.png">
		  </div>
		</div>
	</div>
</body>
</html>