package com.univ.domain;
/** 
 * @author univ 
 * @date 2015年12月30日 上午11:21:04 
 * @version v1.0
 * @Description: 以常量的形式封装了数据库中的所有表名
 * 注意：在封装表名的时候必须在表名的首尾各加一个空格（避免产生不必要的sql语句语法错误）
 */
public interface DBTable {

	public static final String MENU=" menu ";//菜单表，注意表名前后有空格
	public static final String DATADIC=" datadic ";//数据字典表
}

