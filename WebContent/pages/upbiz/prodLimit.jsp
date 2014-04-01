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
<title>产品上架限制</title>
<base href="<%=basePath %>">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">

var prodDict;

$(function(){
	
	  $.getJSON("proddef/getDict",{random: Math.random()},function(json){
		  if(json!=null){
			  prodDict = json;
		  }
	  });
	    
	$("#table").dataGrid({
		url : "prodLimit/listPager",
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:[   
		          {name:'产品',field:'prodid',width:160,fun:function(val){return prodDict[''+val+''];}},
		          {name:'价格调整幅度(%)',field:'adjustrate',width:120,fun:bai},
		          {name:'当天价格调整幅度(%)',field:'dayadjustlimit',width:130,fun:bai},
		          {name:'数量上限',field:'amountlimit',width:120}
		          ],
	    operates:{width:130, value:[
	               {name:'修改', fun:function(row){update('修改限制','pages/upbiz/updateProdLimit.jsp?totalStr='+prodDict[''+row.prodid+'']+'&id='+row.prodid); } },
	               {name:'解除', fun:function(row){disable(row.prodid);}},
	                                ]
	             }
	});

	$(".add").click(function(){
		update('新增商品','pages/upbiz/updateProdLimit.jsp');
	});

	//100*row.dayadjustlimit;
	function bai(val){
		return Math.floor(100*val);
	}

	function xiaoshu(val){
		return val/100;
	}
});
 
function disable(id){
	var content={text:'是否解除限制?',fun:function(){
		 $.post("prodLimit/delete",{prodid:id},function(json){
			   $.unblockUI();
			    refresh();
		   });
	}};
	$.confirm(content,'解除限制');  
}
 
   function refresh(){
	   $("#table").dataRefresh();
	   $.unblockUI();
   }
   
   function closed(){
	   $.unblockUI();
   }
   
   function update(title,url){
	   $.layer({
		   width:380,
		   height:310,
		   isZY:true,
		   moveLayer:true,
		   parent:'keepsoft_1_',
           content:[{name:title,url:url}]
		});
   }
 
 function myeval(id){
		alert(id);
		return prodDict[''+id+''];
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
   <span class="div">产品价格、数量限制</span>
   <span class="add">新增产品价格、数量限制</span>
</div>
<div style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>