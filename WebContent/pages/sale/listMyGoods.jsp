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
<%
java.util.Calendar ca = java.util.Calendar.getInstance();
java.util.Date now = ca.getTime();
boolean canUpdate = true;
int hour = now.getHours();
if(hour<=9 && hour>=18){
	canUpdate = false;
}

boolean containTaxMode = false;
int prodUpLimit = -1;
com.oj.jxc.commons.LoginedUser loginedUser = (com.oj.jxc.commons.LoginedUser)session.getAttribute("OJ_JXC_USER");
if(loginedUser.getProfile()!=null){
	
	prodUpLimit = loginedUser.getProfile().getProdUpLimit();
	
	if(loginedUser.getProfile().getContaintax()==1){
		containTaxMode = true;
	}
	
}
%>
<script type="text/javascript">
var url="goods/listPager?role=${sessionScope.OJ_JXC_USER.roleInfo}&type=<%=request.getParameter("type")%>&bandId=<%=request.getParameter("bandId")%>";
var urlCount="goods/getPagerCount?role=${sessionScope.OJ_JXC_USER.roleInfo}&type=<%=request.getParameter("type")%>&bandId=<%=request.getParameter("bandId")%>";
var _totalSize=0;
var containTaxMode = <%=(containTaxMode?"true":"false")%>
var prodUpLimit=<%=prodUpLimit%>;
function calcPriceOut(value){
	if(!containTaxMode){
		return value;
	}else{
		return Math.floor(value/0.96);
	}
}
//用户输入的价格打96折，即不含税的价格
function calcPriceIn(value){
	if(!containTaxMode){
		return value;
	}else{
		return  Math.floor(value*0.96);
	}
}

function canUpProd(vlue){
	if(prodUpLimit==-1){
		return true;
	}else{
		return !(vlue>=prodUpLimit);
	}
}

function myPrice(value){
	//var str="<input size='5' style='height: 10px;' value='"+calcPriceOut(value)+"'/>";
	var str="<input size='5' style='height: 10px;' value='"+ value +"'/>";
	return str;
}
function myAmount(value){
	var str="<input size='5' style='height: 10px;' value='"+value+"'/>";
	return str;
}

//var canAddNewProd = true;

function loadData(){
	
	$("#table").dataGrid({
		onLoadSuccess:onGridLoadSuccess,
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:[ 
				  {name:'编号',field:'goodsid',width:50},
				  {name:'名称',field:'nickname',width:100},
				  {name:'价格',field:'price',width:70,fun:myPrice},
				  {name:'数量',field:'amount',width:70,fun:myAmount},
				  {name:'待改价格',field:'pendingprice',width:70},
				  {name:'待改数量',field:'pendingamount',width:70},				  
				  {name:'配置',field:'config',width:200},
				  //{name:'属性',field:'prodid',width:50},
		          {name:'上架时间',field:'createtime',width:80},
		          {name:'价格待审?',field:'ispendingchange',width:70,fun:toStatus},
		          {name:'上架待审?',field:'ispendingup',width:70,fun:toStatus}
		          ]
		          <%if(canUpdate){%>
		          ,
	    operates:{width:130, value:[
	                                {name:'修改价格与数量', fun:function(row){ 
	                                	var inp=$(this).parent().parent().find("input");
	                                	var price=inp[0].value;//calcPriceIn(inp[0].value);
	                                	var amount=''+inp[1].value;
	                                	var param={"goodsid":row.goodsid,"price":price,"amount":amount,"prodid":row.prodid};
	                                	//var param={"goodsid":1,"price":1,"amount":1,"prodid":1};
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
	                                  		  alert(json.success);//"系统异常!");
	                                  	  }	
	                                    });
	                                	
	                                } }]
	             }	
		         <%}%>
	});	
	
}
$(function(){
	/*
	$.getJSON("goods/canAddNewProd",{random: Math.random()},function(json){
		  if(json!=null){
			  canAddNewProd = json>1;
		  }
	  });	
 	*/

	$.post(urlCount,{},function(json){
		_totalSize = json;
		
		loadData();
		
		onGridLoadSuccess();
    });
	
	//data.rows.length 当前页数据量

	
});

function onGridLoadSuccess(){
	if( canUpProd(_totalSize)){
		$(".add").click(function(){
			update('入库','pages/sale/update.jsp');
		});
	}else{
		var str ="您上架的商品类数: "+prodUpLimit+" 已达到限定值：" + _totalSize;
		//var str="上架商品品类超限";
		$('#warningMsg').html(str);
		$(".add").click(function(){
			$.alert(str,'');
		});
	}
}
	function toStatus(value){
		switch(value){
		case 1:return "是";
		case 0:return "否";
		}
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
   <span class="div">我的仓库</span>
   <span class="add">新商品入库</span>
</div>
<div  style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>