package com.ktds.sems.member.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.GrdtTpVO;
import com.ktds.sems.member.vo.HighestEduTpVO;
import com.ktds.sems.member.vo.MbrTpVO;

public class MemberServiceImpl implements MemberService{

	private MemberBiz memberBiz;
		
	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	/*Grtd*/
	
	@Override
	public ModelAndView viewGrdtPage() {
		
		List<GrdtTpVO> grdtTpList = memberBiz.getAllGrtdList();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/grdtPage");
		view.addObject("grtdTpList", grdtTpList);
		return view;
	}

	@Override
	public String doGrdtDelete(String cdId) {
		memberBiz.doGrdtDelete(cdId);
		
		//WEB-INF/view/member/grdtPage.jsp
		return "redirect:/grdtPage";
	}

	@Override
	public String doGrdtModify(String cdId, String cdNm) {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId(cdId);
		grdtTpVO.setCdNm(cdNm);
			
		if(memberBiz.isExistCdNmData(grdtTpVO) > 0){
			return "FAIL";
		}	
		
		else{	
			boolean data = memberBiz.doGrdtModify(grdtTpVO) > 0;
				
			if(!data){
				return "FAIL";
			}
			return "OK";
		}
	}

	@Override
	public String doGrdtInsert(String cdId, String cdNm) {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId(cdId);
		grdtTpVO.setCdNm(cdNm);
		
		if(memberBiz.isExistData(grdtTpVO) > 0){
			return "FAIL";
		}	
		
		else{	
			boolean data = memberBiz.doGrdtInsert(grdtTpVO) > 0;
				
			if(!data){
				return "FAIL";
			}
			return "OK";
		}
	}
	
	/* Highest Edu */
	@Override
	public ModelAndView viewHighestEduPage() {
		List<HighestEduTpVO> highestEduTpList = memberBiz.getAllHighestEduList();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("member/highestEduLv");
		view.addObject("highestEduTpList", highestEduTpList);
		return view;
	}

	@Override
	public String doHighestEduDelete(String cdId) {
		if(memberBiz.doHighestEduDelete(cdId) ) {
			return "redirect:/highestEduPage";
		} else {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public void doHighestEduModify(String cdId, String cdNm) {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId(cdId);
		highestEduTpVO.setCdNm(cdNm);
		
		if (!memberBiz.doHighestEduModify(highestEduTpVO) ) {
			throw new RuntimeException("일시적인 오류가 발생했습니다.");
		}
	}

	@Override
	public String doHighestEduInsert(String cdId, String cdNm) {
		HighestEduTpVO highestEduTpVO = new HighestEduTpVO();
		highestEduTpVO.setCdId(cdId);
		highestEduTpVO.setCdNm(cdNm);
		
		if(memberBiz.isExistHighestEduData(highestEduTpVO) > 0){
			return "FAIL";
		}	
		
		else{	
			boolean data = memberBiz.doHighestEduInsert(highestEduTpVO) > 0;
				
			if(!data){
				return "FAIL";
			}
			return "OK";
		}
	}
	
	/*MbrTp*/
	@Override
	public ModelAndView viewMbrTpPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("member/mbrTp");	
		List<MbrTpVO> mbrTpVOList = memberBiz.getAllMbrTpList();
		view.addObject("mbrTpVOList", mbrTpVOList);
		
		return view;
	}
	
	@Override
	public String doInsertMbrTp(String cdId, String cdNm) {
		MbrTpVO newMbrTpVO = new MbrTpVO();
		newMbrTpVO.setCdId(cdId);
		newMbrTpVO.setCdNm(cdNm);
		memberBiz.doInsertMbrTp(newMbrTpVO);
		return "redirect:/mbrTpPage";
	}
	
	@Override
	public String doMbrTpDelete(String cdId) {
		memberBiz.doMbrTpDelete(cdId);
		return "redirect:/mbrTpPage";
	}

	@Override
	public String doMbrTpModify(String cdId, String cdNm) {
		MbrTpVO mbrTpVO = new MbrTpVO();
		mbrTpVO.setCdId(cdId);
		mbrTpVO.setCdNm(cdNm);
		memberBiz.doMbrTpModify(mbrTpVO);
		return "redirect:/mbrTpPage";
	}

}
