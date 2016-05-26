package com.ktds.sems.team.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ktds.sems.SemsTestCase;

@Transactional
public class TeamDAOTest extends SemsTestCase {

	@Autowired
	private TeamDAO teamDAO;
	
	
}
