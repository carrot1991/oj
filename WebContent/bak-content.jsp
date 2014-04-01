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
<script type="text/javascript">
var typeid = <%=request.getParameter("type")%>;
var propStrs = '';
function myInput(){
	var str="<input size='8' style='height: 10px;'/>";
	return str;
}

function prepareData(){
	var canBuy = '${sessionScope.OJ_JXC_USER.roleInfo}'=='1';
	var url="goods/listPager?role=${sessionScope.OJ_JXC_USER.roleInfo}&type=<%=request.getParameter("type")%>&bandId=<%=request.getParameter("bandId")%>&prodId=<%=request.getParameter("prodId")%>";
	var clums=[  
				  {name:'名称',field:'nickname',width:100},
				  {name:'品牌',field:'title',width:100},
				  {name:'型号',field:'xinghao',width:100},
				  {name:'数量',field:'amount',width:50},
				  {name:'价格',field:'price',width:50},
				 // {name:'属性',field:'prodid',width:50},
				  {name:'配置',field:'config',width:200},
				  {name: canBuy?'购买数量':'',field:'rid',width:100,fun:canBuy?myInput:function(){}}
				    
		          ];
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:clums,
	    operates:{width:100, value:[
			canBuy?
	                                {name:'加入购物车' , fun:function(row){
	                                		if('${sessionScope.OJ_JXC_USER.roleInfo}'=='6'){
	                                			if(confirm("您当前未登录是否立即登录？"))
			                                	{
			                                	        window.parent.location.href="logout.jsp";
			                                	}
	                                		}else{
	                                			var am=$(this).parent().parent().find("input").val();
	                                			var param={"amount":am,"prodid":row.prodid};
	                                			$.post("goods/addCart",param,function(json){
	      	                                  	  if(json.success=='success'){
	      	                                  		  alert("加入购物车成功！");
	      	                                  		  refresh();
	      	                                  	  }else if(json.success=='-1'){
	  		                                  		  alert("数量异常!");
	  		                                  	  }else{
	  		                                  		  alert("系统异常!");
	  		                                  	  }	      	                                  	  
	      	                                    });
	                                		}}
	                                 }:{name:'' , fun:function(row){}}
	                               ]
	              }
	});
}

$(function(){
	
	getFixPropByType( 0 );
	//alert('${sessionScope.OJ_JXC_USER.roleInfo}');
 
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
 
   function refresh(){
	   $("#table").dataRefresh();
	   $.unblockUI();
   }
   
   function closed(){
	   $.unblockUI();
   }
   
  function buy(prodid){
	 
	 $.post("shopCart/add",{prodid:id},function(json){
			   $.unblockUI();
			   refresh();
	  });

  }
  
  function search(){
	  
	  var arr = propStrs.split(",");
	  var selectedStr = '';
	  for(var i=0;i<arr.length;i++){
		  var pid = arr[i];
		  if(pid!=''){
			  var ck = $('#checked_'+pid);
			  if(ck.attr("checked")=='checked'){
				  selectedStr+= pid+':'+ $('#prop_'+pid).val()+',';
			  }
		  }
	  }
	  
	  var nickname=$("#nickname").val();
	  var lowPrice=$("#price1").val();
	  var highPrice=$("#price2").val();
	  $("#table").dataRefresh({"configFilter":selectedStr,"nickname":nickname,"lowPrice":lowPrice,"highPrice":highPrice});
  }

  function getFixPropByType( tid ){
		
		$.getJSON("prodfixprop/listByType",{random: Math.random(),typeid:tid},function(jsonstr){
			  if(jsonstr!=null){
				  propStrs = '';
				  var str='<br/>';
				  var json=eval(jsonstr);
				  
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  
					  str += '<input value='+d.propid+' type=checkbox  id=checked_' + d.propid+'>';
					  
					  propStrs+=d.propid+",";
					  
					  str += d.name;
					  if(d.vlue!=''){ //如有预定，则使用。
						  var strs=d.vlue.split(",");//字符分割  
						  str += '<select id=prop_'+d.propid+'>';
						  for (var j=0;j<strs.length;j++ )    
					      {    
					          str += '<option value='+strs[j]+'>' + strs[j]  + '</option>';
					      }
						  str += '</select>';
					  }
					  
					  str +=  d.unit +'<br/>';
				  }
				  
				  $('#configFilter').html(str);
				  
			  }
		  });
		
		 prepareData();
		
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

<div style="padding:25px;">

                名称: 
                     <input id="nickname"> <br/>
    价格: 
                     <input id="price1" /> - 
                     <input id="price2" /><br/>
             配置筛选: <div id='configFilter'></div>
             
                     <input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;搜&nbsp;&nbsp;&nbsp;索&nbsp;&nbsp;&nbsp;&nbsp;"  id="searchButton"	onclick="search();" /> 
                     
  <div id="table"></div>
</div>
</body>
</html>