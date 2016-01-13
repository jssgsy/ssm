说明：
	根目录下的database文件夹存放的项目中所需表的sql文件；

项目使用技术：
	后台：springmvc(4.1.7)+mybatis(3.2.8)；
	前台：jquery easyui(1.4.4)；
	数据库：mysql(5.5.20)
	数据库客户端：MySQL-Front 5.1
	日志：log4j(1.2.17);
	编码：utf-8
	jdk:jdk1.7.0_71
	tomcat:apache-tomcat-7.0.62-windows-x64
	数据源：commons-dbcp(1.4)

约定：
1.方法的请求地址和方法名同名；


复用代码：
1.日志定义
	private Logger logger = Logger.getLogger(.class);
	logger.debug("=======这里是调用的方法名 invoked=======");
2.导入数据库表常量
	import static com.univ.domain.DBTable.*;
3.导入指示操作是否成功的常量
	import static com.univ.domain.Constant.*;
	
3.导入easyui
	<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
	%>	
	
	<!-- 引入easyui的css文件 -->
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=contextPath %>/easyui/themes/icon.css">
	<!-- 引入easyui的js文件 -->
	<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/easyui/easyui-lang-zh_CN.js"></script>
整合中遇到的问题：
	eclipse下的maven项目时常发生加入依赖时报错的问题，需要不时的update project，竟然发生过需要update project好几次的情况，耗费了大量时间；
	<context:annotation-config />和<mvc:annotation-driven />不同，一个属于spring一个属于springmvc，
		使用springmvc则必须使用<mvc:annotation-driven />
	关于easyui tabs的content和href注意事项：
		content可以嵌入一个iframe，优点是对编码能力稍微弱一些，可以实现所有功能，因为此时iframe就是相当于一个新页面，
			但也正因为如何所以比较耗资源，每个页面都需要包含easyui的css文件和js文件以及其他可能的资源；
		使用href对编码能力要求较高，因为此时整个项目相当于只有一个页面，因此id值冲突需要需要(因此主页面home.jsp上应该少定义id值，以免和其他被加载的页面发生冲突)，
			另外使用href时只会加载body元素内的内容（不包含body元素），因此注意不要在被加载的页面上对body布局；
		使用href时虽然只会加载body元素内的内容，但不要去掉jsp的文档头，因为这里包含了页面的编码信息，可以只包含如下的内容：
			<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			<body></body>
			但最好也还是保留html和head标签，以免出现意想不到的错误。

经验：
	任何异步请求都需要一些响应数据，这里的重点是虽然不返回数据也可以操作数据库，但前台可能会警告。因此即使不想返回数据也可以返回一些操作是否成功的提示信息；



可以优化：
	系统设置---》菜单管理：在新增菜单项时，父菜单项只能选中一级菜单，这对于只有二级菜单导航来说可以满足要求，但对应大于二级以上的树需要再优化；
	数据字典删除时没有考虑删除数据类型时是否删除其下属的数据项，即没有考虑级联删除或是否允许删除有数据项数据类型;
	






	

