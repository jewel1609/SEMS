package com.ktds.sems.education.dao;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.member.vo.MemberVO;

public interface EducationDAO {

	public 	int nextEduSeq();
	public String nowDate();
	public int insertNewEducation(EducationVO educationVO);
	public List<CostVO> costCodeList();
	public List<EducationTypeVO> typeCodeList();
	public List<CategoryVO> categoryCodeList();
	
	public EducationVO getOneEducation(String educationId);
	public int modifyNewEducation(EducationVO changedEducationVO);
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO);
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);
	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO);
	public int applyJoinEducationByMemberId(String educationHistoryId, String changeState);
	public int cancelJoinEducationByMemberId(String educationHistoryId, String changeState);
	public String getStateByEducationHistroyId(String educationHistoryId);
	public int changeEducationApplyState(String educationHistoryId);
	
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO);
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO);
	
	public String doActionDeleteBeforeCheck(MemberVO memberVO);
	
	public void doActionDelete(String educationId);
	
	public List<MemberVO> attendedLectureUserList(String educationId);
	
	public MemberVO emailNoticeForUser(String id);

}
