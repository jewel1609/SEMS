package com.ktds.sems.menu.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.menu.dao.MenuDAO;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuDAOImpl extends SqlSessionDaoSupport implements MenuDAO{

	@Override
	public int doMenuUpdate() {
		return 0;
	}

	@Override
	public List<MenuManageVO> getMenuCategory() {
		return getSqlSession().selectList("MenuDao.getMenuCategory");
	}

}
