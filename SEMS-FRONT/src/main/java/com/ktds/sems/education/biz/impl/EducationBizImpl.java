package com.ktds.sems.education.biz.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.biz.EducationBiz;
import com.ktds.sems.education.dao.EducationDAO;
import com.ktds.sems.education.vo.EducationQNABBSVO;
import com.ktds.sems.education.vo.EducationQNAReplyVO;
import com.ktds.sems.education.vo.EducationReportSearchVO;
import com.ktds.sems.education.vo.EducationReportVO;
import com.ktds.sems.education.vo.EducationSearchVO;
import com.ktds.sems.education.vo.EducationVO;
import com.ktds.sems.education.vo.QNASearchVO;
import com.ktds.sems.education.vo.QNAVO;
import com.ktds.sems.education.vo.ReRplyEvalVO;
import com.ktds.sems.education.vo.ReportReplySearchVO;
import com.ktds.sems.education.vo.ReportReplyVO;
import com.ktds.sems.member.vo.MemberVO;

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
	public List<EducationVO> getMemberRegInfo(String id) {
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
	public String isApplyMemberByEducationId(String educationId, String id) {
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
	public int getTotalQNACount(QNASearchVO qnaSearchVO) {
		return educationDAO.getTotalQNACount(qnaSearchVO);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public List<QNAVO> getAllQNAList(QNASearchVO qnaSearchVO) {
		return educationDAO.getAllQNAList(qnaSearchVO);
	}
	
	@Override
	public boolean doReReplyInsert(QNAVO qnaVO) {
		return educationDAO.doReReplyInsert(qnaVO) > 0;
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
	public List<QNAVO> getSelectedQNAAnswer(String replyId) {
		return educationDAO.getSelectedQNAAnswer(replyId);
	}

	/**
	 * @author 206-025 이기연
	 */
	@Override
	public boolean exportQNAListAsExcel(String memberId) {
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
		wo.setTitles(titles);

		List<String[]> contents = new ArrayList<String[]>();

		// LoginHistory 만들기
		try {
			List<QNAVO> qnaVO = educationDAO.exportQNAListAsExcel(memberId);
			Iterator<QNAVO> tempIterator = qnaVO.iterator();

			// TODO while문으로 null을 만날 때 까지 while문을 돌려야 할 것 같다
			while (tempIterator.hasNext()) {
				// TODO String[] 타입인데... 이걸 수정해바야 할 것 같다.
				// 하나씩 String[]에 담는 것 그리고 add
				QNAVO tempQnaVO = new QNAVO();
				tempQnaVO = tempIterator.next();

				String[] content = new String[5];

				content[0] = tempQnaVO.getReplyId();
				content[1] = tempQnaVO.getEduId();
				content[2] = tempQnaVO.getCreatedDate();
				content[3] = tempQnaVO.getDescription();
				content[4] = tempQnaVO.getIsAnswered();

				contents.add(content);
			}
			wo.setContents(contents);

			File excelFile = ExcelWrite.write(wo);
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public boolean hasApplyHistory(String memberId, String educationId) {
		List<EducationVO> educationVO = educationDAO.getApplyHistory(memberId, educationId);
		if (educationVO.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isEducationStarted(String educationId) {
		Date educationStartDate;
		Date currentDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		EducationVO educationVO = educationDAO.getOneEducationDetail(educationId);
		String startDate = educationVO.getStartDate();

		long time = System.currentTimeMillis();

		try {
			educationStartDate = dateFormat.parse(startDate);
			currentDate = dateFormat.parse(dateFormat.format(new Date(time)));
			int compare = currentDate.compareTo(educationStartDate);
			if ( compare < 0 ) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
		}

		return true;
	}

	@Override
	public String getEmail(String id) {
		return educationDAO.getEmail(id);
	}

	@Override
	public void sendEmailByReReply(QNAVO questionVO, QNAVO answerVO, String email) {
		SendMail sendMail = new SendMail();
		MailVO mailVO = new MailVO();

		mailVO.setFromId("testForSendEmailKtds@gmail.com");
		mailVO.setFromPassword("123qwe!@#qwe");
		mailVO.setSubject("문의하신 질문에 대한 답변입니다.");
		mailVO.setText("<html><body>문의하신분 : " + questionVO.getMbrId() + "<br/> 작성 시간 : " + questionVO.getCreatedDate()
				+ "<br/> 문의 내용 : " + questionVO.getDescription() + "<br/><br/><br/><br/>" + " 답변 작성자: "
				+ answerVO.getMbrId() + "<br/> 작성 시간 : " + answerVO.getCreatedDate() + "<br/> 답변 내용 : "
				+ answerVO.getDescription() + "<br/><br/> 문의해 주셔서 감사합니다." + "</body></html>");

		mailVO.setToId(email);

		sendMail.sendMailToCustomer(mailVO);
	}

	@Override
	public int getNextReReplyEval() {
		return educationDAO.getNextReReplyEval();
	}

	@Override
	public boolean plusReReplyLike(String replyId) {
		return educationDAO.plusReReplyLike(replyId) > 0;
	}

	@Override
	public boolean insertReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.insertReReplyEval(reRplyEvalVO) > 0;
	}

	@Override
	public boolean checkReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.checkReReplyEval(reRplyEvalVO) > 0;
	}

	@Override
	public String getStartYear() {
		return educationDAO.getStartYear();
	}

	@Override
	public String getEndYear() {
		return educationDAO.getEndYear();
	}
	
	@Override
	public boolean insertReReplyEvalByDislike(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.insertReReplyEvalByDislike(reRplyEvalVO) > 0;
	}

	@Override
	public boolean plusReReplyDislike(String replyId) {
		return educationDAO.plusReReplyDislike(replyId) > 0;
	}

	@Override
	public boolean doRequestRetraction(String educationId, String retractionMsg, String memberId) {
		return educationDAO.doRequestRetraction(educationId, retractionMsg, memberId) > 0;
	}

	@Override
	public boolean doReReplyDelete(QNAVO qnaVO) {
		return educationDAO.doReReplyDelete(qnaVO) > 0;
	}

	@Override
	public boolean deleteReReplyEval(ReRplyEvalVO reRplyEvalVO) {
		return educationDAO.deleteReReplyEval(reRplyEvalVO) > 0;
	}
	

	@Override
	public int getTotalMemberNumber(String educationId) {
		return educationDAO.getTotalMemberNumber(educationId);
	}

	@Override
	public boolean doReserveEducation(String educationId, String id) {
		return educationDAO.doReserveEducation(educationId, id) > 0;
	}

	@Override
	public boolean updateStateToApply(String educationId) {
		return educationDAO.updateStateToApply(educationId) > 0;
	}

	@Override
	public List<EducationVO> getMyEducationList(String id) {
		return educationDAO.getMyEducationList(id);
	}

	@Override
	public List<EducationQNABBSVO> getAllEducationQNAList() {
		return educationDAO.getAllEducationQNAList();
	}

	@Override
	public void addQNABBS(EducationQNABBSVO eduBBS) {
		educationDAO.addQNABBS(eduBBS);
	}

	@Override
	public List<EducationReportVO> getAllEducationReportList(EducationReportSearchVO educationReportSearchVO) {
		return educationDAO.getAllEducationReportList(educationReportSearchVO);
	}

	@Override
	public int getTotalEducationReportCount(EducationReportSearchVO educationReportSearchVO) {
		return educationDAO.getTotalEducationReportCount(educationReportSearchVO);
	}

	@Override
	public EducationQNABBSVO getOneQNABBSByAtcId(String atcId) {
		return educationDAO.getOneQNABBSByAtcId(atcId);
	}

	@Override
	public void doReportWriteAction(EducationReportVO educationReportVO) {
		educationDAO.doReportWriteAction(educationReportVO);
	}

	@Override
	public int getNextReportSeq() {
		return educationDAO.getNextReportSeq();
	}

	@Override
	public void addHitsByAtcId(String atcId) {
		educationDAO.addHitsByAtcId(atcId);
	}

	@Override
	public void addQNAReply(EducationQNAReplyVO eduBBSReplyVO) {
		educationDAO.addQNAReply(eduBBSReplyVO);
	}

	@Override
	public List<MemberVO> getAllMemberOfEducation(String educationId) {
		return educationDAO.getAllMemberOfEducation(educationId);
	}

	@Override
	public boolean addRequestRetractionHistory(String educationId, String retractionMsg, String memberId, String ip) {
		return educationDAO.addRequestRetractionHistory(educationId, retractionMsg, memberId, ip) > 0;
	}

	@Override
	public int getTotalReportReplyCount(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getTotalReportReplyCount(reportReplySearchVO);
	}

	@Override
	public List<ReportReplyVO> getAllReportReply(ReportReplySearchVO reportReplySearchVO) {
		return educationDAO.getAllReportReply(reportReplySearchVO);
	}


	@Override
	public EducationReportVO getOneEducationReport(EducationReportVO educationReportVO) {
		return educationDAO.getOneEducationReport(educationReportVO);
	}

	@Override
	public void doReportSubmit(ReportReplyVO reportReplyVO) {
		educationDAO.doReportSubmit(reportReplyVO);
	}

	@Override
	public int getNextReportReplySeq() {
		return educationDAO.getNextReportReplySeq();
	}

	@Override
	public List<ReportReplyVO> getAllReportByArticleId(String articleId, ReportReplySearchVO searchVO) {
		return educationDAO.getAllReportByArticleId(articleId, searchVO);
	}

	@Override
	public int getReportReplyCount(String articleId) {
		return educationDAO.getReportReplyCount(articleId);
	}

	@Override
	public String getNowDateTime() {
		return educationDAO.getNowDateTime();
	}
}


