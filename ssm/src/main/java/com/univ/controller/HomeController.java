package com.univ.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @author univ 
 * @date 2015年12月30日 上午9:11:07 
 * @version v1.0
 * @Description: 入口控制器
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	private Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/toHome")
	public String toHome(){
		logger.debug("=======HomeController.tohHome() invoked=======");
		return "home";
	}
	
	/**
	 * 跳转到converter.jsp页面上
	 */
	@RequestMapping("/toConverter")
	public String toConverter(){
		return "converter";
	}
	
	/**
	 * 测试自动类型转换（String--->Integer）
	 * @param age
	 */
	@RequestMapping("/converter")
	public String converter(Integer age){
		System.out.println(age);
		return "converter";
	}
	
	/**
	 * 跳转到converter.jsp页面上
	 */
	@RequestMapping("/toFormatter")
	public String toFormatter(){
		return "formatter";
	}
	
	/**
	 * 测试全局日期格式化
	 * @param date
	 */
	@RequestMapping("/formatter")
	public String formatter(Date date){
		System.out.println(date);
		return "formatter";
	}
	
	
	
}

