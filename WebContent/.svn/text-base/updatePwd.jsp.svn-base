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
<title>修改密码</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />
<script type="text/javascript">

$(function(){
	  $("#save").click(function(){

          var u = $("#uname").val();
          var p = $("#pwd").val();
          
          if(  p=='' || p.length<6){
        	  $.alert('请输入不少于6位的密码！','修改密码',true);
        	  return;
          }
          $.post("user/changePwd",{uname:u,pwd:p},function(json){
        	  if(json.success=='success'){
        		  //var m={text:'成功修改密码！',fun:function(){parent.window.refresh();}}
        		  $.alert('成功修改密码','修改密码',true);
        	  }else{
        		  $.alert(json.success,'修改密码');
        	  }
          });
		});
});

</script>
<style>
.bot{  font-size:12px; }
</style>
</head>
<body>

 
<div  style="padding:25px;">
  <div id="table" align='center'>
  
   <input type=hidden id='uname' name='uname' value='${sessionScope.OJ_JXC_USER.loginUser.uname}' />
<div class=bot>新密码： <input id='pwd' name='pwd' type=password value="" />
 <input type=button id=save name=save value="修改密码" /></div>
  
  </div>
</div>


</body>
</html>