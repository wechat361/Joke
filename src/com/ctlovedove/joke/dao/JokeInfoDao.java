package com.ctlovedove.joke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctlovedove.joke.bean.JokeInfo;
import com.ctlovedove.joke.bean.Manager;

/**
 * 冷笑话信息DAO
 * @author chenting
 *
 */
public interface JokeInfoDao {
	/**
	 * 新增
	 * @param manager
	 * @throws Exception 
	 */
	public void save(JokeInfo jokeInfo) throws Exception;
	/**
	 * 修改
	 * @param manager
	 * @throws Exception 
	 */
	public void update(JokeInfo jokeInfo) throws Exception;
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
	 * 查询
	 * @param keyword
	 */
	public Manager queryByKeyword(String keyword);
	
	/**
	 * 分页查找
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<JokeInfo> queryJokeInfoListByPager(JokeInfo jokeInfo, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param manager
	 * @return
	 */
	public int getTotalCountByPager(JokeInfo jokeInfo);
	
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public JokeInfo queryById(@Param("id") int id);
	/**
	 * 批量插入
	 * @param jokeInfoList
	 */
	public void addBatch(@Param("jokeInfoList") List<JokeInfo> jokeInfoList);
	/**
	 * 批量审核
	 * @param ids
	 */
	public void updateJokeInfoState(String[] ids) throws Exception;
	
}
