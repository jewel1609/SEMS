package com.ktds.sems.member.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MbrTpVO;
import com.ktds.sems.member.vo.GrdtTpVO;

public class MemberServiceImpl implements MemberService{

	private MemberBiz memberBiz;
		
	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

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
		memberBiz.doGrdtModify(grdtTpVO);
		return "redirect:/grdtPage";
	}

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


	@Override
	public String doGrdtInsert(String cdId, String cdNm) {
		GrdtTpVO grdtTpVO = new GrdtTpVO();
		grdtTpVO.setCdId(cdId);
		grdtTpVO.setCdNm(cdNm);
		memberBiz.doGrdtInsert(grdtTpVO);
		return "redirect:/grdtPage";
	}

}
