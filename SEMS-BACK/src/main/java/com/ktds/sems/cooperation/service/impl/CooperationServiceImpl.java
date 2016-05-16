package com.ktds.sems.cooperation.service.impl;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.service.CooperationService;

public class CooperationServiceImpl implements CooperationService{

	private CooperationBiz cooperationBiz;

	public void setCooperationBiz(CooperationBiz cooperationBiz) {
		this.cooperationBiz = cooperationBiz;
	}
	
}
