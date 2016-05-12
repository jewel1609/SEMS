package com.ktds.sems.member.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.SendMail;
import com.ktds.sems.common.Session;
import com.ktds.sems.common.vo.MailVO;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

import kr.co.hucloud.utilities.SHA256Util;
import kr.co.hucloud.utilities.web.Paging;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	@Override
	public ModelAndView addNewMember(MemberVO member, Errors errors, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		if (sessionMember != null) {
			throw new RuntimeException("유효한 접근이 아닙니다.");
		}
		else if ( errors.hasErrors() && member.getMemberType().equals("TR") ) {
			view.setViewName("member/registerTeacher");
			view.addObject("member", member);
		}
		else if ( errors.hasErrors() && member.getMemberType().equals("MBR") ) {
			view.setViewName("member/registerStudent");
			view.addObject("member", member);
		}
		else {
			boolean isVerifyId = memberBiz.isVerifyId(member.getId());
			boolean isVerifyPassword = memberBiz.isVerifyPassword(member.getPassword());
			
			if ( !isVerifyId || !isVerifyPassword ) {
				throw new RuntimeException("입력 값 오류 아이디 혹은 비밀번호");
			}

			String salt = SHA256Util.generateSalt();
			member.setSalt(salt);
			
			String newPassword = SHA256Util.getEncrypt(member.getPassword(), salt);
			member.setPassword(newPassword);

			memberBiz.addNewMember(member);
			
			view.setViewName("redirect:/");
		}

		return view;
	}
		
	@Override
	public void checkValidationById(String id, HttpServletResponse response) {
		String message = "OK";
		boolean isVerifyId = memberBiz.isVerifyId(id);
		if (!isVerifyId){
			message = "NO";
			AjaxUtil.sendResponse(response, message);
			return;
		}
		
		boolean isExistId = memberBiz.isExistId(id);
		if (isExistId){
			message = "EXIST";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}
	
	@Override
	public void checkValidationByPassword(String password, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPassword = memberBiz.isVerifyPassword(password);
		if (isVerifyPassword){
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public void checkValidationByRepeatPassword(String password, String repeatPassword, HttpServletResponse response) {
		String message = "NO";
		boolean isEquals = password.equals(repeatPassword);
		if (isEquals){
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}
	
	@Override
	public void checkExistionByEmail(String email, HttpServletResponse response) {
		String message = "EXIST";
		boolean isExistEmail = memberBiz.isExistEmail(email);
		if (!isExistEmail){
			message = "NO";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if ( !memberBiz.isExistId(loginVO.getId()) ) {
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
				
				memberBiz.attend(loginVO);
				
				// 로그인 내역 남기기 
				memberBiz.stampLoginTime(session, request, loginVO);
				
				if(memberBiz.needToChangPassword(loginVO.getId())) {
					return "CNGPW";
				} else {
					return "OK";
				}
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
		
		//대학구분, 최종학력 구분을 가져온다.
		List<String> graduationTypeCodeNameList = memberBiz.getGraduationType();
		view.addObject("member", member);
		view.addObject("graduationTypeList", graduationTypeCodeNameList);
		view.setViewName("member/modifyMyInfo");
		return view;
		
	}

	@Override
	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors) {
		
		int changeCount = 0;
		MemberVO changeMember = new MemberVO();
		
		String inputPassword = member.getPassword();
		
		MemberVO originMember = memberBiz.getOneMember(member.getId());
		
		System.out.println(inputPassword+"수정원하는 암호");
		System.out.println(originMember.getPassword()+"원래암호");
		
		if ( inputPassword != ""){
			System.out.println("들어오나?");
			changeCount++;
			
			String salt = SHA256Util.generateSalt();
			String newPassword = SHA256Util.getEncrypt(inputPassword, salt);
			
			changeMember.setPassword(newPassword);
			changeMember.setSalt(salt);
			System.out.println(newPassword + "<>>" + salt);
		}
		
		
		if ( !originMember.getName().equals(member.getName()) ) {
			changeCount++;
			changeMember.setName(member.getName());
		}

		if ( !originMember.getEmail().equals(member.getEmail()) ) {
			changeCount++;
			changeMember.setEmail(member.getEmail());
		}
		
		if ( !originMember.getHighestEducationLevel().equals(member.getHighestEducationLevel()) ) {
			changeCount++;
			changeMember.setHighestEducationLevel(member.getHighestEducationLevel());
		}
		
		if ( !originMember.getGraduationType().equals(member.getGraduationType()) ) {
			changeCount++;
			changeMember.setGraduationType(member.getGraduationType());
		}
		
		if ( !originMember.getPhoneNumber().equals(member.getPhoneNumber()) ) {
			changeCount++;
			changeMember.setPhoneNumber(member.getPhoneNumber());
		}
		
		if ( !originMember.getBirthDate().equals(member.getBirthDate()) ) {
			changeCount++;
			changeMember.setBirthDate(member.getBirthDate());
		}
		
		if( changeCount == 0 ) {
			System.out.println("변경사항 없음");
		}
		else {
			changeMember.setId(member.getId());
			System.out.println(changeMember.getSalt()+"---"+changeMember.getPassword()+"---"+changeMember.getId());
			//회원정보 수정
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
	public void saveLoginHistoryAsExcel(HttpSession session) {
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		String memberId = sessionMember.getId();
		
		// 로그인된 멤버의 로그인 내역만 저장시키기 위해서 보낸다. 
		// boolean 값으로 받아와 excel 변환 여부를 체크한다.
		memberBiz.saveLoginHistoryAsExcel(memberId);
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


	@Override
	public ModelAndView viewLoginHistoryPage(int pageNo) {
		ModelAndView view = new ModelAndView();
		LoginHistoryListVO loginHistoryListVO = new LoginHistoryListVO();
		Paging paging = new Paging();
		loginHistoryListVO.setPaging(paging);
		paging.setPageNumber(pageNo + "");
		
		int totalLoginHisotryCount = memberBiz.getTotalLoginHisotryCount();
		paging.setTotalArticleCount(totalLoginHisotryCount);
		
		LoginHistorySearchVO loginHistorySearchVO = new LoginHistorySearchVO();
		loginHistorySearchVO.setStartIndex(paging.getStartArticleNumber());
		loginHistorySearchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<LoginHistoryVO> loginHistoryList = memberBiz.getAllLoginHistory(loginHistorySearchVO);
		loginHistoryListVO.setLoginHistoryList(loginHistoryList);
		
		view.setViewName("member/loginHistory");
		view.addObject("loginHistoryListVO", loginHistoryListVO);
		
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

	
//	public static void main(String[] args) {
//		
//		MemberServiceImpl memberService = new MemberServiceImpl();
//		System.out.println("성공!!");
//		memberService.sendBlockAccountEmail("shinmi0315@naver.com");
//		System.out.println("성공!!");
//		
//	}
	
	@Override
	public void logout(HttpSession session) {
		// 세션 없애기
		session.removeAttribute("_MEMBER_");
		
		//로그아웃 stamp 찍기 위해서.. 
		memberBiz.stampLogoutTime(session);
	}
}
