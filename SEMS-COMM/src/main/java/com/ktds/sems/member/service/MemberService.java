package com.ktds.sems.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.vo.MbrTpVO;

public interface MemberService {

	public ModelAndView viewGrdtPage();

	public String doGrdtDelete(String cdId);

	public String doGrdtModify(String cdId, String cdNm);

	public String doGrdtInsert(String cdId, String cdNm);

	public ModelAndView viewMbrTpPage();

	public String doInsertMbrTp(String cdId, String cdNm);
	
	public String doMbrTpDelete(String cdId);

	public String doMbrTpModify(String cdId, String cdNm);

	public ModelAndView viewHighestEduPage();

	public String doHighestEduDelete(String cdId);

	public void doHighestEduModify(String cdId, String cdNm);

	public String doHighestEduInsert(String cdId, String cdNm);


}
