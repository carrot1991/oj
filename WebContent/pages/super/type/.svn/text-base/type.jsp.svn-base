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
<title>分类管理</title>
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
	$("#table").dataGrid({
		url : "type/lists",
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		isPage:false,
		pageSize:100,
		itemClass:['item1','item2'],
		fields:[  {name:'编号',field:'typeid',width:50},
		          {name:'名称',field:'title',width:200}
		       ],
	    operates:{width:100, value:[
	                                {name:'修改', fun:function(row){update('修改','pages/super/type/update.jsp?typeid='+row.typeid); } 
	                                },
	                                {name:'删除', fun:function(row){remove(row.typeid); } 
	                                }
	                               ]
	             }
	});

	$(".add").click(function(){
		update('新建','pages/super/type/update.jsp');
	});
});

	function remove(val){
		$.post("type/remove",{typeid:val},function(json){
      	  if(json.success=='success'){
      		  //var m={text:'删除成功！',fun:function(){parent.window.refresh();}}
      		  alert("删除成功");
      		  refresh();
      		  //$.alert(m,'删除',true);
      	  }else{
      		  //$.alert(json.success,'删除');
      		alert("删除失败:"+json.success);
      	  }
        });
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
   <span class="div">分类列表</span>
   <span class="add">新建分类</span>
</div>
<div style="padding:25px;">
  <div id="table"></div>
</div>
</body>
</html>