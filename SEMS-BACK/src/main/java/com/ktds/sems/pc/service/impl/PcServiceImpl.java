package com.ktds.sems.pc.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.pc.biz.PcBiz;
import com.ktds.sems.pc.service.PcService;
import com.ktds.sems.pc.vo.ReportedPcListVO;
import com.ktds.sems.pc.vo.ReportedPcSearchVO;
import com.ktds.sems.pc.vo.ReportedPcVO;
import com.ktds.sems.pc.vo.UsedPcListVO;
import com.ktds.sems.pc.vo.UsedPcSearchVO;
import com.ktds.sems.pc.vo.UsedPcVO;

import kr.co.hucloud.utilities.web.Paging;

public class PcServiceImpl implements PcService {

	private PcBiz pcBiz;

	public void setPcBiz(PcBiz pcBiz) {
		this.pcBiz = pcBiz;
	}

	@Override
	public ModelAndView getUsedPcList(UsedPcSearchVO usedPcSearchVO) {

		UsedPcListVO usedPcListVO = new UsedPcListVO();
		Paging paging = new Paging();
		usedPcListVO.setPaging(paging);
		
		if(usedPcSearchVO == null) {
			usedPcSearchVO = new UsedPcSearchVO();
			usedPcSearchVO.setPageNo(0);
		}
		
		paging.setPageNumber(usedPcSearchVO.getPageNo() + "");
		
		int totalCount = pcBiz.getTotalUsedPcCount(usedPcSearchVO);
		paging.setTotalArticleCount(totalCount);

		usedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		usedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<UsedPcVO> usedPcList = pcBiz.getUsedPcList(usedPcSearchVO);
		usedPcListVO.setUsedPcList(usedPcList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("pc/usedPcList");
		view.addObject("usedPcListVO", usedPcListVO);
		view.addObject("usedPcSearchVO", usedPcSearchVO);
		
		return view;
	}

	@Override
	public ModelAndView getReportedPcListWithPaging(ReportedPcSearchVO reportedPcSearchVO) {
		
		ReportedPcListVO reportedPcListVO = new ReportedPcListVO();
		Paging paging = new Paging();
		reportedPcListVO.setPaging(paging);
		
		if(reportedPcSearchVO == null) {
			reportedPcSearchVO = new ReportedPcSearchVO();
			reportedPcSearchVO.setPageNo(0);
		}
		paging.setPageNumber(reportedPcSearchVO.getPageNo() + "");
		
		int totalCount = pcBiz.getTotalReportedPcCount(reportedPcSearchVO);
		paging.setTotalArticleCount(totalCount);
		
		reportedPcSearchVO.setStartIndex(paging.getStartArticleNumber());
		reportedPcSearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ReportedPcVO> reportedPcList = pcBiz.getReportedPcListWithPaging(reportedPcSearchVO);
		reportedPcListVO.setReportedPcList(reportedPcList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("pc/reportedPcList");
		view.addObject("reportedPcListVO", reportedPcListVO);
		view.addObject("reportedPcSearchVO", reportedPcSearchVO);
		
		return view;
	}

	@Override
	public String changeReportedState(ReportedPcVO reportedPcVO) {
		
		String reportedState = reportedPcVO.getReportedState();
		
		if(reportedState.equals("PC_WT_IN")) {
			reportedPcVO.setReportedState("PC_PB_CH");
		} else if (reportedState.equals("PC_PB_CH")) {
			reportedPcVO.setReportedState("PC_AC_IN");
		} else if (reportedState.equals("PC_AC_IN")) {
			reportedPcVO.setReportedState("PC_AC_CP");
		} else {
			return "NO";
		}
		
		if(pcBiz.changeReportedState(reportedPcVO)) {
			return "OK";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

}
