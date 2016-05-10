package com.ktds.sems.menu.biz.impl;

import java.util.List;

import com.ktds.sems.menu.biz.MenuBiz;
import com.ktds.sems.menu.dao.MenuDAO;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuBizImpl implements MenuBiz{

	private MenuDAO menuDAO;
	
	public void setMenuDao(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	@Override
	public boolean doMenuUpdate() {
		return menuDAO.doMenuUpdate() > 0;
	}

	@Override
	public List<MenuManageVO> getMenuCategory() {
		return menuDAO.getMenuCategory();
	}

}
