package com.ktds.sems.cooperation.biz.impl;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.dao.CooperationDAO;

public class CooperationBizImpl implements CooperationBiz{

	private CooperationDAO cooperationDAO;

	public void setCooperationDAO(CooperationDAO cooperationDAO) {
		this.cooperationDAO = cooperationDAO;
	}
	
}
