package com.ktds.sems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 페이지 접근할 때, 세션이 있는지 없는지 체크
		boolean wasLogin = request.getSession().getAttribute("_MEMBER_") != null;
		
		if (wasLogin) {
			// 로그인 체크시 강사, 관리자, 회원이 접근 가능 
			System.out.println("세션 있음: " + request.getSession().getAttribute("_MEMBER_"));
		} else {
			System.out.println("세션 없음");
			//response.sendRedirect("");
		}
		
		//return wasLogin;
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
