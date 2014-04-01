<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>it</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
        <!-- ZTree树形插件 -->  
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <link rel="stylesheet" href="<%=basePath%>ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">  
    <script type="text/javascript" src="<%=basePath%>/ztree/js/jquery.ztree.core-3.5.js"></script>  
    <script type="text/javascript">
    
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

	//ztree
		$(document).ready(function(){
			$.ajax({type:"POST",
					url:"<%=basePath%>type/list?by=level",
					dataType: "json",
					success:function(data){
						var zNodes =eval(data.success);
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					},
					error:function(){
						alert("资源繁忙");
					}
			});
				
		});
	</script>
	</head>
    <body>
    <div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
    </body>
</html>

