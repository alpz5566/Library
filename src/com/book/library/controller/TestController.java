package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Test")
public class TestController {
	
	@RequestMapping(value="/test")
	public String test(ServletRequest request){
		
		System.out.println("反法");
		return "test/test";
	}
}
