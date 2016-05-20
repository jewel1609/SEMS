package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
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

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public boolean writeNewEducation(EducationVO educationVO) {

		int nextEducationId = educationDAO.nextEduSeq();
		String nowDate = educationDAO.nowDate();
		
		/*
		 * ArticleID의 형식
		 * ED-20160421-000001
		 */
		
		String educationId = "ED-" + nowDate + "-" + lpad(nextEducationId + "", 6, "0");
		
		educationVO.setEducationId(educationId);
		
		return educationDAO.insertNewEducation(educationVO) > 0;
	}

	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
		
	}
	
	@Override
	public EducationVO getOneEducation(String educationId) {
		return educationDAO.getOneEducation(educationId);
	}

	@Override
	public boolean modifyNewEducation(EducationVO educationVO) {
		
		EducationVO newEducationVO = educationDAO.getOneEducation(educationVO.getEducationId());
		EducationVO changedEducationVO = new EducationVO();
		changedEducationVO.setEducationId(newEducationVO.getEducationId());

		boolean isEducationCategory = !newEducationVO.getEducationCategory().equals(educationVO.getEducationCategory());
		boolean isEducationTitle = !newEducationVO.getEducationTitle().equals(educationVO.getEducationTitle());
		boolean isMemberId = !newEducationVO.getMemberId().equals(educationVO.getMemberId());
		boolean isMaxMember = !(newEducationVO.getMaxMember()==educationVO.getMaxMember());
		boolean isEducationLocation = !newEducationVO.getEducationLocation().equals(educationVO.getEducationLocation());
		boolean isEducationCurriculum = !newEducationVO.getEducationCurriculum().equals(educationVO.getEducationCurriculum());
		boolean isEducationIntroduce = !newEducationVO.getEducationIntroduce().equals(educationVO.getEducationIntroduce());
		boolean isStartDate = !newEducationVO.getStartDate().equals(educationVO.getStartDate());
		boolean isEndDate = !newEducationVO.getEndDate().equals(educationVO.getEndDate());
		boolean isStartTime = !newEducationVO.getStartTime().equals(educationVO.getStartTime());
		boolean isEndTime = !newEducationVO.getEndTime().equals(educationVO.getEndTime());
		boolean isEducationType = !newEducationVO.getEducationType().equals(educationVO.getEducationType());
		boolean isCost = !newEducationVO.getCost().equals(educationVO.getCost());
		
		if (!isEducationCategory && !isEducationTitle && !isMemberId && !isMaxMember
				&& !isEducationLocation 
				&& !isEducationCurriculum
				&& !isEducationIntroduce 
				&& !isStartDate
				&& !isEndDate 
				&& !isStartTime
				&& !isEndTime
				&& !isEducationType
				&& !isCost) {
			return true;
		}
		if (isEducationCategory) {
			changedEducationVO.setEducationCategory(educationVO.getEducationCategory());
		}
		if (isEducationTitle) {
			changedEducationVO.setEducationTitle(educationVO.getEducationTitle());
		}
		if (isMemberId) {
			changedEducationVO.setMemberId(educationVO.getMemberId());
		}
		if (isMaxMember) {
			changedEducationVO.setMaxMember(educationVO.getMaxMember());
		}
		if (isEducationLocation) {
			changedEducationVO.setEducationLocation(educationVO.getEducationLocation());
		}
		if (isEducationCurriculum) {
			changedEducationVO.setEducationCurriculum(educationVO.getEducationCurriculum());
		}
		if (isEducationIntroduce) {
			changedEducationVO.setEducationIntroduce(educationVO.getEducationIntroduce());
		}
		if (isStartDate) {
			changedEducationVO.setStartDate(educationVO.getStartDate());
		}
		if (isEndDate) {
			changedEducationVO.setEndDate(educationVO.getEndDate());
		}
		if (isStartTime) {
			changedEducationVO.setStartTime(educationVO.getStartTime());
		}
		if (isEndTime) {
			changedEducationVO.setEndTime(educationVO.getEndTime());
		}
		if (isEducationType) {
			changedEducationVO.setEducationType(educationVO.getEducationType());
		}
		if (isEducationType) {
			changedEducationVO.setEducationType(educationVO.getEducationType());
		}
		if (isCost) {
			changedEducationVO.setCost(educationVO.getCost());
		}

		return educationDAO.modifyNewEducation(changedEducationVO) > 0;
	}

	@Override
	public List<CostVO> costCodeList() {
		return educationDAO.costCodeList();
	}

	@Override
	public List<EducationTypeVO> typeCodeList() {
		return educationDAO.typeCodeList();
	}

	@Override
	public List<CategoryVO> categoryCodeList() {
		return educationDAO.categoryCodeList();
	}

	@Override
	public List<EducationHistoryVO> getAllEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getAllEducationHistory(eduHistorySearchVO);
	}

	@Override
	public int getAllEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getAllEduHistoryCount(eduHistorySearchVO);
	}

	@Override
	public int getJCEduHistoryCount(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getJCEduHistoryCount(eduHistorySearchVO);
	}

	@Override
	public List<EducationHistoryVO> getJCEducationHistory(EducationHistorySearchVO eduHistorySearchVO) {
		return educationDAO.getJCEduHistoryHistory(eduHistorySearchVO);
	}

	@Override
	public void sendEmailRejection(String educationHistoryId, String memberId, String description, String email, String name) {
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();
		mailVO.setFromId("testForSendEmailKtds@gmail.com");
		//mailVO.setFromId("semsjhg@gmail.com");
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("[sems] " + name + "님의 교육 포기/취소 신청에 대한 답변입니다.");
		mailVO.setText(
						memberId + "(" + name + ")" + "님이 신청하신 요청 번호 [" + educationHistoryId + "] 에 대해 검토해본 결과 관리자에 의해 요청이 거절되었음을 알립니다. "
						+ " 요청에 대한 거절 사유: [ " + description + " ]"
					  );
		
		mailVO.setToId(email);
		sendMail.sendMailToCustomer(mailVO);
		
	}

	public boolean applyJoinEducationByMemberId(String educationHistoryId, String changeState) {
		return educationDAO.applyJoinEducationByMemberId(educationHistoryId, changeState) > 0;
	}

	@Override
	public boolean cancelJoinEducationByMemberId(String educationHistoryId, String changeState) {
		return educationDAO.cancelJoinEducationByMemberId(educationHistoryId, changeState) > 0;
	}
	
	@Override
	public String getStateByEducationHistroyId(String educationHistoryId) {
		return educationDAO.getStateByEducationHistroyId(educationHistoryId);
	}

	@Override
	public int getTotalEduReportCount(EduReportSearchVO eduReportSearchVO) {
		return educationDAO.getTotalEduReportCount(eduReportSearchVO);
	}
	
	@Override
	public List<EduReportVO> getAllEduReport(EduReportSearchVO eduReportSearchVO) {
		return educationDAO.getAllEduReport(eduReportSearchVO);
	}
	
	@Override
	public int getTotalEduQnaCount(EduQnaSearchVO eduQnaSearchVO) {
		return educationDAO.getTotalEduQnaCount(eduQnaSearchVO);
	}

	@Override
	public List<EduQnaVO> getAllEduQna(EduQnaSearchVO eduQnaSearchVO) {
		return educationDAO.getAllEduQna(eduQnaSearchVO);
	}

	@Override
	public int getTotalEduFileCount(EduFileSearchVO eduFileSearchVO) {
		return educationDAO.getTotalEduFileCount(eduFileSearchVO);
	}

	@Override
	public List<EduFileVO> getAllEduFile(EduFileSearchVO eduFileSearchVO) {
		return educationDAO.getAllEduFile(eduFileSearchVO);
	}

	@Override
	public int changeEducationApplyState(String educationHistoryId) {
		return educationDAO.changeEducationApplyState(educationHistoryId);
	}
}
