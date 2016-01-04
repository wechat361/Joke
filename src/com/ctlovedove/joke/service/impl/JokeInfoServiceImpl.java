package com.ctlovedove.joke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctlovedove.joke.bean.JokeInfo;
import com.ctlovedove.joke.dao.JokeInfoDao;
import com.ctlovedove.joke.service.JokeInfoService;
/**
 * 笑话内容service实现类
 * @author chenting
 *
 */
@Service("jokeInfoService")
public class JokeInfoServiceImpl implements JokeInfoService {
	
	@Resource
	private JokeInfoDao jokeInfoDao;

	public JokeInfoDao getJokeInfoDao() {
		return jokeInfoDao;
	}

	public void setJokeInfoDao(JokeInfoDao jokeInfoDao) {
		this.jokeInfoDao = jokeInfoDao;
	}

	@Override
	public void save(JokeInfo jokeInfo) throws Exception {
		try {
			jokeInfoDao.save(jokeInfo);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception 
	 */
	public void deleteByIds(String[] ids) throws Exception{
		try {
			jokeInfoDao.deleteByIds(ids);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 修改
	 * @param jokeInfo
	 * @throws Exception 
	 */
	public void update(JokeInfo jokeInfo) throws Exception{
		try {
			jokeInfoDao.update(jokeInfo);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<JokeInfo> queryJokeInfoListByPager(JokeInfo jokeInfo,
			int pageIndex, int pageSize) {
		return jokeInfoDao.queryJokeInfoListByPager(jokeInfo, pageIndex, pageSize);
	}

	@Override
	public int getTotalCountByPager(JokeInfo jokeInfo) {
		return jokeInfoDao.getTotalCountByPager(jokeInfo);
	}

	@Override
	public JokeInfo queryById(int accountId) {
		return jokeInfoDao.queryById(accountId);
	}

	/**
	 * 批量插入数据库
	 * @param jokeInfoList
	 */
	public void addBatch(List<JokeInfo> jokeInfoList){
		jokeInfoDao.addBatch(jokeInfoList);
	}
	
	/**
	 * 批量审核
	 * @param ids
	 */
	public void updateJokeInfoState(String[] ids) throws Exception{
		try {
			jokeInfoDao.updateJokeInfoState(ids);
		} catch (Exception e) {
			throw e;
		}
	}
}
