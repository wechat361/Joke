package com.ctlovedove.joke.service;

import java.util.List;

import com.ctlovedove.joke.bean.JokeInfo;
/**
 * 笑话内容service接口
 * @author chenting
 *
 */
public interface JokeInfoService {
	/**
	 * 新增
	 * @param jokeInfo
	 * @throws Exception 
	 */
	public void save(JokeInfo jokeInfo) throws Exception;
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
	 * @param jokeInfo
	 * @throws Exception 
	 */
	public void update(JokeInfo jokeInfo) throws Exception;
	/**
	 * 分页查找
	 * @param jokeInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<JokeInfo> queryJokeInfoListByPager(JokeInfo jokeInfo, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param jokeInfo
	 * @return
	 */
	public int getTotalCountByPager(JokeInfo jokeInfo);
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public JokeInfo queryById(int accountId);
	/**
	 * 批量插入数据库
	 * @param jokeInfoList
	 */
	public void addBatch(List<JokeInfo> jokeInfoList);

	/**
	 * 批量审核
	 * @param ids
	 * @throws Exception 
	 */
	public void updateJokeInfoState(String[] ids) throws Exception;
}
