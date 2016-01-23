package com.book.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 测试
 * @author L------F
 *
 */
@Controller  //申明这是个控制器
@RequestMapping(value="/user")
public class TestAdmin {
	@RequestMapping(value="/admin")
	public String test(){
		System.out.println("哈哈");
		return "test/test";
	}

}
