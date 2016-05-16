package com.ktds.sems.cooperation.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.cooperation.biz.CooperationBiz;
import com.ktds.sems.cooperation.service.CooperationService;
import com.ktds.sems.cooperation.vo.CooperationListVO;
import com.ktds.sems.cooperation.vo.CooperationSearchVO;
import com.ktds.sems.cooperation.vo.CooperationVO;

import kr.co.hucloud.utilities.web.Paging;

public class CooperationServiceImpl implements CooperationService{

	private CooperationBiz cooperationBiz;

	public void setCooperationBiz(CooperationBiz cooperationBiz) {
		this.cooperationBiz = cooperationBiz;
	}

	@Override
	public ModelAndView getAllCooperationList(int pageNo) {
		
		CooperationListVO cooperationListVO = new CooperationListVO();
		Paging paging = new Paging();
		cooperationListVO.setPaging(paging);
		
		paging.setPageNumber(pageNo + "");
		
		int totalCooperationCount = cooperationBiz.getTotalCooperationCount();
		paging.setTotalArticleCount(totalCooperationCount);
		
		CooperationSearchVO searchVO = new CooperationSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<CooperationVO> cooperationList = cooperationBiz.getAllCooperation(searchVO);
		cooperationListVO.setCooperationList(cooperationList);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("cooperation/cooperationList");
		view.addObject("cooperationListVO", cooperationListVO);
		
		return view;
	}

	@Override
	public ModelAndView getOneCooperation(String cooperationId) {
		
		ModelAndView view = new ModelAndView();
		CooperationVO cooperationVO = cooperationBiz.getOneCooperation(cooperationId);
		
		view.setViewName("cooperation/cooperationDetail");
		view.addObject("cooperationVO", cooperationVO);
		return view;
	}

	@Override
	public String doDeleteCooperation(String cooperationId) {
		
		boolean deleteResult = cooperationBiz.doDeleteCooperation(cooperationId);
		
		if ( deleteResult ) {
			return "redirect:/cooList";
		}
		else {
			throw new RuntimeException("일시적인 장애가 발생했습니다. 잠시후 다시 시도해주세요.");
		}
		
	}
	
}
