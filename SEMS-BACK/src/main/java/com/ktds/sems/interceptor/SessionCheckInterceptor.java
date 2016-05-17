package com.ktds.sems.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.sems.common.Session;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean wasLogin = request.getSession().getAttribute(Session.MEMBER) != null;
		
		if ( wasLogin ) {
			// 로그인 체크시 관리자 접근 가능 
			String authority = (String) request.getSession().getAttribute(Session.MEMBER_TYPE);
			
			if (authority.equals("ADM")) {
				response.sendRedirect("/main");
				return true;
			} else {
				request.getSession().invalidate();
				return false;
			}
		}
		else {
			response.sendRedirect("/backend");
			return false;
		}
	}

}
