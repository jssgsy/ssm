package com.univ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import static com.univ.domain.DBTable.*;

import com.univ.domain.DataDicInfo;

/** 
 * @author univ 
 * @date 2015年12月30日 下午1:39:08 
 * @version v1.0
 * @Description: 
 */
public interface DataDicMapper {

	/**
	 * 获取一页的数据
	 * @param startRow 检索的起始行
	 * @param pageSize	每页显示的记录数
	 * @return
	 * 
	 */

	/**
	 * 获取表中所有的记录数
	 * @return
	 */
	@Select("select count(id) from" + DATADIC)
	int totalCount();

	/**
	 * 获取一页的数据
	 * @param startRow 检索的起始行(mysql数据库是从0开始)
	 * @param pageSize	每页显示的记录数
	 * @param dataDic	封装了查询参数
	 * @return
	 */
	@SelectProvider(type=DataDicSql.class,method="getOnePage")
	@Results(value={
			@Result(column="id",property="id"),
			@Result(column="name",property="name"),
			@Result(column="description",property="description"),
			@Result(column="pId",property="parent.id"),
	})
	List<DataDicInfo> getOnePage(@Param("startRow")int startRow, @Param("pageSize")int pageSize, @Param("dataDic")DataDicInfo dataDic);

	/**
	 * 如果数据库中显示设置在新增时如果当pId为空则赋值为0，则不需要后面的is null,这里显得更健壮
	 * @return	所有的数据字典类型
	 */
	@Select("select id,name from" + DATADIC + "where pId = 0 or pId is null")
	@Results(value={
			@Result(column="id",property="id"),
			@Result(column="name",property="name"),
	})
	List<DataDicInfo> getLevelOne();

	/**
	 * 新增单条数据字典项记录
	 * @param dataDic	欲被新增的数据字典项
	 */
	@Insert("insert into" + DATADIC + "(id,name,description,pId)"
			+ "values("
			+ "#{dataDic.id},#{dataDic.name},#{dataDic.description},#{dataDic.parent.id}"
			+ ")")
	void add(@Param("dataDic")DataDicInfo dataDic);

	/**
	 * 删除单条数据字典记录
	 * @param id	欲删除数据字典记录的id
	 */
	@Delete("delete from" + DATADIC + "where id = #{id}")
	void delete(@Param("id")Integer id);

	/**
	 * 更新单条数据字典记录
	 * @param dataDic	欲被修改的数据字典记录
	 */
	@Update("update" + DATADIC + "set name = #{dataDic.name},description = #{dataDic.description},pId = #{dataDic.parent.id} where id=#{dataDic.id}")
	void update(@Param("dataDic")DataDicInfo dataDic);

	/**
	 * 获取某个数据项所属的数据类型
	 * @param dataDicInfo
	 * @return
	 */
	@Select("select id,name from" + DATADIC + "where id = #{dataDic.parent.id}")
	@Results(value={
			@Result(column="id",property="id"),
			@Result(column="name",property="name")
	})
	DataDicInfo getParent(@Param("dataDic")DataDicInfo dataDic);

	/**
	 * 获取某个数据类型下的所有数据项(涉及到子查询)
	 * @param dataDicType
	 */
	@Select("select id,name from" + DATADIC + "where pId in "
			+ "(select id from" + DATADIC + "where name = #{dataDicType})")
	List<DataDicInfo> getDataDicItems(@Param("dataDicType")String dataDicType);
	
}

