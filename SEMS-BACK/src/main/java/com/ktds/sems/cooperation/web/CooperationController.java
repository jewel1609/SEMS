package com.ktds.sems.cooperation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.cooperation.service.CooperationService;

@Controller
public class CooperationController {

	private CooperationService cooperationService;

	public void setCooperationService(CooperationService cooperationService) {
		this.cooperationService = cooperationService;
	}
	
	@RequestMapping("/cooList")
	public ModelAndView viewCooListPage(@RequestParam(required=false, defaultValue="0") int pageNo) {
		return cooperationService.getAllCooperationList(pageNo);
	}
	
	@RequestMapping("/cooDetail/{cooperationId}")
	public ModelAndView viewCooDetailPage(@PathVariable String cooperationId) {
		return cooperationService.getOneCooperation(cooperationId);
	}
	
	@RequestMapping("/cooDelete/{cooperationId}")
	public String doDeleteCooperation(@PathVariable String cooperationId){
		return cooperationService.doDeleteCooperation(cooperationId);
	}
	
}
