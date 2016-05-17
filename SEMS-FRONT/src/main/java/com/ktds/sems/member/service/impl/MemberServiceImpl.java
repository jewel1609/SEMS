package com.ktds.sems.member.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.Session;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.education.vo.EducationHistoryListVO;
import com.ktds.sems.education.vo.EducationHistorySearchVO;
import com.ktds.sems.education.vo.EducationHistoryVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;
import com.ktds.sems.member.vo.MenuManageVO;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.AjaxUtil;
import kr.co.hucloud.utilities.web.Paging;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	@Override
	public ModelAndView addNewMember(MemberVO member, Errors errors, String repeatPassword, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String memberType = member.getMemberType();
		if (sessionMember != null || memberType == null) {
			view.setViewName("member/registErrorPage");
			return view;
		}

		boolean isNotError = true;
		isNotError = isAllValidValue(member, repeatPassword, view);

		if (errors.hasErrors() || !isNotError) {
			List<String> highestEducationLevelCodeNameList = memberBiz.getHighestEducationLevelCodeNames();
			List<String> graduationTypeList = memberBiz.getGraduationType();

			view.addObject("graduationTypeList", graduationTypeList);
			view.addObject("highestEducationLevelCodeNameList", highestEducationLevelCodeNameList);

			view.addObject("member", member);
		} else if (isNotError) {
			if (memberType.equals("MBR")) {
				String graduationType = member.getGraduationType();
				String highestEducationLevel = member.getHighestEducationLevel();

				String selectGraduationTypeCodeId = null;
				String selecthelCodeId = null;

				selecthelCodeId = memberBiz.gethelCodeId(highestEducationLevel);
				selectGraduationTypeCodeId = memberBiz.getGraduationTypeCodeId(graduationType);

				member.setGraduationType(selectGraduationTypeCodeId);
				member.setHighestEducationLevel(selecthelCodeId);
			}

			setSaltAndPassword(member);
			memberBiz.addNewMember(member);
			view.setViewName("redirect:/");
		} else {
			throw new RuntimeException("잘 못 된 입력 : 회원 종류");
		}

		return view;
	}

	private boolean isAllValidValue(MemberVO member, String repeatPassword, ModelAndView view) {

		boolean isNotError = true;
		int errorCount = 0;
		String memberType = member.getMemberType();

		if (repeatPassword == null) {
			view.addObject("isEmptyRepeatPassword", "true");
			errorCount++;
		}

		if (repeatPassword != null && member.getPassword() != null && !member.getPassword().equals(repeatPassword)) {
			view.addObject("isEqualsPassword", "true");
			errorCount++;
		}

		if (member.getId() != null && !memberBiz.isVerifyId(member.getId())) {
			errorCount++;
		}

		if (member.getPassword() != null && !memberBiz.isVerifyPassword(member.getPassword())) {
			errorCount++;
		}

		if (member.getPhoneNumber() != null && !memberBiz.isVerifyPhoneNumber(member.getPhoneNumber())) {
			errorCount++;
		}
		
		if (memberType.equals("MBR")) {
			if (member.getGraduationType() == null) {
				view.addObject("isEmptyGraduationType", "true");
				errorCount++;
			}

			if (member.getHighestEducationLevel() == null) {
				view.addObject("isEmptyHighestEducationLevel", "true");
				errorCount++;
			}

			if (member.getMajorName() == null || member.getMajorName().equals("")) {
				view.addObject("isEmptyMajorName", "true");
				errorCount++;
			}

			if (member.getUniversityName() == null || member.getUniversityName().equals("")) {
				view.addObject("isEmptyUniversityName", "true");
				errorCount++;
			}
			view.setViewName("member/registerStudent");
		} else if (memberType.equals("TR")) {
			view.setViewName("member/registerTeacher");
		}

		if (errorCount > 0) {
			isNotError = false;
		}

		return isNotError;
	}

	private void setSaltAndPassword(MemberVO member) {
		String salt = SHA256Util.generateSalt();
		member.setSalt(salt);

		String newPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
		member.setPassword(newPassword);
	}

	@Override
	public void checkValidationById(String id, HttpServletResponse response) {
		String message = "OK";
		boolean isVerifyId = memberBiz.isVerifyId(id);
		if (!isVerifyId) {
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}

		boolean isExistId = memberBiz.isExistId(id);
		if (isExistId) {
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByPassword(String password, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPassword = memberBiz.isVerifyPassword(password);
		if (isVerifyPassword) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response) {
		String message = "NO";
		boolean isEquals = password.equals(repeatPassword);
		if (isEquals) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPhoneNumber = memberBiz.isVerifyPhoneNumber(phoneNumber);
		if (isVerifyPhoneNumber) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByEmail(String email, HttpServletResponse response) {
		String message = "OK";
		boolean isVerifyEmail = memberBiz.isVerifyEmail(email);
		if (!isVerifyEmail) {
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}

		boolean isExistEmail = memberBiz.isExistEmail(email);
		if (isExistEmail) {
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if (!memberBiz.isExistId(loginVO.getId())) {
			return "NO";
		}

		// 탈퇴한 회원인지 확인
		if (memberBiz.isResign(loginVO.getId())) {
			return "RSN";
		}

		// 잠긴 계정은 로그인 못하도록 막는다.
		if (memberBiz.isAccountLock(loginVO.getId())) {
			return "OVER";
		}

		// 로그인 30일 경과 계정
		if (memberBiz.needToChangPassword(loginVO.getId())) {
			return "CNGPW";
		}

		boolean isLoginSuccess = memberBiz.login(session, loginVO, request);
		
		// 로그인 횟수 제한 방어코드 작성
		if (isLoginSuccess) {
			/*
			 * 1. LOGIN_FAIL_COUNT를 0으로 초기화한다. 2. IS_ACCOUNT_LOCK을 'N'으로 초기화 한다.
			 * 3. LATEST_LOGIN_DATE를 현재시간으로 수정한다.
			 */
			// Token 값 생성 및 등록 코드 작성
			if (memberBiz.loginSuccess(loginVO.getId())) {
				/*
				 * 로그인한 회원이 글을 작성하는 write.jsp 에 아래 코드를 추가해야함!
				 * <input type="hidden" name="csrfToken" value="${sessionScope._CSRF_TOKEN_}" />
				 */
				String csrfToken = UUID.randomUUID().toString();
				session.setAttribute(Session.CSRF_TOKEN, csrfToken);

				// 출석 체크
				memberBiz.attendCheck(loginVO);

				// 로그인 내역 남기기
				memberBiz.stampLoginTime(session, request, loginVO);

				return "OK";

			} else {
				return "NO";
			}

		} else {

			/*
			 * 1. LOGIN_FAIL_COUNT를 1 증가 시킨다.
			 */
			if (!memberBiz.plusLoginFailCount(loginVO.getId())) {
				return "NO";
			}
			/*
			 * 1. LOGIN_FAIL_COUNT를 5 이상이면 IS_ACCOUNT_LOCK을 'Y'로 수정한다.
			 */
			if (!memberBiz.updateAccountLock(loginVO.getId())) {
				return "NO";
			}
			/*
			 * 1. IS_ACCOUNT_LOCK이 'Y'라면 브라우저에게 'OVER'라고 보낸다. 'OVER'를 응답으로 받은
			 * 브라우저는 "로그인이 지속 실패하여, 계정이 잠겼습니다. 운영자에게 문의하세요!" 를 출력한다.
			 */
			boolean isLock = memberBiz.isAccountLock(loginVO.getId());

			if (isLock) {
				return "OVER";
			}
			return "NO";
		}
	}

	@Override
	public ModelAndView modifySuccess(String id) {

		ModelAndView view = new ModelAndView();

		MemberVO member = memberBiz.getOneMember(id);

		// 졸업 구분 값들을 보낸다. 유저가 회원가입시 선택한 졸업구분을 보낸다.
		List<String> graduationTypeCodeNameList = memberBiz.getGraduationType();
		String selectedGraduationTypeCodeName = memberBiz.selectedGraduationTypeCodeName(id);

		// 최종학력 구분 값들을 보낸다. 유저가 회원가입시 선택한 최종학력을 보낸다.
		List<String> highestEducationLevelCodeNameList = memberBiz.getHighestEducationLevelCodeNames();
		String selectedHighestEducationLevelCodeName = memberBiz.getSelectedHighestEducationLevelCodeName(id);

		// 회원구분을 조인해서 한글로 보여준다.
		String memberTypeCodeName = memberBiz.memberTypeCodeName(id);

		// 강사인지 아닌지 체크
		boolean isTeacher = memberBiz.isTeacher(id);

		view.addObject("member", member);
		view.addObject("graduationTypeList", graduationTypeCodeNameList);
		view.addObject("selectedGraduationTypeCodeName", selectedGraduationTypeCodeName);
		view.addObject("highestEducationLevelCodeNameList", highestEducationLevelCodeNameList);
		view.addObject("selectedHighestEducationLevelCodeName", selectedHighestEducationLevelCodeName);
		view.addObject("memberTypeCodeName", memberTypeCodeName);
		if (!isTeacher) {
			view.addObject("isTeacher", "F");
		} else {
			view.addObject("isTeacher", "T");
		}
		view.setViewName("member/modifyMyInfo");
		return view;

	}

	@Override
	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors, String graduationType, String helCodeName) {

		int changeCount = 0;
		MemberVO changeMember = new MemberVO();
		String inputPassword = member.getPassword();
		String selectGraduationTypeCodeId = memberBiz.getGraduationTypeCodeId(graduationType);
		String selecthelCodeId = memberBiz.gethelCodeId(helCodeName);

		MemberVO originMember = memberBiz.getOneMember(member.getId());

		if (inputPassword != "") {
			changeCount++;

			String salt = SHA256Util.generateSalt();
			String newPassword = SHA256Util.getEncrypt(inputPassword, salt);

			changeMember.setPassword(newPassword);
			changeMember.setSalt(salt);
		}

		if (!originMember.getName().equals(member.getName())) {
			changeCount++;
			changeMember.setName(member.getName());
		}

		if (!originMember.getEmail().equals(member.getEmail())) {
			changeCount++;
			changeMember.setEmail(member.getEmail());
		}

		if (!originMember.getHighestEducationLevel().equals(selecthelCodeId)) {
			changeCount++;
			changeMember.setHighestEducationLevel(selecthelCodeId);
		}

		if (!originMember.getGraduationType().equals(selectGraduationTypeCodeId)) {
			changeCount++;
			changeMember.setGraduationType(selectGraduationTypeCodeId);
		}

		if (!originMember.getPhoneNumber().equals(member.getPhoneNumber())) {
			changeCount++;
			changeMember.setPhoneNumber(member.getPhoneNumber());
		}

		if (!originMember.getBirthDate().equals(member.getBirthDate())) {
			changeCount++;
			changeMember.setBirthDate(member.getBirthDate());
		}

		if (changeCount == 0) {
		} else {
			changeMember.setId(member.getId());
			// 회원정보 수정
			memberBiz.modifyMemberInfo(changeMember);
		}

		ModelAndView view = new ModelAndView();
		view.setViewName("member/myPage");

		return view;
	}

	/**
	 * @author 이기연
	 */
	@Override
	public ModelAndView saveLoginHistoryAsExcel(HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String memberId = sessionMember.getId();

		// 로그인된 멤버의 로그인 내역만 저장시키기 위해서 보낸다.
		// boolean 값으로 받아와 excel 변환 여부를 체크한다.
		memberBiz.saveLoginHistoryAsExcel(memberId);
		view.setViewName("redirect:/member/loginHistory");
		return view;
	}

	@Override
	public void plusModifyFailCount(String sessionId) {
		memberBiz.plusModifyFailCount(sessionId);
	}

	@Override
	public void updateModifyAccountLock(String sessionId) {
		memberBiz.updateModifyAccountLock(sessionId);
	}

	@Override
	public void resetModifyLockAndCount(String sessionId) {
		memberBiz.resetModifyLockAndCount(sessionId);
	}

	@Override
	public boolean isModifyAccountLock(String sessionId) {
		return memberBiz.isModifyAccountLock(sessionId);
	}

	/**
	 * @author 206-025 김동규 > 이기연 (수정)
	 * 
	 */
	@Override
	public ModelAndView viewLoginHistoryPage(LoginHistorySearchVO loginHistorySearchVO, int pageNo,
			HttpSession session, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		int totalLoginHistoryCount = 0;
		List<LoginHistoryVO> loginHistoryList = null;
		
		MemberVO memberVO = (MemberVO) session.getAttribute("_MEMBER_");
		
		loginHistorySearchVO.setId(memberVO.getId());
		
		totalLoginHistoryCount = memberBiz.getTotalLoginHistoryCount(loginHistorySearchVO);
		
		Paging paging = new Paging();
		paging.setTotalArticleCount(totalLoginHistoryCount);
		paging.setPageNumber(pageNo + "");

		loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());

		loginHistoryList = memberBiz.getAllLoginHistory(loginHistorySearchVO);
		
		LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
		loginHistoryListVO.setLoginHistoryList(loginHistoryList);
		loginHistoryListVO.setPaging(paging);
		
		view.setViewName("member/loginHistory");
		view.addObject("loginHistoryListVO", loginHistoryListVO);
		view.addObject("loginHistorySearchVO", loginHistorySearchVO);
		
		return view;
	}

	@Override
	public void sendBlockAccountEmail(String id) {

		SendMail sendMail = new SendMail();
		MailVO mail = new MailVO();
		MemberVO member = memberBiz.getOneMember(id);

		mail.setFromId("showil2001@gmail.com");
		mail.setFromPassword("whrudwns1355!");
		mail.setSubject("[SEMS] 계정 차단 알림");
		mail.setText("비밀번호 3회 이상 오류로, 계정이 차단되었습니다. 문의사항은 관리자에게 연락하세요.");
		mail.setToId(member.getEmail());

		sendMail.sendMailToCustomer(mail);
	}

	@Override
	public String getSaltById(String id) {
		return memberBiz.getSaltById(id);
	}

	@Override
	public String getPasswordById(String id) {
		return memberBiz.getPasswordById(id);
	}

	@Override
	public void logout(HttpSession session) {
		// 세션 없애기
		session.removeAttribute("_MEMBER_");

		// 로그아웃 stamp 찍기 위해서..
		memberBiz.stampLogoutTime(session);
	}

	@Override
	public ModelAndView changePassword(MemberVO memberVO) {

		ModelAndView view = new ModelAndView();

		String originSalt = memberBiz.getSaltById(memberVO.getId());
		String inputPassword = SHA256Util.getEncrypt(memberVO.getPrevPassword(), originSalt);

		String originPassword = memberBiz.getPasswordById(memberVO.getId());

		if (inputPassword.equals(originPassword)) {

			// 입력한 현재 비밀번호가 맞은 경우
			String newSalt = SHA256Util.generateSalt();
			memberVO.setSalt(newSalt);

			String newPassword = SHA256Util.getEncrypt(memberVO.getPassword(), newSalt);
			memberVO.setPassword(newPassword);

			memberBiz.changePassword(memberVO);

			view.setViewName("redirect:/");

			return view;

		} else {

			// 입력한 현재 비밀번호가 틀렸을 경우
			view.setViewName("redirect:/changePassword/" + memberVO.getId());
			return view;
		}
	}

	@Override
	public ModelAndView registerStudent(HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			view.setViewName("member/registErrorPage");
		}
		else {
			List<String> highestEducationLevelCodeNameList = memberBiz.getHighestEducationLevelCodeNames();
			List<String> graduationTypeList = memberBiz.getGraduationType();
			
			view.setViewName("member/registerStudent");
			view.addObject("graduationTypeList", graduationTypeList);
			view.addObject("highestEducationLevelCodeNameList", highestEducationLevelCodeNameList);
		}

		return view;
	}
	
	@Override
	public String registerTeacher(HttpSession session) {
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			return "member/registErrorPage";
		}
		else {
			return "member/registerTeacher";
		}
	}

	@Override
	public String insertUuidForResign(HttpSession session) {

		MemberVO memeber = (MemberVO) session.getAttribute("_MEMBER_");
		String uuid = UUID.randomUUID().toString();

		memeber.setUuid(uuid);

		memberBiz.insertUuidForResign(memeber);

		return uuid;
	}

	@Override
	public ModelAndView sendEmailForResign(HttpSession session, String uuid) {
		ModelAndView view = new ModelAndView();

		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		member = memberBiz.getOneMember(member.getId());
		memberBiz.sendEmailForResign(member.getEmail(), member.getId(), uuid);
		// memberBiz.sendEmailForResign("abonno@naver.com", uuid);

		view.setViewName("member/myPage");

		return view;
	}

	@Override
	public ModelAndView loginForResign(String resignCode, String id) {
		ModelAndView view = new ModelAndView();

		view.addObject("resignCode", resignCode);
		view.addObject("id", id);

		view.setViewName("member/loginForResign");

		return view;
	}

	@Override
	public String doResign(MemberVO loginVO, Errors errors, String resignCode) {

		boolean isSuccess = memberBiz.doResign(loginVO);
		MemberVO memberVO = memberBiz.getOneMember(loginVO.getId());

		if (isSuccess) {
			if (memberVO.getUuid() != null && memberVO.getUuid().equals(resignCode)) {

				boolean isDeleteSuccess = memberBiz.doDeleteMember(memberVO.getId());
				if (isDeleteSuccess) {
					return "OK";
				}
				return "NO";
			} else {
				return "FAIL";
			}
		} else {
			return "NO";
		}
	}

	@Override
	public String doCheckPrevPassword(String id, String prevPassword, HttpServletRequest request) {

		String originSalt = memberBiz.getSaltById(id);
		String inputPassword = SHA256Util.getEncrypt(prevPassword, originSalt);

		String originPassword = memberBiz.getPasswordById(id);

		if (!inputPassword.equals(originPassword)) {
			return "NO";
		}

		return "OK";
	}

	@Override
	public ModelAndView viewMyPageMenu() {
		ModelAndView view = new ModelAndView();
		List<MenuManageVO> menuList = memberBiz.getMenuCategoryList();

		view.setViewName("member/myPage");
		view.addObject("menuList", menuList);

		return view;
	}

	@Override
	public ModelAndView getAllEducationHistoryListByIdWithPaging(int pageNo, HttpSession session) {
		
		EducationHistoryListVO educationHistoryListVO = new EducationHistoryListVO();
		Paging paging = new Paging();
		educationHistoryListVO.setPaging(paging);

		paging.setPageNumber(pageNo + "");
		
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		int totalEducationHistoryCountById = memberBiz.getTotalEducationHistoryCountById(memberVO.getId());
		paging.setTotalArticleCount(totalEducationHistoryCountById);

		EducationHistorySearchVO educationHistorySearchVO = new EducationHistorySearchVO();
		educationHistorySearchVO.setPageNo(pageNo);
		educationHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		educationHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
		educationHistorySearchVO.setMemberId(memberVO.getId());
		
		List<EducationHistoryVO> educationHistoryList = memberBiz.getAllEducationHistoryListByIdWithPaging(educationHistorySearchVO);
		educationHistoryListVO.setEducationHistoryList(educationHistoryList);

		ModelAndView view = new ModelAndView();
		view.setViewName("education/educationHistory");
		view.addObject("educationHistoryListVO", educationHistoryListVO);
		
		return view;
	}

	@Override
	public String registerPolicy(HttpSession session) {
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( sessionMember != null ) {
			return "member/registErrorPage";
		}
		else {
			return "member/registerPolicy";
		}
	}

	@Override
	public ModelAndView loginHistoryInit() {
		ModelAndView view = new ModelAndView();
		
		view.addObject("beginDate", null);
		view.addObject("closeDate", null);
		view.setViewName("redirect:/member/loginHistory");
		
		return view;
	}

	@Override
	public ModelAndView doRequestIpHistory(int lgiHtrId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute(Session.MEMBER);
					
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId(sessionMember.getId());
		loginHistoryVO.setLgiHtrId(lgiHtrId);
		
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		if(memberCheck) {
			memberBiz.doRequestIpHistory(lgiHtrId);
			view.setViewName("redirect:/member/loginHistory");
			return view;
		}else {
			view.setViewName("redirect:/");
			return view;
		}
	}

	@Override
	public ModelAndView doCheckIp(int lgiHtrId, HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberVO sessionMember = (MemberVO) session.getAttribute(Session.MEMBER);
		
		LoginHistoryVO loginHistoryVO = new LoginHistoryVO();
		loginHistoryVO.setId(sessionMember.getId());
		loginHistoryVO.setLgiHtrId(lgiHtrId);
		
		boolean checkIp = memberBiz.doCheckIp(loginHistoryVO);
		boolean memberCheck = memberBiz.doMatchHistoryWithMember(loginHistoryVO);
		
		if(checkIp && memberCheck) {
			LoginHistoryVO loginHistory = memberBiz.checkIpInfo(loginHistoryVO);
			view.setViewName("member/checkIP");
			view.addObject("loginHistory", loginHistory);
			return view;
		}else {
			return new ModelAndView("redirect:/");
		}
	}

	@Override
	public String eduationHistoryExportExcel(HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute(Session.MEMBER);
		boolean isSuccess = memberBiz.eduationHistoryExportExcel(memberVO.getId());
		
		if(isSuccess) {
			return "redirect:/member/myPage/educationHistory";
		} else {
			return "redirect:/member/myPage";
		}
		
	}

}
