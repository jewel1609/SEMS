package com.hucloud.sems.batch.jobs.member.dao;

import java.util.List;

public interface MemberDAO {

	public List<String> selectDropOutMembers(int dropOutDays);
	
	public void removeDropOutMembers(String memberId);
	

}
