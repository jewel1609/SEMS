package com.hucloud.sems.batch.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SemsContext {

private static AbstractApplicationContext ctx = null;
	
	public static void start() {
		
		String rootContext = "classpath:/spring/rootContext.xml";
		String quartz = "classpath:/spring/quartz.xml";
		
		if(ctx == null)
			ctx = new GenericXmlApplicationContext(rootContext, quartz);
	}
	
	public static <T> T getBean(String beanName) {
		return (T) load().getBean(beanName);
	}
	
	private static ApplicationContext load() {
		return ctx;
	}
	
}
