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
var bandid = <%=(request.getParameter("bandId")==null?0:Integer.parseInt(request.getParameter("bandId")))%>;
var role = ${sessionScope.OJ_JXC_USER.roleInfo};

var propStrs = '';
var baseUrl = "goods/listPager?role="+role+"&isass=1&bandId="+bandid;

function myInput(){
	var str="<input size='8' style='height: 10px;'/>";
	return str;
}
function getTingchan(val){
	return val==1?'是':'';
}
function prepareData(){
	var clums=[  
				  {name:'',field:'prodid',width:0},
				  {name:'名称',field:'nickname',width:100},
				  {name:'是否停产',field:'tingchan',width:100,fun:getTingchan},
				  {name:'品牌',field:'title',width:100},
				  {name:'型号',field:'xinghao',width:100},
				  {name:'数量',field:'amount',width:50},
				  {name:'价格',field:'price',width:50},
				  {name:'配置',field:'config',width:200},
				  {name:'购买数量',field:'rid',width:100,fun:myInput}
		          ];
	if('${sessionScope.OJ_JXC_USER.roleInfo}'=='6'){
		clums=[  
				  {name:'名称',field:'nickname',width:100},
				  {name:'数量',field:'amount',width:100},
				  {name:'配置',field:'config',width:200}
		          ];
	}
	$("#table").dataGrid({
		url : baseUrl,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:clums,
	    operates:{width:100, value:[
	                                {name:'加入购物车', fun:addToCart
	                                 }
	                               ]
	              }
	});
}

function restore(){
	$("#table").dataRefresh();
}
$(function(){
	
	getFixPropByType( 0 );
	prepareData();
	
});

function addToCart(row){
	if('${sessionScope.OJ_JXC_USER.roleInfo}'=='6'){
		if(confirm("您当前未登录是否立即登录？"))
    	{
    	    window.parent.location.href="../../logout.jsp";
    	}
	}else{
		
		if(confirm("您的订货需求即将通知供货商，是否确认采购本产品？")){
			var am=$(this).parent().parent().find("input").val();
			var param={"amount":am,"prodid":row.prodid};
			$.post("goods/addCart",param,function(json){
	        	  if(json.success=='success'){
	        		    alert("加入购物车成功！");
	        		  	refresh();
	        		  	
	        		  //	if(confirm("是否采购配件？")){
	        			//	$("#table").dataRefresh({"tingchan":0,"isass":1,"bandId":row.bandid});
	        			//}
	        		  	
	        	  }else if(json.success=='-1'){
	        		  alert("数量异常!");
	        	  }else{
	        		  alert("系统异常!");
	        	  }	      	                                  	  
	          });
			
			//导入至配件页...
			//$.post("goods/listAssoCount",{"bandId":row.bandid},function(json){
	        //  });
			
			
		}
	}}
	
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
	   $("#table").dataRefresh(
			   
	   );
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
	  var tingchan=''+$("#tingchan").val();
	  $("#table").dataRefresh({"configFilter":selectedStr,"nickname":nickname,"lowPrice":lowPrice,"highPrice":highPrice,"tingchan":tingchan});
  }
  /*
  function search(){
	  
	  var nickname=$("#nickname").val();
	  var lowPrice=$("#price1").val();
	  var highPrice=$("#price2").val();
	  var tingchan=$("#tingchan").val();
	  
	  $("#table").dataRefresh(
			  { 
				  "configFilter":selectedStr,"nickname":nickname,"lowPrice":lowPrice,"highPrice":highPrice,"tingchan":tingchan}
			  );
  }*/

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
					  
					  str +=  d.unit +'&nbsp;&nbsp;';//+'<br/>';
				  }
				  
				  $('#configFilter').html(str);
				  
			  }
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
   <span class="div">配件商品列表</span>
   
</div>
 <button value="返回前页" onclick="history.back(1)">返回前页</button>
  <div id="table"></div>
  
  
</div>
</body>
</html>