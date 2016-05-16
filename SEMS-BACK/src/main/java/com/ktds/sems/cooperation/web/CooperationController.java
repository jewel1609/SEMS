package com.ktds.sems.cooperation.web;

import org.springframework.stereotype.Controller;

import com.ktds.sems.cooperation.service.CooperationService;

@Controller
public class CooperationController {

	private CooperationService cooperationService;

	public void setCooperationService(CooperationService cooperationService) {
		this.cooperationService = cooperationService;
	}
	
}
