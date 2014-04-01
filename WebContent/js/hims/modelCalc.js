var sbcd = null;// 站点ID
var subBasinData = null;// 子流域ID
var intv = null;
var hmc = {
	"futurePCP" : {},// 未来天气
	"futurePET" : {},// 蒸发
	"futureTmp" : {},// 温度
	"param" : {},// 参数
	"scInit" : {}
// 初始值
};
function getTime(intv) {
	$.getJSON("serverTime/defaultStEt", {
		"intv" : intv
	}, function(data) {
		$('#st').val(data.st);
		$('#et').val(data.et);
		// checkEt(intv, data.et, data.st);
	});
}

function hourDateStartPicker(event) {
	WdatePicker({
		dateFmt : 'yyyy-MM-dd HH',
		maxDate : '%y-%M-%d',
		onpicked : function() {}
	});
}

function hourDateEndPicker(event) {
	var date={dateFmt:'yyyy-MM-dd HH',minDate:'#F{$dp.$D(\'st\')}',maxDate:'%y-%M-%d {%H+'+(intv*7)+'}'};
	date.onpicked=function(){
		 checkEt(intv,$(this).val(),$("#st").val());
	};
	WdatePicker(date);
}

function getFuture(){
	var st=$('#st').val();
	var et=$('#et').val();
	$.getJSON("serverTime/checkEt",{"intv":intv,"et":et,"st":st},function(data){
		if(!data.afterCurrent)
			return;
		var th=$("#pcp .th").empty().append("<span class=\"first\">子流域信息</span>");
		th.nextAll().remove();
		var w=162;
		$.each(data.futureTime,function(i,n){
			var td=$('<div class="td"><div class="div time24">'+n+'</div><div class="div s24"><span>降水信息</span><span>最高温度</span><span class="last">最低温度</span></div></div>');
			$.each(subBasinData,function(ii,nn){
				if(i==0){
					w=w+81;
					th.append("<span class='pcp_sbcd' val='"+nn.sbcd+"'>"+nn.name+"</span>");
				}
				var PCPInput=$('<span contenteditable="true" time="'+n+'" class="PCPInput'+(interval==24?'':'last')+'"></span>');
				var TMPMax=$("<span class=\"TMPMax\" contenteditable=\"true\"></span>");
				var TMPMin=$("<span contenteditable=\"true\" class=\"last TMPMin\"></span>");
				var div=$('<div class="div sbcd'+nn.sbcd+'" ></div>');
				div.append(PCPInput);
				div.append(TMPMax);
				div.append(TMPMin);
				td.append(div);
			});
			td.append('<div class="clearfix"></div>');
			td.find(".PCPInput").keyup(function(){$.clearNoNum(this,"html");}).blur(function(){setPCP(this,td,".PCPInput");});
			td.find(".TMPMax").keyup(function(){$.clearNoNum(this,"html");}).blur(function(){setPCP(this,td,".TMPMax");});
			td.find(".TMPMin").keyup(function(){$.clearNoNum(this,"html");}).blur(function(){setPCP(this,td,".TMPMin");});
			$("#pcp .info").append(td);
		});
		
		if(w>693)
			$("#pcp .info").css("width",w+"px");
	});
}

function setPCP(e,p,s){
	var el=$(e);
	p.find(s).each(function(){
		if($(this).html()==""){
			$(this).html(el.html());
		}
	});
}

function checkEt(intv,et,st){
	$.getJSON("serverTime/checkEt",{"intv":intv,"et":et,"st":st},function(data){
		if(!data.afterCurrent){
	
			$("#future_pcp_state").attr( "checked",false )
			$("#future_pcp_state").attr("disabled",true);	
		}else{
			$("#future_pcp_state").attr("disabled",false);	
		}
	 });
}

function calculate(){
	 var st = $('#st').val();
	 var et = $('#et').val();
	 hmc['sbcd']=sbcd;
	 if(parseInt(intv)==24){
		 hmc['st'] = st.split(" ")[0]+" 00";
		 hmc['et'] = et.split(" ")[0]+" 00";
	 }else{
		 hmc['st'] = st;
		 hmc['et'] = et;
	 }
	 hmc['intv'] = intv;
	 
	 hmc['currentCounty']=$("#currentCounty").prop("checked");
	
	 var b=false;
	 /*考虑未来气象*/
	 if($("#future_pcp_state").prop("checked")){
		 $("#pcp .th .pcp_sbcd").each(function(){
			 var val=$(this).attr("val");
			 hmc.futurePCP[val] = new Array();
			 hmc.futureTmp[val] = new Array();
			 $("#pcp .sbcd"+val).each(function(){
				 var PCPInput=$.trim($('.PCPInputlast',this).html());
				 var PCPTime=$('.PCPInputlast',this).attr("time");
				 hmc.futurePCP[val].push({"pcp":PCPInput.length==0?0:PCPInput,"date":PCPTime});
				 var TMPMax=$.trim($('.TMPMax',this).html());
				 var TMPMin=$.trim($('.TMPMin',this).html());
				 if(parseInt(TMPMax)<parseInt(TMPMin)){
					 b=true;
				 }
				 hmc.futureTmp[val].push({"max":TMPMax.length==0?0:TMPMax,"min":TMPMin.length==0?0:TMPMin});
			 });
		 });
	 }
	
	 if(b){
		 $.alert("请您检查下，是否最高温度比最低温度值高？",'水情预报');
	    return;
	 }
	 
	 var jsonStr = JSON.stringify(hmc);
	 hmc = {"futurePCP":{},"futurePET":{},"futureTmp":{},"param":{},"scInit":{}};
	 $.loadA('正在进行计算 ..')
	 $.ajax({
		 url:"model/doModelCalc",
		 type:"POST",
		 data:{"jsonParam":jsonStr},
		 success:function(data){
			 $("#pcp,#futurePcpOverlay").hide();
			 $("#fbBtn").show();
			 //结果发布
			 $("#fbBtn").click(function(){
				 $.getJSON("model/savePlan",{planId:data.success},function(r){
					 if(r.success=='success')
						 $.alert("预报结果保存成功！",'保存预报结果',true);
					 else
						 $.alert(r.success,'保存预报结果');
				 });
			 });
			 //画图
			 drawModelResultChart(data.success,sbcd,true);
			 $.unblockUI();
		 },
		 error:function(data){
			 $.alert("计算过程出错了，请检查您选择的时间段是否有数据或参数是否正常？",'水情预报');
		 }
	 });
	 
}

$(function() {
	sbcd = $.query.get("id");
	intv = $("#interval").val();
	getTime(intv);
	getTable();
	$.getJSON("modelInstance/rbList",{sbcd:sbcd},function(data){
		 subBasinData=data;
		 $("#sbHover").html(data[0].name);
	 });
	
	$('#st').bind('click', hourDateStartPicker);
	$('#et').bind('click', hourDateEndPicker);
	$("#interval").change(function() {
		interval = $(this).val();
		getTime($(this).val());
	});
	 $("#future_pcp_state").click(function(){
		 if($(this).attr("checked")){
			 getFuture();
			 $("#pcp").show();
		 }else{
			 $("#pcp").hide();
		 }
	 });
	 
	 $("#ybBtn").click(function(){
		 calculate();
	 });
});



