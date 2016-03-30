package com.ctlovedove.joke.service;

import java.util.List;

import com.ctlovedove.joke.bean.Keyword;
/**
 * 关键字service接口
 * @author chenting
 *
 */
public interface KeywordService {
	/**
	 * 新增
	 * @param keyword
	 * @throws Exception 
	 */
	public void save(Keyword keyword) throws Exception;
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception 
	 */
	public void deleteByIds(String[] ids) throws Exception;
	/**
	 * 修改
	 * @param keyword
	 * @throws Exception 
	 */
	public void update(Keyword keyword) throws Exception;
	/**
	 * 分页查找
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Keyword> queryKeywordListByPager(Keyword keyword, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param keyword
	 * @return
	 */
	public int getTotalCountByPager(Keyword keyword);
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public Keyword queryById(int accountId);
	/**
	 * 批量插入数据库
	 * @param keywordList
	 * @throws Exception 
	 */
	public void addBatch(List<Keyword> keywordList) throws Exception;

	/**
	 * 批量审核
	 * @param ids
	 * @throws Exception 
	 */
	public void updateKeywordState(String[] ids) throws Exception;
}
