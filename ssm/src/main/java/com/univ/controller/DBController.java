package com.univ.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.univ.mapper.DBMapper;



/**
 * Company: miaxis
 * @author :liu.ml
 * @date :2015-12-28 上午10:59:12
 * Description: 
 */
@Controller
@RequestMapping("/db")
public class DBController {
	@Autowired
	private DBMapper dBMapper;

	@RequestMapping("/query")
	public String query(String id){
		int totalCount = dBMapper.totalCount();
		System.out.println("totalCount: " + totalCount);
		return "ok";
	}
}
