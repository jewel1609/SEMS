package com.ktds.sems.pc.service.impl;

import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.service.PcService;

public class PcServiceImpl implements PcService {

	private PcBiz pcBiz;

	public void setPcBiz(PcBiz pcBiz) {
		this.pcBiz = pcBiz;
	}

}
