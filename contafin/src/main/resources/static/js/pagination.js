$(document).ready(function() {
	var page = 1;
	$("#addUsers").click(function(){
			//Hide button and show spinner
	        $(this).hide();
	        $('#loading').html('<img src="img/spinner.gif" width="80px" height="80px"/>');		
	        //Data upload
			$.ajax({
				url: ('Admin/loadUsers/'+page),
				dataType: 'html',
				success: function(html) {
					$('#userTable').append(html);
					$(this).hide();
					page++;
				}
			});
			//Show button and hide spinner
			$(this).show();
			$('#loading').hide(); 
	 });
	
});

