package com.ktds.sems.education.biz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.member.vo.LoginHistoryVO;

import kr.co.hucloud.utilities.excel.option.WriteOption;
import kr.co.hucloud.utilities.excel.write.ExcelWrite;

public class EducationBizImpl implements EducationBiz {

	private EducationDAO educationDAO;
	
	public void setEducationDAO(EducationDAO educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public List<EducationVO> getAllEducationList(EducationSearchVO searchVO) {
		return educationDAO.getAllEducationList(searchVO);
	}

	@Override
	public int getTotalEducationCount() {
		return educationDAO.getTotalEducationCount();
	}

	@Override
	public EducationVO getOneEducationDetail(String educationId) {
		return educationDAO.getOneEducationDetail(educationId);
	}
	
	@Override
	public int getSearchedEducationCount(EducationVO educationVO) {
		return educationDAO.getSearchedEducationCount( educationVO);
	}

	@Override
	public List<String> getMemberRegInfo(String id) {
		return educationDAO.getMemberRegInfo(id);

	}

	@Override
	public List<EducationVO> doSearchList(EducationVO educationVO, EducationSearchVO searchVO) {
		return educationDAO.doSearchList( educationVO , searchVO);
	}
	
	@Override
	public boolean writeNewComment(QNAVO qnaVO) {
		//qnaVO.getReplyId();
		return educationDAO.insertNewComment(qnaVO) > 0;
	}

	@Override
	public boolean doApplyEducation(String educationId, String id) {
		return educationDAO.doApplyEducation(educationId, id) > 0;
	}
	
	@Override
	public boolean doCancelEducation(String educationId, String id) {
		return educationDAO.doCancelEducation(educationId, id) > 0;
	}

	@Override
	public String getNowDate() {
		return educationDAO.getNowDate();
	}

	@Override
	public int getNextReplySeq() {
		return educationDAO.getNextReplySeq();
	}

	@Override
	public int isApplyMemberByEducationId(String educationId, String id) {
		return educationDAO.isApplyMemberByEducationId(educationId, id);
	}

	@Override
	public int getEduReplyCount(String educationId) {
		return educationDAO.getEduReplyCount(educationId);
	}

	@Override
	public List<QNAVO> getAllCommentByEducationId(String educationId, EducationSearchVO searchVO) {
		return educationDAO.getAllCommentByEducationId(educationId, searchVO);
	}

	@Override
	public List<String> getTypeName() {
		return educationDAO.getTypeName();
	}

	@Override
	public List<String> getCostName() {
		return educationDAO.getCostName();
	}

	@Override
	public String doTransTypeId(String educationType) {
		return educationDAO.doTransTypeId(educationType);
	}

	@Override
	public String doTransCostId(String cost) {
		// TODO Auto-generated method stub
		return educationDAO.doTransCostId(cost);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public int getTotalQNACount(String memberId) {
		return educationDAO.getTotalQNACount(memberId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return educationDAO.getAllQNAList(qnaSearchVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public QNAVO getSelectedQNA(String replyId) {
		return educationDAO.getSelectedQNA(replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public QNAVO getSelectedQNAAnswer(String replyId) {
		return educationDAO.getSelectedQNAAnswer(replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public void exportQNAListAsExcel(String memberId) {
		WriteOption wo = new WriteOption();
		wo.setSheetName("교육 문의 내역");
		wo.setFileName("교육 문의 내역.xlsx");
		wo.setFilePath("D:\\");
		List<String> titles = new ArrayList<String>();

		titles.add("문의 아이디");
		titles.add("교육명");
		titles.add("문의 날짜");
		titles.add("문의 내용");
		titles.add("답변 여부");
		titles.add("답변");
		wo.setTitles(titles);

		List<String[]> contents = new ArrayList<String[]>();

		// LoginHistory 만들기
		try {
			List<QNAVO> qnaVO = educationDAO.exportQNAListAsExcel(memberId);
			Iterator<QNAVO> tempIterator = qnaVO.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext())
			{
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				QNAVO tempQnaVO = new QNAVO();
				tempQnaVO = tempIterator.next();

				String[] content = new String[6];

				content[0] = tempQnaVO.getReplyId();
				content[1] = tempQnaVO.getEduId();
				content[2] = tempQnaVO.getCreatedDate();
				content[3] = tempQnaVO.getDescription();
				content[4] = tempQnaVO.getIsAnswered();
				content[5] = tempQnaVO.getAnswer();
				
				contents.add(content);
			}

			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);

		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
}


