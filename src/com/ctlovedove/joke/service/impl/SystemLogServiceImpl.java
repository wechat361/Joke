package com.ctlovedove.joke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctlovedove.joke.bean.SystemLog;
import com.ctlovedove.joke.dao.SystemLogDao;
import com.ctlovedove.joke.service.SystemLogService;
@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {

	@Resource
	private SystemLogDao systemLogDao;
	
	@Override
	public void save(SystemLog systemLog) throws Exception {
		try {
			systemLogDao.save(systemLog);
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public void update(SystemLog systemLog) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SystemLog> queryListByPager(SystemLog systemLog, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCountByPager(SystemLog systemLog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SystemLog queryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
