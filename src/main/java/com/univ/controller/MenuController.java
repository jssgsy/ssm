package com.univ.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.univ.domain.Constant.*;
import com.univ.domain.EasyUITreeNode;
import com.univ.domain.MenuInfo;
import com.univ.service.MenuService;

/** 
 * @author univ 
 * @date 2015年12月30日 上午9:42:27 
 * @version v1.0
 * @Description: 菜单控制器
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	private Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/getLevelOne")
	@ResponseBody
	public List<MenuInfo> getLevelOne(){		
		return menuService.getLevelOneMenu();
	}
	/**
	 * @return 作为树形结构显示的菜单（导航栏）,有排序
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public List<EasyUITreeNode> getMenuTree(){
		logger.debug("=======MenuController.getMenuTree() invoked=======");
		List<EasyUITreeNode> tree = new ArrayList<EasyUITreeNode>();		
		List<MenuInfo> rootMenuList = menuService.getLevelOneMenu();
		for (MenuInfo rootMenu : rootMenuList) {
			EasyUITreeNode node = new EasyUITreeNode();//对应于根菜单的树节点			
			//映射根节点的非children属性(不需要映射parent)
			node.setId(rootMenu.getId());
			node.setText(rootMenu.getName());
			node.setIconCls(rootMenu.getIconCls());
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", rootMenu.getUrl());
			attributes.put("px", rootMenu.getPx());
			node.setAttributes(attributes);
			
			EasyUITreeNode finalNode = traversal(rootMenu,node);
			tree.add(finalNode);
		}		
		return tree;
	}
	
	/**
	 * 跳转到菜单管理中
	 * @return
	 */
	@RequestMapping("/toTree")
	public String toTree(){
		logger.debug("=======MenuController.toTree() invoked=======");
		return "menu/toTree";
	}
	
	/**
	 * 新增菜单项
	 * @param menu	欲被新增的菜单项
	 */
	@RequestMapping("/addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(MenuInfo menu){
		logger.debug("=======MenuController.addMenu() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			menuService.addMenu(menu);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", OPERATE_FAIL);
		}
		return map;
	}
	/**
	 * 更新菜单项
	 * @param menu	欲被更新的菜单项
	 * @return
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	public Map<String, Object> updateMenu(MenuInfo menu){
		logger.debug("=======MenuController.updateMenu() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			menuService.updateMenu(menu);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", OPERATE_FAIL);
		}
		
		return map;
	}
	
	/**
	 * 根据id删除一条菜单项记录
	 * @param id	欲被删除菜单项记录的id
	 */
	@RequestMapping("/delMenuById")
	@ResponseBody
	public Map<String, Object> delMenuById(int id){
		logger.debug("=======MenuController.delMenuById() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			menuService.delMenuById(id);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", OPERATE_FAIL);
		}		
		return map;
	}
	
	/**
	 * 以某个菜单为根节点，获取其所有的子节点，并将其映射成easyui的树节点
	 * 涉及到树就涉及到递归，树也是一个节点
	 * @param menu	根菜单
	 * @param node	对应于根菜单的树节点
	 * @return 以menu作为根节点的menu树(节点)
	 */
	private EasyUITreeNode traversal(MenuInfo menu,EasyUITreeNode node) {	
		logger.debug("=======MenuController.traversal() invoked=======");
		List<MenuInfo> children = menuService.getChildrenById(menu.getId());
		for (MenuInfo m : children) {
			EasyUITreeNode childNode = new EasyUITreeNode();
			//映射非children属性
			childNode.setId(m.getId());
			childNode.setText(m.getName());
			childNode.setIconCls(m.getIconCls());
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", m.getUrl());
			attributes.put("px", m.getPx());
			attributes.put("parent", m.getParent());
			childNode.setAttributes(attributes);
			
			//children属性在这里映射
			node.getChildren().add(childNode);
			traversal(m,childNode);
		}
		return node;
	}
	
}

