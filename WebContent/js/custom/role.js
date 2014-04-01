$(function(){
	$(".add").click(function(){
		update('新建角色信息','pages/system/role/update.jsp');
	});
	roleList();
});

function roleList(){
	$.getJSON('role/allRoles',function(data){
		var juese=$(".juese ul").empty();
		$.each(data,function(i,n){
			var s="";
			if(i==0){
				fetchRoleAuthorityTree(n.id);
				s="select";
			}
			var li=$("<li class=\""+s+"\" val=\""+n.id+"\">"+n.name+"</li>");
			li.click(function(){
				juese.find("li").removeClass("select");
				$(this).addClass("select");
				fetchRoleAuthorityTree(juese.find(".select").attr("val"));
			});
			juese.append(li);
		});
		$("#modi").click(function(){
			update('修改角色信息','pages/system/role/update.jsp?id='+juese.find(".select").attr("val"));
		});
		$("#delete").click(function(){
			if($(".juese ul").find(".select").size()>0){
                 var id=$(".juese ul").find(".select").attr("val");
                 var name=$(".juese ul").find(".select").html();
                 if(name=='管理员'){
                	 _alert("管理员角色无法删除");
                	 return;
                 }
                 
				$.layer({
					  isZY:true,
					  width : 250,
					  height: 100,
					  parent:'keepsoft_',
					  type:'confirm',
			          content:{text:'是否将此角色息删除?\n删除后将无法恢复',fun:function(){del(id);}}
				 });

			}else{
				_alert("请选中角色");
			}
		});
	});
}

function del(id){
	$.getJSON('role/delete',{id:id},function(data){
		 roleList();
	});
}

function fetchRoleAuthorityTree(roleId){
	$.getJSON('aut/fetchRoleAuthorityTree',{roleId:roleId},function(data){
		var qx_list=$(".qx_list");
		qx_list.empty();
		$.each(data,function(i,n){
			var kuang=$("<div class=\"kuang"+(i%2==0?"1":"2")+"\" ></div>");
			var nparts=$("<div class=\"part\" ><div class=\""+n.authCss+"_icon\" ></div><div class=\"p_title\" ><input "+(n.selected?"checked=\"checked\"":"")+" type=\"checkbox\" value=\""+n.id+"\"  class=\"inp_tt\" />"+n.authName+"</div></div>");
			kuang.append(nparts);
			qx_list.append(kuang);
			var children=n.children;
			if(children.length>0){
				setAuthority(children,'p_cont',kuang);
			}
		});
		
		qx_list.find("input").click(function(){
			var cek=$(this).prop("checked");
			var parentId=$(this).val();
			$(this).prop( "checked",cek);
			
			if(qx_list.find("input[val='"+parentId+"']").length>0){
				serchDown(parentId,cek);
			}
		});
		
		$("#baocun_btn").toHover("","selecteds");
		$("#baocun_btn").click(function(){
			 var roleId=$(".juese ul .select").attr("val");
			 var value="";
			 $(".inp_tt:checked").each(function(i){
				 value+=","+$(this).val();
			 });
			 value=value.substring(1,value.length);
			$.post("role/distribute",{id:roleId,authorityIds:value},function(json){
				$.alert("权限设置成功！","权限设置",true);
			});
		 });
	});
}

function serchDown(parentId,b){
	var qx_list=$(".qx_list").find("input[val='"+parentId+"']");
	qx_list.prop( "checked",b);
	qx_list.each(function(){
		var val=$(this).val();
		if($(".qx_list").find("input[val='"+val+"']").length>0){
			serchDown(val,b);
		}
	});
}

function setAuthority(data,div,elem){

	var p_cont=$("<div  class=\""+div+"\"></div>");
	var ul=$("<ul></ul>");
	p_cont.append(ul).append('<div class=\"clearfix\"></div>');
	for(var i=0;i<data.length;i++){
		var li=$("<li><input type=\"checkbox\" "+(data[i].selected?"checked=\"checked\"":"")+" value=\""+data[i].id+"\" val=\""+data[i].parentId+"\" class=\"inp_tt\" />"+data[i].authName+"</li>");
	
		if(data[i].children.length>0){
			li.css("font-weight","bold");
			setAuthority(data[i].children,'p_cont_n',li);
		}
		ul.append(li);
	}
	elem.append(p_cont);
}




   function update(title,url){
	   $.layer({
		   width:368,
		   height:150,
		   isZY:true,
		   parent:'keepsoft_1_',
		   type:'iframe',
		   moveLayer:false,
           content:[{name:title,url:url}]
		});
   }
   function closed(){
		$.unblockUI();
		roleList();
	}