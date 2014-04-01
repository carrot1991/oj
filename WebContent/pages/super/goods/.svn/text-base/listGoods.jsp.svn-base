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

$(function(){
	var url="goods/listPager?role=${sessionScope.OJ_JXC_USER.roleInfo}&type=<%=request.getParameter("type")%>&bandId=<%=request.getParameter("bandId")%>";
	var clums=[  
				  {name:'名称',field:'nickname',width:100},
		{name:'型号',field:'xinghao',width:100},
				  {name:'添加人',field:'uname',width:100},
				  {name:'数量',field:'amount',width:100},
				  {name:'价格',field:'price',width:100},
				  {name:'配置',field:'config',width:200},
				  {name:'添加时间',field:'createtime',width:200}
		          ];
	if('${sessionScope.OJ_JXC_USER.roleInfo}'=='6'){
		clums=[  
				  {name:'名称',field:'nickname',width:100},
				  {name:'数量',field:'amount',width:100},
				  {name:'配置',field:'config',width:200}
		          ];
	}
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:clums,
	    operates:{width:90, value:[
	                                
				/*{name:'加入购物车', fun:function(row){ 
	                                		if('${sessionScope.OJ_JXC_USER.roleInfo}'=='6'){
	                                			if(confirm("您当前未登录是否立即登录？"))
			                                	{
			                                	        window.parent.location.href="logout.jsp";
			                                	}
	                                		}else{
		                                		alert(row.price);
	                                		}}
	                                 }*/
	                               ]
	              }
	});
 
});


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
 
  function buy(prodid){
	 
	 $.post("shopCart/add",{prodid:id},function(json){
			   $.unblockUI();
			   refresh();
	  });

  }
  
  function search(){
	  var nickname=$("#nickname").val();
	  var lowPrice=$("#price1").val();
	  var highPrice=$("#price2").val();
	  $("#table").dataRefresh({"nickname":nickname,"lowPrice":lowPrice,"highPrice":highPrice});
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
   <span class="div">商品列表</span>
</div>
                名称: 
                     <input id="nickname">
                 价格: 
                     <input id="price1" /> - 
                     <input id="price2" />
                     <input type="button" value="搜索"  id="searchButton"	onclick="search();" /> 
<div style="margin: 10px auto 0;">
  <div id="table"></div>
</div>
</body>
</html>