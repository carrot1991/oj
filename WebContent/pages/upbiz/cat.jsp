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
<link rel="stylesheet" href="<%=path%>/theme/jquerycss/jquery.ui.all.css">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.21.custom.js"></script>
<script type="text/javascript">
var typeDictStr='';
var bandDictStr='';
var canDelete = false;
<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='2' || sessionScope.OJ_JXC_USER.roleInfo=='8'}">
canDelete = true;
</c:if>
function reloadTree(){
	window.parent.frames['left'].location.reload();
}

$(function(){
	$("#tabs").tabs({ 
        selected:0
    });
	
	getCategory();
	
	//getType('new_band_typeid');
	
	$('#newCateButton').click(
			function(){
				var title=$('#new_cate_title').val();
				var level=$('#new_cate_level').val();
				if(title==''){
					alert("大类名称不能为空！");
					return false;
				}
				var param = {
						'title':title,
						'level':level,
				};
				$.post("type/saveOrupdateCate", param ,function(json){
		        	  if(json.success=='success'){
		        		  $.alert('成功','大类新增成功');
		        		  getCategory();
		        		  reloadTree();
		        	  }else{
		        		  $.alert(json.success,'大类新增');
		        	  }
		          });
			}		
		);//end
		
	$('#newTypeButton').click(
			function(){
				var typetitle=$('#new_type_title').val();
				if(typetitle==''){
					alert("子类名称不能为空！");
					return false;
				}
				var param = {
						'cateid':$('#new_type_cateid').val(),
						'level':$('#new_type_level').val(),
						title:typetitle
				};
				$.post("type/saveOrupdate", param ,function(json){
		        	  if(json.success=='success'){
		        		  $.alert('成功','子类新增');
		        		  getType($('#new_type_cateid').val());
		        		  reloadTree();
		        	  }else{
		        		  $.alert(json.success,'子类新增');
		        		  //document.frames('main').location.reload();
		        	  }
		          });
			}		
		);//end
		
	$('#newBandButton').click(
		function(){
			var tid = $('#new_band_typeid').val();
			if(tid=='---'){
				alert("子类不能为空！");
				return false;
			}
			var bandtitle = $('#new_band_title').val();
			var bandlevel = $('#new_band_level').val();
			if(bandtitle==''){
				alert("品牌名称不能为空！");
				return false;
			}
			var param = {
					typeid:tid,
					title:bandtitle,
					level:bandlevel,
			};
			$.post("band/save", param ,function(json){
	        	  if(json.success=='success'){
	        		  getBandByType(tid);
	        		  
	        		  reloadTree();
	        		  
	        	  }else{
	        		  $.alert(json.success,'品牌新增');
	        	  }
	          });
		}		
	);//end
	
	}
);

function getCategory(){
	$.getJSON("type/findCategory",
			{random: Math.random()},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  str += "<option value='---'>请选择大类</option>";
			  var json=eval(jsonstr);//array
			   
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<option  value="+d.id+">"+d.title+"</option>";
			  }
			  $('#new_type_cateid').html(str);
			  var td='';
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  td += "<tr><td><input id='cate_title_"+d.id+"' value='" +  d.title + "' /></td>";
				  td += "<td><input id='cate_level_"+d.id+"' value='" +  d.level + "' /></td>";
				  if(canDelete){
					  td += "<td><input type=button onclick='removeCate("+ d.id +")' value='删除'/>";
				  }else{
					  td += "<td>";
				  }
				  td += "<input type=button onclick=\"updateCate("+ d.id +",'cate_title_"+d.id+"','cate_level_"+d.id+"')\" value='修改'/>";
				  td += "</td></tr>";
			  }	
			  $('#cate tr:not(:first)').remove();
			  $('#cate').append(td);	  
		  }
	  });
}

function getType(cateid){
	$.getJSON("type/findType",
			{random: Math.random(),
		'cateid':cateid
		},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  str += "<option value='---'>请选择子类</option>";
			  var json=eval(jsonstr);//array
			   
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<option  value="+d.typeid+">"+d.title+"</option>";
			  }
			  $('#new_band_typeid').html(str);
			  var td='';
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				 
				  td += "<tr><td><input id='type_title_"+d.typeid+"' value='" +  d.title + "' /></td>";
				  td += "<td><input id='type_level_"+d.typeid+"' value='" +  d.level + "' /></td>";
				  if(canDelete){
				  	td += "<td><input type=button onclick='removeType("+ d.cateid +','+d.typeid +")' value='删除'/>";
				  }else{
					  td += "<td>";
				  }
				  td += "<input type=button onclick=\"updateType("+ d.typeid +",'type_title_"+d.typeid+"','type_level_"+d.typeid+"')\" value='修改'/>";
				  td += "</td></tr>";
				  
			  }	
			  $('#dalei tr:not(:first)').remove();
			  $('#dalei').append(td);	  
		  }
	  });
}
 
function getBandByType(tid){
	$.getJSON("band/findBandByType",{ random: Math.random(),typeid:tid},function(jsonstr){
		  if(jsonstr!=null){
			  var td='';
			  var json=eval(jsonstr);
			  
			  for(var i=0;i<json.length;i++){
				  var d = json[i];
				  td += "<tr><td><input id='band_title_"+d.bandid+"' value='" +  d.title + "' /></td>";
				  td += "<td><input id='band_level_"+d.bandid+"' value='" +  d.level + "' /></td>";
				  if(canDelete){
				  	td += "<td><input type=button onclick='removeBand("+d.bandid +"," + d.typeid+")' value='删除'/>";
				  }else{
					  td += "<td>";  
				  }
				  td += "<input type=button onclick=\"updateBand("+ d.bandid +",'band_title_"+d.bandid+"','band_level_"+d.bandid+"')\" value='修改'/>";
				  td += "</td></tr>";
			  }
			  $('#pingpai tr:not(:first)').remove();
			  $('#pingpai').append(td);
		  }else{
			  $('#pingpai tr:not(:first)').remove();
		  }
	});
}

function removeCate(tid){
	if(confirm("确认删除吗？")){
		$.post("type/removeCate", {cateid:tid} ,function(json){
	  	  if(json.success=='success'){
	  		   $.alert('成功','大类删除');
	  			getCategory();
	  			reloadTree();
	  	  }else{
	  		  $.alert(json.success,'大类删除');
	  	  }
	    });
	}
}
function updateCate(cateid,titleID,levelID){
		var title = $('#'+titleID).val();
		var level = $('#'+levelID).val();
		$.post("type/saveOrUpdateCate", {'id':cateid,'title':title,'level':level} ,function(json){
	  	  if(json.success=='success'){
	  		  $.alert('成功','大类修改');
	  			getCategory();
	  			reloadTree();
	  	  }else{
	  		  $.alert(json.success,'修改');
	  	  }
	    });
}
function removeType(cateid,tid){
	if(confirm("确认删除吗？")){
		$.post("type/remove", {typeid:tid} ,function(json){
	  	  if(json.success=='success'){
	  		  $.alert('成功','子类删除');
	  		  getType(cateid);
	  		  reloadTree();
	  	  }else{
	  		  $.alert(json.success,'子类删除');
	  	  }
	    });
	}
}
function updateType(typeid,titleID,levelID){
		var title = $('#'+titleID).val();
		var level = $('#'+levelID).val();
		$.post("type/saveOrupdate", {'typeid':typeid,'title':title,'level':level} ,function(json){
	  	  if(json.success=='success'){
	  		$.alert('成功','子类修改');
	  		getType(typeid);
  		    reloadTree();
	  	  }else{
	  		  $.alert(json.success,'子类修改');
	  	  }
	    });
}
function removeBand(bid,tid){
	if(confirm("确认删除吗？")){
		$.post("band/remove", {bandid:bid} ,function(json){
	  	  if(json.success=='success'){
	  		  $.alert('成功','品牌删除');
	  		  
	  		  getBandByType(tid);
	  		  reloadTree();
	  	  }else{
	  		  $.alert(json.success,'品牌删除');
	  	  }
	    });
	}
}
function updateBand(bid,titleID,levelID){
		var title = $('#'+titleID).val();
		var level = $('#'+levelID).val();
		$.post("band/save", {'bandid':bid,'title':title,'level':level} ,function(json){
	  	  if(json.success=='success'){
	  		    $.alert('成功','修改');
	  			getCategory();
	  			reloadTree();
	  	  }else{
	  		  $.alert(json.success,'修改');
	  	  }
	    });
}
function getProdByBand(bid){
		$.getJSON("proddef/findDefByBand",{random: Math.random(),bandid:bid},function(jsonstr){
			  if(jsonstr!=null){
				  var str='';
				  str += "<option value='---'>请选择产品</option>";
				  var json=eval(jsonstr);//array
			  	  for(var i=0;i<json.length;i++){
			  		  d = json[i];
					  str += "<option value="+d.prodid+">"+d.nickname+"</option>";
				  }
				  $('#prodid').html(str);
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
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 40%; }
	div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 4px;
</style>
</head>
<body>

<div id="tabs"> 
<ul> 
<li><a href="#tab-1">大类</a></li> 
<li><a href="#tab-2">子类</a></li> 
<li><a href="#tab-3">品牌</a></li> 
</ul> 

<!-- 1 -->
<div id="tab-1">
<div style="margin: 10px auto 0;">
名称：<input id='new_cate_title' />
<button id="newCateButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
<span class="ui-button-text">新增大类</span>
</button>
</div>
<div id="users-contain">
<table id="cate">
	<thead>
			<tr class="ui-widget-header ">
				<th>名称</th>
				<th>级别</th>
				<th style="width:12em;">操作</th>
			</tr>
	</thead>
</table>
</div>
</div>

<!-- 2 -->
<div id="tab-2">
<div style="margin: 10px auto 0;">
大类:
<select id="new_type_cateid"  onchange='getType(this.value);'>
  <option value='---'>请选择大类</option>
</select>
名称：<input id='new_type_title' />
级别：<input id='new_type_level' value=0>
<button id="newTypeButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
<span class="ui-button-text">新增子类</span>
</button>
</div>
<!-- 大类 列表 -->
<div id="users-contain">
<table id="dalei">
	<thead>
			<tr class="ui-widget-header ">
				<th>名称</th>
				<th>级别</th>
				<th style="width:12em;">操作</th>
			</tr>
	</thead>
</table>
</div>
</div>

<!-- 3 -->
<div id="tab-3">
<div style="margin: 10px auto 0;">
子类:
<select id="new_band_typeid"  onchange='getBandByType(this.value);'>
  <option value='---'>请选择子类</option>
</select>
品牌名称:
<input id='new_band_title' /> 
<button id="newBandButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false">
<span class="ui-button-text">新增品牌</span>
</button>
<!-- 品牌列表 -->
<div id="users-contain">
<table id="pingpai">
	<thead>
			<tr class="ui-widget-header ">
				<th>名称</th>
				<th>级别</th>
				<th style="width:12em;">操作</th>
			</tr>
	</thead>
</table>
</div>
</div>

</div>
</body>
</html>