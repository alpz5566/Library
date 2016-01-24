package com.book.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="index")
public class IndexController {

	/**
	 * 测试用例
	 * @return
	 */
	@RequestMapping(value="/home")
	public String toIndexPage(){
		return "index";
	}
}
