package com.ktds.sems.education.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EducationController {
	
	@RequestMapping("/category")
	public String viewCategoryPage(){
		return "/education/category";
	}

}
