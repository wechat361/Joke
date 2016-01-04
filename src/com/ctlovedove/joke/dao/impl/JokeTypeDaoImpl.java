package com.ctlovedove.joke.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ctlovedove.joke.bean.JokeType;
import com.ctlovedove.joke.dao.JokeTypeDao;
/**
 * DAO实现类
 * @author chenting
 *
 */
@Repository("joketypeDao")
public class JokeTypeDaoImpl extends BaseDaoImpl implements JokeTypeDao{

	@Override
	public void save(JokeType joketype) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 修改
	 * @param manager
	 * @throws Exception 
	 */
	public void update(JokeType joketype) throws Exception {
		try {
			sqlSessionTemplate.update("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.updateJokeType", joketype);
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
			sqlSessionTemplate.delete("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.deleteJokeTypesByIds", ids);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JokeType> queryJokeTypeListByPager(JokeType joketype,
			int pageIndex, int pageSize) {
		Map map = new HashMap();
		map.put("joketype", joketype);
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("pageSize", pageSize);
		return sqlSessionTemplate.selectList("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.queryJokeTypeListByPager", map);
	}

	@Override
	public int getTotalCountByPager(JokeType joketype) {
		//名称空间.id
		return sqlSessionTemplate.selectOne("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.getTotalCountByPager", joketype);
	}

	@Override
	public JokeType queryById(int id) {
		return sqlSessionTemplate.selectOne("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.getJokeTypeById", id);
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	public List<JokeType> getJokeTypeAll(){
		return sqlSessionTemplate.selectList("com.ctlovedove.joketype.dao.impl.JokeTypeDaoImpl.getJokeTypeAll");
	}
	
}
