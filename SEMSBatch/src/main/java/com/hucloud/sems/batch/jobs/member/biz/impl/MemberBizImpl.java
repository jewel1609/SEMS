package com.hucloud.sems.batch.jobs.member.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hucloud.sems.batch.jobs.member.biz.MemberBiz;
import com.hucloud.sems.batch.jobs.member.dao.MemberDAO;

@Component("memberBizImpl")
public class MemberBizImpl implements MemberBiz {
	
	private Logger logger = LoggerFactory.getLogger(MemberBizImpl.class);
	
	@Autowired
	@Qualifier("memberDAOImpl")
	private MemberDAO memberDAO;
	
	public void removeDropOutMembers(int dropOutDays) {
		List<String> dropOutMembers = memberDAO.selectDropOutMembers(dropOutDays);
		logger.info("삭제 대상 [" + dropOutMembers.size() + "]");
		if ( dropOutMembers != null && dropOutMembers.size() > 0 ) {
			for (String memberId : dropOutMembers) {
				memberDAO.removeDropOutMembers(memberId);
				logger.info("삭제 [" + memberId + "]");
			}
		}
		
	}
	
}
