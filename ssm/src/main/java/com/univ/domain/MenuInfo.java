package com.univ.domain;
/** 
 * @author univ 
 * @date 2015年12月30日 上午9:38:58 
 * @version v1.0
 * @Description: 菜单实体
 */
public class MenuInfo {
	private Long id;	//逻辑主键
	private String name;	//菜单名称
	private String url;	//点击触发的访问地址
	private Integer px;	//显示的顺序
	private MenuInfo parent;
	private String iconCls;	//图标
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	public MenuInfo getParent() {
		return parent;
	}
	public void setParent(MenuInfo parent) {
		this.parent = parent;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}

