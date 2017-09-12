$(function() {
	$("#submit").click(function(e){
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		if(username == "" || password =="")
			alert("The name and pwd cannot be empty!");
		else {
			jQuery.ajax({
				url : 'checkLogin_admin',
				processData : true,
				dataType : "text",
				data : {
					username : username,
					password : password
				},
				success : function(data) {
					var data = eval("("+data+")");
					if(data == "success") {
						location.href="index";
					}
					else {
						$('#alarm').html("Invlalid username or pwd!");
					}
				}
			})
		}
	});
	
	
	$("input").on('input',function(e){  
		$('#alarm').html("");
	});
});