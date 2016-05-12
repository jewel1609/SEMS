package com.hucloud.sems.batch.jobs.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hucloud.sems.batch.jobs.member.biz.MemberBiz;
import com.hucloud.sems.batch.jobs.member.service.MemberService;

@Service("memberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	@Qualifier("memberBizImpl")
	private MemberBiz memberBiz;
	
	public void removeDropOutMembers(int dropOutDays) {
		memberBiz.removeDropOutMembers(dropOutDays);
	}
	
}
