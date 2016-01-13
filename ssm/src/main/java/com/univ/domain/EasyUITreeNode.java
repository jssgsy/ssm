package com.univ.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * @author univ 
 * @date 2015年12月30日 上午9:46:44 
 * @version v1.0
 * @Description: 对应于easyui中的tree插件的树节点,字段是对应于tree节点的各个属性
 */
public class EasyUITreeNode {

	private Long id;	//An identity value bind to the node.
	private String text;	//Text to be showed.
	private String iconCls;	//The css class to display icon.
	private boolean checked = false;	//Indicate whether the node is checked selected.默认为false
	private String state = "open";	//node state, 'open' or 'closed', default is 'open'.
	private Map<String, Object> attributes;	//custom attributes can be added to a node
	private List<EasyUITreeNode> children = new ArrayList<EasyUITreeNode>();	//之所以定义成Object是因为不知道具体是什么类型
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<EasyUITreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}
}

