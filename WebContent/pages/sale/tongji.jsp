<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<base href="<%=basePath %>">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="<%=path%>/js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">

function myStatus(value){
	switch(value){
		case 0: return "未审核";
		case 1: return "已审核";
		case 2: return "已完成";
		case 3: return "已删除";
	}
	return value;
}

var i=1;
function xl(){
	var s="订单"+i;
	i++;
	return s;
}

$(function(){
	var url="shopord/tongjiForSale";
	 
	
	var clums=[  
				 //{name:'订单号',field:'ddh',width:120},
				  {name:'大类',field:'typename',width:200},
				  {name:'品牌',field:'bandname',width:200},
				  {name:'名称',field:'nickname',width:200},
				  {name:'配置',field:'config',width:200},
				  {name:'待发货总数量',field:'totalamount',width:100} 
				  
		      ];
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		itemClass:['item1','item2'],
		fields:clums,
		isSerialNo:true 
		 
	});
 
});
 
    function refresh(){
		   $("#table").dataRefresh();
		   $.unblockUI();
	}
	
	function closed(){
		   $.unblockUI();
	}
	function search(){
		  var whichDay=$("#whichDay").val();
		  $("#table").dataRefresh( {"whichDay":whichDay} );
	}
	 function update(title,url){
		   $.layer({
			   width:700,
			   height:450,
			   isZY:true,
			   moveLayer:true,
			   parent:'keepsoft_1_',
	           content:[{name:title,url:url}]
			});
	   }
	
</script>
<style type="text/css">
   body{background: #fff;}
   body,p,table,p,img {margin:0; padding:0;font-family:"宋体",Verdana, Arial,Sans; font-size:12px;}
   ul,li{margin:0; padding:0; list-style:none;}
   table,td {border-collapse:collapse;empty-cells:show;}
   .h1{height: 29px;background:#f1f1f1;border-bottom: solid 1px #c6dbee;}
   .h1 span{float:left;height: 24px;line-height: 24px; padding:0 10px;cursor: pointer;margin-top: 4px;display:inline;color: #547392;font-weight: bold;margin-left: 25px;}
   .h1 span.div{background:#ddeefe;border: solid 1px #c7ddeb;border-bottom-width: 0;}
   .h1 span.add{background:url("theme/images/add.gif") no-repeat 0 3px;padding-left: 20px;}
   .keepsoft-table  .tableHeadArea{border-bottom-color:#a0b4c6; }
   .keepsoft-table  .tableHeadArea .input input{margin-top: 8px;}
   .keepsoft-table  .tableBodyArea .input input{margin-top: 5px;}
   .item1{background: #f9fdff;}
   .item2{background: #f1f8fd;}
</style>
</head>
<body>
<div class="h1">
   <span class="div">我的待发货统计清单</span>
</div>
<div>
&nbsp;&nbsp;&nbsp;&nbsp;日期  <input id="whichDay" type="text" onClick="WdatePicker()">
<button id='search' value="搜索" onclick='search()'>切换日期</button> 
</div>
<div  style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>