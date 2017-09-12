$(function() {
	$('#overview').click(function(e) {
		jQuery.ajax({
			url : 'getOverViewStatics',
			processData : true,
			dataType : "text",
			data : {},
			success : function(record) {
				var json = eval("("+record+")");
				var array = new Array();
				var max = 0;
				if (json == "null") {
					var array = [];
				}
				
				else {
					var index = 0;
					json = JSON.parse(json);
					$.each(json, function (name, ele) {
						if (ele > max) 
							max = ele;
						
						var myMap = new Object();    //对象
						myMap['name'] = name;
						myMap['value'] = ele;
						myMap['color'] = '#a5c2d5';    
						array[index] = myMap;
						index++;
					});
				}
				
				var chart = new iChart.Column2D({
					render : 'canvasDiv',//渲染的Dom目标,canvasDiv为Dom的ID
					data: array,//绑定数据
					title : 'Total Statics',//设置标题
					width : 800,//设置宽度，默认单位为px
					height : 400,//设置高度，默认单位为px
					shadow:true,//激活阴影
					shadow_color:'#c7c7c7',//设置阴影颜色
					coordinate:{//配置自定义坐标轴
						scale:[{//配置自定义值轴
							 position:'left',//配置左值轴	
							 start_scale:0,//设置开始刻度为0
							 end_scale:max,//设置结束刻度为max
							 scale_space:max / 10,//设置刻度间距
							 listeners:{//配置事件
								parseText:function(t,x,y){//设置解析值轴文本
									return {text:t+" ￥"};
								}
							}
						}]
					}
				});
				//调用绘图方法开始绘图
				chart.draw();
			}
		});
	});
	
	$('#type').click(function(e) {
		jQuery.ajax({
			url : 'getTypeStatics',
			processData : true,
			dataType : "text",
			data : {},
			success : function(record) {
				var json = eval("("+record+")");
				var array = new Array();
				var max = 0;
				var index = 0;
				json = JSON.parse(json);
				$.each(json, function (name, ele) {	
					var myMap = new Object();    //对象
					myMap['name'] = name;
					myMap['value'] = ele;
					myMap['color'] = '#' +  String.fromCharCode(97+index) + '5c2d5';   
					array[index] = myMap;
					index++;
				});
				
				new iChart.Pie2D({
					render : 'canvasDiv',
					data: array,
					title : 'Top 5 Browsers from 1 to 29 Feb 2012',
					legend : {
						enable : true
					},
					showpercent:true,
					decimalsnum:2,
					width : 800,
					height : 400,
					radius:140
				}).draw();
			}
		});
	});
});
