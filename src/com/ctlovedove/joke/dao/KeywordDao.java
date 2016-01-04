package com.ctlovedove.joke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctlovedove.joke.bean.Keyword;

/**
 * 关键字DAO
 * @author chenting
 *
 */
public interface KeywordDao {
	/**
	 * 新增
	 * @param keyword
	 */
	public void save(Keyword keyword);
	/**
	 * 修改
	 * @param keyword
	 */
	public void update(Keyword keyword);
	/**
	 * 删除
	 * @param accountId
	 */
	public void deleteById(int id);
	/**
	 * 查询
	 * @param accountName
	 */
	public List<Keyword> queryByAccount(String account);
	
	/**
	 * 分页查找
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Keyword> queryListByPager(Keyword keyword, int pageIndex, int pageSize);
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
	public Keyword queryById(@Param("id") int id);
}
