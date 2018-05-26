<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 核心数据表格区域 -->
<table id="dataDic_datagrid" ></table>

<!-- 查询工具条 -->
<div id="search">
	数据项:<input id="name" name="name" style="width:100px;">
	数据类型:<input id="parentId"/>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchDataDic()" style="width:60px">搜索</a><br/>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add_dialog()" style="width:60px">新增</a>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="update_dialog()" style="width:60px">修改</a>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del_dialog()" style="width:60px">删除</a>
</div>

<!-- 新增数据字典窗口,这里直接使用样式设置display=none(不初始化为easyui的dialog，当用户点击的时候才生成dialog),减少负荷-->
<div id="addDataDic_dialog" style="display: none;">
	<form id="addDataDic_form" method="post">
		<table style="text-align: right;padding: 5px 5px;">
			<tr>
				<!-- 将文本和input标签放在不同的td中，有助于施加样式，如上面设置将文本向右对齐 -->
				<td>数据项:</td>
				<td><input id="name_add" name="name" class="easyui-textbox" data-options="required:true" style="width:172px;"></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td>
				<textarea id="description_add" name="description" rows="5" maxlength="50" class="textbox" style="resize:none;width:170px;white-space:pre-wrap"></textarea>
				</td>
			</tr>
			<tr>
				<td>数据类型:</td>
				<td><input id="parent_add" name="parent.id" style="width:172px;"></td>
			</tr>		
		</table>
		<!-- 下面是保存和取消操作（取消操作直接写在这里） -->
		<div style="text-align: center;margin-top: 20px;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addDataDic()">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',onClick:function(){$('#addDataDic_dialog').dialog('close');}">取消</a>
		</div>
	</form>
</div>

<!-- 修改数据字典窗口,这里直接使用样式设置display=none(不初始化为easyui的dialog，当用户点击的时候才生成dialog),减少负荷-->
<div id="updateDataDic_dialog" style="display: none;">
	<form id="updateDataDic_form" method="post">
		<table style="text-align: right;padding: 5px 5px;">
			<tr>
				<!-- 将文本和input标签放在不同的td中，有助于施加样式，如上面设置将文本向右对齐 -->
				<td>数据项:</td>
				<td>
					<input id="name_update" name="name" class="easyui-textbox" data-options="required:true" style="width:172px;">
					<input type="hidden" id="id_update" name="id">
				</td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><textarea id="description_update" name="description" rows="5" maxlength="50" class="textbox" style="resize:none;width:170px;white-space:pre-wrap"></textarea></td>
			</tr>
			<tr>
				<td>数据类型:</td>
				<td><input id="parent_update" name="parent.id" style="width:172px;"></td>
			</tr>		
		</table>
		<!-- 下面是保存和取消操作（取消操作直接写在这里） -->
		<div style="text-align: center;margin-top: 20px;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="updateDataDic()">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',onClick:function(){$('#updateDataDic_dialog').dialog('close');}">取消</a>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	
	//查询框中的数据项查询
	$("#name").textbox({
		 icons: [{
				iconCls:'icon-clear',
				handler: function(e){
					$(e.data.target).textbox('clear');
				}
			}]
	});
	
	//查询框中的数据类型查询
	$("#parentId").combobox({
		url:'dataDic/getLevelOne',
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
	
	$("#dataDic_datagrid").datagrid({
		title:'数据字典详情',
		pagination:true,
		pageSize:20,
		singleSelect:true,
		rownumbers:true,
		fit:true,//此时当点击最外围的layout时整个表格可以自动适应大小
		toolbar:'#search',
		url:'dataDic/getOnePage',
		columns:[[
	          {field:'name',title:'数据项',width:120},
	          {field:'description',title:'描述',width:150},
	          {field:'xxx',title:'数据类型',width:100,
	        	  formatter: function(value,row,index){
	        		  if(row.parent){
	        			  return row.parent.name;
	        		  }else{
	        			  return value;
	        		  }
	        	  }
	          }
	    	  ]] 
	});
	
});

//查询数据字典
function searchDataDic(){
	$("#dataDic_datagrid").datagrid('load',{
		name:$("#name").textbox('getValue'),
		parentId:$("#parentId").combobox('getValue')
	});	
}

//真正执行保存操作
function addDataDic(){
	$("#addDataDic_form").form('submit',{
		url:'dataDic/addDataDic',
		success:function(data){
			var data = eval('(' + data + ')');
			//如果新增成功，做三件事：1.刷新链表，2.提示新增操作成功,3.关闭新增窗口
			if(data.result == 'success'){
				$.messager.alert('新增数据字典项','新增成功。');
				$("#dataDic_datagrid").datagrid('reload');
			}else{
				$.messager.alert('新增数据字典项','新增失败。');
			}
			$("#addDataDic_dialog").dialog('close');
			
			
		}
	});
}

//新增数据字典(打开新增窗口)
function add_dialog(){
	$("#addDataDic_dialog").css("display","block");
	//设置各字段对应的easyui插件
	$("#parent_add").combobox({
		url:'dataDic/getLevelOne',
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
	
	$("#addDataDic_dialog").dialog({
		title:'新增数据字典项',
		width:400,
		height:300,
		modal:true
	});
}

//真正执行更新操作
function updateDataDic(){
	$("#updateDataDic_form").form('submit',{
		url:'dataDic/updateDataDic',
		success:function(data){
			var data = eval('(' + data + ')');
			//如果更新成功，做三件事：1.刷新链表，2.提示新增操作成功,3.关闭新增窗口
			if (data.result == 'success') {
				$.messager.alert('修改数据字典项','修改成功。');
				$("#dataDic_datagrid").datagrid('reload');
			} else {
				$.messager.alert('修改数据字典项','修改失败。');
			}			
			$("#updateDataDic_dialog").dialog('close');
		}
	});
}

//打开修改数据字典
function update_dialog(){
	var row = $("#dataDic_datagrid").datagrid('getSelected');	
	if (row == null) {
		$.messager.alert("修改数据字典项",'请先选中需要修改的项。','info');
		return false;
	}
	//给id赋值便于传递到后台
	$("#id_update").val(row.id);
	
	$("#updateDataDic_dialog").css("display","block");
	//设置各字段对应的easyui插件
	$("#parent_update").combobox({
		url:'dataDic/getLevelOne',
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
	
	$("#updateDataDic_dialog").dialog({
		title:'修改数据字典项',
		width:400,
		height:300,
		modal:true
	});
	//给各字段赋值
	$("#name_update").textbox('setValue',row.name);
	$("#description_update").val(row.description);
	$("#parent_update").combobox('setValue',row.parent.id);
}

//删除数据字典
function del_dialog(){
	var row = $("#dataDic_datagrid").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("删除数据字典项",'请先选中需要删除的项。','info');
		return false;
	}
	$.messager.confirm('删除数据字典项',"确定删除此条记录吗?",function(flag){
		if(flag){
			var id = row.id;
			$.ajax({
				url:'dataDic/delDataDicById',
				type:'post',
				dataType:'json',
				data:{id:row.id},
				success:function(data){
					if(data.result == 'success'){
						$.messager.alert('删除数据字典','删除成功。');
						$("#dataDic_datagrid").datagrid('reload');
					}else{
						$.messager.alert('删除数据字典','删除失败。');
					}
				}
			});
		}
	});
}
</script>
</body>
</html>