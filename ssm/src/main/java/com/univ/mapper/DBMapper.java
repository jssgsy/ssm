package com.univ.mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Company: miaxis
 * @author :liu.ml
 * @date :2015-12-28 上午11:01:56
 * Description: 
 */
public interface DBMapper {

	/**
	 * @param id
	 */
	@Select("select count(*) from dbtest")
	int totalCount();

}
