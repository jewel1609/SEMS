package com.ktds.sems.education.biz.impl;

import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.CategoryVO;
import com.ktds.sems.education.vo.CostVO;
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
	public List<EducationHistoryVO> getAllEducationHistory() {
		return educationDAO.getAllEducationHistory();
	}

	@Override
	public int getAllEduHistoryCount() {
		return educationDAO.getAllEduHistoryCount();
	}

	@Override
	public int getJCEduHistoryCount() {
		return educationDAO.getJCEduHistoryCount();
	}

	@Override
	public List<EducationHistoryVO> getJCEducationHistory() {
		return educationDAO.getJCEduHistoryHistory();
	}

	@Override
	public boolean applyJoinEducationByMemberId(String educationId, String memberId) {
		return educationDAO.applyJoinEducationByMemberId(educationId, memberId) > 0;
	}

	@Override
	public boolean cancelJoinEducationByMemberId(String educationId, String memberId) {
		return educationDAO.cancelJoinEducationByMemberId(educationId, memberId) > 0;
	}
	
	@Override
	public boolean completeCancelEducationByMemberId(String educationHistoryId) {
		return educationDAO.completeCancelEducationByMemberId(educationHistoryId) > 0;
	}
	
	@Override
	public boolean denyCancelEducationByMemberId(String educationHistoryId) {
		return educationDAO.denyCancelEducationByMemberId(educationHistoryId) > 0;
	}
	
	@Override
	public boolean completeGiveUpEducationByMemberId(String educationHistoryId) {
		return educationDAO.completeGiveUpEducationByMemberId(educationHistoryId) > 0;
	}
	
	@Override
	public boolean denyGiveUpEducationByMemberId(String educationHistoryId) {
		return educationDAO.denyGiveUpEducationByMemberId(educationHistoryId) > 0;
	}
	
	
	


}
