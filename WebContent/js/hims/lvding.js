var sbcd=null;
var modelType=1;
var intv=1;
$(function(){
	sbcd=$.query.get("id");
	
	$("#model").change(function(){
		intv=$(this).val();
		modelType=$(this).val();
		$(".left .name span").html($(this).find("option[value='"+$(this).val()+"']").html());
		$("#table").dataRefresh({
			"modelType":$(this).val(),
			sbcd:sbcd
		});
	});
	
	getParmTable(modelType);
	
	$("#do").click(function(){
		doParam();
	});
	
	 $('#st').bind('click', hourDateStartPicker);
	 $('#et').bind('click', hourDateEndPicker);
	 
	 $("#save").click(function(){
		 var paramVal={};
		 var b=true;
		 $(".enName").each(function(){
			 var _v=$.trim($(this).html());
			 if(_v==''){
				 b=false;
			 }
			 paramVal[$(this).attr("f")]=_v;
		 });
		 paramVal.coefficient=$("#xiaolv font:eq(0)").html();
		 if(!b){
			 $.alert("参数不能为空！",'参数率定');
			 return;
		 }
		 var paramValue=JSON.stringify(paramVal);
         $.post("modelInstance/save",{basinId:sbcd,modelType:modelType,paramValue:paramValue},function(json){
        	 if(json.success=='success'){
	       		 $.alert("参数保存成功!",'参数率定',true)
	       	  }else{
	       		  $.alert(json.success,'参数率定');
	       	  }
         });
	 });
	 
});

/*模型计算*/
function calculate(){
	var st = $('#st').val();
	var et = $('#et').val();
	var hmc = {"param":{}};
	hmc['sbcd']=sbcd;
	if(parseInt(intv)==24){
		hmc['st'] = st.split(" ")[0]+" 00";
		hmc['et'] = et.split(" ")[0]+" 00";
	}else{
		hmc['st'] = st;
		hmc['et'] = et;
	}
	hmc['intv'] =intv ;
	var paramVal={};
	 var b=true;
	 $(".enName").each(function(){
		 var _v=$.trim($(this).html());
		 if(_v==''){
			 b=false;
		 }
		 paramVal[$(this).attr("f")]=_v;
	 });
	 if(!b){
		 $.alert("参数不能为空！",'参数率定');
		 return;
	 }
	 hmc.param[sbcd]=paramVal;
	 var jsonStr = JSON.stringify(hmc);
	 var hmc = {"param":{}};
	 $.laodA('正在率定当中....')
	 $.ajax({
			url:"model/doModelCalc",
			type:"POST",
			data:{"jsonParam":jsonStr},
			success:function(data){
				drawModelResultChart(planId,sbcd,false);
				$.unblockUI();
			},
			error:function(data){
				$.alert("参数率定出错了,请检查选择的时段中是否拥有数据？",'参数率定');
			}
		});
}

function doParam(){
	var hmc = {};
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
	var jsonStr = JSON.stringify(hmc);
	
	var paramVal={};
	var b=true;
	$("input[name='dataId']:checked").each(function(){
		var span=$(this).parent().siblings(".enName");
		var _val=$.trim(span.html());
		if(_val==''){
			b=false;
		}
		paramVal[span.attr("f")]=_val;
	});
	
	if(!b){
		$.alert("选中的参数没有值!",'参数率定');
		return;
	}
	var paramValue=JSON.stringify(paramVal);
	var load='<div id="load" style="border: solid 3px #939393;line-height: 60px;font-size: 14px;background: #fff; text-align: left;"><div style="width: 32px;height: 32px;float: left;background: url(\'theme/images/load.gif\') no-repeat left center ;margin: 15px 0 15px 10px ;padding-right: 5px;"></div>  参数率定当中...</div>'
	$.blockUI({ message:load});
	$.ajax({
		url:"model/doParamCalc",
		type:"POST",
		data:{"jsonParam":jsonStr,fixedParamsStr:paramValue,num:$("#num").val()},
		success:function(data){
			if(data.errStr!=null){
				$.alert(data.errStr,'参数率定');
				return;
			}
			$.unblockUI();
			drawChart(data.chart);
			$("#xiaolv font:eq(0)").html(((data.maxNash)*100));
			var params=data.bestParam;
			$(".enName").each(function(){
				var v=params[$(this).attr("f")];
				$(this).html(v.toFixed(3));
			})
		},
		error:function(data){
			$.alert("参数率定出错了,请检查选择的时段中是否拥有数据？",'参数率定');
		}
	});
	
}

function hourDateStartPicker(event) {
	WdatePicker({dateFmt : 'yyyy-MM-dd HH',
		maxDate : '#F{$dp.$D(\'et\')}'		,
		onpicked:function(){
			checkEt(intv,$("#et"),$(this).val());
		}	
	});
}
function hourDateEndPicker(event) {
	WdatePicker({
		dateFmt : 'yyyy-MM-dd HH',
		minDate : '#F{$dp.$D(\'st\')}',
		maxDate : '%y-%M-%d %H',
		onpicked:function(){
			if($("#st").val()==''){
				$.alert("请先选择开始时间",'参数率定');
				$(this).val("");
				return ;
			}
			checkEt(intv,$(this),$("#st").val());
		}
	});
}

function checkEt(intv,el,st){
//	$.getJSON("serverTime/checkEt",{"intv":intv,"et":$(el).val(),"st":st},function(data){
//		if(intv==24){
//			if(data.total>365){
//				alert("日过程模拟历史数据选择不能大于365天");
//				$(el).val("");
//				return;
//			}
//		}else if(intv==1){
//			if(data.total>3*24){
//				alert("次洪过程历史数据选择不能大于3天");
//				$(el).val("");
//				return;
//			}
//			
//		}
//
//	});
}

function getParmTable(modelType){
	$("#table").dataGrid({
		url : "modelParam/list",
		param:{modelType:modelType,sbcd:sbcd},
		headHeight : 30,
		bodyHeight:319,
		rowHeight:28,
		isPage:false,
		result:null,
		checkbox:true,
		
		//pageHeight:20,
		fields : [{
			name : '中文名称',
			field : 'cnName',
			width :90
		}, {
			name : '编码',
			field : 'enName',
			width : 35,
			fun:formatABC
		}, {
			name : '值',
			width : 50,
			field:"value",
			cls:'f$enName',
			onkeyup:'$.clearNoNum(this,"html");',
			//dataUrl:'modelInstance/getParam?sbcd='+sbcd+'&modelType='+modelType,
			input:true,
			fun:setval
		}]
	});
}
function formatABC(val){
	if(val=='swm')
		return 'SWm'
	if(val=='fc')
		return 'FC'
	return val;
}

function setval(val){
	if(val==null)
		return "0.0";
	return val;
}