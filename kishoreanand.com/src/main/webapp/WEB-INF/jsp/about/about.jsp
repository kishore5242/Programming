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
				<h4 class="notCard">Why flashcards?</h4>
				<p>The human brain consists of about one billion neurons. And some studies say it can store upto 
				2 petabytes of data on an average. But why do we feel hard to even remember the names of the people 
				we met or their phone numbers. Lets not think more about that for the fact that we can only 
				remember what we really need to and we are fine with it. But sometimes situations in our lives force 
				us to remember more. Like for instance one need to remember multiplication tables at least till number 10.
				So how do we memorise? what is the best way? Well there are many ways to do it. One such way is by using 
				"flashcards".
				</p>
				<p>
				So what are flashcards?
				A flashcard or flash card is a card bearing information on both sides, which is intended to be used as an 
				aid in memorization. Each flashcard bears a question on one side and an answer on the other. Flashcards are 
				often used to memorize vocabulary, historical dates, formulae or any subject matter that can be learned via 
				a question-and-answer format. Flashcards can be virtual (part of a flashcard software), or physical.
				- says Wikipedia
				</p>
				<p>
				They basically help us remember more with less memory in our brain. We don't have to keep thinks on top of 
				our head all the time. flashcards help us exercise art of recollection. Our brains store huge information deep 
				within. Most of the time it is hard to get it out. With flashcars we can sort of make connections to our deep 
				memory and access it easily whenever needed. front side of the card is like a "key" and back side is the memory  
				stored deep within our brain. We just have to remember "keys" on top of our head.  
				</p>
				
				<br>
				<br>
				<h4 class="notCard">kishoreanand.com</h4>
				<p>
				This website is a hobby project I started in 2019. It is solely developed and maintained by me.
				For any suggestion, feedback or bug reporting please feel free to email me at 
				<a href = "mailto: kishore8764@gmail.com">kishore8764@gmail.com</a><br><br>
				<a href="${pageContext.request.contextPath}/blogadmin">Edit site contents</a>
				</p>
			</div>	
		</div>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-4">
				<img src="${pageContext.request.contextPath}/img/profile-pic.jpg" class="img-circle profile-pic" alt="Kishore Anand" >
			</div>
			<div class="col-xs-8">
				<h4 class="notCard">Kishore Anand</h4>
				<p>I am a passionate programmer who code to express creativity and thought process. I work as a full-time Software Engineer
				for a reputed company. 
				</p>
				<p>
				Email: <a href = "mailto: kishore8764@gmail.com">kishore8764@gmail.com</a>
				</p>
			</div>		
		</div>
	</div>
	
	<br>
	<br>
	
	<jsp:include page="/WEB-INF/jsp/footer/footer.jsp" />

</body>
</html>