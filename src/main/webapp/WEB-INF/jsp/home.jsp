<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 指定页面上地址的相对地址 -->
<base href="<%= basePath %>" /> 

<!-- 引入easyui的css文件 -->
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/icon.css">
<!-- 引入easyui的js文件 -->
<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/easyui/easyui-lang-zh_CN.js"></script>

</head>

<body class="easyui-layout" data-options="onCollapse:function(param){alert('这是由onCollapse事件触发');	}">
<div data-options="region:'north',title:'页眉',collapsible:false" style="height: 100px;">
	当前时间，登录用户，退出登录
</div>
<div data-options="region:'south',title:'页脚',collapsible:false" style="height: 100px;"></div>
<div data-options="region:'west',title:'功能菜单',split:true" style="width: 200px;">
	<ul id="menu_nav_tree"></ul>
</div>
<div data-options="region:'center'" >
	<div id="globle_tabs" class="easyui-tabs" data-options="fit:true"><!-- 在最外层设置fit属性 -->
		<div data-options="title:'欢迎页',border:false,closable:true" style="padding:10px;">
			welcome。
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#menu_nav_tree").tree({
		url:'menu/getMenuTree',
		onClick:function(node){
			if(node.attributes.url !=""){//说明不是根节点（这要求在后台需要映射根节点的attributes属性）
				//这里使用（比较省资源但对编码能力要求高的）href方式
				//var content = '<iframe scrolling="no" frameborder="0" src="'+node.attributes.url+'" style="width:100%;height:100%;"></iframe>';  
				if(!$("#globle_tabs").tabs('exists',node.text)){
					$("#globle_tabs").tabs('add',{
						title:node.text,
						//content:content,
						href:node.attributes.url,
						closable:true
					});
				}else{//此tab已存在则选中此tab
					$("#globle_tabs").tabs('select',node.text);
				}
			}
		}
	});
});
</script>

</body>
</html>