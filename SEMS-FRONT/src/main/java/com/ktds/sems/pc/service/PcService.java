package com.ktds.sems.pc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.vo.ReportedPcVO;

public interface PcService {

	public ModelAndView viewMyPcPage(HttpSession session, HttpServletRequest request);

	public void getEduLocationByTitle(String title, HttpServletResponse response, HttpSession session);

	public ModelAndView viewReportPcPage(String educationId, HttpSession session, HttpServletRequest request);

	public String reportProblemPc(ReportedPcVO reportedPcVO, HttpSession session, HttpServletRequest request);

}
