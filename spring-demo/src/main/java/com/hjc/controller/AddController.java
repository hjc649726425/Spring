package com.hjc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {

	@GetMapping("test1")
	@ResponseBody
	public String test(String s){
		return "test:" + s;
	}




}
