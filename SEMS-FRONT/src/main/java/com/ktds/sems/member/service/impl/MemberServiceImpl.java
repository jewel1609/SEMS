package com.ktds.sems.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public String login(MemberVO loginVO, Errors errors, HttpSession session) {

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
				
				if(memberBiz.needToChangPassword(loginVO)) {
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
		view.setViewName("/member/modifyMyInfo");
		return view;
		
	}

	@Override
	public ModelAndView modifyMemberInfo(MemberVO member, Errors errors) {
		// TODO Auto-generated method stub
		return null;
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
		
		List<LoginHistoryVO> loginHistoryList = new ArrayList<LoginHistoryVO>();
		loginHistoryListVO.setLoginHistoryList(loginHistoryList);
		
		view.setViewName("member/loginHistory");
		view.addObject("loginHistoryListVO", loginHistoryListVO);
		
		return view;
	}

}
