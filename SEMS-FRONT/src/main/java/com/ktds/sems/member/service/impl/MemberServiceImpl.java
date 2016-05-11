package com.ktds.sems.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.LoginHistoryListVO;
import com.ktds.sems.member.vo.LoginHistorySearchVO;
import com.ktds.sems.member.vo.LoginHistoryVO;
import com.ktds.sems.member.vo.MemberVO;

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
		else if ( errors.hasErrors() ) {
			view.setViewName("member/register");
			view.addObject("member", member);
		} else {
			// TODO 비밀번호 암호화
			// 1. salt 생성
			
			// 2. 암호화
			boolean isVerifyId = isVerifyId(member.getId());
			if ( !isVerifyId ) {
				throw new RuntimeException("입력 값 오류 : ID");
			}
			
			boolean isVerifyPassword = isVerifyPassword(member.getPassword());
			if ( !isVerifyPassword ) {
				throw new RuntimeException("입력 값 오류 : PASSWORD");
			}

			memberBiz.addNewMember(member);
			
			
			// TODO 이동할 페이지
			view.setViewName("");
		}

		return view;
	}
	
	
	
	private boolean isVerifyId (String id) {
		String idPolicy = "((?=.*[a-zA-Z])(?=.*[0-9]).{8,})";
		Pattern pattern = Pattern.compile(idPolicy);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	
	private boolean isVerifyPassword (String password) {
		String passwordPolicy = "((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})";
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if ( memberBiz.isExistId(loginVO.getId()) ) {
			return "NO";
		}
		
		// 잠긴 계정은 로그인 못하도록 막는다.
		if (memberBiz.isAccountLock(loginVO.getId())) {
			return "OVER";
		}

		boolean isLoginSuccess = memberBiz.login(session, loginVO);
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
				
				// 로그인 내역 남기기 
				memberBiz.stampLoginTime(request, loginVO);
				
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
		
		// memberVO를 가져온다.
		MemberVO member = memberBiz.getOneMember(id);
		
		view.addObject("member", member);
		view.setViewName("member/modifyMyInfo");
		return view;
		
	}

	@Override
	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors) {
		
		int changeCount = 0;
		MemberVO changeMember = new MemberVO();
		
		String inputPassword = member.getPassword();
		
		MemberVO originMember = memberBiz.getOneMember(member.getId());
		if ( inputPassword != ""){
			changeCount++;
			String salt = SHA256Util.generateSalt();
			String newPassword = SHA256Util.getEncrypt(inputPassword, salt);
			changeMember.setPassword(newPassword);
			changeMember.setSalt(salt);
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
}
