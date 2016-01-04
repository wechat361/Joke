package com.ctlovedove.joke.service;

import java.util.List;

import com.ctlovedove.joke.bean.JokeType;
/**
 * 笑话分类service接口
 * @author chenting
 *
 */
public interface JokeTypeService {
	/**
	 * 新增
	 * @param jokeType
	 */
	public void save(JokeType jokeType);
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
	 * @param jokeType
	 * @throws Exception 
	 */
	public void update(JokeType jokeType) throws Exception;
	/**
	 * 分页查找
	 * @param jokeType
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<JokeType> queryJokeTypeListByPager(JokeType jokeType, int pageIndex, int pageSize);
	/**
	 * 获取所有的笑话分类
	 * @return
	 */
	public List<JokeType> queryJokeTypeAll();
	/**
	 * 获取总记录数
	 * @param jokeType
	 * @return
	 */
	public int getTotalCountByPager(JokeType jokeType);
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public JokeType queryById(int accountId);
	
}
