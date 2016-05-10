package com.ktds.sems.menu.service.impl;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ktds.sems.menu.biz.MenuBiz;
import com.ktds.sems.menu.service.MenuService;
import com.ktds.sems.menu.vo.MenuManageVO;

public class MenuServiceImpl implements MenuService{

	private MenuBiz menuBiz;
	
	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
	}


	@Override
	public String doMenuUpdate() {
		
		boolean success = menuBiz.doMenuUpdate();
		
		if ( success ) {
			return "menu/menuManage";
		}
		else {
			return "error/505";
		}
		
	}


	@Override
	public ModelAndView viewMenuManagePage() {
		
		ModelAndView view = new ModelAndView();
		
		List<MenuManageVO> menuList = menuBiz.getMenuCategory();
		view.setViewName("menu/menuManage");
		view.addObject("menuList",menuList);
		
		return view;	
		
	}

}
