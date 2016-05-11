package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNAVO;

public interface EducationBiz {

	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO);

	public int getTotalEducationCount();

	public EducationVO getOneEducationDetail(String educationId);

	public boolean writeNewComment(QNAVO qnaVO);
	
	public boolean doCancelEducation(String educationId);

	public int getSearchedEducationCount(EducationVO educationVO);

	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO);
}
