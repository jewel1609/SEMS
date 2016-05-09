package com.ktds.sems.member.service.impl;

import java.util.Random;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.member.service.MemberService;
import com.ktds.sems.member.vo.MemberVO;

public class MemeberServiceImpl implements MemberService {

	@Override
	public ModelAndView registerNewMember(MemberVO member, Errors errors) {
		String salt = generateSalt();
		member.setSalt(salt);

		return null;
	}
	
	private static String generateSalt() {
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < salt.length; i++) {
			// byte 값을 Hex 값으로 바꾸기.
			sb.append(String.format("%02x",salt[i]));
		}
		
		return sb.toString();
	}
	
}
