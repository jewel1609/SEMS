package com.ktds.sems.menu.biz.impl;

import java.util.List;

import com.ktds.sems.menu.biz.MenuBiz;
import com.ktds.sems.menu.dao.MenuDAO;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuBizImpl implements MenuBiz{

	private MenuDAO menuDAO;
	
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}


	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return menuDAO.getMenuCategoryList();
	}


	@Override
	public boolean doMenuUpdate(MenuManageVO menuManageVO) {
		return menuDAO.doMenuUpdate(menuManageVO) > 0;
	}


	@Override
	public MenuManageVO getOneMenuCategory(String codeId) {
		return menuDAO.getOneMenuCategory(codeId);
	}

}
