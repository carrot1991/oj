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
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息更新</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />
<%
	boolean newMode = (request.getParameter("uname") == null || ((String) request
			.getParameter("uname")).length() == 0);
%>
<script type="text/javascript">
  var prodDict;
  var uname=$.query.get("uname");
  var newMode = uname==null;
  
  $(function(){
	  $("#save,#cancel").toHover('','select');
	  $("#cancel").click(function(){
		  parent.window.closed();
	  });
	  
	  
	  if(!newMode){//修改...
		  $('#uname').val(uname);
		  $('#uname').attr('readonly','true');
		  
		  $.getJSON("user/find",{'uname':uname,random: Math.random()},function(json){
			  if(json!=null){
				  $('#qq').val(json.qq);
				  $('#email').val(json.email);
				  $('#tel').val(json.tel);
			  }
		  });
		  
	  } /////////修改
	  
	      //bandid mytitle level
		  $.getJSON("band/getDict",{random: Math.random()},function(jsonstr){
			  if(jsonstr!=null){
				  	var str='';
				  	var ids ='';
					var json=jsonstr;//eval(jsonstr);
					
					 for(var i=0;i<json.length;i++){
						  d = json[i];
						  //<li><span></span></li>
						  str+='<li><span></span><input type=checkbox id=selband name=selband value='+d.bandid+' />'+d.mytitle+'</li>';
						 // ids +=d.bandid+',';
					  }
					// alert(ids);
					$('#bandList').html(str);
					
					//,random: ''+Math.random()
					
					  if(!newMode){//修改...
						  $.getJSON("userprod/findByUnameForCheckBox",{'uname':''+uname+'',random: ''+Math.random()},function(jsonstr){
							  if(jsonstr!=null){
								   // ids ='';
									var json=jsonstr;//eval(jsonstr);
									for(var i=0;i<json.length;i++){
										  d = json[i];
										  $("input[name='selband'][value="+d.bandid+"]").attr('checked','checked');
										  ids +=d.bandid+',';
									}
									//alert(ids);
							  }
						  });	
					  }
			  }
		  });
      
	  $("#btnSelect").click(function(){
		  $("input[name='selband']").attr("checked",this.checked);
	  });
	  
	  $("#save").click(function(){
		  
		  var str='';
		  
		  $("input:checked").each(function(){
			  if(this.name!='btnSelect'){
					 str += $(this).val() + ",";
			  }
      		});
		  //alert('will commiting...'+str);
          if(str.length==0){
        	  $.alert('请选中至少一项','信息更新',true);
        	  return;
          }
          var commitStr = str.substring(0,str.length-1);
          
          if(newMode){
         	 if($('#uname').val().length==0 || $('#pwd').val().length==0){
        	  	$.alert('请输入用户名与密码','信息更新',true);
	        	return;
	         }
          }
           
        	  var ub;
        	  if(newMode){
        		  ub ={ 
	        			  'uname':$('#uname').val(),
	        			  'pwd':$('#pwd').val(),
	        			  'tel':$('#tel').val(),
	        			  'email':$('#email').val(),
	        			  'qq':$('#qq').val(),
        				  'pids':commitStr
	        	  };
        	  }else{
        		  ub ={ 
	        			  'uname':$('#uname').val(),
	        			  'tel':$('#tel').val(),
	        			  'email':$('#email').val(),
	        			  'qq':$('#qq').val(),
        				  'pids':commitStr
	        	  };
        	  }
        	  
			  $.post("userprod/saveByComma",ub,function(json){
	        	  if(json.success=='success'){
	        		  var m={text:'更新成功！',fun:function(){parent.window.refresh();}}
	        		  $.alert(m,'信息更新',true);
	        	  }else{
	        		  $.alert(json.success,'信息更新');
	        	  }
	          }); 
		 
	  });//保存
  });
</script>
<style type="text/css">
   body,p,table,p,img {margin:0; padding:0;font-family:"宋体",Verdana, Arial,Sans; font-size:12px;}
   ul,li{margin:0; padding:0; list-style:none;}
   table,td {border-collapse:collapse;empty-cells:show;}
   body{background: #f3f3f3;}
   .div{padding-top: 15px;}
   li{padding:5px 0;}
   li span{width: 120px;float:left;text-align: right;padding-top: 5px;}
   li i{font-style: normal;float:left;padding-top: 2px;}
   li i input{margin-top: 5px;}
   .div #save{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -214px;margin-left: 120px;cursor: pointer;float:left;  }
   .div #save.select{background-position:0 -239px;}
   .div #cancel{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -114px;margin-left: 18px;cursor: pointer;float:left;   }
   .div #cancel.select{background-position:0 -139px;}
</style>
</head>
<body>
  <input type="hidden" class="v" name="id" id="id"  />
  
    <div class="div">
         <ul>
 				  <li>
                      <span>账号</span>
                      <input type="text" id="uname" name="uname" class="textbg" />
                  </li>
                  <%if(newMode){ %>
					<li>
                      <span>密码</span>
                      <input type="password" id="pwd" name="pwd" class="textbg" />
                  </li>
                  <%}%>
                  
<li><span>电话</span> <input type="text" id="tel" name="tel"
				class="textbg" /></li>
<li><span>邮箱</span> <input type="text" id="email" name="email"
				class="textbg" /></li>
 <li><span>QQ</span> <input type="text" id="qq" name="qq"
				class="textbg" /></li>
				                  
				  <li>
                      <span></span>
                       <input type="checkbox" value="1" name="btnSelect" id="btnSelect" />全选/全消<br/>
                  </li>
                   
                   <div id="bandList">
                   </div>
                                   
         </ul>
     </div>
     <div class="div">
        <div id="save"></div>
     </div>
</body>
</html>