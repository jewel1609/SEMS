package com.ktds.sems.cooperation.service;

import org.springframework.web.servlet.ModelAndView;

public interface CooperationService {

	public ModelAndView getAllCooperationList(int pageNo);

	public ModelAndView getOneCooperation(String cooperationId);

	public String doDeleteCooperation(String cooperationId);

}
