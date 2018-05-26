package com.univ.domain;
/** 
 * @author univ 
 * @date 2015年12月30日 下午1:32:02 
 * @version v1.0
 * @Description: 数据字典实体类
 * 这里将数据字典的类型和数据字典的项存放在同一个表中
 */
public class DataDicInfo {

	private Long id;	//逻辑主键
	private String name;	//数据项名称
	private String description;	//对数据项的描述
	private DataDicInfo parent ;	//数据项从属的数据类型
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DataDicInfo getParent() {
		return parent;
	}
	public void setParent(DataDicInfo parent) {
		this.parent = parent;
	}
}

