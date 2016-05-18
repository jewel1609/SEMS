package com.ktds.sems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.sems.common.Session;

public class CommInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String userType = (String) request.getSession().getAttribute(Session.MEMBER_TYPE);
		if(userType != null && userType.equals("ADM")) {
			return;
		}
		/*
		 * FIXME 원할한 테스트를 위해 주석처리함.
		 * 테스트 완료후 주석 삭제
		else {
			response.sendRedirect("/comm");
			return;
		}*/
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
