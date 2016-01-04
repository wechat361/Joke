package com.ctlovedove.joke.service;

import java.util.List;

import com.ctlovedove.joke.bean.Manager;
/**
 * 管理员service接口
 * @author chenting
 *
 */
public interface ManagerService {
	/**
	 * 新增
	 * @param manager
	 */
	public void save(Manager manager);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 修改
	 * @param manager
	 */
	public void update(Manager manager);
	/**
	 * 查询
	 * @param accountName
	 * @return
	 */
	public Manager queryByName(String accountName);
	/**
	 * 根据用户名和密码查找
	 * @param username
	 * @param password
	 * @return
	 */
	public Manager queryManagerByNameAndPwd(String username, String password);
	
	/**
	 * 分页查找
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Manager> queryManagerListByPager(Manager manager, int pageIndex, int pageSize);
	/**
	 * 获取总记录数
	 * @param manager
	 * @return
	 */
	public int getTotalCountByPager(Manager manager);
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public Manager queryById(int accountId);
}
