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

function myNo(value){
	var v=$("#ids").val();
	$("#ids").val(v+value+",");
	return "";
}

$(function(){
	var url="shopcart/currentUserCartPage";
	var clums=[  
				  {name:'',field:'id',width:100,fun:myNo},
				  //{name:'用户',field:'uname',width:100},
				  //{name:'型号',field:'uname',width:100},
				  {name:'商品',field:'title',width:300},
				  //{name:'配置',field:'uname',width:100},
				  {name:'下单价',field:'price',width:100},
				  {name:'订购数量',field:'amount',width:100},
				  {name:'',field:'partdetails',width:100},
				  //{name:'属性',field:'prodid',width:50},
				  //{name:'名称',field:'nickname',width:100},
				  
				  {name:'购买时间',field:'createtime',width:150}
		          ];
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		itemClass:['item1','item2'],
		fields:clums,
		dataId:"id",
		isSerialNo:true,
		operates:{width:90, value:[
	                                {name:'删除', fun:function(row){
		                                	if (confirm("确认要删除该项？")) {
		                                		var param={"cartid":row.id};
		                            			$.post("shopcart/removecart",param,function(json){
		  	                                  	  if(json.success=='success'){
		  	                                  			$("#ids").val("");
		  	                                  		    alert("删除成功！");
		  	                                  			refresh();
		  	                                  	  }else{
			                                  		  	alert("删除失败!");
			                                  	  }			                                    
  	                                    		}); 
                                			}
	                                }}
                             	  ]
                  }
	});
 //.attr("disabled","disabled")
});
 
function refresh(){
	   $("#table").dataRefresh(
			   
	   );
	   $.unblockUI();
}
	
	function closed(){
		   $.unblockUI();
	}
 
    function createOrder(){
    	var ids=$("#ids").val();
    	var beizhu=$("#beizhu").val();
    	if(ids!=""){
	    	$.post("shopcart/createOrder",{"cartid":ids,'beizhu':beizhu},function(json){
	          	  if(json.success=='success'){
	          			$("#ids").val("");
	          		    alert("提交成功！");
	          			refresh();
	          	  }else{
	        		  	alert("提交失败!");
	        	  }			                                    
	   		});
    	}else{
    		alert("暂无购物车可提交");
    	}
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
   <span class="div">购物车列表</span>
</div>
	  <input id="ids" type="hidden"/>
	   &nbsp; &nbsp;&nbsp;&nbsp;其它要求: <input id='beizhu'> 
      <input type="button" value="提交购物车"  id="ordButton" onclick="createOrder();" />
<div  style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>