package com.ctlovedove.joke.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ctlovedove.joke.bean.Manager;
import com.ctlovedove.joke.dao.ManagerDao;
/**
 * 管理员DAO实现类
 * @author chenting
 *
 */
@Repository("managerDao")
public class ManagerDaoImpl extends BaseDaoImpl implements ManagerDao{

	@Override
	public void save(Manager manager) {
		sqlSessionTemplate.insert("saveManager", manager);
	}

	@Override
	public void update(Manager manager) {
		sqlSessionTemplate.update("updateManager", manager);
	}

	@Override
	public void deleteById(int accountId) {
		sqlSessionTemplate.delete("deleteManagerById", accountId);
	}

	@Override
	public Manager queryByName(String accountName) {
		return sqlSessionTemplate.selectOne("queryByName", accountName);
	}
	/**
	 * 根据用户名和密码查找
	 * @param username
	 * @param password
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Manager queryManagerByNameAndPwd(String username, String password){
		Map param = new HashMap();
		param.put("accountName", username);
		param.put("password", password);
		return sqlSessionTemplate.selectOne("queryManagerByNameAndPwd", param);
	}

	/**
	 * 分页查找
	 * @param manager
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Manager> queryManagerListByPager(Manager manager, int pageIndex, int pageSize){
		Map param = new HashMap();
		param.put("manager", manager);
		param.put("pageIndex", (pageIndex - 1) * pageSize);
		param.put("pageSize", pageSize);
		return sqlSessionTemplate.selectList("queryManagerListByPager", param);
	}
	
	/**
	 * 获取总记录数
	 * @param manager
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getTotalCountByPager(Manager manager){
		Map param = new HashMap();
		
		param.put("manager", manager);
		return sqlSessionTemplate.selectOne("com.ctlovedove.joke.dao.impl.ManagerDaoImpl.getTotalCountByPager", param);
	}
	
	/**
	 * 根据ID获取信息
	 * @param accountId
	 * @return
	 */
	public Manager queryById(int accountId){
		return sqlSessionTemplate.selectOne("queryManagerById", accountId);
	}
}
