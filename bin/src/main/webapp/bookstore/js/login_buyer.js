$(function() {
	$("#submit").click(function(e){
		var email = $("input[name='email']").val();
		var password = $("input[name='password']").val();
		if(email == "" || password =="")
			alert("The email and pwd cannot be empty!");
		else {
			jQuery.ajax({
				url : 'checkLogin_buyer',
				processData : true,
				dataType : "text",
				data : {
					email : email,
					password : password
				},
				success : function(data) {
					var data = eval("("+data+")");
					if(data == "success") {
						location.href="index";
					}
					else {
						$('#alarm').html("Invlalid email or pwd!");
					}
				}
			})
		}
	});
	
	
	$("input").on('input',function(e){  
		$('#alarm').html("");
	});
});