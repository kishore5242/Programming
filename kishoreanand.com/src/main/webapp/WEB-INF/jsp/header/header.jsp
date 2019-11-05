<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html lang="en">

<head>
<title>KishoreAnand.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Here you can create and manage your flashcards. You can also find some interesting articles on software technologies">
<meta charset="UTF-8">

<!-- favicon -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/global/favicon.ico" type="image/x-icon">

<!-- common styles -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">


<!-- context path variable -->
<%-- <c:url value="${pageContext.request.contextPath}" var="contextPath"/> --%>

<!-- common JavaScripts -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<!-- code mirror -->
<script src="${pageContext.request.contextPath}/js/codemirror.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/codemirror.css">
<script src="${pageContext.request.contextPath}/mode/javascript/javascript.js"></script>


<style type="text/css">

.navbar {
    min-height: 40px;
}

.topnav ul {
    width: 100%;
    min-height: 40px;
}

.topnav li a {
    color: white;
}

.topnav li a:hover {
    background-color: grey;
}

.navbar-nav {
    margin: 0;
}

/* Hide the link that should open and close the topnav on small screens */
.topnav .icon {
  display: none;
}

/* When the screen is less than 600 pixels wide, hide all links, except for the first one ("Home"). Show the link that contains should open and close the topnav (.icon) */
@media screen and (max-width: 600px) {
  .topnav {
  	position: relative;
  }
  .topnav li {
  	display: none;
  }
  .topnav li.active {
  	display: block;
  }
  .topnav .icon {
 	display: block;
	position: absolute;
    right: 0;
    top: 0;
    padding: 10px 15px 15px 15px;
    color: white;
    width: 70%;
    text-align: right;
  }
}

/* The "responsive" class is added to the topnav with JavaScript when the user clicks on the icon. This class makes the topnav look good on small screens (display the links vertically instead of horizontally) */
@media screen and (max-width: 600px) {
  .topnav.responsive li {
    display: block;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
  
  .topnav.responsive .icon {
    text-align: right;
    width: 65%;
  }

}



</style>

</head>

<body>

<div class="navbar navbar-inverse" id="topFixedNav">	
	<div class="topnav" id="myTopnav">
		<ul class="nav navbar-nav">		
		     <li class="reqInResp"><a href="${pageContext.request.contextPath}/"><img alt="Home" class="headerlogo" src="${pageContext.request.contextPath}/img/global/favicon.ico"></a></li>
		     <li class="dropdown">
		     	<a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/cards">
		     		<sec:authorize access="isAuthenticated()">
		     			My Flashcards<span class="caret"></span>
		     		</sec:authorize>
		     		<sec:authorize access="!isAuthenticated()">
		     			Flashcards<span class="caret"></span>
		     		</sec:authorize>
		     	</a>
		     	<ul class="dropdown-menu" id="streamList">
		    	<c:forEach items="${sessionScope.streams}" var="stream" varStatus="loop">
					<li class="navSubmenu"><a href="${pageContext.request.contextPath}/topics?stream_id=${stream.stream_id}">${stream.stream_name}</a></li>
				</c:forEach>
		     	</ul>
		     </li>
		     <li class="dropdown">
		     	<a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/blogs">
		     		Blogs<span class="caret"></span>
		     	</a>
		     	<ul class="dropdown-menu" id="blogsList">
		    	<c:forEach items="${sessionScope.blogs}" var="blog" varStatus="loop">
					<li class="navSubmenu"><a href="${pageContext.request.contextPath}/bloguser/blog/${blog.blog_id}">${blog.blog_name}</a></li>
				</c:forEach>
		     	</ul>
		     </li>
		     <sec:authorize access="isAuthenticated()">
		     	<li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
		     </sec:authorize>
		     <li><a href="${pageContext.request.contextPath}/about">About</a></li>
		     <sec:authorize access="!isAuthenticated()">
		     	<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
		     </sec:authorize>
		     <sec:authorize access="isAuthenticated()">
		     	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		     </sec:authorize> 
		     <sec:authorize access="!isAuthenticated()">
		     	<li><a href="${pageContext.request.contextPath}/fctutorial">Create your own flashcards!</a></li>
		     </sec:authorize>
		</ul>
		<div class="icon" onclick="toggleResponsive()">
	    	<i class="fa fa-bars"></i>
	 	</div>
	</div>
</div>
	
	<div id="loading" hidden="true"></div>
	
</body>

<script type="text/javascript">

/////////////// remove 8443 from url /////////////////////////////////
if(window.location.href.includes("kishoreanand.com:8443")){
	window.open(window.location.href.replace('kishoreanand.com:8443','kishoreanand.com'));
}
//////////////////////////////////////////////////////////////////////



/////////////// navbar script /////////////////////////////////////////

$(document).ready(function () {
        var url = window.location;
        $('ul.nav a[href="'+ url +'"]').parent().addClass('active');
        $('ul.nav a').filter(function() {
             return this.href == url;
        }).parent().addClass('active');
        
        fixContainerMargin();

		loadBlogsList();

		loadStreams();
		
        
    });

$(window).resize(function() {
	fixContainerMargin();
});

/* When the user scrolls down, hide the navbar. When the user scrolls up, show the navbar */
var header = $('#topFixedNav'),
headerHeight = header.height(),
treshold = 0,
lastScroll = 0;

$(document).on('scroll', function (evt) {
    var newScroll = $(document).scrollTop(),
        diff = newScroll-lastScroll;

    // normalize treshold range
    treshold = (treshold+diff>headerHeight) ? headerHeight : treshold+diff;
    treshold = (treshold < 0) ? 0 : treshold;

    header.css('top', (-treshold)+'px');

    lastScroll = newScroll;
});



/* Toggle between adding and removing the "responsive" class to topnav when the user clicks on the icon */
function toggleResponsive() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}

function fixContainerMargin(){
	$('.container').css('margin-top', $('#topFixedNav').height())
}

function loadBlogsList() {
	$.get( "${pageContext.request.contextPath}/bloguser/blogs", function(data) {
		if(Array.isArray(data)){
			//alert(data.length);
			var blogListHtml = '';
			for(i=0; i<data.length; i++){
				blogListHtml += '<li class="navSubmenu"><a href="${pageContext.request.contextPath}/bloguser/blog/'+data[i].blog_id+'">'+data[i].blog_name+'</a></li>'
			}
			$('#blogsList').html(blogListHtml);
			
		} else {
			console.log('Blogs could not be loaded');
		}
	});
}

function loadStreams() {
	$.get( "${pageContext.request.contextPath}/streamList", function(data) {
		if(Array.isArray(data)){
			//alert(data.length);
			var streamListHtml = '';
			for(i=0; i<data.length; i++){
				streamListHtml += '<li class="navSubmenu"><a href="${pageContext.request.contextPath}/topics?stream_id='+data[i].stream_id+'">'+data[i].stream_name+'</a></li>'
			}
			$('#streamList').html(streamListHtml);
			
		} else {
			console.log('Streams could not be loaded');
		}
	});
}

///////////////////////////////////////////////////////////////////
    
    
///////// password and confirm password ////////////
    
    $('#password').on('change', function(){
    	validatePassword();
    });

    $('#confirmPassword').on('keyup', function(){
    	validatePassword();
    });

    function validatePassword() {
    	var password = $('#password').val();
    	var confirmPassword = $('#confirmPassword').val();
    	
    	if(password != confirmPassword){
    		$('#confirmPassword').get(0).setCustomValidity("Passwords don't match");
    	} else {
    		$('#confirmPassword').get(0).setCustomValidity("");
    	}
    }
    
//////////////////////////////////////////////////



////////////////// loader //////////////////////////

	function hideLoader() {
	   $('#loading').hide();
	}
	
	$(window).ready(hideLoader);
	// Strongly recommended: Hide loader after 20 seconds, even if the page hasn't finished loading
	setTimeout(hideLoader, 20 * 1000);
	
	function showLoader(){
		$('#loading').show();
		setTimeout(hideLoader, 20 * 1000);
	}

////////////////////////////////////////////////////

////////////// file upload form validation //////////////////////

$(function(){
    var fileInput = $('.upload-file');
    var maxSize = fileInput.data('max-size');
    $('.upload-form').submit(function(e){
        if(fileInput.get(0).files.length){
            var fileSize = fileInput.get(0).files[0].size; // in bytes
            if(fileSize>maxSize){
                alert('file size is more then ' + maxSize + ' bytes');
                return false;
            }else{
                //alert('file size is - '+fileSize+' bytes');
            }
        }else{
            alert('choose file, please');
            return false;
        }

    });
});

///////////////////////////////////////////////////////////////
    
</script>

</html>
