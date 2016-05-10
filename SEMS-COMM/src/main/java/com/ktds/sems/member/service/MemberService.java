package com.ktds.sems.member.service;

import org.springframework.web.servlet.ModelAndView;

public interface MemberService {

	public ModelAndView viewGrdtPage();

	public ModelAndView viewMbrTpPage();

	public String doGrdtDelete(String cdId);

	public String doGrdtModify(String cdId, String cdNm);

	public String doGrdtInsert(String cdId, String cdNm);

}
