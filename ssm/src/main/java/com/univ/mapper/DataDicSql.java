package com.univ.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.univ.domain.DataDicInfo;
import static com.univ.domain.DBTable.*;

/** 
 * @author univ 
 * @date 2015年12月30日 下午3:33:32 
 * @version v1.0
 * @Description: 
 */
public class DataDicSql {

	/**
	 * 获取一页的数据
	 * @param map 封装了查询参数
	 * @return
	 */
	public String getOnePage(Map<String, Object> map){
		final DataDicInfo dataDicTemp =  (DataDicInfo) map.get("dataDic");
		String sql = new SQL(){
			{
				SELECT("*");
				FROM(DATADIC);
				
				//数据项名称查询
				if(null != dataDicTemp.getName() && !"".equals(dataDicTemp.getName())){
					WHERE("name like CONCAT('%', #{dataDic.name}, '%')");
				}
				//根据数据类型查询
				if (null != dataDicTemp.getParent() && null != dataDicTemp.getParent().getId()) {
					WHERE("pId =  #{dataDic.parent.id}");
				}
			}
		}.toString();
		sql = sql + " limit #{startRow},#{pageSize} ";
		return sql;
	}
}

