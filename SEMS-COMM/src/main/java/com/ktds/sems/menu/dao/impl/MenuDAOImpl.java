package com.ktds.sems.menu.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.menu.dao.MenuDAO;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuDAOImpl extends SqlSessionDaoSupport implements MenuDAO{

	@Override
	public List<MenuManageVO> getMenuCategoryList() {
		return getSqlSession().selectList("MenuDAO.getMenuCategoryList");
	}

	@Override
	public int doMenuUpdate(MenuManageVO menuManageVO) {
		return getSqlSession().update("MenuDAO.doMenuUpdate", menuManageVO);
	}

	@Override
	public MenuManageVO getOneMenuCategory(String codeId) {
		return getSqlSession().selectOne("MenuDAO.getOneMenuCategory", codeId);
	}


	@Override
	public void downMenuList(Map<String, Integer> sortNo) {
		getSqlSession().update("MenuDAO.downMenuList", sortNo);
		
	}

	@Override
	public void upMenuList(Map<String, Integer> sortNo) {
		getSqlSession().update("MenuDAO.upMenuList", sortNo);
		
	}

	
	

}
