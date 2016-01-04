package com.ctlovedove.joke.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.dao.ManagerDao;
import com.ctlovedove.joke.service.ManagerService;
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private ManagerDao managerDao;
	
	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public void save(Manager manager) {
		managerDao.save(manager);
	}

	@Override
	public void deleteById(int id) {
		managerDao.deleteById(id);
	}

	@Override
	public void update(Manager manager) {
		managerDao.update(manager);
	}

	@Override
	public Manager queryByName(String accountName) {
		return managerDao.queryByName(accountName);
	}
	
	/**
	 * 根据用户名和密码查找
	 * @param username
	 * @param password
	 * @return
	 */
	public Manager queryManagerByNameAndPwd(String username, String password){
		return managerDao.queryManagerByNameAndPwd(username, password);
	}
	
	/**
	 * 分页查找
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Manager> queryManagerListByPager(Manager manager, int pageIndex, int pageSize){
		return managerDao.queryManagerListByPager(manager, pageIndex, pageSize);
	}
	
	/**
	 * 获取总记录数
	 * @param manager
	 * @return
	 */
	public int getTotalCountByPager(Manager manager){
		return managerDao.getTotalCountByPager(manager);
	}
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public Manager queryById(int accountId){
		return managerDao.queryById(accountId);
	}

}
