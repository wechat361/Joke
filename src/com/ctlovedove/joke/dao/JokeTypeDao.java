package com.ctlovedove.joke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctlovedove.joke.bean.JokeType;

/**
 * 分类DAO
 * @author chenting
 *
 */
public interface JokeTypeDao {
	/**
	 * 新增
	 * @param manager
	 */
	public void save(JokeType jokeType);
	/**
	 * 修改
	 * @param manager
	 * @throws Exception 
	 */
	public void update(JokeType jokeType) throws Exception;
	/**
	 * 删除
	 * @param accountId
	 */
	public void deleteById(int id) throws Exception;
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception 
	 */
	public void deleteByIds(String[] ids) throws Exception;
	
	/**
	 * 分页查找
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<JokeType> queryJokeTypeListByPager(JokeType jokeType, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param manager
	 * @return
	 */
	public int getTotalCountByPager(JokeType jokeType);
	
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public JokeType queryById(@Param("id") int id);
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<JokeType> getJokeTypeAll();

}
