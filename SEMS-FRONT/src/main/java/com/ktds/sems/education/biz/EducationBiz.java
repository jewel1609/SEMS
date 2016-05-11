package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationBiz {

	public List<EducationVO> doSearchList(String startDate, String endDate, String eduName, String educationType, String cost, EducationSearchVO searchVO);

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public int getTotalEducationCount();

	public EducationVO getOneEducationDetail(String educationId);

	public boolean writeNewComment(QNAVO qnaVO);

	public int getSearchedEducationCount(EducationVO educationVO, EducationSearchVO searchVO);
}
