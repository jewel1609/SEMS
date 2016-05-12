package com.ktds.sems.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SemsContext {

	public static ServletContext getContext(ServletRequest request) {
		ServletContext context = request.getServletContext();
		return context;
	}
	
	public static ServletContext getContext(HttpSession session) {
		ServletContext context = session.getServletContext();
		return context;
	}
	
	public static ServletContext getContext(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		return getContext(session);
	}
	
	public static <T> T getBean(HttpSession session, String beanName) {
		WebApplicationContext context = getApplicationContext(getContext(session));
		return (T) context.getBean(beanName);
	}
	
	private static WebApplicationContext getApplicationContext(ServletContext context) { 
		return WebApplicationContextUtils.getWebApplicationContext(context);
		
	}
	
}
