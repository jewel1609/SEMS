package com.ktds.sems.menu.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.vo.MenuManageVO;


public interface MenuService {

	public ModelAndView viewMenuManagePage();

	public ModelAndView doMenuUpdate(MenuManageVO menuManageVO, Errors errors, HttpServletRequest request);

}
