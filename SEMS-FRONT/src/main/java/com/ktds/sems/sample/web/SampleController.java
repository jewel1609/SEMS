package com.ktds.sems.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@RequestMapping("/")
	public String viewSamplePage(){
		return "index";
	}
}
