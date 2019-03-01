<div class="container-fluid footerNav">
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4">
		</div>
		<div class="col-xs-4">
			<!-- <span><small>developed by </small>Kishore Anand</span> -->
		</div>
	</div>
</div>


<script type="text/javascript">

	var ctx = '${pageContext.request.contextPath}';
	
	function openPage(uri) {
		$('#loading').show();
		window.open(ctx + uri, "_self");
	}
	
	
	$(".open-button").on("click", function() {
	  $(this).closest('.collapse-group').find('.collapse').collapse('show');
	});

	$(".close-button").on("click", function() {
	  $(this).closest('.collapse-group').find('.collapse').collapse('hide');
	});
	

	$(document).on("click", ".deleteCardGlyph", function () {
		
	    //get data-id attribute of the clicked element
	    var deleteId = $(this).attr("data-deleteId");
	    var deleteStream = $(this).attr("data-deleteStream");
	    
	    $("#deleteCardModal #deleteId").val(deleteId);
	    $("#deleteCardModal #deleteStream").val(deleteStream);
	});

	//editCardGlyph
	$(document).on("click", ".editCardGlyph", function () {
		var cardId = $(this).attr("data-cardId");
		$('#loading').show();
		window.open(ctx + "/editFlashcard?cardId="+cardId, "_self");
	});

	
	function hideLoader() {
	    $('#loading').hide();
	}
	$(window).ready(hideLoader);
	// Strongly recommended: Hide loader after 20 seconds, even if the page hasn't finished loading
	setTimeout(hideLoader, 20 * 1000);
	
	
/* 	$("code").each(function(index) {
		  CodeMirror($(this).get(0), {
			  mode:  "javascript"
			});
		  $(this).click();
		}); */
		
		//CodeMirror.fromTextArea($("#back").get(0));
	
</script>