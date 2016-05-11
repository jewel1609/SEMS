package com.ktds.sems.menu.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.service.MenuService;
import com.ktds.sems.menu.vo.MenuManageVO;

@Controller
public class MenuController {
	
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping("/menu")
	public ModelAndView viewMenuPage() {
		return menuService.viewMenuManagePage();
	}
	
	@RequestMapping("/doMenuUpdate")
	public ModelAndView doMenuUpdate(@Valid MenuManageVO menuManageVO, Errors errors, HttpServletRequest request) {
	
		return menuService.doMenuUpdate(menuManageVO, errors, request);
	}
	
}
