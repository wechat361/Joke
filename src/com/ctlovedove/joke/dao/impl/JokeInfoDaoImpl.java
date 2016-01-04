package com.ctlovedove.joke.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ctlovedove.joke.bean.JokeInfo;
import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.dao.JokeInfoDao;
/**
 * 管理员DAO实现类
 * @author chenting
 *
 */
@Repository("jokeInfoDao")
public class JokeInfoDaoImpl extends BaseDaoImpl implements JokeInfoDao{

	@Override
	public void save(JokeInfo jokeInfo) throws Exception {
		try {
			sqlSessionTemplate.insert("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.saveJokeInfo", jokeInfo);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 修改
	 * @param manager
	 * @throws Exception 
	 */
	public void update(JokeInfo jokeInfo) throws Exception {
		try {
			sqlSessionTemplate.update("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.updateJokeInfo", jokeInfo);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByIds(String[] ids) throws Exception{
		try {
			Map map = new HashMap();
			map.put("ids", ids);
			sqlSessionTemplate.delete("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.deleteJokeInfosByIds", ids);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Manager queryByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JokeInfo> queryJokeInfoListByPager(JokeInfo jokeInfo,
			int pageIndex, int pageSize) {
		Map map = new HashMap();
		map.put("jokeInfo", jokeInfo);
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("pageSize", pageSize);
		return sqlSessionTemplate.selectList("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.queryJokeInfoListByPager", map);
	}

	@Override
	public int getTotalCountByPager(JokeInfo jokeInfo) {
		//名称空间.id
		return sqlSessionTemplate.selectOne("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.getTotalCountByPager", jokeInfo);
	}

	@Override
	public JokeInfo queryById(int id) {
		return sqlSessionTemplate.selectOne("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.getJokeInfoById", id);
	}
	/**
	 * 批量插入
	 * @param jokeInfoList
	 */
	public void addBatch(List<JokeInfo> jokeInfoList){
		sqlSessionTemplate.insert("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.batchAddJokeInfo", jokeInfoList);
	}
	
	/**
	 * 批量审核
	 * @param ids
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateJokeInfoState(String[] ids) throws Exception{
		try {
			Map map = new HashMap();
			map.put("ids", ids);
			sqlSessionTemplate.update("com.ctlovedove.jokeInfo.dao.impl.JokeInfoDaoImpl.updateJokeInfoState", map);
		} catch (Exception e) {
			throw e;
		}
	}

}
