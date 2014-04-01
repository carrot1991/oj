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
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">

function myStatus(value){
	switch(value){
		case 0: return "被选中";
		case 1: return "已销售";
		case 2: return "已完成";
		case 3: return "已删除";
	}
	return value;
}

var i=1;
function xl(){
	var s=i;
	i++;
	return s;
}

$(function(){
	var url="shopord/findOrd?isZj=1";
	var clums=[  
				  {name:'订单号',field:'ddh',width:100,fun:myStatus},
				  {name:'备注',field:'beizhu',width:100},
				  {name:'状态',field:'status',width:100,fun:myStatus},
				  {name:'下单时间',field:'createtime1',width:100}
		      ];
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		itemClass:['item1','item2'],
		fields:clums,
		isSerialNo:true,
		operates:{width:200, value:[
	                                {name:'查看订单详细', fun:function(row){
	                                	update('订单详细','pages/buy/myOrdersDetail.jsp?id='+row.id);
	                                }},
	                                {name:'取消定单', fun:function(row){
	                                	if(confirm('确认要取消定单吗?')){
		                                	$.get("shopord/removeOrd",{'id':row.id},function(json){
		                                		  if(json.success=='success'){
	  	                                  		  	  alert("取消成功！");
	  	                                  		  	  refresh();
	  	                                  	  	  }else{
			                                  		  alert("系统异常:"+json.success);
			                                  	  }	      	                                  	  
	    	                                    });
		                                	}
	                                }}
                             	  ]
                  }
	});
 
});
 
    function refresh(){
		   $("#table").dataRefresh();
		   $.unblockUI();
	}
	
	function closed(){
		   $.unblockUI();
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
   <span class="div">我的订单列表</span>
</div>
<div  style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>