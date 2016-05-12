package com.hucloud.sems.batch.jobs.member.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hucloud.sems.batch.context.SemsContext;
import com.hucloud.sems.batch.jobs.member.service.MemberService;

public class RemoveDropOutMember extends QuartzJobBean {

	private MemberService memberService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		memberService = SemsContext.getBean("memberServiceImpl");
		
		memberService.removeDropOutMembers(60);
		
	}

}
