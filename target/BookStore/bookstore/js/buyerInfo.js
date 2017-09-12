$(function() {
	$('#pwd').click(function(e){
		$('#ChangePassword').modal('show');
	});
	
	$('#change').click(function(e){
		var dataset = e.currentTarget.dataset;
		var buyerid = dataset.id;
		var oldPwd = $("input[name='old-pwd']").val();
		var newPwd = $("input[name='new-pwd']").val();
		var newPwdConfirm = $("input[name='new-pwd-confirm']").val();

		if (newPwd != newPwdConfirm) {
			$('#warn').html("The two passwords you input is not the same");
		}
			
		else {
			jQuery.ajax({
				url : 'ModifyPwd',
				processData : true,
				dataType : "text",
				data : {
					buyerId : buyerid,
					oldPwd : oldPwd,
					newPwd : newPwd
				},
				success : function(data) {
					if (data == '"fail"') {
						$('#warn').html("Incorrect password!");
					}
					else {
						bootbox.alert({
							message : 'Change Successfully! ',
							callback : function() {
								location.reload();
							}
						});
					}
				}
			});
		}
	});
	
	$('#new-pwd').on('input', function(e){
		$('#warn').html("");
	});
	
	$('#new-pwd-confirm').on('input', function(e){
		$('#warn').html("");
	});
	
	$('#old-pwd').on('input', function(e){
		$('#warn').html("");
	});
	
	$('#addr').click(function(e){
		$('#address').modal('show');
		var dataset = e.currentTarget.dataset;
		var buyerid = dataset.id;
		jQuery.ajax({
			url : 'ListAddress',
			processData : true,
			dataType : "text",
			data : {
				buyerId : buyerid
			},
			success : function(data) {
				var json = eval("("+data+")");
				var json = JSON.parse(json);
				
				var index = 1;
				$.each(json, function (name, ele) {
					$("#tbody").append("<tr><th>"+index+"</th><th>"+ele+
							"</th><th><button class=\"btn btn-default delete\" value=\""+buyerid+"\" type=\"button\"" + 
								"name=\""+ele+"\" onclick=\"addr(this)\"><i class=\"fa fa-trash\"></i></button></th></tr>");
					index ++;
				});
			}
		});
	});
	
	
	$('#close').click(function(e){
		$('#tbody').html("");
	});
	
	$('.edit').click(function(e) {
		var addr = $("input[name='add-address']").val();
		if (addr == "") {
			alert("Address cannot be empty!");
			return;
		}
		
		jQuery.ajax({
			url : 'AddAddress',
			processData : true,
			dataType : "text",
			data : {
				buyerId : id,
				address : addr
			},
			success : function(data) {
				if (data == '"Duplicate Address!"') {
					bootbox.alert({
						message : 'Duplicate Address!',
						callback : function() {
							$("input[name='add-address']").val("");
						}
					});
				}
				
				else {
					bootbox.alert({
						message : 'Success！',
						callback : function() {
							$("input[name='add-address']").val("");
							$('#close').trigger("click");
						}
					});
				}
			}
		});
	})
});

function addr(e) {
	var addr = e.name;
	jQuery.ajax({
		url : 'DeleteAddress',
		processData : true,
		dataType : "text",
		data : {
			buyerId : id,
			address : addr
		},
		success : function(data) {
			$('#close').trigger("click");
		}
	});
}

