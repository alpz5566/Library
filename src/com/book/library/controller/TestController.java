package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@RequestMapping(value="/ceshi")
	public String test(ServletRequest request){
		System.out.println("反法");
		return "test/test";
	}
}
