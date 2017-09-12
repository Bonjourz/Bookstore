$(function() {
	$("#mycart").click(function(e){
		if (status == 3) {
			alert("Please login as a buyer!");
		}
		else if (status == 2) {
			alert("You are adminsrator now, please login as a buyer!");
		}
		
		else {
			location.href = "myCart";
		}
	});
	
	$(".edit").click(function(e){
		if (status == 3) {
			alert("Please login as a buyer!");
		}
		else if (status == 2) {
			alert("You are adminsrator now, please login as a buyer!");
		}
		
		else {
			var dataset = e.currentTarget.dataset;
			var id = dataset.id;
			jQuery.ajax({
				url : 'addBook',
				processData : true,
				dataType : "text",
				data : {
					bookId : id
				},
				success : function(data) {
					bootbox.alert({
						message : data
					});
				}
			});
		}
		
	});
	
	$("#info").click(function(e){
		if (status == 3) {
			alert("Please login!");
		}
		
		else if (status == 2){
			location.href = "allUsersPro";
		}
		
		else if (status == 1){
			location.href = "buyerInfo";
		}
	});
	
	$(".buy").click(function(e){
		if (status == 3) {
			alert("Please login as a buyer!");
		}
		else if (status == 2) {
			alert("You are adminsrator now, please login as a buyer!");
		}
		
		else {
			var dataset = e.currentTarget.dataset;
			$("#buynow").attr("data-id", dataset.id);
			$("#input-num").attr("max",dataset.stock);
			$("input[name='num']").val("");
			$('#modal').modal('show');
		}
	});
	
	$("#buynow").click(function(e){
		var num = $("input[name='num']").val();
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		jQuery.ajax({
			url : 'addBookSingle',
			processData : true,
			dataType : "text",
			data : {
				bookId : id,
				num : num
			},
			success : function(data) {
				location.href = "myOrdersPro";
			}
		});
		
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
	$("#search").click(function(e){
		var key = $("input[name='key']").val();
		location.href = "index?key="+key;
	});
});

