package com.univ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univ.domain.MenuInfo;
import com.univ.mapper.MenuMapper;

/** 
 * @author univ 
 * @date 2015年12月30日 上午10:00:32 
 * @version v1.0
 * @Description: 
 */
@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * @return	所有的一级菜单,有排序
	 */
	public List<MenuInfo> getLevelOneMenu() {
		return menuMapper.getLevelOne();
	}

	/**
	 * 获取某菜单的所有子菜单,有排序
	 * @param id
	 * @return
	 */
	public List<MenuInfo> getChildrenById(Long id) {
		return menuMapper.getChildrenById(id);
	}

	/**
	 * 更新菜单项
	 * @param menu	欲被更新的菜单项
	 */
	public void updateMenu(MenuInfo menu) {
		menuMapper.updateMenu(menu);
	}

	/**
	 * 新增菜单项
	 * @param menu	欲被新增的菜单项
	 */
	public void addMenu(MenuInfo menu) {
		menuMapper.addMenu(menu);
	}

	/**
	 * 根据id删除一条菜单项记录
	 * @param id	欲被删除菜单项记录的id
	 */
	public void delMenuById(int id) {
		menuMapper.deleteMenu(id);
	}
}

