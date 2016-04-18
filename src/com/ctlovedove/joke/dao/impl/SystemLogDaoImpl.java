package com.ctlovedove.joke.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ctlovedove.joke.bean.SystemLog;
import com.ctlovedove.joke.dao.SystemLogDao;
/**
 * 系统日志管理DAO
 * @author chenting
 *
 */
@Repository("systemLogDao")
public class SystemLogDaoImpl extends BaseDaoImpl implements SystemLogDao {

	@Override
	public void save(SystemLog systemLog) {
		sqlSessionTemplate.insert("com.ctlovedove.joke.dao.impl.SystemLogDaoImpl.saveSystemLog", systemLog);
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
