package com.univ.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.univ.domain.Constant.*;
import com.univ.domain.DataDicInfo;
import com.univ.service.DataDicService;

/** 
 * @author univ 
 * @date 2015年12月30日 下午1:35:35 
 * @version v1.0
 * @Description: 
 */
@Controller
@RequestMapping("/dataDic")
public class DataDicController {

	private Logger logger = Logger.getLogger(DataDicController.class);
	
	@Autowired
	private DataDicService dataDicService;
	
	@RequestMapping("/toList")
	public String toList(){
		logger.debug("=======DataDicController.toList() invoked=======");
		return "dataDic/dataDicList";
	}
	
	/**
	 * 获取一页的数据字典的信息
	 * @param page 获取第几页的数据
	 * @param rows	一页显示多少条数据
	 * @return
	 */
	@RequestMapping("/getOnePage")
	@ResponseBody
	public Map<String, Object> getOnePage(@RequestParam("page")Integer page,@RequestParam("rows")Integer rows,HttpServletRequest request){
		logger.debug("=======DataDicController.getOnePage() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		int startRow = (page-1)*rows;//检索的起始行
		int pageSize = rows;//每页检索的数据条数
		
		//获取查询参数并封装成bean
		String name = request.getParameter("name");
		DataDicInfo dataDic = new DataDicInfo();
		dataDic.setName(name);
		//根据数据类型查询
		Long parentId = null;
		if(null != request.getParameter("parentId") && !"".equals(request.getParameter("parentId"))){
			parentId = Long.valueOf(request.getParameter("parentId"));
			DataDicInfo parent = new DataDicInfo();
			parent.setId(parentId);
			dataDic.setParent(parent);
		}
		
		List<DataDicInfo> dataDicList =  dataDicService.getOnePage(startRow,pageSize,dataDic);
		if (StringUtils.isEmpty(name) && StringUtils.isEmpty(parentId)) {//查询参数为空
			map.put("total", totalCount());
		}else {
			map.put("total", dataDicList.size());
		}
		map.put("rows", dataDicList);
		return map;
	}
	
	/**
	 * 获取数据字典类型
	 * @return
	 */
	@RequestMapping("/getLevelOne")
	@ResponseBody
	public List<DataDicInfo> getLevelOne(){
		logger.debug("=======DataDicController.getLevelOne() invoked=======");
		List<DataDicInfo> dataDicList = dataDicService.getLevelOne();
		return dataDicList;
	}
	
	@RequestMapping("/addDataDic")
	@ResponseBody
	public Map<String, Object> addDataDic(DataDicInfo dataDic){
		logger.debug("=======DataDicController.addDataDic() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			dataDicService.addDataDic(dataDic);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			map.put("result", OPERATE_FAIL);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除单条数据字典记录
	 * @param id	欲删除数据字典记录的id
	 * @return
	 */
	@RequestMapping("/delDataDicById")
	@ResponseBody
	public Map<String, Object> delDataDicById(@RequestParam("id")Integer id){
		logger.debug("=======DataDicController.delDataDicById() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			dataDicService.delDataDicById(id);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			map.put("result", OPERATE_FAIL);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改单条数据字典记录
	 * @param id	欲修改数据字典记录的id
	 * @return
	 */
	@RequestMapping("/updateDataDic")
	@ResponseBody
	public Map<String, Object> updateDataDic(DataDicInfo dataDic){
		logger.debug("=======DataDicController.updateDataDic() invoked=======");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			dataDicService.updateDataDic(dataDic);
			map.put("result", OPERATE_SUCCESS);
		} catch (Exception e) {
			map.put("result", OPERATE_FAIL);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("/getDataDicItems")
	@ResponseBody
	public List<DataDicInfo> getDataDicItems(String dataDicType){
		List<DataDicInfo> dataDicItems = dataDicService.getDataDicItems(dataDicType);
		return dataDicItems;
	}
	/**
	 * 获取表中所有的记录
	 * @return
	 */
	private int totalCount() {
		return dataDicService.totalCount();
	}
}

