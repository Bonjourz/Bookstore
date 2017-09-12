$(function() {

	$("#save").click(function(e) {
		var num = $("input[name='num']").val();
		console.log(num);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		jQuery.ajax({
			url : 'updateCartPro',
			processData : true,
			dataType : "text",
			data : {
				id : id,
				num : num
			},
			success : function(data) {
				console.log(id);
				bootbox.alert({
					message : 'Modify Successfully! '
						+ 'PS: No change if foreign key does not exist!',
						callback : function() {
						location.reload();
					}
				});
			}
		});
		$('#modal').modal('hide');
	});

	$(".delete").click(function(e) {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : 'Delete'
				},
				cancel : {
					label : 'Cancel'
				}
			},
			message : 'Sure to delete?',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteCartPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! '
									+ 'PS: No change if foreign key does not exist!',
								callback : function() {
									location.reload();
								}
							});
						}
					});

				}
			}
		});
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='num']").val(dataset.num);
		$("#save").attr("data-id", dataset.id);
		$("#input-num").attr("max",dataset.stock);
		$('#modal').modal('show');
	});
	
	$("#input-num").on('input',function(e){  
		$('#warn').html("");
		var stock = Number($("#input-num").attr("max"));
		var num = Number($("input[name='num']").val());
		if (num > stock) {
			$("input[name='num']").val(stock);
			$('#warn').html("The max num is "+stock+"!");
		}
	});
	
	$('#makeOrders').click(function(e) {
		obj = document.getElementsByName("select");
	    check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	    console.log(check_val);
	    if (check_val.length == 0) {
	    	alert("You must select at least one book!");
	    	return;
	    }
	    
	    /* Get the address of the buyer */
	    var dataset = e.currentTarget.dataset;
		var id = dataset.id;
	    jQuery.ajax({
			url : 'ListAddress',
			processData : true,
			dataType : "text",
			data : {
				buyerId : id
			},
			success : function(data) {
				$('#address').modal('show');
				data = eval("("+data+")");
				data = JSON.parse(data);
				$.each(data, function (name, ele) {
					$('#addressList').append("<input type=\"radio\" name=\"address\"" +
							" value=\"" + ele + "\"/><label>" + ele + "</label><br>");
				});
			}
		});
	});
	
	$('#close').click(function(e) {
		$('#addressList').html("");
	});
	
	$('#generateOrder').click(function(e) {
		var addr = $('input:radio[name="address"]:checked').val();
		if (addr == null) {
			alert("Please choose one address");
			return;
		}
		console.log(check_val);
		jQuery.ajax({
			url : 'generateOrder',
			processData : true,
			dataType : "text",
			data : {
				cartId : JSON.stringify(check_val),
				address : addr
			},
			success : function(data) {
				location.reload();
			}
		});
	});
});