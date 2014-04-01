var sbcd=null;
var intv=6;
$(function(){
	sbcd=$.query.get("id");
	getTable();
	var y = new Date().getFullYear();
	for ( var i = 2013; i <= y; i++) {
		var option = $('<option value='+i+'>' + i + '</option>');
		if (i == y )
			option.attr("selected", true);
		$("#year").append(option);
	}
	
	$("#year").change(function(){
		getPlan($(this).val(),intv);
	});
	
	$("#interval").change(function(){
		intv=parseInt($(this).val());
		getPlan($("#year").val())
	});
	getPlan($("#year").val());
	
	$.getJSON("rb/detail",{id:sbcd},function(data){
		if(data==null)
			return;
		$("#sbHover").html(data.riverBasin.name);
	});
	
	 $("#fxt").click(function(){
			parent.window.findFXT($(this).attr("val")); 
  });
});

function getPlan(year){
	$.getJSON("modelResult/fetPlans",{y:year,intv:intv,sbcd:sbcd},function(data){
		var result=$(".result").empty();
		if(data==null){
			return;
		}
		if(data.length==0){
			$.alert("该条件下无预报结果!",'预报结果');
			return;
		}
			
		$.each(data,function(i,n){
			var span=$("<span class=\"list\" val=\""+n.id+"\">"+n.calcTime+"</span>");
			if(i==0){
				span.addClass("selected");
				drawModelResultChart(n.id,sbcd,true);
			}
			span.toHover("","hover");
			span.click(function(){
				result.find("span").removeClass("selected");
				span.addClass("selected");
				drawModelResultChart($(this).attr("val"),sbcd,true);
			});
			result.append(span);
		});
	});
}
