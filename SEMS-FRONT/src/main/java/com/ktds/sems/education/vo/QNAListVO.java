package com.ktds.sems.education.vo;

import java.util.List;

import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.Paging;

public class QNAListVO extends MemberVO{

	private List<QNAVO> qnaVO;
	private Paging paging;

	public List<QNAVO> getQnaVO() {
		return qnaVO;
	}
	public void setQnaVO(List<QNAVO> qnaVO) {
		this.qnaVO = qnaVO;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
}
