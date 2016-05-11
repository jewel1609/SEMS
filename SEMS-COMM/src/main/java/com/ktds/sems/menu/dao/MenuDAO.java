package com.ktds.sems.menu.dao;

import java.util.List;

import com.ktds.sems.menu.vo.MenuManageVO;

public interface MenuDAO {

	public List<MenuManageVO> getMenuCategoryList();

	public int doMenuUpdate(MenuManageVO menuManageVO);

	public MenuManageVO getOneMenuCategory(String codeId);
}
