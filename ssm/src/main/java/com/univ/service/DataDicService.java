package com.univ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univ.domain.DataDicInfo;
import com.univ.mapper.DataDicMapper;

/** 
 * @author univ 
 * @date 2015年12月30日 下午1:38:26 
 * @version v1.0
 * @Description: 
 */
@Service
public class DataDicService {

	@Autowired
	private DataDicMapper dataDicMapper;

	/**
	 * 获取一页的数据
	 * @param startRow 检索的起始行
	 * @param pageSize 每页显示多少条记录
	 * @param dataDic 封装了查询参数
	 * @return
	 */
	public List<DataDicInfo> getOnePage(int startRow,int pageSize,DataDicInfo dataDic) {
		List<DataDicInfo> dataDicList = dataDicMapper.getOnePage(startRow,pageSize,dataDic);
		for (DataDicInfo dataDicInfo : dataDicList) {
			//注意这里不能直接使用dataDicInfo.getParent().getName(),有空指针
			DataDicInfo parent = dataDicMapper.getParent(dataDicInfo);
			dataDicInfo.setParent(parent);
		}
		return dataDicList;
	}

	/**
	 * 获取表中所有的记录数
	 * @return
	 */
	public int totalCount() {
		return dataDicMapper.totalCount();
	}
	
	/**
	 *  获取所有的数据字典类型
	 * @return
	 */
	public List<DataDicInfo> getLevelOne() {
		return dataDicMapper.getLevelOne();
	}

	/**
	 * 新增数据字典项
	 * @param dataDic	欲被新增的数据字典项
	 */
	public void addDataDic(DataDicInfo dataDic) {
		dataDicMapper.add(dataDic);
	}

	/**
	 * 删除单条数据字典记录
	 * @param id	欲删除数据字典记录的id
	 * @return
	 */
	public void delDataDicById(Integer id) {
		dataDicMapper.delete(id);
	}

	/**
	 * 更新单条数据字典记录
	 * @param dataDic	欲被修改的数据字典记录
	 */
	public void updateDataDic(DataDicInfo dataDic) {
		dataDicMapper.update(dataDic);
		
	}

	/**
	 * 获取某个数据类型下的所有数据项
	 * @param dataDicType
	 */
	public List<DataDicInfo> getDataDicItems(String dataDicType) {
		return dataDicMapper.getDataDicItems(dataDicType);
	}
}

