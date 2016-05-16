package com.ktds.sems.member.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;

import com.ktds.sems.common.Session;
import com.ktds.sems.member.biz.MemberBiz;
import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

import kr.co.hucloud.utilities.web.AjaxUtil;

public class MemberServiceImpl implements MemberService{

	private MemberBiz memberBiz;
		
	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	/* 로그인 */
	@Override
	public String login(MemberVO loginVO, Errors errors, HttpSession session, HttpServletRequest request) {

		// 아이디 있는지 확인
		if ( memberBiz.isExistId(loginVO.getId()) ) {
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
				memberBiz.stampLoginTime(session, request, loginVO);
				
				if(memberBiz.needToChangPassword(loginVO.getId())) {
					return "CNGPW";
				} else {
					
					String memberType = (String) session.getAttribute(Session.MEMBER_TYPE);
					if(memberType != null && !memberType.equals("ADM")) {
						return "NOADM";
					}
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
	public void logout(HttpSession session) {
		// 세션 없애기
		session.removeAttribute("_MEMBER_");
		
		//로그아웃 stamp 찍기 위해서.. 
		memberBiz.stampLogoutTime(session);
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

		boolean isExistId = memberBiz.isDuplicationId(id);
		if (isExistId) {
			message = "EXIST";
		}
		System.out.println(isExistId);
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
	public void checkValidationByPhoneNumber(String phoneNumber, HttpServletResponse response) {
		String message = "NO";
		boolean isVerifyPhoneNumber = memberBiz.isVerifyPhoneNumber(phoneNumber);
		if (isVerifyPhoneNumber) {
			message = "OK";
		}
		AjaxUtil.sendResponse(response, message);
		return;
	}

}
