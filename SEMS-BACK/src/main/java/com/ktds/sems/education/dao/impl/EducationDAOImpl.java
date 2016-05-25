package com.ktds.sems.education.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.sems.education.dao.EducationDAO;
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

public class EducationDAOImpl extends SqlSessionDaoSupport implements EducationDAO {

	@Override
	public int nextEduSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEduSeq");
	}
	
	@Override
	public String nowDate() {
		return getSqlSession().selectOne("EducationDAO.nowDate");
	}

	@Override
	public int insertNewEducation(EducationVO educationVO) {
		return getSqlSession().insert("EducationDAO.insertNewEducation", educationVO);
	}
	
	@Override
	public EducationVO getOneEducation(String educationId) {
		return getSqlSession().selectOne("EducationDAO.getOneEducation", educationId);
	}

	@Override
	public int modifyNewEducation(EducationVO changedEducationVO) {
		return getSqlSession().update("EducationDAO.modifyNewEducation", changedEducationVO);
	}

	@Override
	public List<CostVO> costCodeList() {
		return getSqlSession().selectList("EducationDAO.costCodeList");
	}

	@Override
	public List<EducationTypeVO> typeCodeList() {
		return getSqlSession().selectList("EducationDAO.typeCodeList");
	}

	@Override
	public List<CategoryVO> categoryCodeList() {
		return getSqlSession().selectList("EducationDAO.categoryCodeList");
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEducationHistory",eduHistorySearchVO);
	}

	@Override
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectOne("EducationDAO.getAllEduHistoryCount",eduHistorySearchVO);
	}

	@Override
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		logger.info(eduHistorySearchVO.getSearchKeyword());
		return getSqlSession().selectOne("EducationDAO.getJCEduHistoryCount", eduHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getJCEduHistoryHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return getSqlSession().selectList("EducationDAO.getJCEduHistoryHistory", eduHistorySearchVO);
	}

	@Override
	public int applyJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.applyJoinEdcationByMemberId", paramMap);
	}

	@Override
	public int cancelJoinEducationByMemberId(String educationHistoryId, String changeState) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("educationHistoryId", educationHistoryId);
		paramMap.put("changeState", changeState);
		return getSqlSession().update("EducationDAO.cancelJoinEducationByMemberId", paramMap);
	}

	@Override
	public String getStateByEducationHistroyId(String educationHistoryId) {
		return getSqlSession().selectOne("EducationDAO.getStateByEducationHistroyId", educationHistoryId);
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return getSqlSession().update("EducationDAO.changeEducationApplyState", educationHistoryId);
	}

	@Override
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduReportCount", eduReportSearchVO);
	}
	
	@Override
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduReport", eduReportSearchVO);
	}

	@Override
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduQnaCount", eduQnaSearchVO);
	}

	@Override
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduQna", eduQnaSearchVO);
	}

	@Override
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduFileCount", eduFileSearchVO);
	}

	@Override
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduFile", eduFileSearchVO);
	}

	@Override
	public int getTotalAttendanceCount(EduAttendanceSearchVO eduAttendanceSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalAttendanceCount", eduAttendanceSearchVO);
	}

	@Override
	public List<EduAttendanceVO> getAllAttendance(EduAttendanceSearchVO eduAttendanceSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllAttendance", eduAttendanceSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberInEducation() {
		return getSqlSession().selectList("EducationDAO.getAllMemberInEducation");
	}

	@Override
	public List<TeamVO> getAllTeamInEducation() {
		return getSqlSession().selectList("EducationDAO.getAllTeamInEducation");
	}

	@Override
	public List<EducationVO> getAllEducation() {
		return getSqlSession().selectList("EducationDAO.getAllEducation");
	}

	@Override
	public String doActionDeleteBeforeCheck(MemberVO memberVO) {
		return getSqlSession().selectOne("EducationDAO.doActionDeleteBeforeCheck", memberVO);
	}

	@Override
	public void doActionDelete(String educationId) {
		getSqlSession().delete("EducationDAO.doActionDelete", educationId);
	}

	@Override
	public List<EducationVO> attendedLectureUserList(String educationId) {
		return getSqlSession().selectList("EducationDAO.attendedLectureUserList", educationId);
	}

	@Override
	public MemberVO emailNoticeForUser(String memberId) {
		return getSqlSession().selectOne("EducationDAO.emailNoticeForUser", memberId);
	}

	@Override
	public int nextEduNoticeSeq() {
		return getSqlSession().selectOne("EducationDAO.nextEduNoticeSeq");
	}

	@Override
	public int insertNewEduFileNotice(EduNoticeVO eduNoticeVO) {
		return getSqlSession().insert("EducationDAO.insertNewEduFileNotice", eduNoticeVO);
	}

	@Override
	public int getTotalEduFileNoticeCount(EduNoticeSearchVO eduNoticeSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduFileNoticeCount");
	}

	@Override
	public List<EduNoticeVO> getAllEduFileNotice(EduNoticeSearchVO eduNoticeSearchVO) {
		List<EduNoticeVO> eduNoticeVOs = getSqlSession().selectList("EducationDAO.getAllEduFileNotice", eduNoticeSearchVO);
		return eduNoticeVOs;
	}

	@Override
	public EduNoticeVO getOneNotice(String eduNoticeId) {
		return getSqlSession().selectOne("EducationDAO.getOneNotice", eduNoticeId);
	}

	@Override
	public void addhits(String eduNoticeId) {
		getSqlSession().update("EducationDAO.addhits", eduNoticeId);
	}

	@Override
	public int doDeleteEduNotice(String eduNoticeId) {
		return getSqlSession().delete("EducationDAO.doDeleteEduNotice", eduNoticeId);
	}

	@Override
	public int doEduFileNoticeModify(EduNoticeVO changeEduNoticeVO) {
		return getSqlSession().update("EducationDAO.doEduFileNoticeModify", changeEduNoticeVO);
	}

	@Override
	public List<TeacherVO> teacherVOList() {
		return getSqlSession().selectList("EducationDAO.teacherVOList");
	}

	@Override
	public int getTotalEduReportHisotryCount(EduReportSearchVO reportSearchVO) {
		return getSqlSession().selectOne("EducationDAO.getTotalEduReportHisotryCount", reportSearchVO);
	}

	@Override
	public List<EduReportVO> getAllEduReportHistory(EduReportSearchVO reportSearchVO) {
		return getSqlSession().selectList("EducationDAO.getAllEduReportHistory", reportSearchVO);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return getSqlSession().selectList("EducationDAO.getAllMemberList");
	}

	@Override
	public List<EducationVO> getJoinEducation(String memberId) {
		return getSqlSession().selectList("EducationDAO.getJoinEducation", memberId);
	}

	@Override
	public List<AttendVO> getOneMemberAttendance(String memberId) {
		return getSqlSession().selectList("EducationDAO.getOneMemberAttendance", memberId);
	}

	@Override
	public List<EducationVO> getAllStartedEducationList() {
		return getSqlSession().selectList("EducationDAO.getAllStartedEducationList");
	}

	@Override
	public List<MemberVO> getAllMemberListByEduId(String educationId) {
		return getSqlSession().selectList("EducationDAO.getAllMemberListByEduId", educationId);
	}
}