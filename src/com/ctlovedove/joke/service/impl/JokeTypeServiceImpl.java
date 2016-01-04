package com.ctlovedove.joke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctlovedove.joke.bean.JokeType;
import com.ctlovedove.joke.dao.JokeTypeDao;
import com.ctlovedove.joke.service.JokeTypeService;
/**
 * 笑话分类service实现类
 * @author chenting
 *
 */
@Service("jokeTypeService")
public class JokeTypeServiceImpl implements JokeTypeService {

	@Resource
	private JokeTypeDao jokeTypeDao;
	
	public JokeTypeDao getJokeTypeDao() {
		return jokeTypeDao;
	}

	public void setJokeTypeDao(JokeTypeDao jokeTypeDao) {
		this.jokeTypeDao = jokeTypeDao;
	}

	@Override
	public void save(JokeType jokeType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(JokeType jokeType) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<JokeType> queryJokeTypeListByPager(JokeType jokeType,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCountByPager(JokeType jokeType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JokeType queryById(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JokeType> queryJokeTypeAll() {
		return jokeTypeDao.getJokeTypeAll();
	}

}
