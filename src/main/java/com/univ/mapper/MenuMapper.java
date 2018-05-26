package com.univ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import static com.univ.domain.DBTable.*;

import com.univ.domain.MenuInfo;

/** 
 * @author univ 
 * @date 2015年12月30日 上午10:00:15 
 * @version v1.0
 * @Description: 菜单
 */
public interface MenuMapper {
	/**
	 * @return	所有的一级菜单,根据px排序
	 */
	@Select("select * from" + MENU + "where pId = 0 or pId is null order by px" )
	@Results(value={
			@Result(column="id",property="id"),
			@Result(column="name",property="name"),
			@Result(column="url",property="url"),
			@Result(column="px",property="px")
	})
	public List<MenuInfo> getLevelOne();
	
	/**
	 * 获取某个菜单的所有子菜单,根据px排序
	 * @param id
	 * @return
	 */
	@Select("select * from" + MENU + "where pId = #{id}  order by px")
	@Results(value={
			@Result(column="id",property="id"),
			@Result(column="name",property="name"),
			@Result(column="url",property="url"),
			@Result(column="px",property="px"),
			@Result(column="iconCls",property="iconCls"),
			@Result(column="pId",property="parent.id")
	})
	public List<MenuInfo> getChildrenById(@Param("id")Long id);

	/**
	 * 更新菜单项
	 * @param menu	欲被更新的菜单项
	 */
	@Update("update" + MENU + "set name=#{menu.name},url=#{menu.url},px=#{menu.px},iconCls=#{menu.iconCls},pId=#{menu.parent.id} "
			+ "where id = #{menu.id}")
	public void updateMenu(@Param("menu")MenuInfo menu);

	/**
	 * 新增菜单项
	 * @param menu	欲被新增的菜单项
	 */
	@Insert("insert into" + MENU + "(name,url,px,iconCls,pId)"
			+ "values(#{menu.name},#{menu.url},#{menu.px},#{menu.iconCls},#{menu.parent.id})")
	public void addMenu(@Param("menu")MenuInfo menu);

	/**
	 * 根据id删除一条菜单项记录
	 * @param id	欲被删除菜单项记录的id
	 */
	@Delete("delete from" + MENU + "where id=#{id}")
	public void deleteMenu(@Param("id")int id);

}

