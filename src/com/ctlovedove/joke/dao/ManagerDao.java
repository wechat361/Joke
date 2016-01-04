package com.ctlovedove.joke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctlovedove.joke.bean.Manager;

/**
 * 管理员DAO
 * @author chenting
 *
 */
public interface ManagerDao {
	/**
	 * 新增
	 * @param manager
	 */
	public void save(Manager manager);
	/**
	 * 修改
	 * @param manager
	 */
	public void update(Manager manager);
	/**
	 * 删除
	 * @param accountId
	 */
	public void deleteById(int accountId);
	/**
	 * 查询
	 * @param accountName
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
	public Manager queryById(@Param("accountId") int accountId);
}
