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
<link href="js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
function myPrice(value){
	var str="<input size='5' style='height: 10px;' value='"+value+"'/>";
	return str;
}
function myAmount(value){
	var str="<input size='5' style='height: 10px;' value='"+value+"'/>";
	return str;
}

$(function(){
	var url="goods/listPagerForBiz?random="+Math.random()+"&type=<%=request.getParameter("type")%>&bandId=<%=request.getParameter("bandId")%>";
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:[ 
				  {name:'编号',field:'goodsid',width:50},
				  {name:'供货商',field:'uname',width:50},
				  {name:'名称',field:'nickname',width:100},
				  {name:'价格',field:'price',width:70,fun:myPrice},
				  {name:'数量',field:'amount',width:70,fun:myAmount},
				  
				 // {name:'属性',field:'prodid',width:50},
		          {name:'录入时间',field:'createtime',width:100},
		          {name:'修改待审核?',field:'ispendingchange',width:90,fun:toStatus},
		          {name:'上架待审核?',field:'ispendingup',width:90,fun:toStatus}
		          ],
	    operates:{width:330, value:[
	                               
	                                
	                                {name:'上架√', fun:function(row){
	                                	var id=row.goodsid;
	                                	$.get("goods/examine?status=1&id="+id,{},function(json){
    	                                  	  if(json.success=='success'){
    	                                  		  alert("审核成功！");
    	                                  		  refresh();
    	                                  	  }else if(json.success=='-1'){
		                                  		  alert("审核失败！");
		                                  	  }else{
		                                  		  alert("系统异常！");
		                                  	  }	      	                                  	  
    	                                    });
	                                }},
	                                {name:'上架  X', fun:function(row){
	                                	var id=row.goodsid;
	                                	$.get("goods/examine?status=2&id="+id,{},function(json){
	                                		  if(json.success=='success'){
  	                                  		  	  alert("审核成功！");
  	                                  		  	  refresh();
  	                                  	  	  }else if(json.success=='-1'){
		                                  		  alert("审核失败！");
		                                  	  }else{
		                                  		  alert("系统异常！");
		                                  	  }	      	                                  	  
    	                                    });
	                                }},                         
	                                {name:'变动 √', fun:function(row){
	                                	var id=row.goodsid;
	                                	$.get("goods/examine?status=11&id="+id,{},function(json){
    	                                  	  if(json.success=='success'){
    	                                  		  alert("审核成功！");
    	                                  		  refresh();
    	                                  	  }else if(json.success=='-1'){
		                                  		  alert("审核失败！");
		                                  	  }else{
		                                  		  alert("系统异常！");
		                                  	  }	      	                                  	  
    	                                    });
	                                }},
	                                {name:'变动  X', fun:function(row){
	                                	var id=row.goodsid;
	                                	$.get("goods/examine?status=22&id="+id,{},function(json){
	                                		  if(json.success=='success'){
  	                                  		  	  alert("审核成功！");
  	                                  		  	  refresh();
  	                                  	  	  }else if(json.success=='-1'){
		                                  		  alert("审核失败！");
		                                  	  }else{
		                                  		  alert("系统异常！");
		                                  	  }	      	                                  	  
    	                                    });
	                                }},
	                                {name:'修改价与量', fun:function(row){ 
	                                	var inp=$(this).parent().parent().find("input");
	                                	var price=inp[0].value;
	                                	var amount=inp[1].value;
	                                	var param={"goodsid":row.goodsid,"price":price,"amount":amount,"prodid":row.prodid};
	                                	$.post("goods/update",param,function(json){
	                                  	  if(json.success=='success'){
	                                  		  alert("修改价格与数量成功!");
	                                  	  }
	                                  	  else if(json.success=='-1'){
	                                  		  alert("数量异常!");
	                                  	  }else if(json.success=='-2'){
	                                  		  alert("比上次价格调整超过幅度!");
	                                  	  }else if(json.success=='-3'){
	                                  		  alert("日调整超过幅度!");
	                                  	  }else{
	                                  		  alert("系统异常!");
	                                  	  }	
	                                    });
	                                	
	                                } }
	                                ]
	             }	
	});
	
});

	function toStatus(value){
		switch(value){
		case 1:return "是";
		default:return "";
		}
	}

   function refresh(){
	   $("#table").dataRefresh();
	   $.unblockUI();
   }
   
   function closed(){
	   $.unblockUI();
   }
   /*
   function gotoViewCustomer(row){ 
	   viewCustomer('查看','pages/super/user/customerUpdate.jsp?id='+ row.id);
   }
   function viewCustomer(title,url){
	   $.layer({
		   width:368,
		   height:310,
		   isZY:true,
		   moveLayer:true,
		   parent:'keepsoft_1_',
           content:[{name:title,url:url}]
		});
   }*/
   function update(title,url){
	   $.layer({
		   width:368,
		   height:310,
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
   <span class="div">上架商品列表</span>
</div>
<div  style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>