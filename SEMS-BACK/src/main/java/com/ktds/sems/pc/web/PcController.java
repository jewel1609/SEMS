package com.ktds.sems.pc.web;

import org.springframework.stereotype.Controller;

import com.ktds.sems.pc.service.PcService;

@Controller
public class PcController {

	private PcService pcService;

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}

}
