package com.hucloud.sems.batch.jobs.member.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hucloud.sems.batch.context.UserSqlSessionDaoSupport;
import com.hucloud.sems.batch.jobs.member.dao.MemberDAO;

@Repository("memberDAOImpl")
public class MemberDAOImpl extends UserSqlSessionDaoSupport implements MemberDAO {

	private Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private List<String> removeQueries;

	public void setRemoveQueries(List<String> removeQueries) {
		this.removeQueries = removeQueries;
	}

	public List<String> selectDropOutMembers(int dropOutDays) {
		return getSqlSession().selectList("MemberDAO.selectDropOutMembers", dropOutDays);
	}
	
	public void removeDropOutMembers(String memberId) {
		for (String queryId : removeQueries) {
			logger.debug("»èÁ¦ Äõ¸® [" + queryId + "]");
			getSqlSession().delete(queryId, memberId);
		}		
	}
	
}
