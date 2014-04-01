<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
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
var typeDictStr='';
var bandDictStr='';
var canDelete = false;
<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='2' || sessionScope.OJ_JXC_USER.roleInfo=='8'}">
	canDelete = true;
</c:if>
function getCategory(){
	$.getJSON("type/findCategory",
			{random: Math.random()},function(jsonstr){
				if(jsonstr!=null){
					  var str='';
					  str += "<option value='---'>请选择大类</option>";
					  var json=eval(jsonstr); 
					   
					  for(var i=0;i<json.length;i++){
						  d = json[i];
						  str += "<option  value="+d.id+">"+d.title+"</option>";
					  }
					  $('#cateid').html(str);
				  }
	  });
}

$(function(){
	getCategory();
	
	$('#newProdFixPropButton').click(
			function(){
				var typeid = $('#typeid').val();
				var param = {
						id:0,
						'typeid':typeid,
						name:$('#name').val(),
						vlue:$('#vlue').val(),
						unit:$('#unit').val(),
				};
				$.post("prodfixprop/saveOrUpdate", param ,function(json){
		        	  if(json.success=='success'){
		        		  getFixPropByType( typeid );
		        	  }else{
		        		  $.alert(json.success,'属性新增');
		        	  }
		        	  
		          }); 
			}		
		);//end
		
	}
);

function removeFixProp(tid,propid){
	if(confirm("确认删除吗？")){
	$.post("prodfixprop/delete", {'id':propid} ,function(json){
  	  if(json.success=='success'){
  		getFixPropByType(tid);
  	  }else{
  		  $.alert(json.success,'大类新增');
  	  }
    }); 
	}
}

function getType(cateid){
	$.getJSON("type/findType",{random: Math.random(),'cateid':cateid},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  str += "<option value='---'>请选择大类</option>";
			  var json=eval(jsonstr); 
			   
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<option  value="+d.typeid+">"+d.title+"</option>";
			  }
			  $('#typeid').html(str);
		  }
	  });
}
  
function getFixPropByType(tid){
		$.getJSON("prodfixprop/listByType",{random: Math.random(),typeid:tid},function(jsonstr){
			  if(jsonstr!=null){
				  var td='';
				  var json=eval(jsonstr);
				  
				  td = "列表<table border=1>";
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  td += "<tr><td><input id='prod_prop_name_"+d.propid+"' value='" +  d.name + "' /></td>";
					  td += "<td><input id='prod_prop_vlue_"+d.propid+"' value='" +  d.vlue + "' /></td>";
					  td += "<td><input id='prod_prop_unit_"+d.propid+"' value='" +  d.unit + "' /></td>";
					  if(canDelete){ 
						td += "<td><input type=button onclick='removeFixProp("+d.typeid+","+ d.propid +")' value='删除'/>";
					  }else{
						td += "<td>";
					  }
					  td += "<input type=button onclick=\"updateFixProp("+ d.propid + ")\" value='修改'/>";
					  td += "</td></tr>";			  
				  }
				  td += "</table>";
				  $('#prodFixDefList').html(td);
				  
			  }
		  });	
}
function updateFixProp(propid){
		var name= $('#prod_prop_name_'+propid).val();
		var vlue= $('#prod_prop_vlue_'+propid).val();
		var unit= $('#prod_prop_unit_'+propid).val();
		
		var typeid=$('#typeid').val();
		
		$.post("prodfixprop/saveOrUpdate", {'typeid':typeid,'propid':propid,'name':name,'vlue':vlue,'unit':unit} ,function(json){
	  	  if(json.success=='success'){
	  		$.alert('成功','修改');
	  		getCategory();
	  	  }else{
	  		  $.alert(json.success,'修改');
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
   <span class="div">产品配置维护</span>
</div>

    <div class="div">
         <ul>
         <li><span>大类:</span>
			<select id="cateid" name="cateid" class="v" onchange='getType(this.value);'>
		    </select></li>
		    
         	<li><span>子类:</span>
			<select id="typeid" name="typeid" class="v" onchange='getFixPropByType(this.value);'>
		    </select></li>
			 
	        <li><span>属性名称(如cpu型号):</span>
	         <input id="name" name="name" class="v" /></li>
	        <li><span>选择范围值(逗号分隔，如Intel,AMD):</span><br/>
	        <textarea id="vlue" name="vlue" class="v" cols=50 rows=5>
	        </textarea>
	         </li>
	        <li><span>单位:(如G或M)</span><input id="unit" name="unit" class="v" /></li>
	          
	        <input type="button" id="newProdFixPropButton" class="v" value="新增固定属性" />
	        
         </ul>
     </div>
     
      <div id="prodFixDefList">
	 </div>
	  
</body>
</html>