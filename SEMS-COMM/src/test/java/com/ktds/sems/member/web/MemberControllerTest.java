package com.ktds.sems.member.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;

public class MemberControllerTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	public void viewGrdtPageTest(){
		ModelAndView view = memberService.viewGrdtPage();
		assertNotNull(view.getViewName());
	}

}
