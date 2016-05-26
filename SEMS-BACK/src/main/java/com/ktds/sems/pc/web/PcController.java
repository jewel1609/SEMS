package com.ktds.sems.pc.web;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

@Controller
public class PcController {

	private PcService pcService;

	public void setPcService(PcService pcService) {
		this.pcService = pcService;
	}

	@RequestMapping("/usedPcList")
	public ModelAndView viewUsedPcListPage(UsedPcSearchVO usedPcSearchVO) {
		return pcService.getUsedPcList(usedPcSearchVO);
	}
	
	@RequestMapping("/reportedPcList")
	public ModelAndView viewReportedPcListPage(ReportedPcSearchVO reportedPcSearchVO) {
		return pcService.getReportedPcListWithPaging(reportedPcSearchVO);
	}
	
	@RequestMapping("/changeReportedState")
	public void changeReportedState(HttpServletResponse response, ReportedPcVO reportedPcVO) {
		String data = pcService.changeReportedState(reportedPcVO);
		AjaxUtil.sendResponse(response, data);
	}
	
	@RequestMapping("/eduPlaceSet")
	public ModelAndView educationPlaceSetting(HttpSession session){
		return pcService.educationPlaceSetting(session);
	}
	
	@RequestMapping(value="/doRegistClass", method=RequestMethod.POST)
	public ModelAndView doRegistClass(@Valid PcVO pcVO, Errors errors, HttpSession session){
		return pcService.doRegistClass(pcVO, errors, session);
	}
	
	@RequestMapping("/eduPlaceList")
	public ModelAndView viewEducationPlaceList() {
		return pcService.viewEducationPlaceList();
	}
}
