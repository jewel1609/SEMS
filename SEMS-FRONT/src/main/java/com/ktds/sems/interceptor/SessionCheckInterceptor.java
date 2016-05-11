package com.ktds.sems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.sems.common.Session;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 페이지 접근할 때, 세션이 있는지 없는지 체크
		boolean wasLogin = request.getSession().getAttribute(Session.MEMBER) != null;
		
		if ( wasLogin ) {
			System.out.println("세션 있음");
		} else {
			System.out.println("세션 없음");
			response.sendRedirect("/");
		}
		
		return true;
	}
}