package com.ktds.sems.education.biz;

import java.util.List;

import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
import com.ktds.sems.education.vo.EduAttendanceSearchVO;
import com.ktds.sems.education.vo.EduAttendanceVO;
import com.ktds.sems.education.vo.EduFileSearchVO;
import com.ktds.sems.education.vo.EduFileVO;
import com.ktds.sems.education.vo.EduNoticeSearchVO;
import com.ktds.sems.education.vo.EduNoticeVO;
import com.ktds.sems.education.vo.EduQnaSearchVO;
import com.ktds.sems.education.vo.EduQnaVO;
import com.ktds.sems.education.vo.EduReportSearchVO;
import com.ktds.sems.education.vo.EduReportVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.education.vo.EducationTypeVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.TeamVO;
import com.ktds.sems.member.vo.AttendVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.teacher.vo.TeacherVO;

public interface EducationBiz {

	public boolean writeNewEducation(EducationVO educationVO );

	public EducationVO getOneEducation(String educationId);

	public boolean modifyNewEducation(EducationVO educationVO);
	
	public List<CostVO> costCodeList();

	public List<EducationTypeVO> typeCodeList();

	public List<CategoryVO> categoryCodeList();

	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO);

	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO);

	public List<EducationHistoryVO> getJCEducationHistory(EducationHistorySearchVO eduHistorySearchVO);
	
	public boolean applyJoinEducationByMemberId(String educationHistoryId, String changeState);

	public boolean cancelJoinEducationByMemberId(String educationHistoryId, String changeState);

	public String getStateByEducationHistroyId(String educationHistoryId);

	public void sendEmailRejection(String educationHistoryId, String memberId, String description, String email, String name);

	public int changeEducationApplyState(String educationHistoryId);

	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO);

	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO);

	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO);

	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO);

	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO);

	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO);

	public boolean writeEduFileNoticeAction(EduNoticeVO eduNoticeVO);

	public int getTotalAttendanceCount(EduAttendanceSearchVO eduAttendanceSearchVO);

	public List<EduAttendanceVO> getAllAttendance(EduAttendanceSearchVO eduAttendanceSearchVO);

	public List<MemberVO> getAllMemberInEducation();

	public List<TeamVO> getAllTeamInEducation();

	public List<EducationVO> getAllEducation();

	public boolean doActionDeleteBeforeCheck(MemberVO memberVO);

	public void doActionDelete(String educationId);

	public void emailNoticeForUser(String educationId);
	
	public List<TeacherVO> teacherVOList();

	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO);

	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO);
	
	public int getTotalEduFileNoticeCount( EduNoticeSearchVO eduNoticeSearchVO);

	public List<EduNoticeVO> getAllEduFileNotice( EduNoticeSearchVO eduNoticeSearchVO);

	public EduNoticeVO getOneNotice(String eduNoticeId);

	public void addhits(String eduNoticeId);

	public boolean doDeleteEduNotice(String eduNoticeId);

	public boolean doEduFileNoticeModify(EduNoticeVO eduNoticeVO);
	
	public List<MemberVO> getAllMemberList();

	public List<EducationVO> getJoinEducation(String memberId);

	public List<AttendVO> getOneMemberAttendance(String memberId);

	public List<EducationVO> getAllStartedEducationList();

	public List<MemberVO> getAllMemberListByEduId(String educationId);
}
