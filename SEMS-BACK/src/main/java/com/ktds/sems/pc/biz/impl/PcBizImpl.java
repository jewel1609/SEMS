package com.ktds.sems.pc.biz.impl;

import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.dao.PcDAO;

public class PcBizImpl implements PcBiz {

	private PcDAO pcDAO;

	public void setPcDAO(PcDAO pcDAO) {
		this.pcDAO = pcDAO;
	}

}
