package com.ktds.sems.pc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.ReportedPcVO;

@Controller
public class PcController {

	private PcService pcService;

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}
	
	@RequestMapping("/member/myPc")
	public ModelAndView viewMyPcPage(HttpSession session, HttpServletRequest request) {
		return pcService.viewMyPcPage(session, request);
	}
	@RequestMapping(value=("/getEduLocationByTitle"), method = RequestMethod.POST)
	public void getEduLocationByTitle(@RequestParam String title, HttpServletResponse response, HttpSession session) {
		pcService.getEduLocationByTitle(title, response, session);
	}
	
	/**
	 * PC를 신고할 수 있는 팝업 창 
	 * 이기연
	 */
	@RequestMapping("/myPc/reportPage/{pcId}")
	public ModelAndView viewReportPcPage(@PathVariable String pcId, HttpSession session, HttpServletRequest request) {
		return pcService.viewReportPcPage(pcId, session, request);
	}
	
	@RequestMapping("/myPc/reportPage/report")
	public String reportProblemPc(@Valid ReportedPcVO reportedPcVO, HttpSession session, HttpServletRequest request) {
		return pcService.reportProblemPc(reportedPcVO, session, request);
	}
	
}
