package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;

public interface EducationBiz {

	public boolean writeNewEducation(EducationVO educationVO );

	public EducationVO getOneEducation(String educationId);

	public boolean modifyNewEducation(EducationVO educationVO);
	
	public List<CostVO> costCodeList();

	public List<EducationTypeVO> typeCodeList();

	public List<CategoryVO> categoryCodeList();

	public List<EducationHistoryVO> getAllEducationHistory();

	public int getAllEduHistoryCount();

	public int getJCEduHistoryCount();

	public List<EducationHistoryVO> getJCEducationHistory();
	
	

	public boolean applyJoinEducationByMemberId(String educationId, String memberId);

	public boolean cancelJoinEducationByMemberId(String educationId, String memberId);
	
	public boolean completeCancelEducationByMemberId(String educationHistoryId);
	
	public boolean denyCancelEducationByMemberId(String educationHistoryId);
	
	public boolean completeGiveUpEducationByMemberId(String educationHistoryId);
	
	public boolean denyGiveUpEducationByMemberId(String educationHistoryId);
	
	

}
