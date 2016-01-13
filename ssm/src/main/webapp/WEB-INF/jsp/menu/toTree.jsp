<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 此页面只有body元素内的内容才会被home.jsp加载，因此这里不能利用body作layout的容器 -->
<div class="easyui-layout" style="width:100%;height:100%">
	<div data-options="region:'west',title:'功能树'" style="width:200px;">
		<ul id="menu_tree"></ul>
	</div>
	<div data-options="region:'center',title:'详情',tools:'#menu_tool'" style="padding:5px 5px;">
		<div style="width:400px;height:300px;">
			<form id="updateMenu_form" method="post">
				<table style="text-align: right;padding: 5px 5px;">
					<tr>
						<!-- 将文本和input标签放在不同的td中，有助于施加样式，如上面设置将文本向右对齐 -->
						<td>名称:</td>
						<td>
							<input id="name_update" name="name" class="easyui-textbox" data-options="required:true" style="width:172px;">
							<input type="hidden" id="id_update" name="id">
						</td>
					</tr>
					<tr>
						<td>图标:</td>
						<td><input id="iconCls_update" name="iconCls" style="width:172px;"></td>
					</tr>	
					<tr>
						<td>url:</td>
						<td><input id="url_update" name="url" class="easyui-textbox" style="width:172px;"></td>
					</tr>
					<tr>
						<td>排序:</td>
						<td><input id="px_update" name="px" class="easyui-numberbox" data-options="prompt:'数字越大,排序越靠后'" style="width:172px;"></td>
					</tr>	
					<tr>
						<td>所属菜单:</td>
						<td><input id="parent_update" name="parent.id" style="width:172px;"></td>
					</tr>		
				</table>
				
				<div style="text-align: center;margin-top: 20px;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="updateMenu()">修改</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="delMenu_dialog()">删除</a>
				</div>
			</form>
		</div>		
	</div>
	 <div id="menu_tool">
        <a href="javascript:void(0)" class="icon-add" onclick="addMenu_dialog()"></a>
    </div>
    
	<!-- 新增菜单项窗口,这里直接使用样式设置display=none(不初始化为easyui的dialog，当用户点击的时候才生成dialog),减少负荷-->
	<div id="addMenu_dialog" style="display: none;">
		<form id="addMenu_form" method="post">
			<table style="text-align: right;padding: 5px 5px;">
				<tr>
					<!-- 将文本和input标签放在不同的td中，有助于施加样式，如上面设置将文本向右对齐 -->
					<td>名称:</td>
					<td><input id="name_add" name="name" class="easyui-textbox" data-options="required:true" style="width:172px;"></td>
				</tr>
				<tr>
					<td>图标:</td>
					<td><input id="iconCls_add" name="iconCls" style="width:172px;"></td>
				</tr>
				<tr>
					<td>url:</td>
					<td><input id="url_add" name="url" class="easyui-textbox" style="width:172px;"></td>
				</tr>	
				<tr>
					<td>排序:</td>
					<td><input id="px_add" name="px" class="easyui-numberbox" data-options="prompt:'数字越大,排序越靠后'" style="width:172px;"></td>
				</tr>	
				<tr>
					<td>所属菜单:</td>
					<td><input id="parent_add" name="parent.id" style="width:172px;"></td>
				</tr>		
			</table>
			<!-- 下面是保存和取消操作（取消操作直接写在这里） -->
			<div style="text-align: center;margin-top: 20px;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addMenu()">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',onClick:function(){$('#addMenu_dialog').dialog('close');}">取消</a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$(function(){
	
	$("#menu_tree").tree({
		url:'menu/getMenuTree',
		onClick:function(node){
			$("#name_update").textbox('setValue',node.text);
			$("#id_update").val(node.id);//便于传递到后台作为更新的id
			$("#iconCls_update").textbox('setValue',node.iconCls);
			$("#url_update").textbox('setValue',node.attributes.url);
			$("#px_update").textbox('setValue',node.attributes.px);
			$("#parent_update").combobox('setValue',node.attributes.parent.id);
		}
	});
	
	//图标下拉框
	$("#iconCls_update").combobox({
		url:'dataDic/getDataDicItems?dataDicType=图标',
		valueField:'name',	//注意这里不是id，因为这里并不是关联关系
		textField:'name',
		editable:false,
		icons:[{
            iconCls:'icon-clear',
            handler:function(e){
            	$(e.data.target).combobox('clear');
            }
        }]
	});
	//所属菜单
	$("#parent_update").combobox({
		url:'menu/getLevelOne',
		valueField:'id',
		textField:'name',
		editable:false,
		icons:[{
            iconCls:'icon-clear',
            handler:function(e){
            	$(e.data.target).combobox('clear');
            }
        }]
	});
});

//真正新增菜单项的场所
function addMenu(){
	$("#addMenu_form").form('submit',{
		url:'menu/addMenu',
		success:function(data){
			var data = eval('(' + data + ')');
			if (data.result == 'success') {
				$.messager.alert('新增菜单项','新增成功。');
				$("#menu_tree").tree('reload');
				$("#addMenu_dialog").dialog('close');
			} else {
				$.messager.alert('新增菜单项','新增失败。');
				$("#addMenu_dialog").dialog('close');
			}
		}
	});
}
//新增菜单项(打开新增窗口)
function addMenu_dialog(){
	$("#addMenu_dialog").css("display","block");
	
	//图标下拉框
	$("#iconCls_add").combobox({
		url:'dataDic/getDataDicItems?dataDicType=图标',
		valueField:'name',	//注意这里不是id，因为这里并不是关联关系
		textField:'name',
		editable:false,
		icons:[{
            iconCls:'icon-clear',
            handler:function(e){
            	$(e.data.target).combobox('clear');
            }
        }]
	});
	//所属菜单
	$("#parent_add").combobox({
		url:'menu/getLevelOne',
		valueField:'id',
		textField:'name',
		editable:false,
		panelHeight:'auto',
		icons:[{
            iconCls:'icon-clear',
            handler:function(e){
            	$(e.data.target).combobox('clear');
            }
        }]
	});
	
	$("#addMenu_dialog").dialog({
		title:'新增菜单项',
		width:350,
		height:250,
		modal:true
	});
}
//修改菜单项
function updateMenu(){
	var node = $("#menu_tree").tree('getSelected');
	if (node == null) {
		$.messager.alert("修改菜单项",'请先从树种选中需要更新的菜单项。','info');
		return false;
	}

	$("#updateMenu_form").form('submit',{
		url:'menu/updateMenu',
		success:function(data){
			var data = eval('(' + data + ')');
			if (data.result == 'success') {
				$.messager.alert('修改菜单项','修改成功。');
				$("#menu_tree").tree('reload');
			} else {
				$.messager.alert('修改菜单项','修改失败。');
			}
			
		}
	});
}

//删除菜单项
function delMenu_dialog(){
	var node = $("#menu_tree").tree('getSelected');
	if (node == null) {
		$.messager.alert("删除菜单项",'请先从树种选中需要删除的菜单项。','info');
		return false;
	}
	if(!$("#menu_tree").tree('isLeaf',node.target)){
		$.messager.alert('删除菜单项','非叶子节点不能删除。','warning');
		return false;
	}
	$.messager.confirm('删除菜单项',"确定删除此条记录吗?",function(flag){
		if(flag){
			var id = node.id;
			$.ajax({
				url:'menu/delMenuById',
				type:'post',
				dataType:'json',
				data:{id:node.id},
				success:function(data){
					if(data.result == 'success'){
						$.messager.alert('删除菜单项','删除成功。');
						$("#menu_tree").tree('reload');
					}else{
						$.messager.alert('删除菜单项','删除失败。');
					}
				}
			});
		}
	});
}
</script>
</body>
</html>