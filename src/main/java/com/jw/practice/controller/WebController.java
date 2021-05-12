package com.jw.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	@RequestMapping("/")
	public String jspCheck(Model model) {
		System.out.println(" /jsp 타는지 ");
		System.out.println(" sourceTree 연동 테스트 ");
		
		model.addAttribute("name", "name 입니다.");
		return "index";
	}
}
