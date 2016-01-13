<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入easyui的js和css文件 -->
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/icon.css">
<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.easyui.min.js"></script>

</head>
<body>
  <h2>ComboBox with Extra Icons</h2>
    <p>The user can attach extra icons to the ComboBox.</p>
    <div style="margin:20px 0"></div>
    <input class="easyui-combobox" 
            name="language"
            data-options="
                    url:'combobox_data1.json',
                    method:'get',
                    valueField:'id',
                    textField:'text',
                    panelHeight:'auto',
                    icons:[{
                        iconCls:'icon-add'
                    },{
                        iconCls:'icon-cut'
                    }]
            ">
</body>
</html>