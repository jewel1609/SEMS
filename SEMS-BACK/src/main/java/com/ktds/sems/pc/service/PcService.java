package com.ktds.sems.pc.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.vo.PcVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;

public interface PcService {

	public ModelAndView getUsedPcList(UsedPcSearchVO usedPcSearchVO);

	public ModelAndView getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO);

	public String changeReportedState(ReportedPcVO reportedPcVO);

	public ModelAndView educationPlaceSetting(HttpSession session);

	public ModelAndView doRegistClass(PcVO pcVO, HttpSession session);

	public ModelAndView viewEducationPlaceList();
}
