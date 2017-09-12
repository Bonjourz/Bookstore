$(function() {

	$("#save").click(function(e) {
		var userid = $("#userid").val();
		console.log(userid);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		var status = $("#status").val();
		jQuery.ajax({
			url : 'updataMyOrder',
			processData : true,
			dataType : "text",
			data : {
				id : id,
				status : status
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
						url : 'deleteOrderPro',
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

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("#userid").val("");

		$("#save").attr("data-id", "");
		$('#addOrders').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("ChangeStatus");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("#userid").val(dataset.userid);
		$("#status").val(dataset.status);
		
		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});
	
	$(".item").click(function(e){ 
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		jQuery.ajax({
			url : 'myOrderitemsAction',
			processData : true,
			dataType : "text",
			data : {
				orderId : id
			},
			success : function(data){
				console.log(id);
				var json = eval("("+data+")");
				var json=JSON.parse(json);
				
				$("#orderItems").modal('show');
				$("#itemsTitle").html("Orderitems of Order ID:" + id);
				
				var index = 1;
				$.each(json, function (name, ele) {
					$.each(ele, function(idx, value) {
						$("#tbody").append("<tr><th>"+index+"</th><th>"+value.bookname+
								"</th><th>"+(Number(value.totalPrice)/100/Number(value.num)).toFixed(2)+
								"</th><th>"+value.num+"</th><th>"+(Number(value.totalPrice)/100).toFixed(2)+
								"</th></tr>");
						index ++;
					});
				});
			}
		});
	});
	
	$("#close-button").click(function(e){
		$("#tbody").html("");
	});
});




